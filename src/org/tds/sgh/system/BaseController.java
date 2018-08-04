package org.tds.sgh.system;

import org.tds.sgh.business.*;
import org.tds.sgh.dtos.DTO;

public class BaseController {
	
	protected final DTO DTO = org.tds.sgh.dtos.DTO.getInstance();
	
	protected MailService mailService = new MailService();
	
	// --------------------------------------------------------------------------------------------
	
	protected CadenaHotelera cadenaHotelera;
	
	// --------------------------------------------------------------------------------------------
	
	public BaseController(CadenaHotelera cadenaHotelera)
	{
		this.cadenaHotelera = cadenaHotelera;
	}
}
