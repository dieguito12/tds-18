package org.tds.sgh.business;

public class Habitacion
{
	// --------------------------------------------------------------------------------------------
	
	private String nombre;
	
	private TipoHabitacion tipoHabitacion;
	
	private boolean ocupada = false;
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		this.nombre = nombre;
		
		this.tipoHabitacion = tipoHabitacion;
	}
	
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
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
		return this.tipoHabitacion.equals(th);
	}
	
	public Boolean habitacionOcupada()
	{
		return this.ocupada;
	}
	
	
}
