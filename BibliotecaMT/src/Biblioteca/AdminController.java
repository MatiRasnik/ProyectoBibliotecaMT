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

public class AdminController {

    @FXML
    private JFXButton button_busqueda;

    @FXML
    private JFXButton button_ingresar;

    @FXML
    private JFXButton button_ingresarus;

    @FXML
    private JFXButton buttont_atras;

    @FXML
    private ImageView button_atras;

    @FXML
    private JFXButton button_busqueda_avanzada;

    @FXML
    private JFXButton button_ingresarstipo;

    @FXML
    private JFXButton button_modificarusuario;

    @FXML
    private JFXButton button_eliminartipo;

    @FXML
    private JFXButton button_consultas;

    @FXML
    void Atras(MouseEvent event) {

    }
    @FXML
    void Busqueda(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("BusquedaAdmin.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    void Busqueda_Avanzada(ActionEvent event) throws IOException {
    	
    	Parent main = FXMLLoader.load(getClass().getResource("BusquedaAvanzadaAdmin.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void Consultas(ActionEvent event) throws IOException {
    

    }
    
    @FXML
    void EliminarTipo(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("EliminarTipo.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void Ingresar(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("IngresarLibro.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    
    @FXML
    void IngresarTipo(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("IngresarTipo.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void IngresarUsuario(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("Registro.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    
    @FXML
    void ModificarUsuario(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("ModificarUsuario.fxml"));

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
