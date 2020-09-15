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
	private Label lbl_uscorrecto;

	@FXML
	private Label lbl_userror;

	@FXML
	private JFXTextField textfield_psswdadmin;

	@FXML
	void Nuevo_Admin(ActionEvent event) throws IOException {
		NuevoAdmin();
		Parent main = FXMLLoader.load(getClass().getResource("Admin.fxml"));

		Scene scene = new Scene(main);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();

	}

	private void NuevoAdmin() {

		String usuario = this.textfield_nuevoadmin.getText();
		String contrase�a = this.textfield_psswdadmin.getText();

		try {
			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String buscarUsuario = "select * from usuarios";
			Statement bu = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = bu.executeQuery(buscarUsuario);

			while (resultado.next()) {

				if (usuario.equalsIgnoreCase(resultado.getString("usuario"))) {
					lbl_uscorrecto.setVisible(false);
					lbl_userror.setVisible(true);

				} else {

					String nuevoAdmin = "UPDATE usuarios SET usuario ='" + usuario + "' , contra ='" + contrase�a
							+ "' WHERE usuario = 'admin'";
					Statement na = con.createStatement();

					na.executeUpdate(nuevoAdmin);
					lbl_userror.setVisible(false);
					lbl_uscorrecto.setVisible(true);

				}
			}
			con.close();
			bu.close();

		} catch (SQLException e) {
			System.out.println("No se pudo conectar con la Base de Datos");
		}
	}
}
