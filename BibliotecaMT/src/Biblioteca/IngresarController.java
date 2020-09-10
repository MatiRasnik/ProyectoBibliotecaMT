package Biblioteca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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

public class IngresarController {

	 @FXML
	    private JFXTextField textfield_tipo;

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
	void Atras(MouseEvent event) {

	}

	@FXML
	void ButtonIngresar(ActionEvent event) {
		IngresarDocumento();

	}

	private void IngresarDocumento() {

		int tipo = Integer.parseInt(textfield_tipo.getText());
		String titulo = textfield_titulo.getText();
		String autor = textfield_autor.getText();
		LocalDate fecha = this.fecha.getValue();
		LocalDate fechacad = this.fechacad.getValue();
		String editorial = textfield_editorial.getText();
		String genero = textfield_genero.getText();
		int paginas = Integer.parseInt(textfield_paginas.getText());
		int tomos = Integer.parseInt(textfield_tomos.getText());
		int unidades = Integer.parseInt(textfield_unidades.getText());
		int precio = Integer.parseInt(textfield_precio.getText());

		try {

			ConexionBD conexion = new ConexionBD();
			Connection con = conexion.conectarConBase();
			String documentos = "select * from almacen";
			Statement doc = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = doc.executeQuery(documentos);

			while (resultado.next()) {

				if (tipo != 0 || titulo == null || fecha == null || unidades != 0) {
					lbl_error.setVisible(true);
				} else {
					if (titulo.equalsIgnoreCase(resultado.getString("titulo"))) {
						lbl_errorexiste.setVisible(true);

					} else {
						String insertString = "INSERT INTO almacen"
								+ "(tipo, titulo, autor, fecha, fecha_caducidad, editorial, genero, paginas, tomos, unidades, precio) "
								+ "values" + "('" + tipo + "', '" + titulo + "', '" + autor + "', '" + fecha + "', '"
								+ fechacad + "', '" + editorial + "', '" + genero + "', '" + paginas + "', '" + tomos
								+ "', '" + unidades + "', '" + precio + "')";
						int cant = doc.executeUpdate(insertString);
						lbl_correcto.setVisible(true);
					}
				}

			}

			con.close();
			doc.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");
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
