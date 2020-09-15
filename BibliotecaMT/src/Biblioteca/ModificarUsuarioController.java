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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModificarUsuarioController {

	@FXML
	private JFXTextField textfield_usuarioactual;

	@FXML
	private JFXButton button_eliminar;

	@FXML
	private JFXButton buttont_atras;

	@FXML
	private ImageView button_atras;

	@FXML
	private Label lbl_error;

	@FXML
	private Label lbl_eliminado;

	@FXML
	private Label lbl_eliminado1;

	@FXML
	private JFXTextField textfield_Nuevousuario;

	@FXML
	private JFXPasswordField textfield_ContraActual;

	@FXML
	private JFXPasswordField textfield_NuevaContra;

	@FXML
	private JFXButton button_Modificar;

	@FXML
	private Label lbl_mod;

	@FXML
	private CheckBox checkbox_admin;

	@FXML
	void Atras(MouseEvent event) {

	}

	@FXML
	void Eliminar_usuario(ActionEvent event) {
		eliminarusuario();

	}

	@FXML
	void Modificar_usuario(ActionEvent event) {
		modificarusuario();

	}

	private void modificarusuario() {
		lbl_error.setVisible(false);
		lbl_mod.setVisible(false);
		lbl_error.setVisible(false);
		lbl_eliminado.setVisible(false);
		try {
			String actualuser = textfield_usuarioactual.getText();
			String actualpsswd = textfield_ContraActual.getText();
			String nwusae = textfield_Nuevousuario.getText();
			String nwpsswd = textfield_NuevaContra.getText();

			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String modusar = "select * from usuarios";
			Statement mu = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = mu.executeQuery(modusar);

			int tipo = 1;
			if (checkbox_admin.isSelected()) {
				tipo = 0;
				System.out.println("Tipo: " + tipo);
			} else {
				tipo = 1;
				System.out.println("Tipo: " + tipo);
			}

			boolean existe = false;
			while (resultado.next()) {

				if (actualuser.equalsIgnoreCase(resultado.getString("usuario"))) {
					existe = true;
					System.out.println("El usuario existe. ");
				}
			}

			if (existe == true) {

				String modUsuario = "UPDATE usuarios SET usuario ='" +nwusae+ "' , contra ='" +nwpsswd
						+ "' , tipo_usuario ='" +tipo+ "' WHERE usuario = '" +actualuser+ "'";
				Statement na = con.createStatement();
				na.executeUpdate(modUsuario);
				lbl_error.setVisible(false);
				lbl_mod.setVisible(true);
				System.out.println("Usuario modificado. ");
			} else {
				lbl_mod.setVisible(false);
				lbl_error.setVisible(true);
				System.out.println("Usuario no modificado. ");
			}

			con.close();
			mu.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");
		}

	}

	private void eliminarusuario() {
		lbl_error.setVisible(false);
		lbl_mod.setVisible(false);
		lbl_error.setVisible(false);
		lbl_eliminado.setVisible(false);
		try {
			String eliminarusuario = textfield_usuarioactual.getText();

			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String deluser = "select * from usuarios";
			Statement ds = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = ds.executeQuery(deluser);

			boolean existe = false;
			while (resultado.next()) {
				if (eliminarusuario.equalsIgnoreCase(resultado.getString("usuario"))) {
					existe = true;
					System.out.println("El usuario existe. ");

				}
			}

			if (existe == true) {
				String insertString = "DELETE FROM usuarios WHERE usuario = '" + eliminarusuario + "'";
				int cant = ds.executeUpdate(insertString);
				lbl_error.setVisible(false);
				lbl_eliminado.setVisible(true);
				System.out.println("Usuario eliminado. ");
			} else {
				lbl_eliminado.setVisible(false);
				lbl_error.setVisible(true);
				System.out.println("Usuario no eliminado. ");
			}

			con.close();
			ds.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");
		}

	}

	@FXML
	void atras(ActionEvent event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("Admin.fxml"));

		Scene scene = new Scene(main);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

}
