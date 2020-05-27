package ru.whoisthere.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.whoisthere.DownloadData;
import ru.whoisthere.Loging;
import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

public class PersonsOverviewController {
    DownloadData downloadData = new DownloadData();
    Departments departs = new Departments();
    Loging logs= new Loging();
    List<Person> persons = new ArrayList<Person>();
    List<ImageView> imagesList = new ArrayList<ImageView>();

    @FXML
    private GridPane gp = new GridPane();
    @FXML
    private Label dataUpdated = new Label();


    public void refreshScreen() {
        try {
            clearData();
            persons = downloadData.getPersons();
            Date refreshingStart = new Date();
            logs.addInfoLog("Интерфейс. Начало обновления интерфейса: " + refreshingStart);
            int maxPersons = downloadData.getMaxPersons();

            Stage stage = (Stage)gp.getScene().getWindow();
            gp.prefHeightProperty().bind(stage.heightProperty().subtract(40));

            for (int i = 0; i<departs.getOtdelsCount(); i++) {
                VBox column = (VBox) gp.lookup("#col" + i);
                column.prefHeightProperty().bind(gp.prefHeightProperty().subtract(40));
                column.prefWidthProperty().bind(gp.getColumnConstraints().get(0).prefWidthProperty());
                column.setAlignment(Pos.TOP_CENTER);
            }

            for (Person person : persons) {
                for (int i = 0; i<departs.getOtdelsCount(); i++) {
                    if (person.getDepartment().equals(departs.getDepartmentName(i))) {
                        VBox mynode = (VBox) gp.lookup("#col" + i);

                        Image photo = (Image)SwingFXUtils.toFXImage(person.getPhoto(), null);
                        ImageView personPhoto = new ImageView(photo);

                        double imgWidth = photo.getWidth();
                        double imgHeight = photo.getHeight();
                        double imgRatio = imgWidth / imgHeight;

                        personPhoto.fitWidthProperty().bind(mynode.prefWidthProperty().multiply(imgRatio));
                        personPhoto.fitHeightProperty().bind(mynode.prefHeightProperty().divide(maxPersons).subtract(40));
                        personPhoto.setPreserveRatio(true);

                        //imagesList.add(personPhoto);
                        mynode.getChildren().addAll(personPhoto, new Label(person.getName()), new Label (person.getSurname()));

                        personPhoto = null;
                        photo = null;
                    }
                }
            }

            Date refreshingEnd = new Date();
            logs.addInfoLog("Интерфейс. Обновлен за: " + (refreshingEnd.getTime() - refreshingStart.getTime())/1000 + " сек.");
            dataUpdated.setText(downloadData.getDataTime());
        } catch (Exception e) {
            logs.addWarningLog(e.getMessage());
        }

    }


    private void clearData() {
        for (Node n: gp.getChildren()) {
            if (n instanceof Pane) {
                for (Node n1: ((Pane) n).getChildren()) {
                    if (n1.getStyleClass().toString().equals("columns")) {
                        VBox node = (VBox) n1;
                        persons = null;
                        imagesList = null;
                        node.getChildren().clear();
                    }
                }
            }
        }
    }

    //@FXML
    public void initialize() {

        try {
            downloadData.start();
        } catch (Exception e) {
            e.getMessage();
        }
        ContextMenu cm = new ContextMenu();
        MenuItem menuItem0 = new MenuItem();
        MenuItem menuItem1 = new MenuItem("Выйти из приложения");
        //-----------------обработка событий нажатия пунктов меню---------------------
        menuItem0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) gp.getScene().getWindow();
                if(stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    menuItem0.setText("Полноэкранный режим");
                } else {
                    stage.setFullScreen(true);
                    menuItem0.setText("Оконный режим");
                }

            }
        });
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        //===========================================================================
        cm.getItems().addAll(menuItem0, new SeparatorMenuItem(), menuItem1);

        gp.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                Stage stage = (Stage) gp.getScene().getWindow();
                if(stage.isFullScreen()) {
                    menuItem0.setText("Оконный режим");

                } else {
                    menuItem0.setText("Полноэкранный режим");
                }
                cm.show(gp, event.getScreenX(), event.getScreenY());
            }
        });

        gp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton()==MouseButton.PRIMARY) {
                    cm.hide();
                }
            }
        });


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30.0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refreshScreen();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }





}