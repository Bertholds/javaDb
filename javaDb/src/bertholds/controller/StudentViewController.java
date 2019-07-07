package bertholds.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentViewController {
    @FXML
    private TableView   StudentTab;
    @FXML
    private TableColumn IdColum;
    @FXML
    private TableColumn NameColum;
    @FXML
    private TableColumn SurnameColum;
    @FXML
    private TableColumn EmailColum;

    @FXML
    private TextField   NameField;
    @FXML
    private TextField   SurnameField;
    @FXML
    private TextField   EmailField;
    @FXML
    private TextField   IdField;
    @FXML
    private TextField   NameSearchField;

}
