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
	
	public void setCliente(Cliente c) {
		this.cliente = c;
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
		if (patronNombreCliente == null) {
			throw new NullPointerException();
		}
		
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
	
	public Reserva seleccionarReserva(long codigo) throws Exception 
	{
		this.reserva = this.reservas.get(codigo);
		if (!this.reserva.getRutCliente().equals(this.cliente.getRut())) {
			this.reserva = null;
			throw new Exception("Reserva no pertenece a cliente seleccionado");
		}
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
	
	public Boolean confirmarDisponibilidad(String nh, String nth, GregorianCalendar fi, GregorianCalendar ff, boolean tomarReserva) throws Exception
	{
		Boolean disponible = false;
		Hotel h;
		TipoHabitacion th;
		if (fi.before(Infrastructure.getInstance().getCalendario().getHoy()) || fi.after(ff)) {
			throw new Exception("Fecha de inicio invalida");
		}
		if(this.hoteles.containsKey(nh)) 
		{
			h = this.hoteles.get(nh);
			th = this.tiposHabitacion.get(nth);
			if (th == null) {
				throw new Exception("No existe el tipo de habitacion solicitado");
			}
			if (tomarReserva) {
				disponible = h.verificarDisponibilidad(th, fi, ff, this.reserva);
			} else {
				disponible = h.verificarDisponibilidad(th, fi, ff, null);
			}
			
		} else {
			throw new Exception("No existe el hotel solicitado");
		}
		return disponible;
	}
	
	public Set<Hotel> sugerirAlternativas(String pais, String nth, GregorianCalendar fi, GregorianCalendar ff) throws Exception
	{
		if (fi.before(Infrastructure.getInstance().getCalendario().getHoy()) || fi.after(ff)) {
			throw new Exception("Fecha de inicio invalida");
		}
		TipoHabitacion th = new TipoHabitacion(nth);
		Set<Hotel> retornoHotel = new HashSet<Hotel>();
		if(this.tiposHabitacion.containsKey(nth))
		{
			th = this.tiposHabitacion.get(nth);
			for(Hotel h : this.hoteles.values())
			{
				if(h.entaEnElPais(pais))
				{
					if (h.verificarDisponibilidad(th, fi, ff, null)) {
						retornoHotel.add(h);
					}
				}
			}
		} else {
			throw new Exception("No existe el tipo de habitacion solicitado");
		}
		return retornoHotel;
	}
	
	public Reserva registrarReserva(String nh,String nth,GregorianCalendar fi,GregorianCalendar ff, boolean mph)
	{
		Hotel h;
		TipoHabitacion th;
		h = this.hoteles.get(nh);
		th = this.tiposHabitacion.get(nth);
		Reserva reserva = h.registrarReserva(this.cliente, th, fi, ff, mph, this.getProximoCodigoReserva());
		this.reservas.put(reserva.getCodigo(), reserva);
		this.reserva = reserva;
		return reserva;
	}
	
	public long getProximoCodigoReserva() {
		return this.reservas.size() + 1;
	}
	
	public Set<Reserva>  buscarReservasDelCliente() throws Exception
	{
		Set<Reserva> retornoReserva = new HashSet<Reserva>();
		if(this.cliente != null)
		{
			for(Reserva r : this.reservas.values())
			{
				if(this.cliente.getRut().equals(r.getRutCliente()) && r.getEstado().equals(EstadoReserva.Pendiente.toString()) && r.getFechaFin().after(Infrastructure.getInstance().getCalendario().getHoy()))
				{
					retornoReserva.add(r);
				}
			}
		} else {
			throw new Exception("Cliente no seleccionado");
		}
		
		return retornoReserva;
	}
	
	public Reserva modificarReserva(String nh,String nth,GregorianCalendar fi,GregorianCalendar ff, Boolean mph) throws Exception
	{
		Hotel h;
		Hotel viejoH;
		TipoHabitacion th;
		h = this.hoteles.get(nh);         //1.1
		if(h != null)
		{
			th = this.tiposHabitacion.get(nth);    //1.2
			if(th != null)
			{
				viejoH = this.hoteles.get(this.reserva.getHotel().getNombre());
				viejoH.deleteReserva(this.reserva.getCodigo());
				this.reserva.actualizar(h, th, fi, ff, mph);    //1.3
				h.registrarReserva(this.cliente, this.reserva.getTipoHabitacion(), this.reserva.getFechaInicio(), this.reserva.getFechaFin(), this.reserva.getModificablePorHuesped(), this.reserva.getCodigo());
			}
		}
		
		
		return this.reserva;
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
	
	public Reserva registrarHuesped(String nombre, String documento)
	{
		this.reserva.agregarHuesped(nombre, documento);
		return this.reserva;
	}
	
	public Reserva tomarReserva()
	{
		this.reserva.tomar();
		return this.reserva;
	}
}
