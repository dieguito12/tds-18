package org.tds.sgh.business;

import java.util.GregorianCalendar;

import org.tds.sgh.infrastructure.ISistemaMensajeria;
import org.tds.sgh.infrastructure.Infrastructure;

public class MailService 
{	
	private ISistemaMensajeria sistemaMensajeria;
	
	public MailService()
	{
		this.sistemaMensajeria = Infrastructure.getInstance().getSistemaMensajeria();
	}
	
	public void confirmarReserva(Reserva r) 
	{
		this.sistemaMensajeria.enviarMail( r.getMailCliente(), "Confirmacion Reserva", this.construirMensaje(r.getCodigo(),r.getFechaInicio(),r.getFechaFin()));
	}
	
	public String construirMensaje(long codigo, GregorianCalendar fi, GregorianCalendar ff)
	{
		String mensaje = "Estimado Cliente \nSe Ha realizado exisotamente la reserva numero " +
							codigo + " cuya fecha de check in es" + fi + "" + ff + 
							" .Gracias por la confianza en nosotros.";
		return mensaje;
	}
}
