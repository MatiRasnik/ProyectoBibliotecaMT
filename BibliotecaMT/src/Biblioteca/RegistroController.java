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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistroController {

	@FXML
	private JFXTextField textfield_usuario;

	@FXML
	private JFXButton button_iniciarsesion;

	@FXML
	private JFXButton buttont_atras;

	@FXML
	private ImageView button_atras;

	@FXML
	private Label lbl_uscorrecto;

	@FXML
	private Label lbl_userror;

	@FXML
	private CheckBox checkbox_admin;

	@FXML
	private JFXTextField textfield_contra;

	@FXML
	void Atras(MouseEvent event) {

	}

	@FXML
	void Iniciar_Sesion(ActionEvent event) throws IOException {
		AgregarUsuario();

	}

	private void AgregarUsuario() {
		String usuario = this.textfield_usuario.getText();
		String contraseña = this.textfield_contra.getText();

		try {
			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String buscarUsuario = "select * from usuarios";
			Statement bu = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = bu.executeQuery(buscarUsuario);

			boolean existe = false;
			while (resultado.next()) {

				if (usuario.equalsIgnoreCase(resultado.getString("usuario"))) {
					lbl_userror.setVisible(true);
					lbl_uscorrecto.setVisible(false);
					existe = true;
					System.out.println("Existe: " + existe);
					textfield_usuario.setText(" ");
					textfield_contra.setText(" ");

				}
			}
			if (existe == false) {
				lbl_userror.setVisible(false);
				int tipo;
				if (checkbox_admin.isSelected()) {
					tipo = 0;
					System.out.println("Tipo usuario: " + tipo);
				} else {
					tipo = 1;
					System.out.println("Tipo usuario: " + tipo);
				}

				String insertString = "INSERT INTO usuarios" + "(usuario,contra,tipo_usuario) values" + "('" + usuario
						+ "', '" + contraseña + "', '" + tipo + "')";
				int cant = bu.executeUpdate(insertString);
				lbl_uscorrecto.setVisible(true);
				System.out.println("usuario creado");
				textfield_usuario.setText(" ");
				textfield_contra.setText(" ");
			}

			con.close();
			bu.close();
		} catch (SQLException e) {
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
