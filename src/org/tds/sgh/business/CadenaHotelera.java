package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.infrastructure.Infrastructure;


import javax.persistence.*;

@Entity
public class CadenaHotelera
{
	// --------------------------------------------------------------------------------------------
	
	private long id;
	
	private Map<String, Cliente> clientes;
	
	private Cliente cliente;
	
	private Map<String, Hotel> hoteles;
	
	private String nombre;
	
	private Map<String, TipoHabitacion> tiposHabitacion;
	
	private Map<Long, Reserva> reservas;
	
	private Reserva reserva;
	
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
	
	@OneToOne(cascade=CascadeType.ALL)
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Cliente c) {
		this.cliente = c;
	}
	
	public CadenaHotelera(String nombre)
	{
		this.setClientes(new HashMap<String, Cliente>());
		
		this.setNombre(nombre);
		
		this.setHoteles(new HashMap<String, Hotel>());
		
		this.setReservas(new HashMap<Long, Reserva>());
		
		this.setTiposHabitacion(new HashMap<String, TipoHabitacion>());	
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="rut")
	public Map<String, Cliente> getClientes() {
		return this.clientes;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="nombre")
	public Map<String, Hotel> getHoteles() {
		return this.hoteles;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="codigo")
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
		if (this.getClientes().containsKey(rut))
		{
			throw new Exception("Ya existe un cliente con el RUT indicado.");
		}
		
		Cliente cliente = new Cliente(rut, nombre, direccion, telefono, mail);
		
		this.getClientes().put(cliente.getRut(), cliente);
		
		return cliente;
	}
	
	public Hotel agregarHotel(String nombre, String pais) throws Exception
	{
		if (this.getHoteles().containsKey(nombre))
		{
			throw new Exception("Ya existe un hotel con el nombre indicado.");
		}
		
		Hotel hotel = new Hotel(nombre, pais);
		
		this.getHoteles().put(hotel.getNombre(), hotel);
		
		return hotel;
	}
	
	public TipoHabitacion agregarTipoHabitacion(String nombre) throws Exception
	{
		if (this.getTiposHabitacion().containsKey(nombre))
		{
			throw new Exception("Ya existe un tipo de habitaci�n con el nombre indicado.");
		}
		
		TipoHabitacion tipoHabitacion = new TipoHabitacion(nombre);
		
		this.getTiposHabitacion().put(tipoHabitacion.getNombre(), tipoHabitacion);
		
		return tipoHabitacion;
	}
	
	public Cliente buscarCliente(String rut) throws Exception
	{
		Cliente cliente = this.getClientes().get(rut);
		
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
		
		for (Cliente cliente : this.getClientes().values())
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
		Hotel hotel = this.getHoteles().get(nombre);
		
		if (hotel == null)
		{
			throw new Exception("No existe un hotel con el nombre indicado.");
		}
		
		return hotel;
	}
	
	public TipoHabitacion buscarTipoHabitacion(String nombre) throws Exception
	{
		TipoHabitacion tipoHabitacion = this.getTiposHabitacion().get(nombre);
		
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
		return new HashSet<Cliente>(this.getClientes().values());
	}
	
	public Set<Hotel> listarHoteles()
	{
		return new HashSet<Hotel>(this.getHoteles().values());
	}
	
	public Set<TipoHabitacion> listarTiposHabitacion()
	{
		return new HashSet<TipoHabitacion>(this.getTiposHabitacion().values());
	}
	
	public Cliente seleccionarCliente(String rut) 
	{
		this.setCliente(this.getClientes().get(rut));
		return this.getCliente();
	}
	
	public Reserva seleccionarReserva(long codigo) throws Exception 
	{
		this.setReserva(this.getReservas().get(codigo));
		if (!this.getReserva().obtenerRutCliente().equals(this.getCliente().getRut())) {
			this.setReserva(null);
			throw new Exception("Reserva no pertenece a cliente seleccionado");
		}
		return this.getReserva();
	}
	
	public Cliente registrarCliente(String r, String n, String d, String t, String m) throws Exception
	{
		Cliente cliente = new Cliente(r,n,d,t,m);
		if(this.getClientes().containsKey(r))
		{
			throw new Exception("El Cliente ya existe.");
		}
		this.getClientes().put(r, cliente);
		this.seleccionarCliente(cliente.getRut());
		return cliente;
	}
	
	public Reserva cancelarReserva() {
		this.getReserva().cancelar();
		return this.getReserva();
	}
	
	public Boolean confirmarDisponibilidad(String nh, String nth, GregorianCalendar fi, GregorianCalendar ff, boolean tomarReserva) throws Exception
	{
		Boolean disponible = false;
		Hotel h;
		TipoHabitacion th;
		if (fi.before(Infrastructure.getInstance().getCalendario().getHoy()) || fi.after(ff)) {
			throw new Exception("Fecha de inicio invalida");
		}
		if(this.getHoteles().containsKey(nh)) 
		{
			h = this.getHoteles().get(nh);
			th = this.getTiposHabitacion().get(nth);
			if (th == null) {
				throw new Exception("No existe el tipo de habitacion solicitado");
			}
			if (tomarReserva) {
				disponible = h.verificarDisponibilidad(th, fi, ff, this.getReserva());
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
		if(this.getTiposHabitacion().containsKey(nth))
		{
			th = this.getTiposHabitacion().get(nth);
			for(Hotel h : this.getHoteles().values())
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
		h = this.getHoteles().get(nh);
		th = this.getTiposHabitacion().get(nth);
		Reserva reserva = h.registrarReserva(this.getCliente(), th, fi, ff, mph, this.obtenerProximoCodigoReserva());
		this.getReservas().put(reserva.getCodigo(), reserva);
		this.setReserva(reserva);
		return reserva;
	}
	
	public long obtenerProximoCodigoReserva() {
		return this.getReservas().size() + 1;
	}
	
	public Set<Reserva>  buscarReservasDelCliente() throws Exception
	{
		Set<Reserva> retornoReserva = new HashSet<Reserva>();
		if(this.getCliente() != null)
		{
			for(Reserva r : this.getReservas().values())
			{
				if(this.getCliente().getRut().equals(r.obtenerRutCliente()) && r.getEstado().equals(EstadoReserva.Pendiente.toString()) && r.getFechaFin().after(Infrastructure.getInstance().getCalendario().getHoy()))
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
		h = this.getHoteles().get(nh);         //1.1
		if(h != null)
		{
			th = this.getTiposHabitacion().get(nth);    //1.2
			if(th != null)
			{
				viejoH = this.getHoteles().get(this.getReserva().getHotel().getNombre());
				viejoH.deleteReserva(this.getReserva().getCodigo());
				this.getReserva().actualizar(h, th, fi, ff, mph);    //1.3
				h.registrarReserva(this.getCliente(), this.getReserva().getTipoHabitacion(), this.getReserva().getFechaInicio(), this.getReserva().getFechaFin(), this.getReserva().getModificablePorHuesped(), this.getReserva().getCodigo());
			}
		}
		
		
		return this.getReserva();
	}
	
	public Set<Reserva> buscarReservasPendientes(String nh)
	{
		Hotel h;
		Set<Reserva> retornoReservas = new HashSet<Reserva>();
		h = this.getHoteles().get(nh);
		if(h != null)
		{
			retornoReservas.addAll(h.reservasPendientes());
		}
		
		return retornoReservas;
	}
	
	public Reserva registrarHuesped(String nombre, String documento)
	{
		this.getReserva().agregarHuesped(nombre, documento);
		return this.getReserva();
	}
	
	public Reserva tomarReserva()
	{
		this.getReserva().tomar();
		return this.getReserva();
	}

	public void setClientes(Map<String, Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setHoteles(Map<String, Hotel> hoteles) {
		this.hoteles = hoteles;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="nombre")
	public Map<String, TipoHabitacion> getTiposHabitacion() {
		return tiposHabitacion;
	}

	public void setTiposHabitacion(Map<String, TipoHabitacion> tiposHabitacion) {
		this.tiposHabitacion = tiposHabitacion;
	}

	public void setReservas(Map<Long, Reserva> reservas) {
		this.reservas = reservas;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Reserva getReserva() {
		return reserva;
	}
}
