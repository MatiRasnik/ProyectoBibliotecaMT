package Biblioteca;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) throws SQLException 
	{
		int log;
		Libros l = new Libros();
		log = l.logearse();
		
		int opc;
		do{
			System.out.println("Que desea hacer?");
			System.out.println("1-Buscar libro");
			System.out.println("2-Consultas");
			if(log == 1) {
				System.out.println("3-Modificar libro");
				System.out.println("4-Agregar libro");
				System.out.println("5-Eliminar libro");
			}
			System.out.println("6-salir");
			Scanner sc = new Scanner(System.in);
			opc = sc.nextInt();
			sc.close();

			switch(opc){

				case 1:
					/*
					System.out.println("Como quiere buscar");
					
					System.out.println("busqueda avanzada");
					{
						BuscarLibrosA(Libro);
					}
					System.out.println("busqueda normal");
					{
						public void BuscarLibros(Libros);
					}
					*/
					break;
				case 2:
					/*
					 System.out.println("que quiere consultar");
					 
					 System.out.println("1-nose");
					 {
					 	Nose1();
					 }
					 System.out.println("2-nose");
					 {
					 	Nose1();
					 }
					 System.out.println("3-nose");
					 {
					 	Nose1();
					 }
					 System.out.println("4-nose");
					 {
					 	Nose1();
					 }
					*/
					break;
				case 3:
					if(log == 1) {
						/*
						System.out.println("Como quiere buscar");
					
					System.out.println("busqueda avanzada");
					{
						BuscarLibrosA(Libro);
					}
					System.out.println("busqueda normal");
					{
						public void BuscarLibros(Libros);
					}
						 */
					}
					break;
				case 4:
					if(log == 1) {
						/*
						 System.out.println("inserte datos del libro");
						 
						 System.out.println("Titulo");
						 System.out.println("autor");
						 System.out.println("fecha");
						 System.out.println("esditorial");
						 System.out.println("tipo");
						 
						 Agregarlibro(libro);
						*/
					}
					break;
				case 5:
					if(log == 1) {
						/*
						System.out.println("Como quiere buscar");
					
					System.out.println("busqueda avanzada");
					{
						BuscarLibrosA(Libro);
					}
					System.out.println("busqueda normal");
					{
						public void BuscarLibros(Libros);
					}
						*/
					}
					break;
			}
		}while(opc == 6);
	}
	
}
