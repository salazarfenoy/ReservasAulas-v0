package org.iesalandalus.programacion.reservasaulas;

import javax.naming.OperationNotSupportedException;

public class Reservas {
	private static final int MAX_RESERVAS = 40;
	private int numReservas;
	private Reserva[] coleccionReservas;


	public Reservas() {
		coleccionReservas = new Reserva[MAX_RESERVAS];
		numReservas = 0;
	}
	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}
	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
		numReservas = reservas.numReservas;

	}
	private Reserva[] copiaProfundaReservas(Reserva[] reservas) {
		Reserva[] otrasReservas = new Reserva[reservas.length];
		for (int i = 0; i < reservas.length && reservas[i] != null; i++) {
			otrasReservas[i] = new Reserva(reservas[i]);
		}
		return otrasReservas;
	}
	public Reserva[] getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return numReservas;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
		if (!indiceNoSuperaTamano(indice)) {
			coleccionReservas[indice] = reserva;
			numReservas++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("La reserva ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más reservas.");
			}		

		}
	}

		private int buscarIndiceReserva(Reserva reserva) {
			int indice = 0;
			boolean reservaEncontrada = false;
			while (indiceNoSuperaTamano(indice) && !reservaEncontrada) {
				if (coleccionReservas[indice].equals(reserva)) {
				reservaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean indiceNoSuperaTamano(int indice) {
		return indice < numReservas;
	}

	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice < MAX_RESERVAS;
	}

	public Reserva buscar(Reserva reserva) {
		int indice = 0;
		indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			return coleccionReservas[indice];
		} else {
			return null;
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
		else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numReservas - 1; i++) {
			coleccionReservas[i] = coleccionReservas[i+1];
		}
		coleccionReservas[numReservas] = null;
		numReservas--;
	}

	public String[] representar() {
		String[] representacion = new String[numReservas];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			representacion[i] = coleccionReservas[i].toString();
		}
		return representacion;
	}
	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasProfesores = new Reserva[MAX_RESERVAS];
		int i;
		int p = 0;
		for (i=0;i<=coleccionReservas.length && coleccionReservas[i] != null;i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				reservasProfesores[p] = new Reserva(coleccionReservas[i]);
				p++;
			}
			
		}
		return reservasProfesores;
		
	}
	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservasAula = new Reserva[numReservas];
		int i;
		int p = 0;
		for (i=0;i<=coleccionReservas.length && coleccionReservas[i] != null;i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				reservasAula[p] = new Reserva(coleccionReservas[i]);
				p++;
			}
			
		}
		return reservasAula;
		
	}
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia = new Reserva[numReservas];
		int i;
		int p = 0;
		for (i=0;i<=coleccionReservas.length && coleccionReservas[i] != null;i++) {
			if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
				reservasPermanencia[p] = new Reserva(coleccionReservas[i]);
				p++;
			}
			
		}
		return reservasPermanencia;
		
		
	}
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		boolean estadisponible = true;
		if (aula == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		}
		if (permanencia == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		}
		for (int i=0;i<=coleccionReservas.length && coleccionReservas[i] != null;i++) {
			if (coleccionReservas[i].getAula().equals(aula) && coleccionReservas[i].getPermanencia().equals(permanencia)) {
				estadisponible = false;
			}
			
		}
		return estadisponible;
		
	}

}
