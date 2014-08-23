package mundo;

import interfaces.IListaSimple;
import iteradores.Iterador;
import iteradores.IteradorLista;

import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import estructuras.ArbolAVL;
import estructuras.Arreglo;
import estructuras.ListaEncadenada;
import uniandes.cupi2.cupIphone.core.ICore;

/**
 * Clase principal de la aplicación de cupiEventos
 * @author Jonathan
 */
public class ComponenteEventos extends Observable{

	//--------------------------------------------
	// Atributos
	//--------------------------------------------

	
	private static final String ARCHIVO_DATOS1 = "datos.eventos1";
	private static final String ARCHIVO_DATOS2 = "datos.eventos2";
	private static final String ARCHIVO_DATOS3 = "datos.eventos3";
	private static final String ARCHIVO_DATOS4 = "datos.eventos4";
	private static final String ARCHIVO_DATOS5 = "datos.eventos5";
	private static final String ARCHIVO_DATOS6 = "datos.eventos6";
			
			
	/**
	 * Arbol que contiene los lugares
	 */
	private ArbolAVL< String , Lugar> lugares;  

	/**
	 * Arbol que contiene los lugares
	 */
	private ArbolAVL< String , Categoria> categorias;

	private ArbolAVL<String, Evento> eventos;

	private ArbolAVL<String, IListaSimple<Evento>> matrizEventosInvitados;

	private ArbolAVL<String, Evento> eventosCancelados;

	private Evento masInvitados;

	/**
	 * Core de la aplicacion principal
	 */
	private ICore core;
	//--------------------------------------------
	// Constructor
	//--------------------------------------------

	/**
	 * Constructor del CupiEventos. Por ser una aplicación para cupiphone recibe
	 * la interface del core como parámetro en caso de necesitar localizar
	 * un nuevo componente o el directorio de datos.
	 * @param core
	 */
	@SuppressWarnings("unchecked")
	public ComponenteEventos ( ICore xCore){

		
		core = xCore;
		
		
    	try{
    		
    		//Tratar de cargar datos previos
    		File f1 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS1);
        	File f2 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS2);
        	File f3 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS3);
        	File f4 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS4);
        	File f5 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS5);
        	File f6 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS6);
    		lugares = (ArbolAVL<String, Lugar>) new ObjectInputStream(new FileInputStream(f1)).readObject();
    		categorias = (ArbolAVL<String, Categoria>) new ObjectInputStream(new FileInputStream(f2)).readObject();
    		eventos = (ArbolAVL<String, Evento>) new ObjectInputStream(new FileInputStream(f3)).readObject();
    		matrizEventosInvitados = (ArbolAVL<String, IListaSimple<Evento>>) new ObjectInputStream(new FileInputStream(f4)).readObject();
    		eventosCancelados = (ArbolAVL<String, Evento>) new ObjectInputStream(new FileInputStream(f5)).readObject();
    		masInvitados = (Evento) new ObjectInputStream(new FileInputStream(f6)).readObject();
    		
    		
    	}
    	catch (Exception e){
    		//No fue posible cargar, inicializar vacío
    		categorias = new ArbolAVL<String, Categoria>(); 
    		lugares = new ArbolAVL<String, Lugar>();
    		eventos = new ArbolAVL<String,Evento> ();
    		matrizEventosInvitados = new ArbolAVL<String, IListaSimple<Evento>>();
    		eventosCancelados = new ArbolAVL<String, Evento>( );
    	}
		
		
	}	

	//--------------------------------------------
	// Metodos
	//--------------------------------------------


	/**
	 * Retorna los lugares que se encontraron dentro del area dada
	 * @param latitud Latitud del punto
	 * @param longitud Longitud del punto
	 * @param radio Radio de busqueda
	 * @return lista de lugares
	 */
	public IListaSimple <Lugar> darLugaresDeLocalizacion ( double latitud, double longitud, double radio){

		IListaSimple <Lugar> lugares = new Arreglo<Lugar>();
		try {

			File xmlFile = new File("./data/xml.xml");
			xmlFile.createNewFile();
			PrintWriter escritor = new PrintWriter(xmlFile);
			URL google = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?key=AIzaSyDp" +
					"xPbxX3IS4wZRs0B-rKcFt6HE17wxjLA&radius="+radio+"&sensor=false&location="+latitud+","+longitud);
			URLConnection yc = google.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {

				escritor.write(inputLine);

			}
			escritor.close();
			in.close();	      			


			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();


			NodeList nList = doc.getElementsByTagName("result");


			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String nombre = eElement.getElementsByTagName("name").item(0).getTextContent();
					String categoria = eElement.getElementsByTagName("type").item(0).getTextContent();
					String vecindad = eElement.getElementsByTagName("vicinity").item(0).getTextContent();
					URL icon = new URL(eElement.getElementsByTagName("icon").item(0).getTextContent());
					double lat = Double.parseDouble(eElement.getElementsByTagName("lat").item(0).getTextContent());
					double longi = Double.parseDouble(eElement.getElementsByTagName("lng").item(0).getTextContent());

					Lugar lugarX = new Lugar(nombre.trim(), categoria, vecindad,lat, longi, icon);
					lugares.agregar(lugarX);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return lugares;
	}

	public void guardarLugares ( IListaSimple<Lugar> nLugares ){

		Iterator <Lugar> i = nLugares.iterator();

		while ( i.hasNext()){

			Lugar actual = i.next();
			lugares.agregar(actual.getNombre(), actual);
		}

	}

	public Object[] darListaLugares() {

		return lugares.toArray();
	}

	public IListaSimple<String> darLugares(){
		
		IListaSimple<String> luga = new Arreglo<String>();
		
		try {
			
			Iterator <Lugar> i = lugares.iterator();
			
			
			while ( i.hasNext()){
				
				Lugar actual = i.next();
				
				luga.agregar(actual.getNombre());
				
			}
			
			return luga;
		}
		catch (Exception e){
			
			return null;
		}
		
	}
	public ArbolAVL<String, Categoria> darCategorias() {

		return categorias;
	}

	public Lugar buscarLugar(String nombreLug) {

		return lugares.buscar(nombreLug);				
	}

	public void agregarCategoriaNombre(String nNombre)
	{
		Categoria nueva = new Categoria(nNombre);
		try
		{
			categorias.agregar(nNombre, nueva);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	public void agregarLugaresACategoria(String nNombre,IListaSimple <Lugar> lugares2)
	{
		Categoria actual = categorias.buscar(nNombre);		
		Iterator<Lugar> i = lugares2.iterator();
		while ( i.hasNext())
		{
			Lugar lugActual = i.next();
			actual.adicionarLugar(lugActual);
		}
	}

	public void crearEvento(Evento nuevo)
	{
		eventos.agregar(nuevo.darNombre(),nuevo);
		if(masInvitados==null)
			masInvitados = nuevo;
	}

	/**
     * Guarda los datos de la aplicación
     */
    public void guardar(){
    	File f1 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS1);
    	File f2 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS2);
    	File f3 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS3);
    	File f4 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS4);
    	File f5 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS5);
    	File f6 = new File(core.darDirectorioDatos(), ARCHIVO_DATOS6);
    	try{
    		new ObjectOutputStream(new FileOutputStream(f1)).writeObject(lugares);
    		new ObjectOutputStream(new FileOutputStream(f2)).writeObject(categorias);
    		new ObjectOutputStream(new FileOutputStream(f3)).writeObject(eventos);
    		new ObjectOutputStream(new FileOutputStream(f4)).writeObject(matrizEventosInvitados);
    		new ObjectOutputStream(new FileOutputStream(f5)).writeObject(eventosCancelados);
    		new ObjectOutputStream(new FileOutputStream(f6)).writeObject(masInvitados);
    	}
    	catch (Exception e) {
    		System.err.println("No se pudo guardar los datos");
			e.printStackTrace();
		}
    }
	public void invitar(Evento evento, String[] correos)
	{
		evento.invitar(correos);

		for(String correo: correos)
		{
			if(!correo.trim().equals("") && correo.split("@").length==2)
			{
				try
				{
					matrizEventosInvitados.buscar(correo.trim()).agregar(evento);
				}
				catch(Throwable e)
				{
					e.printStackTrace();
					IListaSimple<Evento> lista = new ListaEncadenada<Evento>( );
					lista.agregar(evento);
					if(masInvitados == null)
					{
						masInvitados = evento;
						return;
					}
					masInvitados = masInvitados.compareTo(evento)>0?masInvitados:evento;
					matrizEventosInvitados.agregar(correo.trim(), lista);
					continue;
				}
				if(masInvitados == null)
				{
					masInvitados = evento;
					return;	
				}
				masInvitados = masInvitados.compareTo(evento)>0?masInvitados:evento;
			}
			else
				continue;
		}
	}

	public Object[] eventosPorInvitado(Evento evento, String correo) throws Throwable
	{
		return matrizEventosInvitados.buscar(correo).toArray();
	}

	public Object[] darEventos( )
	{
		return eventos.toArray();
	}

	public Object[] eventosCancelados( )
	{
		return eventosCancelados.toArray();
	}

	public void cancelarEvento(Evento evento)
	{
		evento.cancelar();
		eventosCancelados.agregar(evento.darNombre(), evento);
		eventos.eliminar(evento.darNombre());
	}

	public void reanudarEvento(Evento evento)
	{
		evento.reanudar();
		eventos.agregar(evento.darNombre(), evento);
		eventosCancelados.eliminar(evento.darNombre());
	}

	public Object[] darEventos(Lugar lugar)
	{
		return lugar.darEventos();
	}
	
	public Evento eventoMasInvitados()
	{
		return masInvitados;
	}

	public Object[] darEventosCorreo(String text)
	{
		try{
		return matrizEventosInvitados.buscar(text).toArray();}
		catch(Throwable e)
		{
			return null;
		}
	}

	public Object[] darEventosCercanos(Evento actual, int radio)
	{
		IListaSimple <Evento> lugares = new Arreglo<Evento>();
		try {

			File xmlFile = new File("./data/xmltemp.xml");
			xmlFile.createNewFile();
			PrintWriter escritor = new PrintWriter(xmlFile);
			URL google = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?key=AIzaSyDp" +
					"xPbxX3IS4wZRs0B-rKcFt6HE17wxjLA&radius="+radio+"&sensor=false&location="+actual.darLugar().getLatitud()+","+actual.darLugar().getLongitud());
			URLConnection yc = google.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {

				escritor.write(inputLine);

			}
			escritor.close();
			in.close();	      			


			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();


			NodeList nList = doc.getElementsByTagName("result");


			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String lugar = eElement.getElementsByTagName("name").item(0).getTextContent();
					for(Evento evento: eventos)
					{
						if(evento.esEnLugar(lugar))
						{
							lugares.agregar(evento);
						}
					}
				}
				xmlFile.delete();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return lugares.toArray();
	}
}