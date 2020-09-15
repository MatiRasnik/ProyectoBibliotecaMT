package Biblioteca;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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

public class IngresarController implements Initializable {
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
	private JFXButton button_ingresar;

	@FXML
	private JFXButton buttont_atras;

	@FXML
	private ImageView button_atras;

	@FXML
	private Label lbl_correcto;

	@FXML
	private Label lbl_error;

	@FXML
	private Label lbl_errorexiste;

	@FXML
	private JFXComboBox<String> tipobox;

	@FXML
	void Atras(MouseEvent event) {

	}

	@FXML
	void ButtonIngresar(ActionEvent event) {
		IngresarDocumento();

	}

	private void IngresarDocumento() {

		String tipo = this.tipobox.getValue();
		String titulo = textfield_titulo.getText();
		String autor = textfield_autor.getText();
		LocalDate fecha = this.fecha.getValue();
		LocalDate fechacad = this.fechacad.getValue();
		String editorial = textfield_editorial.getText();
		String genero = textfield_genero.getText();
		int paginas = 0;
		int tomos = 0;
		int unidades = 0;
		int precio = 0;

		try {

			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String documentos = "select * from almacen";
			Statement doc = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = doc.executeQuery(documentos);
			boolean existe = false;

			while (resultado.next()) {

				if (titulo.equalsIgnoreCase(resultado.getString("titulo"))) {
					lbl_correcto.setVisible(false);
					lbl_errorexiste.setVisible(true);
					textfield_titulo.setText(" ");
					existe = true;
				}

				if (existe == false) {
					lbl_errorexiste.setVisible(false);
					if (tipo == null || titulo == null || fecha == null || unidades != 0) {
						lbl_error.setVisible(true);

					} else {
						if (!textfield_paginas.getText().equals("")) {
							paginas = Integer.parseInt(textfield_paginas.getText());
						}
						if (!textfield_tomos.getText().equals("")) {
							tomos = Integer.parseInt(textfield_tomos.getText());
						}
						if (!textfield_unidades.getText().equals("")) {
							unidades = Integer.parseInt(textfield_unidades.getText());
						}
						if (!textfield_precio.getText().equals("")) {
							precio = Integer.parseInt(textfield_precio.getText());
						}

						String insertString = "INSERT INTO almacen"
								+ "(tipo, titulo, autor, fecha, fecha_caducidad, editorial, genero, paginas, tomos, unidades, precio) "
								+ "values" + "('" + tipo + "', '" + titulo + "', '" + autor + "', '" + fecha + "', '"
								+ fechacad + "', '" + editorial + "', '" + genero + "', '" + paginas + "', '" + tomos
								+ "', '" + unidades + "', '" + precio + "')";
						int cant = doc.executeUpdate(insertString);
						lbl_correcto.setVisible(true);
						tipobox.setValue(null);
						textfield_titulo.setText(" ");
						textfield_autor.setText("");
						this.fecha.setValue(null);
						this.fechacad.setValue(null);
						textfield_editorial.setText(" ");
						textfield_genero.setText(" ");
						textfield_paginas.setText(" ");
						textfield_tomos.setText(" ");
						textfield_unidades.setText(" ");
						textfield_precio.setText(" ");
					}
				}

			}

			con.close();
			doc.close();
		} catch (SQLException e) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tipobox.setItems(opciones());
		tipobox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			String tipo = newValue;

			try {

				ConexionBD conexion = new ConexionBD();
				Connection con = conexion.conectarConBase();
				String buscar = "SELECT * FROM tipo_documento";

				ResultSet resultado = con.createStatement().executeQuery(buscar);

				boolean encontrado = false;

				while (resultado.next()) {
					if (tipo.equals(resultado.getString("tipodoc"))) {
						encontrado = true;
						break;
					}
				}
				if (encontrado == true) {
					if (resultado.getString("paginas").equals("0")) {
						textfield_paginas.setText("");
						textfield_paginas.setEditable(false);
						textfield_paginas.setDisable(true);
					} else {
						textfield_paginas.setEditable(true);
						textfield_paginas.setDisable(false);
					}

					if (resultado.getString("tomos").equals("0")) {
						textfield_tomos.setText("");
						textfield_tomos.setEditable(false);
						textfield_tomos.setDisable(true);
					} else {
						textfield_tomos.setEditable(true);
						textfield_tomos.setDisable(false);
					}

					if (resultado.getString("precio").equals("0")) {
						textfield_precio.setText("");
						textfield_precio.setEditable(false);
						textfield_precio.setDisable(true);
					} else {
						textfield_precio.setEditable(true);
						textfield_precio.setDisable(false);
					}

					if (resultado.getString("fechacad").equals("0")) {
						fechacad.setEditable(false);
						fechacad.setDisable(true);
					} else {
						fechacad.setEditable(true);
						fechacad.setDisable(false);
					}

				}

			} catch (SQLException e) {
				System.out.println("Error");
			}
		});
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
			System.out.println("Error");
		}

		return opciones;
	}
}
