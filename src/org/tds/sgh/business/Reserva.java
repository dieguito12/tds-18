package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.tds.sgh.infrastructure.Infrastructure;

public class Reserva {
	
	private int codigo;
	
	private GregorianCalendar fechaInicio;
	
	private GregorianCalendar fechaFin;
	
	private Boolean modificablePorHuesped;
	
	private Habitacion habticacion;
	
	private TipoHabitacion tipoHabitacion;
	
	private Hotel hotel;
	
	private Cliente cliente;
	
	private Map<String, Huesped> huespedes;
	
	private EstadoReserva estado;
	
	public Reserva(Cliente cliente, TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, Boolean mph, Hotel h) {
		this.cliente = cliente;
		this.tipoHabitacion = th;
		this.fechaFin = ff;
		this.hotel = h;
		this.fechaInicio = fi;
		this.estado = EstadoReserva.Pendiente;
		this.modificablePorHuesped = mph;
		this.huespedes = new HashMap<String, Huesped>();
		this.codigo = Infrastructure.getInstance().getCalendario().getHoy().toInstant().getNano();
	}
	
	public Boolean verificarConflicto(TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff) {
		return true;
	}
	
	public GregorianCalendar getFechaFin() {
		return this.fechaFin;
	}
	
	public GregorianCalendar getFechaInicio() {
		return this.fechaInicio;
	}
	
	public String getMailCliente() {
		return this.cliente.getMail();
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getRutCliente() {
		if (this.cliente != null) {
			return this.cliente.getRut();
		}
		return null;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public boolean getModificablePorHuesped() {
		return this.modificablePorHuesped;
	}

	public Map<String, Huesped> getHuespedes() {
		return this.huespedes;
	}

	public String getEstado() {
		return this.estado.name();
	}

	public Habitacion getHabitacion() {
		return this.habticacion;
	}
	
	public void actualizar(Hotel h, TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, Boolean mph) {
		this.hotel = h;
		this.tipoHabitacion = th;
		this.fechaFin = ff;
		this.fechaInicio = fi;
		this.modificablePorHuesped = mph;
	}
	
	public Boolean estaPendiente() {
		return this.estado == EstadoReserva.Pendiente;
	}
	
	public void agregarHuesped(String n, String documento) {
		//Huesped h = new Huesped(n, documento);
		//this.huespedes.add(documento, h);
	}
	
	public Huesped getHuesped()
	{
		return null;
		
	}
	
	public void setHabitacion(Habitacion h) {
		this.habticacion = h;
	}
	
	public TipoHabitacion getTipoHabitacion() {
		return this.tipoHabitacion;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void tomar() {
		
	}
}
