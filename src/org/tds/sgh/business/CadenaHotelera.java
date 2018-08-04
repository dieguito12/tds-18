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
	
	public Map<String, Hotel> sugerirAlternativas(String pais, String nth, GregorianCalendar fi, GregorianCalendar ff)
	{
		TipoHabitacion th;
		Hotel h;
		Iterator<Hotel> iter;
		Map<String, Hotel> retornoHotel = new HashMap<String,Hotel>();
		if(this.tiposHabitacion.containsKey(nth))
		{
			th = this.tiposHabitacion.get(nth);
			iter = this.hoteles.values().iterator();
			
			while(iter.hasNext())
			{
				h = (Hotel)iter.next();
				if(h.entaEnElPais(pais))
				{
					if(!retornoHotel.containsKey(h.getNombre()))
					{
						retornoHotel.put(h.getNombre(), h);
					}
				}
			}
		}
		return retornoHotel;
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
	
	public Map<String, Reserva>  buscarReservasDelCliente(Cliente cliente)
	{
		Map<String, Reserva> retornoReserva = new HashMap<String,Reserva>();
		
		/*
		 * */
		
		return retornoReserva;
	}
	
	public Reserva modificarReserva(Reserva r, String nh,String nth,GregorianCalendar fi,GregorianCalendar ff, Boolean mph)
	{
		Hotel h;
		TipoHabitacion th;
		h = this.hoteles.get(nh);
		th = this.tiposHabitacion.get(nth);
		r.actualizar(h, th, fi, ff, mph);
		int code = r.getCodigo();
		this.reservas.remove(code);
		this.reservas.put(code, r);
		return r;
	}
	
	public Map<String, Reserva> buscarReservasPendientes(String nh)
	{
		Map<String, Reserva> retornoReservas = new HashMap<String,Reserva>();
		
		
		return retornoReservas;
	}
	
	public Reserva registrarHuesped(Reserva r, String nombre, String documento)
	{
		Reserva retornoReserva = new Reserva(null,null,null,null,null, null);
		
		/*
		 * */
		
		return retornoReserva;
	}
	
	public Reserva tomarReserva(Reserva r)
	{
		/*
		 * */
		return r;
	}
}
