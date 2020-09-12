package Biblioteca;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BiblotecologoController {

	   @FXML
	    private JFXButton button_busqueda;

	    @FXML
	    private JFXButton buttont_atras;

	    @FXML
	    private ImageView button_atras;

	    @FXML
	    private JFXButton button_ingresar;

	    @FXML
	    private JFXButton button_busqueda_avanzada;

	    @FXML
	    private JFXButton button_ingresartipo;

	    @FXML
	    void Atras(MouseEvent event) {

	    }
	    
	    @FXML
	    void Buscar(ActionEvent event) throws IOException {
	    	Parent main = FXMLLoader.load(getClass().getResource("Busqueda.fxml"));

	        Scene scene = new Scene(main);
	        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        window.setScene(scene);
	        window.show();
	    }

    @FXML
    void Busqueda_Avanzada(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("BusquedaAvanzada.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    void Ingresar(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("Ingresar.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    
    @FXML
    void Ingresar_tipo(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("IngresarTipo.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void atras(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

}
