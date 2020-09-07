package Biblioteca;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;

public class MenuController {
	@FXML
	private JFXButton button_logn;
	@FXML
	private JFXButton button_busqueda;


	@FXML
	public void ButtonLogIn(ActionEvent event) throws IOException {
		buscarAdmin();
		Parent login = FXMLLoader.load(getClass().getResource("LogIn.fxml"));


        Scene scene = new Scene(login);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
        
	}

	private void buscarAdmin() {
			 try {
		            ConexionBD conexion = new ConexionBD();
		            Connection con = conexion.conectarConBase(); 
		            Statement ia =con.createStatement();
		            
		            String buscarAdmin = "select * from usuarios";
		            Statement ba = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ResultSet resultado = ba.executeQuery(buscarAdmin);
					
					//0-->ADMIN
					//1-->Bibliotecologo
					
					if(resultado.first() == false ) {
						String insertAdmin = "INSERT INTO usuarios" + "(id_usuario,usuario,contra,tipo_usuario) values" + "('1','admin', 'admin', '0')";
						int cant = ia.executeUpdate(insertAdmin);
						System.out.println("Se a creado el Admin. ");
						System.out.println("weqweq");
						
						
					}else {
						System.out.println("Ya existe un Admin. ");
						 }
						
					} catch (SQLException e) {
					System.out.println( "No se pudo conectar con la Base de Datos");
				}
			 }
	

	@FXML
	public void ButtonBusqueda(ActionEvent event) throws IOException {
		Parent busqueda = FXMLLoader.load(getClass().getResource("Busqueda.fxml"));

        Scene scene = new Scene(busqueda);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
	}}
