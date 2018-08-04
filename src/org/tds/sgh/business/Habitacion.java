package org.tds.sgh.business;

public class Habitacion
{
	// --------------------------------------------------------------------------------------------
	
	private String nombre;
	
	private TipoHabitacion tipoHabitacion;
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		this.nombre = nombre;
		
		this.tipoHabitacion = tipoHabitacion;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public TipoHabitacion getTipoHabitacion()
	{
		return this.tipoHabitacion;
	}
	
	// --------------------------------------------------------------------------------------------
	// Implementacion Diagrama de Clases
	
	public Boolean habitacionValida(TipoHabitacion th) {
		Boolean result = false;
				
		return result;
	}
	
	public Boolean habitacionOcupada()
	{
		Boolean result = false;
				
		return result;
	}
	
	
}
