package Biblioteca;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class Datos extends JFrame {


	public static void AgregarLibro() {
		
		int tipo;
		String nombre;
		String autor;
		String fecha;
		String editorial;
		String paginas;
		int precio;
		int tomos;
		String fecaha_cad;
		//no se si hay mas
		
		//Parte grafica
		
		try {
		ConexionBD conexion = new ConexionBD();
        Connection con = conexion.conectarConBase(); 
        String buscarAdmin = "select * from Almacen";
        Statement cs =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultado = cs.executeQuery(buscarAdmin);
		while (resultado.next())
		{
			int Tipo = resultado.getInt("tipo");
			
			if(Tipo == 0) { //Libros
				String insertString = "INSERT INTO Almacen" + "(nombre,autor,tipo,fecha,editorial,paginas,tomos) values" + "('nombre', 'autor', '0','fecha','editorial','paginas','tomos')";
			}else {
				if(Tipo == 1) { //revistas
					String insertString = "INSERT INTO Almacen" + "(nombre,autor,tipo,fecha,editorial,paginas) values" + "('nombre', 'autor', '1','fecha','editorial','paginas')";
				}else {
					if(Tipo == 2) { //facturas
						String insertString = "INSERT INTO Almacen" + "(nombre,autor,tipo,fecha,paginas,fecha_cad) values" + "('nombre', 'autor', '2','fecha','paginas','fecha_cad')";
					}else {
						if(Tipo == 3) { //obras de teatro
							String insertString = "INSERT INTO Almacen" + "(nombre,autor,tipo) values" + "('nombre', 'autor', '3')";
						}else {
							if(Tipo == 4) { //cd
								String insertString = "INSERT INTO Almacen" + "(nombre,autor,tipo) values" + "('nombre', 'autor', '4')";
							}else {
								//si hay algo mas despues habisame 
							}
						}
					}
				}
			}	
		}} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");

		}
	}
}