package Biblioteca;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BusquedaController {

    @FXML
    private JFXButton buttont_atras;

    @FXML
    private ImageView button_atras;

    @FXML
    private JFXTextField textfield_titulo;

    @FXML
    private JFXTextField textfield_autor;

    @FXML
    private JFXTextField textfield_editorial;

    @FXML
    private JFXTextField textfield_genero;

    @FXML
    private JFXTextField textfield_paginas;

    @FXML
    private JFXTextField textfield_tomos;

    @FXML
    private JFXTextField textfield_unidades;

    @FXML
    private JFXTextField textfield_precio;

    @FXML
    private JFXDatePicker fecha;

    @FXML
    private JFXDatePicker fechacad;

    @FXML
    private JFXButton button_buscar;

    @FXML
    private JFXButton button_avanzado;

    @FXML
    private JFXComboBox<?> tipobox;

    @FXML
    void Atras(MouseEvent event) {

    }

    @FXML
    void ButtonAvanzado(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("BusquedaAvanzada.fxml"));
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

    @FXML
    void buscar(ActionEvent event) {

    }

}
