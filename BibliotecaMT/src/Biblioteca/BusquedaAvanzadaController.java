package Biblioteca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BusquedaAvanzadaController {

    @FXML
    private JFXButton buttont_atras;

    @FXML
    private ImageView button_atras;

    @FXML
    private JFXTextField textfield_busqueda;
    
    @FXML
    private JFXButton button_buscar;

    @FXML
    void Atras(MouseEvent event) {

    }

    @FXML
    void atras(ActionEvent event) throws IOException {
    	Parent main = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(main);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void buscar(ActionEvent event) {
    	String busqueda = textfield_busqueda.getText();
    	ArrayList<String> palabras = GetPalabras(busqueda);
    	int indice = 0;
    	ArrayList<Number> coincidencias = null;
    	ArrayList<Number> ordenado = BusquedaRecursiva(palabras,indice,coincidencias);  
    	//SE muestran por pantalla todo lo que hay adentro de ordenado
    }
	
	
	public ArrayList<Number> BusquedaRecursiva(ArrayList<String> palabras,int indice,ArrayList<Number> coincidencias) {
		
		if(indice > palabras.size())
		{
    		String palabra = palabras.get(indice);
    		palabra = palabra.trim();
    		
    		try
    		{
    			ConexionBD conexion = new ConexionBD();
    	        Connection con = conexion.conectarConBase(); 
    	        CrearBase crear = new CrearBase();
	            crear.crearDB();
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
    			}	
    		} catch (SQLException e) {
    			e.printStackTrace();
    			System.out.println("Error en la conexion con la Base");
    		}
    		indice++;
			coincidencias = BusquedaRecursiva(palabras,indice,coincidencias);	
		}
		ArrayList<Number> ordenado = null;
		int a=0;
		for(int i=0;i<coincidencias.size();i++)
		{
			for(int j=0;j<coincidencias.size();j++) {
    			if(coincidencias.get(i) == ordenado.get(j)) {
    				a = 1;
    			}	
			}
			if(a==0) {
				ordenado.add(coincidencias.get(i));
			}
			a=0;
		}

		
	return ordenado;	
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

