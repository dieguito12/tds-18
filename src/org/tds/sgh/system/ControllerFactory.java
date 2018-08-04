package org.tds.sgh.system;

import java.util.Map;

import org.tds.sgh.business.*;
import org.tds.sgh.infrastructure.NotImplementedException;


public class ControllerFactory implements IControllerFactory
{
	// --------------------------------------------------------------------------------------------
	
	private CadenaHotelera cadenaHotelera;
	
	private Cliente cliente;
	
	private Map<Integer, Reserva> reservas;
	
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
		// TODO
		throw new NotImplementedException();
	}
	
	@Override
	public IHacerReservaController createHacerReservaController()
	{
		return new HacerReservaController(this.cadenaHotelera, this.cliente, this.reservas);
	}
	
	@Override
	public IModificarReservaController createModificarReservaController()
	{
		// TODO
		throw new NotImplementedException();
	}
	
	@Override
	public ITomarReservaController createTomarReservaController()
	{
		// TODO
		throw new NotImplementedException();
	}
}
