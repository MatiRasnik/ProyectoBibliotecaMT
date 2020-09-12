package Biblioteca;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BusquedaAvanzadaController {

    @FXML
    private JFXButton buttont_atras;

    @FXML
    private ImageView button_atras;

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
    private JFXButton button_buscar;

    @FXML
    void Atras(MouseEvent event) {

    }

    @FXML
    void atras(ActionEvent event) {

    }

    @FXML
    void buscar(ActionEvent event) {

    }
    public class BuscadorAvanzado {

    	public ArrayList<Number> BusquedaRecursiva(ArrayList<String> palabras,int indice,ArrayList<Number> coincidencias) {
    		
    		if(indice > palabras.size())
    		{
	    		String palabra = palabras.get(indice);
	    		palabra = palabra.trim();
	    		
	    		int id = Integer.parseInt(textfield_tipo.getText());
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
	    		try
	    		{
	    			ConexionBD conexion = new ConexionBD();
	    	        Connection con = conexion.conectarConBase(); 
	    	        String buscarAdmin = "select * from Almacen";
	    	        Statement cs =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    			ResultSet resultado = cs.executeQuery(buscarAdmin);
	    			while (resultado.next())
	    			{	
	    				String Titulo = resultado.getString("titulo");
	    				String Autor = resultado.getString("autor");
	    				String Editorial = resultado.getString("editorial");
	    				String Genero = resultado.getString("genero");
	    				String Fecha = resultado.getString("fecha");
	    				String Fechacad = resultado.getString("fechacad");
	    				int Tipo = resultado.getInt("tipo");
	    				int Paginas = resultado.getInt("paginas");
	    				int Tomos = resultado.getInt("tomos");
	    				int Unidades = resultado.getInt("unidades");
	    				int Precio = resultado.getInt("precios");
	    				int ID = resultado.getInt("id");
	    				
	    				if(palabra == Titulo) {
	    					coincidencias.add(ID);
	    				}else {
	    					
							if(palabra == Autor) {
		    					coincidencias.add(ID);
		    				}else {
			    					
			    				if(palabra == Editorial) {
			    					coincidencias.add(ID);
			    				}else {
				    					
				    				if(palabra == Genero) {
				    					coincidencias.add(ID);
				    				}else {
					    					
					    				if(palabra == Fecha) {
					    					coincidencias.add(ID);
					    				}else {
						    					
						    				if(palabra == Fechacad) {
						    					coincidencias.add(ID);
						    				}else {
						    					String TipoCadena= Integer.toString(Tipo);
							    				if(palabra == TipoCadena) {
							    					coincidencias.add(ID);
							    				}else {
							    					String PaginasCadena= Integer.toString(Paginas);
								    				if(palabra == PaginasCadena) {
								    					coincidencias.add(ID);
								    				}else {
								    					String TomosCadena= Integer.toString(Tomos);
									    				if(palabra == TomosCadena) {
									    					coincidencias.add(ID);
									    				}else {
									    					String UnidadesCadena= Integer.toString(Unidades);
										    				if(palabra == UnidadesCadena) {
										    					coincidencias.add(ID);
										    				}else {
										    					String PrecioCadena= Integer.toString(Precio);
											    				if(palabra == PrecioCadena) {
											    					coincidencias.add(ID);
											    				}
										    				}
									    				}
								    				}
							    				}
						    				}
					    				}
				    				}
			    				}
		    				}
						}
	    				indice++;
	    				coincidencias = BusquedaRecursiva(palabras,indice,coincidencias);	
	    			}	
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    			System.out.println("Error en la conexion con la Base");
	    		}
    		}
    	return coincidencias;	
    	}
    	
    	public ArrayList<String> GetPalabras(String busqueda) {
    		
    		ArrayList<String> palabras = new ArrayList<String>();
    		busqueda = busqueda + " ";
    		int espacio;
    		int inicio = 0;
    		for(int i=0 ; i<busqueda.length() ; i++) {
    			if(busqueda.charAt(i) == ' '){
    				 if(i != 0) {
    					 espacio = i;
    					 palabras.add(busqueda.substring(inicio,espacio));
    					 inicio = i;
    				 }
    			}	
    		}
    		return palabras;
    	}
    }
}
