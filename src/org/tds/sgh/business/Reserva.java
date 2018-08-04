package org.tds.sgh.business;

import java.util.GregorianCalendar;

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
	
//	private Map<String, Huesped> huespedes;
	
	private EstadoReserva estado;
	
	public Reserva(Cliente cliente, TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff, Boolean mph) {
		this.cliente = cliente;
		this.tipoHabitacion = th;
		this.fechaFin = ff;
		this.fechaInicio = fi;
		this.modificablePorHuesped = mph;
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
		return this.cliente.getRut();
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
//		Huesped h = new Huesped(n, documento);
//		this.huespedes.add(documento, h);
	}
	
	public void setHabitacion(Habitacion h) {
		this.habticacion = h;
	}
	
	public TipoHabitacion getTipoHabitacion() {
		return this.tipoHabitacion;
	}
	
	public void tomar() {
		
	}
}
