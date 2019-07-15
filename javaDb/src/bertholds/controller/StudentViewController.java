package bertholds.controller;

import java.sql.SQLException;

import bertholds.main.MainApp;
import bertholds.models.Student;
import bertholds.models.StudentDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StudentViewController {
	Label consoleligne1 = new Label("Berthold code console -version(2.0)");
	Label consoleligne2 = new Label("Ndj Brillant technologie 2019 COLD");

	@FXML
	private TableView<Student> StudentTab;
	@FXML
	private TableColumn<Student, Integer> IdColum;
	@FXML
	private TableColumn<Student, String> NameColum;
	@FXML
	private TableColumn<Student, String> SurnameColum;
	@FXML
	private TableColumn<Student, String> EmailColum;
	@FXML
	private TextArea TextA;

	@FXML
	private TextField NameField;
	@FXML
	private TextField SurnameField;
	@FXML
	private TextField EmailField;
	@FXML
	private TextField IdField;
	@FXML
	private TextField NameSearchField;
	@FXML
	private TextField EmailSearchField;
	private int result;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void clear() {
		this.NameField.setText("");
		this.SurnameField.setText("");
		this.EmailField.setText("");
	}

	@FXML
	private void insertStudent(ActionEvent e) throws ClassNotFoundException, SQLException {
		Label dataInsert = new Label("Add student successfull");
		Label dataNotInsert = new Label("Add student Wrong !!");
		try {

			StudentDao.insertStudent(NameField.getText().trim().toLowerCase(),
					SurnameField.getText().trim().toLowerCase(), EmailField.getText().trim().toLowerCase());
			TextA.setText(consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + dataInsert.getText());
			clear();
			ObservableList<Student> studentList = StudentDao.getAllRecord();
			populate(studentList);
		} catch (Exception e2) {
			TextA.setText(consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + dataNotInsert.getText());
		}
	}

	@FXML
	private void deleteStudent(ActionEvent e) throws ClassNotFoundException, SQLException {
		Label dataDelete = new Label("Delete student successfull");
		Label dataNotDelete = new Label("Delete student Wrong !!");
		try {
			result = StudentDao.deleteStudent(Integer.parseInt(IdField.getText()), NameSearchField.getText().trim(),
					EmailSearchField.getText().trim());
			if (result > 0) {
				TextA.setText(consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + dataDelete.getText());
				ObservableList<Student> studentList = StudentDao.getAllRecord();
				populate(studentList);
				clearSearch();
			} else {
				TextA.setText(
						consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + "Etudiant non existant");
				clearSearch();
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
			TextA.setText(consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + dataNotDelete.getText());
			Alert alert = new Alert(AlertType.WARNING, "Renseignez l'id de l'etudiant a supprim", ButtonType.OK);
			alert.setHeaderText("No id student selected");
			alert.initOwner(mainApp.getPrimaryStage());
			alert.show();
			throw e2;
		}
	}

	@FXML
	private void updateStudent() {
		try {
			if (!IdField.getText().trim().isEmpty() && !NameSearchField.getText().trim().isEmpty()
					&& !EmailSearchField.getText().trim().isEmpty()) {
				StudentDao.updateStudent(Integer.parseInt(IdField.getText().trim().toLowerCase()),
						NameSearchField.getText().trim().toLowerCase(),
						EmailSearchField.getText().trim().toLowerCase());
				ObservableList<Student> studentList = StudentDao.getAllRecord();
				populate(studentList);
				clearSearch();
				TextA.setText(
						consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + "Updating succssessfulll");
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Renseignez l'id, le nom et l'email de l'etudiant a editer", ButtonType.OK);
				alert.setHeaderText("Field required !");
				alert.initOwner(mainApp.getPrimaryStage());
				alert.show();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void clearSearch() {
		IdField.setText("");
		NameSearchField.setText("");
		EmailSearchField.setText("");
	}

	@FXML
	public void searchStudent(ActionEvent event) {
		try {
			ObservableList<Student> studentList = StudentDao.searchStudent(
					Integer.parseInt(IdField.getText().trim().toLowerCase()),
					NameSearchField.getText().trim().toLowerCase());
			if (studentList.size() > 0) {
				populate(studentList);
				TextA.setText(
						consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + "record has been found");
				clearSearch();
			} else {
				TextA.setText(consoleligne1.getText() + "\n" + consoleligne2.getText() + "\n" + "record has not found");
				clearSearch();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING, "Renseignez l'id de l'etudiant a rechercher", ButtonType.OK);
			alert.setHeaderText("No id student selected");
			alert.initOwner(mainApp.getPrimaryStage());
			alert.show();
			e.printStackTrace();
		}
	}

	public void initialize() throws Exception {
		IdColum.setCellValueFactory(cellData -> cellData.getValue().getIde().asObject());
		NameColum.setCellValueFactory(cellData -> cellData.getValue().getNamee());
		SurnameColum.setCellValueFactory(cellData -> cellData.getValue().getSurNamee());
		EmailColum.setCellValueFactory(cellData -> cellData.getValue().getEmaile());
		ObservableList<Student> studentList = StudentDao.getAllRecord();
		populate(studentList);
	}

	private void populate(ObservableList<Student> studentList) {
		StudentTab.setItems(studentList);

	}

}
