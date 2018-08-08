package org.tds.sgh.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Huesped;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.HuespedDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class IdentificarReservaController extends BaseController implements IIdentificarReservaClienteController{

	private Cliente cliente;
	
	private Map<Integer, Reserva> reservas;
	
	public IdentificarReservaController(CadenaHotelera cadenaHotelera, Cliente cliente, Map<Integer, Reserva> reservas) {
		super(cadenaHotelera);
		this.cliente = cliente;
		this.reservas = reservas;
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
			
		Reserva reserva = this.cadenaHotelera.seleccionarReserva(codigoReserva);
		
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
				reserva.obtenerRutCliente().toString(),
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
		
}
