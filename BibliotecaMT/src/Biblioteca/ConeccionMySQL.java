package Biblioteca;

import java.sql.*;

public class ConeccionMySQL {
	
	public Connection conexion;
	public Statement sentencia;
	public ResultSet resultado;
	 
	public Connection conectarConBase() {
		
		java.sql.Connection conector;
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			nombreBaseString = "jdbc:mysql://localhost:3306/"+"BibMT";
			conector = DriverManager.getConnection("BibMT","root","root");
			sentencia = conexion.createStatement();
			if (conector != null) {
				
				System.out.println("Conexión Exitosa");
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Error en la conexion con el Driver");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Error en la conexion con el Base”);"
		}
		
	}
	
	public void desconectar() throws SQLException {
			
		try {
			
			if (conector != null) {
			if(sentencia != null) {
			sentencia.close();
			}
				conector.close();
			}
			
		}catch (SQLException ex) {
			 JOptionPane.showMessageDialog(null,ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			 System.exit(1);
		}	
	}
	
	public Connection getConnection(){
		 return conexion;
	}
	
}
