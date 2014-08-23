package mundo;

import java.awt.Image;
import java.io.Serializable;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import estructuras.ListaEncadenada;
import interfaces.IListaSimple;

/**
 * Clase que modela los lugares de las categorias
 * @author Jonathan
 */
public class Lugar implements Serializable
{
	//--------------------------------------------
	// Atributos
	//--------------------------------------------
	
	/**
	 * Nombre del lugar
	 */
	private String nombre;
	
	/**
	 * Categoria del lugar
	 */
	private String categoria;
	
	private String vecindad;
	
	/**
	 * Latitud del lugar (desde paralelo del ecuador)
	 */
	private double latitud;
	
	/**
	 * Longitud del lugar (desde meridiano central)
	 */
	private double longitud;
	
	private ImageIcon icon;
	
	private IListaSimple<Evento> eventos;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------
	
	public Lugar(String nombre, String categoria, String vecindad, double latitud, double longitud, URL icon)
	{
		this.nombre = nombre;
		this.categoria = categoria;
		this.latitud = latitud;
		this.longitud = longitud;
		this.icon = new ImageIcon(icon);
		this.vecindad = vecindad;
		Image img = this.icon.getImage();  
		Image newimg = img.getScaledInstance(21, 21,  java.awt.Image.SCALE_SMOOTH);  
		this.icon = new ImageIcon(newimg);  
		eventos = new ListaEncadenada<Evento>( );
	}

	
	//--------------------------------------------
	// Metodos
	//--------------------------------------------
	
	public void agregarEvento(Evento evento)
	{
		eventos.agregar(evento);
	}
	
	public double getLatitud( ) {
		return latitud;
	}
	
	public double getLongitud( ) {
		return longitud;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Object[] darEventos( )
	{
		return eventos.toArray();
	}
	
	public Icon getIcon()
	{
		return icon;
	}
	
	public String toString (){
		return nombre+"\n"+categoria+"\n"+vecindad;
	}
}
