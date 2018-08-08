package org.tds.sgh.business;
import javax.persistence.*;

@Entity
public class Cliente
{
	// --------------------------------------------------------------------------------------------
	
	private long id;
	
	private String direccion;
	
	private String mail;
	
	private String nombre;
	
	private String rut;
	
	private String telefono;
	
	// --------------------------------------------------------------------------------------------
	
	public Cliente(String rut, String nombre, String direccion, String telefono, String mail)
	{
		this.setDireccion(direccion);
		
		this.setMail(mail);
		
		this.setNombre(nombre);
		
		this.setRut(rut);
		
		this.setTelefono(telefono);
	}
	
	// --------------------------------------------------------------------------------------------
	
	public boolean coincideElNombre(String patronNombreCliente)
	{
		return this.getNombre().matches(patronNombreCliente);
	}
	
	public boolean coincideNombre(String patronNombreCliente)
	{
		return this.coincideElNombre(patronNombreCliente);
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
	
	public String getDireccion()
	{
		return this.direccion;
	}
	
	public String getMail()
	{
		return this.mail;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getRut()
	{
		return this.rut;
	}
	
	public String getTelefono()
	{
		return this.telefono;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
