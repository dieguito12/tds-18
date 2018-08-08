package org.tds.sgh.dtos;

import java.util.*;

import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Habitacion;
import org.tds.sgh.business.Huesped;
import org.tds.sgh.business.Hotel;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.business.TipoHabitacion;


public class DTO
{
	// --------------------------------------------------------------------------------------------
	
	private static final DTO Instance = new DTO();
	
	// --------------------------------------------------------------------------------------------
	
	public static DTO getInstance()
	{
		return Instance;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private DTO()
	{
	}
	
	// --------------------------------------------------------------------------------------------
	
	public ClienteDTO map(Cliente cliente)
	{
		return new ClienteDTO(
			cliente.getRut(),
			cliente.getNombre(),
			cliente.getDireccion(),
			cliente.getTelefono(),
			cliente.getMail());
	}
	
	public HotelDTO map(Hotel hotel)
	{
		return new HotelDTO(hotel.getNombre(), hotel.getPais());
	}

	public ReservaDTO map(Reserva reserva) {
		int size = reserva.getHuespedes().size();
		HuespedDTO[] huespedes = new HuespedDTO[size];
		int i = 0;
		for(Huesped h: reserva.getHuespedes().values()) {
			huespedes[i] = new HuespedDTO(h.getNombre(), h.getDocumento());
			i++;
		}
		return new ReservaDTO(
			reserva.getCodigo(), 
			reserva.obtenerRutCliente(), 
			reserva.getHotel().getNombre(), 
			reserva.getTipoHabitacion().getNombre(),
			reserva.getFechaInicio(),
			reserva.getFechaFin(),
			reserva.getModificablePorHuesped(),
			reserva.getEstado(),
			reserva.getHabitacion() != null ? reserva.getHabitacion().getNombre() : null,
			huespedes);
	}
	
	public HabitacionDTO map(Hotel hotel, Habitacion habitacion)
	{
		return new HabitacionDTO(hotel.getNombre(), habitacion.getTipoHabitacion().getNombre(), habitacion.getNombre());
	}
	
	public TipoHabitacionDTO map(TipoHabitacion tipoHabitation)
	{
		return new TipoHabitacionDTO(tipoHabitation.getNombre());
	}
	
	public Set<ReservaDTO> mapReservas(Set<Reserva> reservas)
	{
		Set<ReservaDTO> reservasDTO = new HashSet<ReservaDTO>();
		
		for (Reserva reserva : reservas)
		{
			reservasDTO.add(this.map(reserva));
		}
		
		return reservasDTO;
	}
	
	public Set<ClienteDTO> mapClientes(Set<Cliente> clientes)
	{
		Set<ClienteDTO> clientesDTO = new HashSet<ClienteDTO>();
		
		for (Cliente cliente : clientes)
		{
			clientesDTO.add(this.map(cliente));
		}
		
		return clientesDTO;
	}
	
	public Set<HabitacionDTO> mapHabitaciones(Hotel hotel, Set<Habitacion> habitaciones)
	{
		Set<HabitacionDTO> habitacionesDTO = new HashSet<HabitacionDTO>();
		
		for (Habitacion habitacion : habitaciones)
		{
			habitacionesDTO.add(this.map(hotel, habitacion));
		}
		
		return habitacionesDTO;
	}
	
	public Set<HotelDTO> mapHoteles(Set<Hotel> hoteles)
	{
		Set<HotelDTO> hotelesDTO = new HashSet<HotelDTO>();
		
		for (Hotel hotel : hoteles)
		{
			hotelesDTO.add(this.map(hotel));
		}
		
		return hotelesDTO;
	}
	
	public Set<TipoHabitacionDTO> mapTiposHabitacion(Set<TipoHabitacion> tiposHabitacion)
	{
		Set<TipoHabitacionDTO> tiposHabitacionDTO = new HashSet<TipoHabitacionDTO>();
		
		for (TipoHabitacion tipoHabitacion : tiposHabitacion)
		{
			tiposHabitacionDTO.add(this.map(tipoHabitacion));
		}
		
		return tiposHabitacionDTO;
	}
}
