package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.business.*;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.HuespedDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class ModificarReservaController extends BaseController implements IModificarReservaController{
	
	public ModificarReservaController(CadenaHotelera cadenaHotelera) {
		super(cadenaHotelera);
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
		return DTO.map(this.cadenaHotelera.seleccionarCliente(rut));
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		return DTO.mapReservas(this.cadenaHotelera.buscarReservasDelCliente());
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		return DTO.map(this.cadenaHotelera.seleccionarReserva(codigoReserva));
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		return this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
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
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		return DTO.mapHoteles(this.cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin));
	}
	
	

}
