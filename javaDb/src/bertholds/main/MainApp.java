package bertholds.main;

import java.io.IOException;

import bertholds.controller.StudentViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private  Stage      primaryStage;
    private BorderPane borderpane;

    @Override
    public void start( Stage primaryStage ) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle( "Student CRUD" );
        this.primaryStage.centerOnScreen();

        initRootlayout();
        showStudentOverView();

    }

    private void initRootlayout() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( getClass().getResource( "../controller/rootView.fxml" ) );
            borderpane = loader.load();

            Scene scene = new Scene( borderpane, 780, 500 );
            primaryStage.setScene( scene );

            primaryStage.show();

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private void showStudentOverView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( getClass().getResource( "../controller/StudentView.fxml" ) );
            AnchorPane anchorPane = loader.load();
            borderpane.setCenter( anchorPane );
            
            StudentViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    

    public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main( String[] args ) {
        launch( args );
    }

}
