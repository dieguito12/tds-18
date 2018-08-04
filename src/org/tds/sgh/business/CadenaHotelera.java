package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class CadenaHotelera
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Cliente> clientes;
	
	private Map<String, Hotel> hoteles;
	
	private String nombre;
	
	private Map<String, TipoHabitacion> tiposHabitacion;
	
	private Map<Integer, Reserva> reservas;
	
	// --------------------------------------------------------------------------------------------
	
	public CadenaHotelera(String nombre)
	{
		this.clientes = new HashMap<String, Cliente>();
		
		this.hoteles = new HashMap<String, Hotel>();
		
		this.nombre = nombre;
		
		this.tiposHabitacion = new HashMap<String, TipoHabitacion>();
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
		return this.clientes.get(rut);
	}
	
	public Cliente registrarCliente(String r, String n, String d, String t, String m) throws Exception
	{
		Cliente cliente = new Cliente(r,n,d,t,m);
		if(this.clientes.containsKey(r))
		{
			throw new Exception("El Cliente ya existe.");
		}
		this.clientes.put(r, cliente);
		return cliente;
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

	
	public Reserva registrarReserva(Cliente cliente, String nh,String nth,GregorianCalendar fi,GregorianCalendar ff, boolean mph)
	{
		Hotel h;
		TipoHabitacion th;
		h = this.hoteles.get(nh);
		th = this.tiposHabitacion.get(nth);
		Reserva reserva = h.registrarReserva(cliente, th, fi, ff, mph);
		return reserva;
	}
	
	public Set<Reserva>  buscarReservasDelCliente(Cliente cliente)
	{
		Set<Reserva> retornoReserva = new HashSet<Reserva>();
		if(cliente != null)
		{
			for(Reserva r : this.reservasEnCadena())
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
		h = this.hoteles.get(nh);
		if(h != null)
		{
			th = this.tiposHabitacion.get(nth);
			if(th != null)
			{
				r.actualizar(h, th, fi, ff, mph);
			}
		}
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
