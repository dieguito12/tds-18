package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Habitacion> habitaciones;
	
	private String nombre;
	
	private String pais;
	
	private Map<Long, Reserva> reservas;
	
	// --------------------------------------------------------------------------------------------
	
	public Hotel(String nombre, String pais)
	{
		this.habitaciones = new HashMap<String, Habitacion>();
		
		this.nombre = nombre;
		
		this.pais = pais;
		
		this.reservas = new HashMap<Long, Reserva>();
	}

	public Reserva registrarReserva(Cliente cliente, TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, boolean mph) {
		Reserva r = new Reserva(cliente, th, fi, ff, mph, this);
		this.reservas.put(r.getCodigo(), r);
		return r;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion agregarHabitacion(TipoHabitacion tipoHabitacion, String nombre) throws Exception
	{
		if (this.habitaciones.containsKey(nombre))
		{
			throw new Exception("El hotel ya tiene una habitación con el nombre indicado.");
		}
		
		Habitacion habitacion = new Habitacion(tipoHabitacion, nombre);
		
		this.habitaciones.put(habitacion.getNombre(), habitacion);
		
		return habitacion;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getPais()
	{
		return this.pais;
	}
	
	public Set<Habitacion> listarHabitaciones()
	{
		return new HashSet<Habitacion>(this.habitaciones.values());
	}

	// --------------------------------------------------------------------------------------------
	// Implementacion Diagrama de Clases
	
	public boolean verificarDisponibilidad(TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff)
	{
		Iterator<Reserva> reservas = this.reservas.values().iterator();
		Iterator<Habitacion> habitaciones = this.habitaciones.values().iterator();
		
		int conflictos = 0;
		int capacidad = 0;
		
		while(reservas.hasNext()) {
			Reserva r = reservas.next();
			if (r.verificarConflicto(th, fi, ff)) {
				conflictos++;
			}
		}
		
		while(habitaciones.hasNext()) {
			Habitacion h = habitaciones.next();
			if (h.habitacionValida(th)) {
				capacidad++;
			}
		}
		
		return conflictos < capacidad;
	}
	
	public boolean entaEnElPais(String pais)
	{
		return this.pais.equals(pais);
	}
	
	public Set<Reserva> reservasPendientes()
	{
		Set<Reserva> reservas = new HashSet<Reserva>();
		
		for(Reserva r: this.reservas.values()) {
			if (r.estaPendiente()) {
				reservas.add(r);
			}
		}
		
		return reservas;
		
	}
	
	public Habitacion buscarHabitacionLibre(TipoHabitacion th) 
	{		
		for(Habitacion hab: this.habitaciones.values()) {
			if (!hab.habitacionOcupada() && hab.habitacionValida(th)) {
				return hab;
			}
		}
		
		return null;
	}
	
	//Implementacion metodo para clase Cadena Hotelera
	
	public void deleteReserva(long codigo)
	{		
		this.reservas.remove(codigo);
				
	}
		
}
