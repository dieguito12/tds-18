package org.tds.sgh.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoHabitacion
{
	// --------------------------------------------------------------------------------------------
	
	private long id;
	
	private String nombre;
	
	// --------------------------------------------------------------------------------------------
	
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
	
	public TipoHabitacion(String nombre)
	{
		this.nombre = nombre;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
