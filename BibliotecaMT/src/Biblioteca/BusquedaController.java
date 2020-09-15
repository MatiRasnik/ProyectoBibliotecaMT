package Biblioteca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    private JFXComboBox<String> tipobox;

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
    ArrayList<Number> buscar(ActionEvent event) throws IOException {
	
    	ArrayList<Number> coincidencias = null;
    	
		String titulo = textfield_titulo.getText();
		String autor = textfield_autor.getText();
		LocalDate fecha = this.fecha.getValue();
		LocalDate fechacad = this.fechacad.getValue();
		String editorial = textfield_editorial.getText();
		String genero = textfield_genero.getText();
		int paginas = Integer.parseInt(textfield_paginas.getText());
		int tomos = Integer.parseInt(textfield_tomos.getText());
		int unidades = Integer.parseInt(textfield_unidades.getText());
		int precio = Integer.parseInt(textfield_precio.getText());
		
		try {
			
			ConexionBD conexion = new ConexionBD();
	        Connection con = conexion.conectarConBase(); 
	        String buscarAdmin = "select * from Almacen";
	        Statement cs =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = cs.executeQuery(buscarAdmin);
			
			while (resultado.next()){
				
				String Titulo = resultado.getString("titulo");
				String Autor = resultado.getString("autor");
				String Editorial = resultado.getString("editorial");
				String Genero = resultado.getString("genero");
				String Fecha = resultado.getString("fecha");
				String Fechacad = resultado.getString("fechacad");
				int Tipo = resultado.getInt("tipo");
				int Paginas = resultado.getInt("paginas");
				int Tomos = resultado.getInt("tomos");
				int Unidades = resultado.getInt("unidades");
				int Precio = resultado.getInt("precios");
				int ID = resultado.getInt("id");
				
				if(titulo == Titulo && titulo != "") {
					coincidencias.add(ID);
				}
				if(autor == Autor && autor != "") {
					coincidencias.add(ID);
				}
				if(Fecha.equals(this.fecha.getValue()) && this.fecha.getValue() != null) {
					coincidencias.add(ID);
				}
				if(Fechacad.equals(this.fechacad.getValue()) && this.fechacad.getValue() != null) {
					coincidencias.add(ID);
				}
				if(editorial == Editorial && editorial != "") {
					coincidencias.add(ID);
				}
				if(genero == Genero && genero != "") {
					coincidencias.add(ID);
				}
				if(paginas == Paginas) {
					coincidencias.add(ID);
				}
				if(tomos == Tomos) {
					coincidencias.add(ID);
				}
				if(unidades == Unidades) {
					coincidencias.add(ID);
				}
				if(precio == Precio) {
					coincidencias.add(ID);
				}
				
		
			}	
		
		Parent main = FXMLLoader.load(getClass().getResource("TablaBusqueda.fxml"));
        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

	    } catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");
		}
		
		ArrayList<Number> ordenado = null;
		int a=0;
		for(int i=0;i<coincidencias.size();i++)
		{
			for(int j=0;j<coincidencias.size();j++) {
    			if(coincidencias.get(i) == ordenado.get(j)) {
    				a = 1;
    			}	
			}
			if(a==0) {
				ordenado.add(coincidencias.get(i));
			}
			a=0;
		}
		return ordenado;
    }
    

}