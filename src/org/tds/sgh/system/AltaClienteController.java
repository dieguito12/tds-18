package org.tds.sgh.system;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.dtos.ClienteDTO;

public class AltaClienteController extends BaseController implements IAltaClienteController {

	public AltaClienteController(CadenaHotelera cadenaHotelera) {
		super(cadenaHotelera);
	}

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
