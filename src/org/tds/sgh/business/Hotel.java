package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import antlr.collections.List;


public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Habitacion> habitaciones;
	
	private String nombre;
	
	private String pais;
	
	// --------------------------------------------------------------------------------------------
	
	public Hotel(String nombre, String pais)
	{
		this.habitaciones = new HashMap<String, Habitacion>();
		
		this.nombre = nombre;
		
		this.pais = pais;
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
		boolean result = false;
		return result;
	}
	
	public boolean entaEnPais(String pais)
	{
		boolean result = false;
		return result;
	}
	
	public Set<Reserva> reservasPendientes()
	{
		Set<Reserva> _reservas = new HashSet<Reserva>();
		
		return _reservas;
		
	}
	
/*	public Habitacion buscarHabitacionLibre(TipoHabitacion th) 
	{
		TipoHabitacion _tipoHabitacion = new TipoHabitacion();
		
		return _tipoHabitacion;
	}*/
		
	
}
