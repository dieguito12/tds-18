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

		ReservaDTO _reserva = new ReservaDTO(
				reserva.getCodigo(),
				reserva.getRutCliente().toString(),
				reserva.getHotel().getNombre().toString(),
				reserva.getTipoHabitacion().getNombre().toString(),
				reserva.getFechaInicio(),
				reserva.getFechaFin(),
				reserva.getModificablePorHuesped(),
				reserva.getEstado(),
				reserva.getHabitacion().getNombre().toString(), 
				_huespedDTO);
				
		return _reserva;
	}

	@Override
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		return DTO.mapHoteles(this.cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin));
	}
	
	

}
