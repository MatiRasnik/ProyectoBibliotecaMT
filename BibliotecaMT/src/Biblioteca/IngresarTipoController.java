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

public class IngresarTipoController {

	@FXML
	private JFXTextField textfield_tipo;

	@FXML
	private JFXButton button_ingresartipo;

	@FXML
	private JFXButton buttont_atras;

	@FXML
	private ImageView button_atras;

	@FXML
	private Label lbl_error;

	@FXML
	private Label lbl_creado;

	@FXML
	void Atras(MouseEvent event) {

	}

	@FXML
	void Ingresar_tipo(ActionEvent event) {
		ingresartipo();

	}

	private void ingresartipo() {
    	try {
    		String nuevotipo = textfield_tipo.getText();
    		
			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String documentos = "select * from tipo_documento";
			Statement doc = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = doc.executeQuery(documentos);

			while (resultado.next()) {
				
					if (nuevotipo.equalsIgnoreCase(resultado.getString("tipodoc"))) {
						lbl_error.setVisible(true);

					} else {
						String insertString = "INSERT INTO usuarios"
								+ "(id, tipodoc) "
								+ "values" + "('" + nuevotipo  + "')";
						int cant = doc.executeUpdate(insertString);
						lbl_creado.setVisible(true);
					}
				}


			con.close();
			doc.close();
		}catch(

	SQLException e)
	{
		e.printStackTrace();
		System.out.println("Error en la conexion con la Base");
	}

	}

	@FXML
	void atras(ActionEvent event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("Menu.fxml"));

		Scene scene = new Scene(main);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

}
