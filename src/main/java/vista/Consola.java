package vista;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = new DateTimeFormatter();
	private static final DatosContacto DATOS_CONTACTO_FICTICIOS = new DatosContacto("950111111", "1@1.es", DIRECCION_POSTAL_FICTICIA);
		
	private Consola() {
		//Evito que se cree el constructor por defecto
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("GestiÃ³n de clientes");
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
			System.out.print("\nElige una opciÃ³n: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}
	
	public static Aula leerCliente() {
		System.out.print("Introduce el nombre: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce los apellidos: ");
		String apellidos = Entrada.cadena();
		System.out.print("Introduce el dni: ");
		String dni = Entrada.cadena();
		System.out.print("Introduce el telÃ©fono: ");
		String telefono = Entrada.cadena();
		System.out.print("Introduce el correo: ");
		String correo = Entrada.cadena();
		System.out.print("Introduce la direcciÃ³n: ");
		String direccion = Entrada.cadena();
		System.out.print("Introduce la localidad: ");
		String localidad = Entrada.cadena();
		System.out.print("Introduce el cÃ³digo postal: ");
		String codigoPostal = Entrada.cadena();
		DatosPersonales datosPersonales = new DatosPersonales(nombre, apellidos, dni);
		DireccionPostal direccionPostal = new DireccionPostal(direccion, localidad, codigoPostal);
		DatosContacto datosContacto = new DatosContacto(telefono, correo, direccionPostal);
		return new Aula(datosPersonales, datosContacto);
	}
	
	public static Aula leerDniCliente() {
		String dni;
		do {
			System.out.print("Introduce el dni: ");
			dni = Entrada.cadena();
		} while (dni.trim().equals(""));
		DatosPersonales datosPersonalesDni = new DatosPersonales("Cliente", "1", dni);
		return new Aula(datosPersonalesDni, DATOS_CONTACTO_FICTICIOS);
	}
	
}


}
