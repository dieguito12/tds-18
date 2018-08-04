package org.tds.sgh.system;

import java.util.Set;

import org.tds.sgh.business.*;
import org.tds.sgh.dtos.ClienteDTO;

public class IdentificarClienteController extends BaseController implements IIdentificarClienteEnRecepcionController{

	private Cliente cliente;
	
	public IdentificarClienteController(CadenaHotelera cadenaHotelera, Cliente cliente) {
		super(cadenaHotelera);
		this.cliente = cliente;
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
	
}
