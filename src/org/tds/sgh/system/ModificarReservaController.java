package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.business.*;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class ModificarReservaController extends BaseController implements IModificarReservaController{

	private Cliente cliente;
	
	private Map<Integer, Reserva> reservas;
	
	private Reserva reserva;
	
	public ModificarReservaController(CadenaHotelera cadenaHotelera, Cliente cliente, Map<Integer, Reserva> reservas) {
		super(cadenaHotelera);
		this.cliente = cliente;
		this.reservas = reservas;
		this.reserva = null;
	}
	
	public void setReserva(Reserva r) 
	{
		this.reserva = r;
	}

	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		return this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO modificarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		this.cadenaHotelera.modificarReserva(this.reserva, nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, 
				modificablePorHuesped);
		
		return null;
	}

	@Override
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		return DTO.mapHoteles(this.cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin));
	}
	
	

}
