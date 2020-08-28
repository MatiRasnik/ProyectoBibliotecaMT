package Biblioteca;


import java.sql.*;
import java.util.Scanner;

public class Libros {

	public int log;
	
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

		System.out.println("Ingrese usuario");
		Scanner sc = new Scanner(System.in);
		String usuario = sc.nextLine();
		System.out.println("Ingrese contraseña");
		String contra =sc.nextLine();
		sc.close();

		ConeccionMySQL sql = new ConeccionMySQL();


		String SQL = "SELECT COUNT(id) AS i FROM usuarios WHERE usuario='"+usuario+"'"+"AND contra = '"+contra+"' ";
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
