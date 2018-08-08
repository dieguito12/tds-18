package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.business.*;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.HuespedDTO;
import org.tds.sgh.dtos.ReservaDTO;
import org.tds.sgh.infrastructure.Infrastructure;

public class TomarReservaController extends BaseController implements ITomarReservaController {
	
	public TomarReservaController(CadenaHotelera cadenaHotelera) {
		super(cadenaHotelera);
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		// TODO Auto-generated method stub
		return DTO.mapReservas(this.cadenaHotelera.buscarReservasDelCliente());
	}

	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
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
		return this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, true);
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
		Reserva reserva = this.cadenaHotelera.modificarReserva(nombreHotel, 
				nombreTipoHabitacion, 
				fechaInicio, 
				fechaFin, 
				modificablePorHuesped);
		int contador = 0;
		HuespedDTO[] _huespedDTO = new HuespedDTO[reserva.getHuespedes().size()];
		
		Map<String, Huesped> map = reserva.getHuespedes();
		
		for (Map.Entry<String, Huesped> entry : map.entrySet())
		{
			
			_huespedDTO[contador] = new HuespedDTO(entry.getKey(), entry.getValue().getNombre());
			contador++;
		}
		
		contador = 0;
		this.mailService.confirmarReserva(reserva);
		return DTO.map(reserva);
	}

	@Override
	public Set<ReservaDTO> buscarReservasPendientes(String nombreHotel) throws Exception {
		return DTO.mapReservas(this.cadenaHotelera.buscarReservasPendientes(nombreHotel));
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		return DTO.map(this.cadenaHotelera.seleccionarReserva(codigoReserva));
	}

	@Override
	public ReservaDTO registrarHuesped(String nombre, String documento) throws Exception {
		return DTO.map(this.cadenaHotelera.registrarHuesped(nombre, documento));
	}

	@Override
	public ReservaDTO tomarReserva() throws Exception {
		Reserva reserva = this.cadenaHotelera.tomarReserva();
		this.mailService.confirmarReserva(reserva);
		ReservaDTO reservaDto = DTO.map(reserva);
		Infrastructure.getInstance().getSistemaFacturacion().iniciarEstadia(reservaDto);
		return reservaDto;
	}

}
