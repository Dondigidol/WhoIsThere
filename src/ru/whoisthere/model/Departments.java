package ru.whoisthere.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Departments {
	List<List<String>> departs = new ArrayList<List<String>>();
	
	public Departments() {
		departs.add(Arrays.asList((new String[]{"��������", "��������"})));
		departs.add(Arrays.asList((new String[]{"������������ ���������", "1 �����"})));
		departs.add(Arrays.asList((new String[]{"��������� �������", "2 �����"})));
		departs.add(Arrays.asList((new String[]{"�������������", "3 �����"})));
		departs.add(Arrays.asList((new String[]{"�����������", "4 �����"})));
		departs.add(Arrays.asList((new String[]{"��������� ��������", "5 �����"})));
		departs.add(Arrays.asList((new String[]{"��������� � ��������� ������", "6 �����"})));
		departs.add(Arrays.asList((new String[]{"����������", "7 �����"})));
		departs.add(Arrays.asList((new String[]{"������������������� ������� - ���������", "8 �����"})));
		departs.add(Arrays.asList((new String[]{"���", "9 �����"})));
		departs.add(Arrays.asList((new String[]{"�������� �������", "10 �����"})));
		departs.add(Arrays.asList((new String[]{"������������� �������", "11 �����"})));
		departs.add(Arrays.asList((new String[]{"���������� ���������", "12 �����"})));
		departs.add(Arrays.asList((new String[]{"���������", "13 �����"})));
		departs.add(Arrays.asList((new String[]{"������������ ����", "14 �����"})));
		departs.add(Arrays.asList((new String[]{"�����", "15 �����"})));
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
