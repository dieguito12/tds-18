package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.*; 

@Entity
public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private long id;
	
	private Map<String, Habitacion> habitaciones;
	
	private String nombre;
	
	private String pais;
	
	private Map<Long, Reserva> reservas;
	
	// --------------------------------------------------------------------------------------------
	
	public Hotel(String nombre, String pais)
	{
		this.setHabitaciones(new HashMap<String, Habitacion>());
		
		this.setNombre(nombre);
		
		this.setPais(pais);
		
		this.setReservas(new HashMap<Long, Reserva>());
	}

	public Reserva registrarReserva(Cliente cliente, TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, boolean mph, long codigo) {
		Reserva r = new Reserva(cliente, th, fi, ff, mph, this, codigo);
		this.getReservas().put(r.getCodigo(), r);
		return r;
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
	
	public Habitacion agregarHabitacion(TipoHabitacion tipoHabitacion, String nombre) throws Exception
	{
		if (this.getHabitaciones().containsKey(nombre))
		{
			throw new Exception("El hotel ya tiene una habitación con el nombre indicado.");
		}
		
		Habitacion habitacion = new Habitacion(tipoHabitacion, nombre);
		
		this.getHabitaciones().put(habitacion.getNombre(), habitacion);
		
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
		return new HashSet<Habitacion>(this.getHabitaciones().values());
	}

	// --------------------------------------------------------------------------------------------
	// Implementacion Diagrama de Clases
	
	public boolean verificarDisponibilidad(TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, Reserva reservaSeleccionada)
	{
		Iterator<Reserva> reservas = this.getReservas().values().iterator();
		Iterator<Habitacion> habitaciones = this.getHabitaciones().values().iterator();
		
		int conflictos = 0;
		int capacidad = 0;
		
		while(reservas.hasNext()) {
			Reserva r = reservas.next();
			if (reservaSeleccionada == null) {
				if (r.verificarConflicto(th, fi, ff)) {
					conflictos++;
				}
			} else {
				if (r.verificarConflicto(th, fi, ff) && !r.equals(reservaSeleccionada)) {
					conflictos++;
				}
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
		return this.getPais().equals(pais);
	}
	
	public Set<Reserva> reservasPendientes()
	{
		Set<Reserva> reservas = new HashSet<Reserva>();
		
		for(Reserva r: this.getReservas().values()) {
			if (r.estaPendiente()) {
				reservas.add(r);
			}
		}
		
		return reservas;
		
	}
	
	public Habitacion buscarHabitacionLibre(TipoHabitacion th) 
	{		
		for(Habitacion hab: this.getHabitaciones().values()) {
			if (!hab.habitacionOcupada() && hab.habitacionValida(th)) {
				return hab;
			}
		}
		
		return null;
	}
	
	//Implementacion metodo para clase Cadena Hotelera
	
	public void deleteReserva(long codigo)
	{		
		this.getReservas().remove(codigo);
				
	}

	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="nombre")
	public Map<String, Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Map<String, Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="codigo")
	public Map<Long, Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Map<Long, Reserva> reservas) {
		this.reservas = reservas;
	}
		
}
