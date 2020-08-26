package Biblioteca;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) 
	{
		System.out.println("Como desea iniciar");
		System.out.println("1-Usuario");
		System.out.println("2-Administrador");
		Scanner sc = new Scanner;
		int us = sc.nextLine();
		if(us == 2)
		{
			int log = logearse();
		}
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
			Scanner sc = new Scanner;
			int opc = sc.nextLine();
			
			switch(opc){
			
				case 1
					
					break;
				case 2
				
					break;
				case 3
					if(log == 1) {
						
					}
					break;
				case 4
					if(log == 1) {
						
					}
					break;
				case 5
					if(log == 1) {
						
					}
					break;
			}
		}while(opc == 6)
	}

	public int logearse() {
		
		int log;
		
		System.out.println("Ingrese usuario");
		Scanner sc = new Scanner;
		String usuario = sc.nextLine();
		System.out.println("Ingrese contraseña");
		Scanner sc = new Scanner;
		String contra =sc.nextLine();
		
		retun log;
	}
}
