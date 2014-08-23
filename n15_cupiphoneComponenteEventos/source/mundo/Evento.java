package mundo;

import interfaces.IListaSimple;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import estructuras.ListaEncadenada;

/**
 * Clase que modela los eventos del cupiEvento
 */
public class Evento implements Comparable<Evento>, Serializable
{
	private static final int INVITACION = 1;
	private static final int CANCELACION = -1;

	private String nombre;
	private Lugar lugar;
	private String hora;

	private boolean activo;

	private IListaSimple<String> invitados;

	public Evento(String nombre, Lugar lugar, String hora)
	{
		this.nombre = nombre;
		this.lugar = lugar;
		this.hora = hora;
		this.lugar.agregarEvento(this);
		activo = true;
		invitados = new ListaEncadenada<String>( );
	}

	public String darNombre()
	{
		return nombre;
	}

	public Lugar darLugar( )
	{
		return lugar;
	}

	public String darHora( )
	{
		return hora;
	}

	public Object[] darInvitados()
	{
		return invitados.toArray();
	}

	public void invitar(String[] correos)
	{
		for(String correo: correos)
		{
			if(!correo.trim().equals("")&& correo.split("@").length==2)
			{
				invitados.agregar(correo.trim());
				enviarCorreo(correo.trim(), INVITACION);
			}
			else
				continue;
		}
	}

	public void cancelar( )
	{
		activo = false;
		for(String correo: invitados)
		{
			enviarCorreo(correo.trim(), CANCELACION);
		}
	}

	private void enviarCorreo(String correo, int tipo)
	{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = tipo==INVITACION?"Se le infoma que ha sido invitado a un evento, "+nombre+". Este se llevará a cabo en "+lugar.getNombre()+" durante "+hora:
			"Se le informa que el evento "+nombre+" ha sido cancelado. Este pensaba llevarse a cabo en "+lugar.getNombre();

		Message msg = new MimeMessage(session);
		try
		{
			msg.setFrom(new InternetAddress("Aplicacion cupiIphone", "CupiEventos"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(correo, "Invitado"));
			msg.setSubject(tipo==INVITACION?"Ha sido invitado a un nuevo evento":"El evento "+nombre+" ha sido cancelado");
			msg.setText(msgBody);
			Transport.send(msg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void reanudar( )
	{
		activo = true;
	}

	public boolean esEnLugar(String nombreLugar)
	{
		return lugar.getNombre().compareToIgnoreCase(nombreLugar)==0;
	}

	public String toString(){

		return "Nombre: "+nombre+(activo?"":" (cancelado)")+"\nLugar: "+lugar.getNombre()+"\nHora: "+hora+"\n";
	}

	@Override
	public int compareTo(Evento evento)
	{
		return new Integer(invitados.tamano()).compareTo(new Integer(evento.invitados.tamano()));
	}
}