package org.tds.sgh.business;

import javax.persistence.*;

@Entity
public class Huesped {
	
	//-----------------------------
	
	private long id;
	
	private String nombre;
	
	private String documento;
	
	//-----------------------------
	
	public Huesped(String nombre, String documento) {
		this.setNombre(nombre);
		this.setDocumento(documento);
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
	

	public String getNombre() {
		return this.nombre;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
