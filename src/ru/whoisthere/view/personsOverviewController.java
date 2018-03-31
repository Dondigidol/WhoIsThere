package ru.whoisthere.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.whoisthere.SqlUtils;
import ru.whoisthere.model.Person;


public class personsOverviewController {
	
	private List<Person> persons = new ArrayList<Person>();
	List<List<String>> otdels = new ArrayList<List<String>>();
	private int colCount = 0;
	private int maxPersons;
	
	@FXML 
	public GridPane gp = new GridPane();
	

	public void downloadData() {
		int personsInOtdel;
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
		
		colCount = otdels.size();
		maxPersons = 0;
		for (int i = 0; i<colCount; i++) {
			personsInOtdel = 0;
			for (Person person : persons) {	
				if (person.getDepartment().equals(otdels.get(i).get(0))) {
					personsInOtdel += 1;
				}
			}
			if (personsInOtdel > maxPersons) {
				maxPersons = personsInOtdel;
			}			
		}		
		// refreshScreen();
	}
	
	public void refreshScreen() {
		System.out.println("---------------------------------------------------------------------------");
		try {
			clearData();
			for (int i = 0; i<colCount; i++) {
				for (Person person : persons) {			
					if (person.getDepartment().equals(otdels.get(i).get(0))) {
						String nodeName = "col" + i;
						VBox mynode = (VBox) gp.lookup("#" + nodeName);
						System.out.println(gp.getRowConstraints().get(1).getMinHeight());
						mynode.setMinHeight(gp.getRowConstraints().get(1).getMinHeight());
						//------------ ������� � �����������--------------------------------
						Image photo = SwingFXUtils.toFXImage(person.getPhoto(), null);
						ImageView personPhoto = new ImageView(photo);	
						Double imgW = photo.getWidth();
						Double imgH = photo.getHeight();
						Double ratio = imgH/imgW;
						Double containerHeight = mynode.getHeight();
						Double containerWidth = mynode.getWidth()-10;
						Double calcHeight = containerWidth * ratio;
						Double defHeight = (containerWidth) * 1.3;
						Double calcWidth = defHeight / ratio;
						
						if (calcHeight + 40 > containerHeight/maxPersons) {
							personPhoto.setFitHeight(containerHeight/maxPersons - 40);						
						} else {
							if (calcWidth > containerWidth)
								personPhoto.setFitWidth(containerWidth);
							else personPhoto.setFitHeight(defHeight);
						}
						// System.out.println(containerHeight);
						personPhoto.setPreserveRatio(true);					
						//==================================================================
						
						//------------ ������� � ������ � ��������--------------------------
						VBox personName = new VBox();
						personName.setAlignment(Pos.TOP_CENTER);
						personName.getChildren().addAll(new Label(person.getName()), new Label(person.getSurname()));

						//==================================================================
						
						//------------- ��������� � ��������� � ������� ����������� � ����������---
						VBox personInfoContainer = new VBox();
						personInfoContainer.setAlignment(Pos.TOP_CENTER);
						personInfoContainer.getChildren().addAll(personPhoto, personName);					
						//=========================================================================
						
						mynode.getChildren().add(personInfoContainer);				
					}
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clearData() {
		for (Node n: gp.getChildren()) {
			if (n.getStyleClass().toString().equals("columns")) {
				VBox node = (VBox) n;
				node.getChildren().clear();
			}
		}
	}
	
	//@FXML
	public void initialize() {
		ContextMenu cm = new ContextMenu();
		MenuItem menuItem0 = new MenuItem("�������� ������ � �������");
		MenuItem menuItem1 = new MenuItem("�������� �����");
		MenuItem menuItem2 = new MenuItem("����� �� ����������");
		//-----------------��������� ������� ������� ������� ����---------------------
		menuItem0.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				downloadData();
			}
		});		
		menuItem1.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				refreshScreen();
			}
		});		
		menuItem2.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);			
			}
		});
		//===========================================================================
		cm.getItems().addAll(menuItem0, menuItem1, new SeparatorMenuItem(), menuItem2);
		
		gp.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent event) {
				cm.show(gp, event.getScreenX(), event.getScreenY());
			}
		});
		
		gp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				cm.hide();
			}
		});
	}	
}
