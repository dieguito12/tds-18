package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.infrastructure.Infrastructure;



public class CadenaHotelera
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Cliente> clientes;
	
	private Cliente cliente;
	
	private Map<String, Hotel> hoteles;
	
	private String nombre;
	
	private Map<String, TipoHabitacion> tiposHabitacion;
	
	private Map<Long, Reserva> reservas;
	
	private Reserva reserva;
	
	// --------------------------------------------------------------------------------------------

	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public CadenaHotelera(String nombre)
	{
		this.clientes = new HashMap<String, Cliente>();
		
		this.nombre = nombre;
		
		this.hoteles = new HashMap<String, Hotel>();
		
		this.reservas = new HashMap<Long, Reserva>();
		
		this.tiposHabitacion = new HashMap<String, TipoHabitacion>();	
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public Map<String, Cliente> getClientes() {
		return this.clientes;
	}
	
	public Map<String, Hotel> getHoteles() {
		return this.hoteles;
	}
	
	public Map<Long, Reserva> getReservas() {
		return this.reservas;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Cliente agregarCliente(
		String rut,
		String nombre,
		String direccion,
		String telefono,
		String mail) throws Exception
	{
		if (this.clientes.containsKey(rut))
		{
			throw new Exception("Ya existe un cliente con el RUT indicado.");
		}
		
		Cliente cliente = new Cliente(rut, nombre, direccion, telefono, mail);
		
		this.clientes.put(cliente.getRut(), cliente);
		
		return cliente;
	}
	
	public Hotel agregarHotel(String nombre, String pais) throws Exception
	{
		if (this.hoteles.containsKey(nombre))
		{
			throw new Exception("Ya existe un hotel con el nombre indicado.");
		}
		
		Hotel hotel = new Hotel(nombre, pais);
		
		this.hoteles.put(hotel.getNombre(), hotel);
		
		return hotel;
	}
	
	public TipoHabitacion agregarTipoHabitacion(String nombre) throws Exception
	{
		if (this.tiposHabitacion.containsKey(nombre))
		{
			throw new Exception("Ya existe un tipo de habitaci�n con el nombre indicado.");
		}
		
		TipoHabitacion tipoHabitacion = new TipoHabitacion(nombre);
		
		this.tiposHabitacion.put(tipoHabitacion.getNombre(), tipoHabitacion);
		
		return tipoHabitacion;
	}
	
	public Cliente buscarCliente(String rut) throws Exception
	{
		Cliente cliente = this.clientes.get(rut);
		
		if (cliente == null)
		{
			throw new Exception("No existe un cliente con el nombre indicado.");
		}
		
		return cliente;
	}
	
	public Set<Cliente> buscarClientes(String patronNombreCliente)
	{
		Set<Cliente> clientesEncontrados = new HashSet<Cliente>();
		
		for (Cliente cliente : this.clientes.values())
		{
			if (cliente.coincideElNombre(patronNombreCliente))
			{
				clientesEncontrados.add(cliente);
			}
		}
		
		return clientesEncontrados;
	}
	
	public Hotel buscarHotel(String nombre) throws Exception
	{
		Hotel hotel = this.hoteles.get(nombre);
		
		if (hotel == null)
		{
			throw new Exception("No existe un hotel con el nombre indicado.");
		}
		
		return hotel;
	}
	
	public TipoHabitacion buscarTipoHabitacion(String nombre) throws Exception
	{
		TipoHabitacion tipoHabitacion = this.tiposHabitacion.get(nombre);
		
		if (tipoHabitacion == null)
		{
			throw new Exception("No existe un tipo de habitación con el nombre indicado.");
		}
		
		return tipoHabitacion;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public Set<Cliente> listarClientes()
	{
		return new HashSet<Cliente>(this.clientes.values());
	}
	
	public Set<Hotel> listarHoteles()
	{
		return new HashSet<Hotel>(this.hoteles.values());
	}
	
	public Set<TipoHabitacion> listarTiposHabitacion()
	{
		return new HashSet<TipoHabitacion>(this.tiposHabitacion.values());
	}
	
	public Cliente seleccionarCliente(String rut) 
	{
		this.cliente = this.clientes.get(rut);
		return this.cliente;
	}
	
	public Reserva seleccionarReserva(long codigo) 
	{
		this.reserva = this.reservas.get(codigo);
		return this.reserva;
	}
	
	public Cliente registrarCliente(String r, String n, String d, String t, String m) throws Exception
	{
		Cliente cliente = new Cliente(r,n,d,t,m);
		if(this.clientes.containsKey(r))
		{
			throw new Exception("El Cliente ya existe.");
		}
		this.clientes.put(r, cliente);
		this.seleccionarCliente(cliente.getRut());
		return cliente;
	}
	
	public Reserva cancelarReserva() {
		this.reserva.cancelar();
		return this.reserva;
	}
	
	public Boolean confirmarDisponibilidad(String nh, String nth, GregorianCalendar fi, GregorianCalendar ff)
	{
		Boolean disponible = false;
		Hotel h;
		TipoHabitacion th;
		if(this.hoteles.containsKey(nh)) 
		{
			h = this.hoteles.get(nh);
			th = this.tiposHabitacion.get(nth);
			disponible = h.verificarDisponibilidad(th, fi, ff);
		}
		return disponible;
	}
	
	public Set<Hotel> sugerirAlternativas(String pais, String nth, GregorianCalendar fi, GregorianCalendar ff)
	{
		TipoHabitacion th = new TipoHabitacion(nth);
		Set<Hotel> retornoHotel = new HashSet<Hotel>();
		if(this.tiposHabitacion.containsKey(nth))
		{
			th = this.tiposHabitacion.get(nth);
			for(Hotel h : this.hoteles.values())
			{
				if(h.entaEnElPais(pais))
				{
					if(!retornoHotel.contains(h))
					{
						retornoHotel.add(h);
					}
				}
			}
		}
		return retornoHotel;
	}
	
	private Set<Reserva> reservasEnCadena()
	{
		Set<Reserva> reservasCadenaHotelera = new HashSet<Reserva>();
		Set<Reserva> reservasCliente = new HashSet<Reserva>();
		for(Cliente c : this.clientes.values())
		{
			reservasCliente = this.buscarReservasDelCliente(c);
			for(Reserva r : reservasCliente)
			{
				reservasCadenaHotelera.add(r);
			}
		}
		return reservasCadenaHotelera;
	}

	
	public Reserva registrarReserva(String nh,String nth,GregorianCalendar fi,GregorianCalendar ff, boolean mph)
	{
		Hotel h;
		TipoHabitacion th;
		h = this.hoteles.get(nh);
		th = this.tiposHabitacion.get(nth);
		Reserva reserva = h.registrarReserva(this.cliente, th, fi, ff, mph);
		this.reservas.put(reserva.getCodigo(), reserva);
		return reserva;
	}
	
	public Set<Reserva>  buscarReservasDelCliente(Cliente cliente)
	{
		Set<Reserva> retornoReserva = new HashSet<Reserva>();
		if(cliente != null)
		{
			for(Reserva r : this.reservas.values())
			{
				if(cliente.getRut().equals(r.getRutCliente()))
				{
					retornoReserva.add(r);
				}
			}
		}
		
		return retornoReserva;
	}
	
	public Reserva modificarReserva(Reserva r, String nh,String nth,GregorianCalendar fi,GregorianCalendar ff, Boolean mph)
	{
		Hotel h;
		TipoHabitacion th;
		h = this.hoteles.get(nh);         //1.1
		if(h != null)
		{
			th = this.tiposHabitacion.get(nth);    //1.2
			if(th != null)
			{
				r.getHotel().deleteReserva(r.getCodigo());
				r.actualizar(h, th, fi, ff, mph);    //1.3
			}
		}
		r.getHotel().registrarReserva(r.getCliente(), r.getTipoHabitacion(), r.getFechaInicio(), r.getFechaFin(), r.getModificablePorHuesped());
		
		return r;
	}
	
	public Set<Reserva> buscarReservasPendientes(String nh)
	{
		Hotel h;
		Set<Reserva> retornoReservas = new HashSet<Reserva>();
		h = this.hoteles.get(nh);
		if(h != null)
		{
			retornoReservas.addAll(h.reservasPendientes());
		}
		
		return retornoReservas;
	}
	
	public Reserva registrarHuesped(Reserva r, String nombre, String documento)
	{
		r.agregarHuesped(nombre, documento);
		return r;
	}
	
	public Reserva tomarReserva(Reserva r)
	{
		r.tomar();
		return r;
	}
}
