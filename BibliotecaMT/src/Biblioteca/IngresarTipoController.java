package Biblioteca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
	private JFXCheckBox checkPaginas;

	@FXML
	private JFXCheckBox checkPrecio;

	@FXML
	private JFXCheckBox checkTomos;

	@FXML
	private JFXCheckBox checkFechacad;

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

			int paginas = 0;
			int precio = 0;
			int tomos = 0;
			int fechacad = 0;

			if (checkPaginas.isSelected()) {
				paginas = 1;
				System.out.println("Paginas: "+ paginas);
			}
			if (checkPrecio.isSelected()) {
				precio = 1;
				System.out.println("Precio: "+ precio);
			}
			if (checkTomos.isSelected()) {
				precio = 1;
				System.out.println("Tomos: "+ precio);
			}
			if (checkFechacad.isSelected()) {
				fechacad = 1;
				System.out.println("Fechacad: "+ fechacad);
			}
			boolean existe = false;
			
			while (resultado.next()) {

				if (nuevotipo.equalsIgnoreCase(resultado.getString("tipodoc"))) {
					lbl_error.setVisible(true);
					lbl_creado.setVisible(false);
					existe = true;
					System.out.println("Existe: "+existe);
					textfield_tipo.setText(" ");
					checkPaginas.setSelected(false);
					checkPrecio.setSelected(false);
					checkTomos.setSelected(false);
					checkFechacad.setSelected(false);

				}}
				if(existe == false) {
					String insertString = "INSERT INTO tipo_documento"
							+ "(tipodoc, paginas, precio, tomos, fechacad) " + "values" + "('" + nuevotipo + "','"
							+ paginas + "','" + precio + "','" + tomos + "','" + fechacad + "')";
					int cant = doc.executeUpdate(insertString);
					System.out.println("tipo crado");
					lbl_creado.setVisible(true);
					lbl_error.setVisible(false);
					textfield_tipo.setText(" ");
					checkPaginas.setSelected(false);
					checkPrecio.setSelected(false);
					checkTomos.setSelected(false);
					checkFechacad.setSelected(false);
				}
			

			con.close();
			doc.close();
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
