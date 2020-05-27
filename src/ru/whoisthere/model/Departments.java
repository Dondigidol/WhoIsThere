package ru.whoisthere.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Departments {
    List<List<String>> departs = new ArrayList<List<String>>();

    public Departments() {
        departs.add(Arrays.asList((new String[]{"äèðåêöèÿ", "äèðåêöèÿ"})));
        departs.add(Arrays.asList((new String[]{"ñòðîèòåëüíûå ìàòåðèàëû", "1 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"ñòîëÿðíûå èçäåëèÿ", "2 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"ýëåêòðîòîâàðû", "3 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"èíñòðóìåíòû", "4 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"íàïîëüíûå ïîêðûòèÿ", "5 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"íàñòåííàÿ è íàïîëüíàÿ ïëèòêà", "6 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"ñàíòåõíèêà", "7 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"âîäîêàíàëèçàöèîííûå ñèñòåìû - îòîïëåíèå", "8 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"ñàä", "9 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"ñêîáÿíûå èçäåëèÿ", "10 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"ëàêîêðàñî÷íûå èçäåëèÿ", "11 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"îòäåëî÷íûå ìàòåðèàëû", "12 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"îñâåùåíèå", "13 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"îáóñòðîéñòâî äîìà", "14 îòäåë"})));
        departs.add(Arrays.asList((new String[]{"êóõíè", "15 îòäåë"})));
    }

    public int getOtdelsCount() {
        return departs.size();
    }

    public String getDepartmentTitle(int otdelIndex) {
        if (otdelIndex < departs.size()) {
            return departs.get(otdelIndex).get(1);
        } else {
            return null;
        }
    }

    public String getDepartmentName(int otdelIndex) {
        if (otdelIndex < departs.size()) {
            return departs.get(otdelIndex).get(0);
        } else {
            return null;
        }
    }

    public List<List<String>> getDepartments() {
        return departs;
    }
}