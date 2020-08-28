package Biblioteca;

import java.sql.*;

public class ConeccionMySQL {
	
	public Connection conexion;
	public Statement sentencia;
	public ResultSet resultado;
	 
	public Connection conectarConBase() {
		
		java.sql.Connection conexion = null;
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("BibMT","root","root");
			sentencia = conexion.createStatement();
			if (conexion != null) {
				
				System.out.println("Conexión Exitosa");
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Error en la conexion con el Driver");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Error en la conexion con el Base");
		}
		return conexion;
	}
	
	public void desconectar() throws SQLException {
			
		try {
			
			if (conexion != null) {
			if(sentencia != null) {
			sentencia.close();
			}
				conexion.close();
			}
			
		}catch (SQLException ex) {
			 System.out.println("Error");
			 System.exit(1);
		}	
	}
	
	public Connection getConnection(){
		 return conexion;
	}
	
	
}
