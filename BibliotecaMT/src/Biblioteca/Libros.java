package Biblioteca;
	
import java.sql.*;

import java.sql.*;
import java.util.Scanner;
	
public class Libros {
	
	
	public void ModificarLibros() {
		
	}
	public String BuscarLibrosA(String Libro) {
		
		return Libro;
	}
	public void BuscarLibros(Libros Libro) {
		
	}
	public void EliminarLibros() {
		
	}
	public void AgregarLibros(Libros Libro) {
		
	}
	public int logearse() throws SQLException {
		
		int log = 0;
		System.out.println("Ingrese usuario");
		Scanner sc = new Scanner(System.in);
		String usuario = sc.nextLine();
		System.out.println("Ingrese contraseña");
		String contra =sc.nextLine();
		sc.close();
		
		Connection conexion = null;
		
		ConeccionMySQL sql = new ConeccionMySQL();
		conexion = sql.conectarConBase();
		
		String SQL = "SELECT * FROM usuarios WHERE usuario='"+usuario+"'"+"AND contra = '"+contra+"' ";
		sql.resultado = sql.sentencia.executeQuery(SQL);
		while (sql.resultado.next()) {
			if(sql.resultado.getString("i").equals("1"))	{
				log = 1;
				System.out.println("se a logiado correctamente");
			}else {
				System.out.println("La contraseña o usuario son invalidas");
			}
		}
		sql.desconectar();
		
		return log;
	}
	
}
