package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class HacerReservaController extends BaseController implements IHacerReservaController{

	private Cliente cliente;
	
	private Map<Integer, Reserva> reservas;
	
	public HacerReservaController(CadenaHotelera cadenaHotelera, Cliente cliente, Map<Integer, Reserva> reservas) {
		super(cadenaHotelera);
		this.cliente = cliente;
		this.reservas = reservas;
	}

	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
		try {
			return DTO.mapClientes(this.cadenaHotelera.buscarClientes(patronNombreCliente));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {
		this.cliente = this.cadenaHotelera.buscarCliente(rut);
		return DTO.map(this.cliente);
	}

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		return this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO registrarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		Reserva reserva = this.cadenaHotelera.registrarReserva(this.cliente, nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);
		this.mailService.confirmarReserva(reserva);
		return DTO.map(reserva);
	}

	@Override
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
