spackage Biblioteca;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

public class Datos extends JFrame {

	public static void AgregarUsuario() {
		String usuario;
		String contra;
		int tipo;
		
		//parte grafica
		
		try {
		ConexionBD conexion = new ConexionBD();
        Connection con = conexion.conectarConBase(); 
        String buscarAdmin = "select * from usuario";
        Statement cs =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultado = cs.executeQuery(buscarAdmin);
		while (resultado.next())
		{
			String Usuario = resultado.getString("usuario");
			if(usuario == Usuario ) {
					//ya existe un usuario con ese nombre
			}else {
				 String insertString = "INSERT INTO usuarios" + "(id,usuario,contra,tipo) values" + "('usuario', 'contra', 'tipo')";
				 //el usuario se a registrado correctamnete 
			}
		}	
		} catch (ClassNotFoundException  e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con el Driver");
			} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");
			}
	}
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
		}} catch (ClassNotFoundException  e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con el Driver");
			} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion con la Base");

		}
	}
}