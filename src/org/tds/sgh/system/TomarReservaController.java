package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.business.*;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class TomarReservaController extends BaseController implements ITomarReservaController {
	
	public TomarReservaController(CadenaHotelera cadenaHotelera) {
		super(cadenaHotelera);
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		// TODO Auto-generated method stub
		//return this.cadenaHotelera.buscarReservasDelCliente(this.cliente);
		return null;
	}

	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
		// TODO Auto-generated method stub
		return DTO.mapClientes(this.cadenaHotelera.buscarClientes(patronNombreCliente));
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {
		// TODO Auto-generated method stub
		return DTO.map(this.cadenaHotelera.seleccionarCliente(rut));
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		return this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO registrarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		// TODO Auto-generated method stub
		Reserva reserva = this.cadenaHotelera.registrarReserva(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);
		this.mailService.confirmarReserva(reserva);
		return DTO.map(reserva);
	}

	@Override
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO modificarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ReservaDTO> buscarReservasPendientes(String nombreHotel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO registrarHuesped(String nombre, String documento) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO tomarReserva() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
