package Biblioteca;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModificarTipoController implements Initializable {

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
	private Label lbl_advertencia;

	@FXML
	private JFXComboBox<String> tipobox;

	@FXML
	private JFXCheckBox checkPaginas;

	@FXML
	private JFXCheckBox checkPrecio;

	@FXML
	private JFXCheckBox checkTomos;

	@FXML
	private JFXCheckBox checkFechacad;

	@FXML
	private JFXButton button_modificar;

	@FXML
	void Atras(MouseEvent event) {

	}

	@FXML
	void Eliminar_tipo(ActionEvent event) {
		eliminartipo();

	}

	@FXML
	void Modificar_tipo(ActionEvent event) {
		modificartipo();

	}

	private void modificartipo() {
		try {
			String modtipo = tipobox.getValue();

			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String documentos = "select * from tipo_documento";
			Statement doc = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = doc.executeQuery(documentos);

			while (resultado.next()) {
				if (modtipo.equals(resultado.getString("tipodoc"))) {

					int paginas = 0;
					int tomo = 0;
					int precio = 0;
					int fechacad = 0;

					if (checkPaginas.isSelected()) {
						paginas = 1;
					}
					if (checkTomos.isSelected()) {
						tomo = 1;
					}
					if (checkPrecio.isSelected()) {
						precio = 1;
					}
					if (checkFechacad.isSelected()) {
						fechacad = 1;
					}

					String UpdateTipo = "UPDATE tipo_documento SET  paginas = '" + paginas + "', tomos = '" + tomo
							+ "', precio = '" + precio + "','" + fechacad + "'  WHERE tipo ='" + modtipo + "'";

					Statement ut = con.createStatement();
					ut.executeUpdate(UpdateTipo);

					checkPaginas.setSelected(false);
					checkTomos.setSelected(false);
					checkPrecio.setSelected(false);
					checkFechacad.setSelected(false);
				}
			}

			con.close();
			doc.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");
		}

	}

	private void eliminartipo() {
		try {
			String eliminartipo = tipobox.getValue();

			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String documentos = "select * from tipo_documento";
			Statement doc = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = doc.executeQuery(documentos);

			while (resultado.next()) {
				if (eliminartipo.equalsIgnoreCase(resultado.getString("tipodoc"))) {
					String insertString = "DELETE FROM tipo_documento WHERE tipodoc = '" + eliminartipo + "'";
					int cant = doc.executeUpdate(insertString);
					lbl_eliminado.setVisible(true);
				} else {
					lbl_error.setVisible(true);
				}
			}

			con.close();
			doc.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");
		}

	}

	private ObservableList<String> opciones() {

		ObservableList<String> opciones = FXCollections.observableArrayList();
		opciones.removeAll(opciones);

		try {
			String buscar = "SELECT * FROM tipo_documento";
			opciones = FXCollections.observableArrayList();
			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();

			ResultSet optipo = con.createStatement().executeQuery(buscar);

			while (optipo.next()) {
				opciones.add(optipo.getString("tipodoc"));
			}
		} catch (Exception e) {
			System.out.println("Error al agregar tipo");
		}

		return opciones;
	}

	@FXML
	void atras(ActionEvent event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("Admin.fxml"));

		Scene scene = new Scene(main);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tipobox.setItems(opciones());
		tipobox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			String tipo = newValue;
			try {
				checkboxtipo();
			} catch (SQLException e) {
				System.out.println("Error");
			}

		});

	}

	private void checkboxtipo() throws SQLException {

		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.conectarConBase();
		String buscar = "SELECT * FROM tipodemateriales";
		Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultado = cs.executeQuery(buscar);
		try {
			while (resultado.next()) {
				if (tipobox.getValue().equals(resultado.getString("tipo"))) {

					boolean paginas = false;
					boolean tomo = false;
					boolean precio = false;
					boolean fechacad = false;

					if (resultado.getString("paginas").equals("1")) {
						paginas = true;
					}
					if (resultado.getString("tomo").equals("1")) {
						tomo = true;
					}
					if (resultado.getString("precio").equals("1")) {
						precio = true;
					}
					if (resultado.getString("fechacad").equals("1")) {
						fechacad = true;
					}
					checkPaginas.setSelected(paginas);
					checkTomos.setSelected(tomo);
					checkPrecio.setSelected(precio);
					checkFechacad.setSelected(fechacad);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error");
		}

	}

}
