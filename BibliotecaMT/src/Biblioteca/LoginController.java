package Biblioteca;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

		@FXML
	    private JFXTextField textfield_usuario;

	    @FXML
	    private JFXPasswordField textfield_psswd;

	    @FXML
	    private JFXButton button_iniciarsesion;
	    
	    @FXML
	    private Label lbl_error;

	    @FXML
	    private JFXButton buttont_atras;

	    @FXML
	    private ImageView button_atras;

	    @FXML
	    void Atras(MouseEvent event) {
	    
	    }
	    boolean sesionIniciada = false;

       
	    @FXML
	    void Iniciar_Sesion(ActionEvent event) throws IOException {
	    	
	    	String usuario = this.textfield_usuario.getText();
	        String contraseña = this.textfield_psswd.getText();
	        
	        boolean u_correcto = false;
	        boolean c_correcto = false;
	        
	    	 try {
		            ConexionBD conexion = new ConexionBD();
		            Connection con = conexion.conectarConBase();             
		            String buscarUsuario = "select * from usuarios";
		            Statement bu = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ResultSet resultado = bu.executeQuery(buscarUsuario);
				
				while(resultado.next()) {
					
							if(usuario.equalsIgnoreCase(resultado.getString("usuario"))){
								 u_correcto = true;
								 if(contraseña.equals(resultado.getString("contra"))) {
									c_correcto = true; 
									}else{
										 lbl_error.setVisible(true);
										}
								 
								 }else {
									 lbl_error.setVisible(true);
								 		}
								}
	
				if(u_correcto == true && c_correcto == true) {
					
					
					
					if(usuario.equalsIgnoreCase("admin") && contraseña.equals("admin")) {
					
						
						Parent nuevoadmin = FXMLLoader.load(getClass().getResource("NuevoAdmin.fxml"));

				        Scene scene = new Scene(nuevoadmin);
				        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
				        window.setScene(scene);
				        window.show();
				

					}else{
	
						//0-->ADMIN
						//1-->Bibliotecologo
					
						String verificartipo = "select * from usuarios where usuario ='"+usuario+"'" ;
						Statement vt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ResultSet resultadoVT = vt.executeQuery(verificartipo);
						
						//System.out.println(resultadoVT);
						//System.out.println(resultadoVT.getBoolean("tipo_usuario"));
						//System.out.println(resultadoVT.getBoolean("tipo_usuario"));
						//System.out.println(resultadoVT.getInt(4));
						//System.out.println(resultadoVT.getString("tipo_usuario"));
							
						if(resultadoVT.getString("tipo_usuario").equals("0")) {
							
							Parent main = FXMLLoader.load(getClass().getResource("Admin.fxml"));
					        Scene scene = new Scene(main);
					        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					        window.setScene(scene);
					        window.show();

							}else{
								Parent main = FXMLLoader.load(getClass().getResource("Biblotecologo.fxml"));

						        Scene scene = new Scene(main);
						        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
						        window.setScene(scene);
						        window.show();

							}
										}
					}
				
			 	} catch (SQLException e) {
					System.out.println( "No se pudo conectar con la Base de Datos");
				}
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
