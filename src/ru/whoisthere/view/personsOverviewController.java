package ru.whoisthere.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.whoisthere.SqlUtils;
import ru.whoisthere.model.Person;


public class personsOverviewController {
	private List<Person> persons = new ArrayList<Person>();
	
	@FXML
	public Button btn;
	public GridPane gp = new GridPane();
	VBox col0 = new VBox(new Label("sdasdsd"));
	VBox col1 = new VBox();
	VBox col2 = new VBox();
	VBox col3 = new VBox();
	VBox col4 = new VBox();
	VBox col5 = new VBox();
	VBox col6 = new VBox();
	VBox col7 = new VBox();
	VBox col8 = new VBox();
	VBox col9 = new VBox();
	VBox col10 = new VBox();
	VBox col11 = new VBox();
	VBox col12 = new VBox();
	VBox col13 = new VBox();
	VBox col14 = new VBox();
	VBox col15 = new VBox();	
	
	@FXML
	public void uploadData() {
		List<List<String>> otdels = new ArrayList<List<String>>();		
		otdels.add(Arrays.asList((new String[]{"��������", "��������"})));
		otdels.add(Arrays.asList((new String[]{"������������ ���������", "1 �����"})));
		otdels.add(Arrays.asList((new String[]{"��������� �������", "2 �����"})));
		otdels.add(Arrays.asList((new String[]{"�������������", "3 �����"})));
		otdels.add(Arrays.asList((new String[]{"�����������", "4 �����"})));
		otdels.add(Arrays.asList((new String[]{"��������� ��������", "5 �����"})));
		otdels.add(Arrays.asList((new String[]{"��������� � ��������� ������", "6 �����"})));
		otdels.add(Arrays.asList((new String[]{"����������", "7 �����"})));
		otdels.add(Arrays.asList((new String[]{"������������������� ������� - ���������", "8 �����"})));
		otdels.add(Arrays.asList((new String[]{"���", "9 �����"})));
		otdels.add(Arrays.asList((new String[]{"�������� �������", "10 �����"})));
		otdels.add(Arrays.asList((new String[]{"������������� �������", "11 �����"})));
		otdels.add(Arrays.asList((new String[]{"���������� ���������", "12 �����"})));
		otdels.add(Arrays.asList((new String[]{"���������", "13 �����"})));
		otdels.add(Arrays.asList((new String[]{"������������ ����", "14 �����"})));
		otdels.add(Arrays.asList((new String[]{"�����", "15 �����"})));
				
		SqlUtils sqlutil = new SqlUtils();
		if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
			persons = sqlutil.execQuery();
		}		
		
		int colCount = otdels.size();
		for (Person person : persons) {			
			for (int i = 0; i<colCount; i++) {
				if (person.getDepartment() == otdels.get(i).get(0)) {
					String nodeName = "col" + i;
					Node node = Parent.
				}
			}	
		}		
	}	
}
