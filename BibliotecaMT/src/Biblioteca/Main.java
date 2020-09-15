package Biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
    	 CrearBase crear = new CrearBase();
         crear.crearDB();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("BibliotecaMT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}