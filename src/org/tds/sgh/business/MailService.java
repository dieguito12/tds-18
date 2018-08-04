package org.tds.sgh.business;

import java.util.GregorianCalendar;

public class MailService 
{	
	private SistemaMensajeria sistemaMensajeria;
	
	public MailService()
	{
		this.sistemaMensajeria = new SistemaMensajeria();
	}
	
	public void confirmarReserva(Reserva r) 
	{
		this.sistemaMensajeria.enviarMensaje( r.getMailCliente(), "Confirmacion Reserva", this.construirMensaje(r.getCodigo(),r.getFechaInicio(),r.getFechaFin()));
	}
	
	public String construirMensaje(int codigo, GregorianCalendar fi, GregorianCalendar ff)
	{
		String mensaje = "Estimado Cliente \nSe Ha realizado exisotamente la reserva numero " +
							codigo + " cuya fecha de check in es" + fi + "" + ff + 
							" .Gracias por la confianza en nosotros.";
		return mensaje;
	}
}
