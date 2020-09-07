package Biblioteca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
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

public class NuevoAdminController {

    @FXML
    private JFXTextField textfield_nuevoadmin;

    @FXML
    private JFXButton button_nuevoadmin;

    @FXML
    private JFXButton buttont_atras;

    @FXML
    private ImageView button_atras;

    @FXML
    private Label lbl_uscorrecto;

    @FXML
    private Label lbl_userror;

    @FXML
    private JFXTextField textfield_psswdadmin;

    @FXML
    void Atras(MouseEvent event) {

    }

    @FXML
    void Nuevo_Admin(ActionEvent event) throws IOException {
    	NuevoAdmin();
    	Parent main = FXMLLoader.load(getClass().getResource("NuevoAdmin.fxml"));

        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    	

    }

	private void NuevoAdmin() {
		
		String usuario = this.textfield_nuevoadmin.getText();
        String contraseña = this.textfield_psswdadmin.getText();
        
        
    	 try {
	            ConexionBD conexion = new ConexionBD();
	            Connection con = conexion.conectarConBase();             
	            String buscarUsuario = "select * from usuarios";
	            Statement bu = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet resultado = bu.executeQuery(buscarUsuario);
				
				while(resultado.next()) {
					
					if(usuario.equalsIgnoreCase(resultado.getString("usuario"))){
							lbl_userror.setVisible(true);
					}else{
						
						String safeoff = "set sql_safe_updates = 0";
						
						String nuevoAdmin = "UPDATE usuarios SET usuario ='" +usuario +"' , contra ='" +contraseña+ "' WHERE usuario = 'admin'";
						
						String safeon = "SET SQL_SAFE_UPDATES=1";

						Statement na = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ResultSet nuevo_safeoff = na.executeQuery(safeoff);
						ResultSet nuevoad = na.executeQuery(nuevoAdmin);
						ResultSet nuevo_safeon = na.executeQuery(safeon);
						
						lbl_uscorrecto.setVisible(true);
						 		}
						}
		
    		} catch (SQLException e) {
				System.out.println( "No se pudo conectar con la Base de Datos");
			}
		 }
}



