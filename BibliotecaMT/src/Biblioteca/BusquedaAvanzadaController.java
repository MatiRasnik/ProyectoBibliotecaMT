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
    	ArrayList<Number> coincidencias = new ArrayList<Number>();
    	ArrayList<Number> ordenado = BusquedaRecursiva(palabras,indice,coincidencias);  
    	System.out.println(ordenado);
    }
	
	
	public ArrayList<Number> BusquedaRecursiva(ArrayList<String> palabras,int indice,ArrayList<Number> coincidencias) {
		
		if(indice < palabras.size())
		{
    		String palabra = palabras.get(indice);
    		palabra = palabra.trim();
    		
    		try
    		{
    			ConexionBD conexion = new ConexionBD();
    	        Connection con = conexion.conectarConBase(); 

    	        String buscarAdmin = "select * from almacen";
    	        Statement cs =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    			ResultSet resultado = cs.executeQuery(buscarAdmin);

    			while (resultado.next())
    			{	

    				String Titulo = resultado.getString("titulo");
    				String Autor = resultado.getString("autor");
    				String Editorial = resultado.getString("editorial");
    				String Genero = resultado.getString("genero");
    				String Fecha = resultado.getString("fecha");
    				String Fechacad = resultado.getString("fecha_caducidad");
    				String Tipo = resultado.getString("tipo");
    				int Paginas = resultado.getInt("paginas");
    				int Tomos = resultado.getInt("tomos");
    				int Unidades = resultado.getInt("unidades");
    				int Precio = resultado.getInt("precio");
    				int ID = resultado.getInt("id");
  
    				if(palabra.equalsIgnoreCase(Titulo)) {

    					coincidencias.add(ID);
    				}else {

						if(palabra.equalsIgnoreCase(Autor)) {
	    					coincidencias.add(ID);
	    				}else {
		    					
		    				if(palabra.equalsIgnoreCase(Editorial)) {
		    					coincidencias.add(ID);
		    				}else {
			    					
			    				if(palabra.equalsIgnoreCase(Genero)) {
			    					coincidencias.add(ID);
			    				}else {
				    					
				    				if(palabra.equalsIgnoreCase(Fecha)) {
				    					coincidencias.add(ID);
				    				}else {
					    					
					    				if(palabra.equalsIgnoreCase(Fechacad)) {
					    					coincidencias.add(ID);
					    				}else {
						    				if(palabra.equalsIgnoreCase(Tipo)) {
						    					coincidencias.add(ID);
						    				}else {
						    					String PaginasCadena= Paginas+"";
						    					PaginasCadena = PaginasCadena.trim();
							    				if(palabra == PaginasCadena) {
							    					coincidencias.add(ID);
							    				}else {
							    					String TomosCadena= Tomos+"";
							    					TomosCadena = TomosCadena.trim();
								    				if(palabra == TomosCadena) {
								    					coincidencias.add(ID);
								    				}else {
								    					String UnidadesCadena= Unidades+"";
								    					UnidadesCadena = UnidadesCadena.trim();
									    				if(palabra == UnidadesCadena) {
									    					coincidencias.add(ID);
									    				}else {
									    					String PrecioCadena= Precio+"";
									    					PrecioCadena = PrecioCadena.trim();
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
		int a=0;
		ArrayList<Number> ordenado = new ArrayList<Number>();
		
		int numero = (int) coincidencias.get(0);
		ordenado.add(numero);
		int Tama�o = coincidencias.size();
		int Tama�oOrd = ordenado.size();
		for(int i=0;i<Tama�o;i++)
		{
			for(int j=0;j<Tama�oOrd;j++) {
    			if(coincidencias.get(i) == ordenado.get(j)) {
    				a = 1;
    			}	
			}
			if(a==0) {
				numero = (int) coincidencias.get(i);
				ordenado.add(numero);
				Tama�oOrd++;
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

