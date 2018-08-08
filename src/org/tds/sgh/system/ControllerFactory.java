package org.tds.sgh.system;

import java.util.*;

import org.tds.sgh.business.*;
import org.tds.sgh.infrastructure.NotImplementedException;


public class ControllerFactory implements IControllerFactory
{
	// --------------------------------------------------------------------------------------------
	
	private CadenaHotelera cadenaHotelera;
	
	// --------------------------------------------------------------------------------------------
	
	public ControllerFactory(CadenaHotelera cadenaHotelera)
	{
		this.cadenaHotelera = cadenaHotelera;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public ICadenaController createCadenaController()
	{
		return new CadenaController(this.cadenaHotelera);
	}
	
	@Override
	public ICancelarReservaController createCancelarReservaController()
	{
//		this.cadenaHotelera.setCliente(null);
		return new CancelarReservaController(this.cadenaHotelera);
	}
	
	@Override
	public IHacerReservaController createHacerReservaController()
	{
//		this.cadenaHotelera.setCliente(null);
		return new HacerReservaController(this.cadenaHotelera);
	}
	
	@Override
	public IModificarReservaController createModificarReservaController()
	{
		this.cadenaHotelera.setCliente(null);
		return new ModificarReservaController(this.cadenaHotelera);
	}
	
	@Override
	public ITomarReservaController createTomarReservaController()
	{
//		this.cadenaHotelera.setCliente(null);
		return new TomarReservaController(this.cadenaHotelera);
	}
}
