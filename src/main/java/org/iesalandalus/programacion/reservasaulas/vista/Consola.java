package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.format.DateTimeFormatter;


import org.iesalandalus.programacion.reservasaulas.Aula;
import org.iesalandalus.programacion.reservasaulas.Profesor;
import org.iesalandalus.programacion.reservasaulas.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		
	private Consola() {
		
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("Reservas de aulas");
		for (Opcion opcion: Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	public static Aula leerAula() {
		
		Aula aula = new Aula(leerNombreAula());
		return aula;
	}

	public static String leerNombreAula() {
		System.out.print("Introduce el nombre del aula: ");
		String nombre = Entrada.cadena();
		
		return nombre;
	}
	
	public static Profesor leerProfesor() {
		System.out.print("Introduce el nombre: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el email: ");
		String correo = Entrada.cadena();
		System.out.print("Introduce el teléfono: ");
		String telefono = Entrada.cadena();

		Profesor profesor = new Profesor(nombre,correo,telefono);
		return profesor;

	}

	public static Tramo leerTramo() {
		System.out.print("Introduce Mañana o Tarde: ");
		String tramoDia = Entrada.cadena();
		Tramo tramo = new Tramo(tramoDia);
		return tramo;
	}

	public static LocalDate leerDia() {


	}
}



