package org.tds.sgh.system;

import java.util.*;

import org.tds.sgh.business.*;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class CancelarReservaController extends BaseController implements ICancelarReservaController{

	public CancelarReservaController(CadenaHotelera cadenaHotelera) {
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
		return DTO.map(this.cadenaHotelera.buscarCliente(rut));
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		try {
			Set<Reserva> reservas = this.cadenaHotelera.buscarReservasDelCliente(this.cadenaHotelera.getCliente());
			return DTO.mapReservas(reservas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		return DTO.map(this.cadenaHotelera.seleccionarReserva(codigoReserva));
	}

	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		// return DTO.map(this.cadenaHotelera);
		return null;
	}

}
