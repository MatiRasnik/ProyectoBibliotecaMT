package Biblioteca;

import java.sql.*;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionBD {
	String nombreBase = "bibmt";
	String usuarioBase = "root";
	String passwordBase = "root";
	java.sql.Connection conector;

	public Connection conectarConBase() {
	try {
		
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		nombreBase = "jdbc:mysql://localhost:3306/"+ nombreBase;
		conector = DriverManager.getConnection(nombreBase, usuarioBase, passwordBase);
		if (conector != null)
		JOptionPane.showMessageDialog(null, "Se a conectado a la Base de Datos");
		
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Error en la conexion con el Driver");
		
	} catch (SQLException e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "No se pudo conectar con la Base de Datos");
	}
	return conector;
	}

	public void desconectarBase() throws SQLException {
		if (conector != null) {
			conector.close();
		}
	}
}