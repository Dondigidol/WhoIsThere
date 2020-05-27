package ru.whoisthere;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtils {

    Departments departs = new Departments();
    private Connection con;
    Loging logs = new Loging();



    public boolean openConnection(String serverAddress, String login, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + serverAddress;
            this.con = DriverManager.getConnection(url, login, password);
            logs.addInfoLog("Подключение к серверу " + serverAddress + " выполнено успешно.");
            return true;
        } catch (SQLException e) {
            logs.addWarningLog(e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            logs.addWarningLog(e.getMessage());
            return false;
        }
    }

    public boolean closeConnection() {
        try {
            this.con.close();
            logs.addInfoLog("Отключение от сервера.");
            return true;
        } catch (SQLException e) {
            logs.addWarningLog(e.getMessage());
            return false;
        }
    }

    public List<Person> execQuery(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String curDate = dateFormat.format(new Date());

        List<Person> persons = new ArrayList<Person>();
        String queryStr = "SELECT pList.FirstName, pList.Name, pDiv.Name, pList.Picture " +
                "FROM (SELECT MAX(TimeVal) as TimeVal, HozOrgan FROM ORION1122.dbo.pLogData Where TimeVal>='" + curDate + "' and Event='32' and DoorIndex='15' Group by HozOrgan) as pLog " +
                "INNER JOIN (SELECT TimeVal, HozOrgan, Mode From ORION1122.dbo.pLogData Where Event='32' and DoorIndex='15' and TimeVal>='" + curDate + "' and Mode=1) maxMode ON maxMode.HozOrgan = pLog.HozOrgan and maxMode.TimeVal = pLog.TimeVal " +
                "INNER JOIN (SELECT ID, Name, FirstName, Section, Picture From ORION1122.dbo.pList where TabNumber <> '' and Company=2) pList ON pList.ID = pLog.HozOrgan " +
                "INNER JOIN (SELECT ID, Name FROM [ORION1122].[dbo].[PDivision] Where GroupID='1') pDiv ON pDiv.ID = pList.Section " +
                "Order By pList.Name";
        int otdCount = departs.getOtdelsCount();
        List<List<String>> otdels = departs.getDepartments();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryStr);
            while (rs.next()) {
                for (int i = 0; i< otdCount; i++) {
                    if (otdels.get(i).get(0).equals(rs.getString(3))) {
                        Person person = new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBytes(4));
                        persons.add(person);
                    }
                }
            }

            rs.close();
            stmt.close();
            logs.addInfoLog("Данные о сотрудниках получены. " + persons.size() + " записей.");
        } catch(SQLException e) {
            logs.addWarningLog(e.getMessage());
        } finally {
            closeConnection();
        }
        return persons;
    }
}

