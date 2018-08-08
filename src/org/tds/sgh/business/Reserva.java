package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity
public class Reserva {
	
	private long id;
	
	private long codigo;
	
	private GregorianCalendar fechaInicio;
	
	private GregorianCalendar fechaFin;
	
	private boolean modificablePorHuesped;
	
	private Habitacion habitacion;
	
	private TipoHabitacion tipoHabitacion;
	
	private Hotel hotel;
	
	private Cliente cliente;
	
	private Map<String, Huesped> huespedes;
	
	private EstadoReserva estado;
	
	public Reserva(Cliente cliente, TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, Boolean mph, Hotel h, long codigo) {
		this.setCliente(cliente);
		this.setTipoHabitacion(th);
		this.setFechaFin(ff);
		this.setHotel(h);
		this.setFechaInicio(fi);
		this.estado = EstadoReserva.Pendiente;
		this.setModificablePorHuesped(mph);
		this.setHuespedes(new HashMap<String, Huesped>());
		this.setCodigo(codigo);
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
	
	public Boolean verificarConflicto(TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff) {
		boolean solapado = false;
		
		if (fi.after(this.getFechaInicio()) && fi.before(this.getFechaFin()) ||
				ff.after(this.getFechaInicio()) && ff.before(this.getFechaFin()) ||
				fi.after(this.getFechaInicio()) && ff.before(this.getFechaFin()) ||
				fi.before(this.getFechaInicio()) && ff.after(this.getFechaFin()) ||
				fi.equals(this.getFechaInicio()) || ff.equals(this.getFechaFin())){
			solapado = true;
		}
		return this.getTipoHabitacion().getNombre() == th.getNombre() && solapado;
	}
	
	public GregorianCalendar getFechaFin() {
		return this.fechaFin;
	}
	
	public GregorianCalendar getFechaInicio() {
		return this.fechaInicio;
	}
	
	public String obtenerMailCliente() {
		return this.getCliente().getMail();
	}
	
	public long getCodigo() {
		return this.codigo;
	}
	
	public String obtenerRutCliente() {
		if (this.getCliente() != null) {
			return this.getCliente().getRut();
		}
		return null;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public Hotel getHotel() {
		return this.hotel;
	}

	public boolean getModificablePorHuesped() {
		return this.modificablePorHuesped;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="documento")
	public Map<String, Huesped> getHuespedes() {
		return this.huespedes;
	}

	public String getEstado() {
		return this.estado.name();
	}
	
	public void setEstado(String estado) {
		this.estado = EstadoReserva.valueOf(estado);
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Habitacion getHabitacion() {
		return this.habitacion;
	}
	
	public void actualizar(Hotel h, TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, Boolean mph) throws Exception {
		if (!this.getModificablePorHuesped()) {
			throw new Exception("La reserva no es modificable");
		}
		this.setHotel(h);
		this.setTipoHabitacion(th);
		this.setFechaFin(ff);
		this.setFechaInicio(fi);
		this.setModificablePorHuesped(mph);
	}
	
	public Boolean estaPendiente() {
		return this.estado == EstadoReserva.Pendiente;
	}
	
	public void agregarHuesped(String n, String documento) {
		Huesped h = new Huesped(n, documento);
		this.getHuespedes().put(documento, h);
	}
	
	public void setHabitacion(Habitacion h) {
		this.setHabticacion(h);
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public TipoHabitacion getTipoHabitacion() {
		return this.tipoHabitacion;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void tomar() {
		this.estado = EstadoReserva.Tomada;
		Habitacion hab = this.getHotel().buscarHabitacionLibre(this.getTipoHabitacion());
		hab.setOcupada(true);
		this.setHabitacion(hab);
	}
	
	public void cancelar() {
		this.estado = EstadoReserva.Cancelada;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public void setFechaInicio(GregorianCalendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(GregorianCalendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setModificablePorHuesped(boolean modificablePorHuesped) {
		this.modificablePorHuesped = modificablePorHuesped;
	}

	public void setHabticacion(Habitacion habticacion) {
		this.habitacion = habticacion;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setHuespedes(Map<String, Huesped> huespedes) {
		this.huespedes = huespedes;
	}
}
