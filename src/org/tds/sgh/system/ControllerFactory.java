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
		return new CancelarReservaController(this.cadenaHotelera);
	}
	
	@Override
	public IHacerReservaController createHacerReservaController()
	{
		return new HacerReservaController(this.cadenaHotelera);
	}
	
	@Override
	public IModificarReservaController createModificarReservaController()
	{
		return new ModificarReservaController(this.cadenaHotelera);
	}
	
	@Override
	public ITomarReservaController createTomarReservaController()
	{
		return new TomarReservaController(this.cadenaHotelera);
	}
}
