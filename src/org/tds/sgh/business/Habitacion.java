package org.tds.sgh.business;

import javax.persistence.*; 

@Entity
public class Habitacion
{
	// --------------------------------------------------------------------------------------------
	
	private long id;
	
	private String nombre;
	
	private TipoHabitacion tipoHabitacion;
	
	private boolean ocupada = false;
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		this.setNombre(nombre);
		
		this.setTipoHabitacion(tipoHabitacion);
	}
	
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId()
	{
		return this.id;
	}
	
	protected void setId(long id)
	{
		this.id = id;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public TipoHabitacion getTipoHabitacion()
	{
		return this.tipoHabitacion;
	}
	
	// --------------------------------------------------------------------------------------------
	// Implementacion Diagrama de Clases
	
	public Boolean habitacionValida(TipoHabitacion th) {
		return this.getTipoHabitacion().equals(th);
	}
	
	public Boolean habitacionOcupada()
	{
		return this.isOcupada();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public boolean isOcupada() {
		return ocupada;
	}
	
	
}
