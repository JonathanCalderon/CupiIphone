package interfaz;

import interfaces.IListaSimple;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import mundo.Categoria;
import mundo.ComponenteEventos;
import mundo.Evento;
import mundo.Lugar;

public class ComponenteEventosPanel extends JPanel {

	private ComponenteEventos mundo;
	
	private JPanel panelActual;
	
	private PanelInicio panelInicio;

	public ComponenteEventosPanel( ComponenteEventos mundo)
	{
		
		
		this.mundo = mundo;
		setBounds(100, 100, 258, 401);
		setLayout(new BorderLayout());
		panelInicio = new PanelInicio(mundo, this);
		panelActual = panelInicio;	
		add( panelActual, BorderLayout.CENTER);		
	}

	
	public ComponenteEventosPanel() {
		
		mundo = new ComponenteEventos(null);
		setBounds(100, 100, 258, 401);
		setLayout(new BorderLayout());
		panelInicio = new PanelInicio(mundo, this);
		panelActual = panelInicio;	
		add( panelActual, BorderLayout.CENTER);	
	}


	public void mostrarPanelBusquedaSitios() {
		
		remove(panelActual);
		panelActual = new PanelBusquedaSitios(this);
		add( panelActual, BorderLayout.CENTER);
		validate();
		repaint();
	}

	public IListaSimple <Lugar> darListaSitios(double latitud, double longitud, double radio) {
		
				
		return mundo.darLugaresDeLocalizacion(latitud, longitud, radio);
	}

	public void guardarLugares(IListaSimple <Lugar> listaLugares) {
		
		mundo.guardarLugares(listaLugares);
	}

	public Object[] darTodosLugares (){
		
		return mundo.darListaLugares();
	}

	public IListaSimple <String> darLugares(){
		
		return mundo.darLugares();
	}
	public Lugar buscarLugar(String nombreLug) {
		
		return mundo.buscarLugar ( nombreLug);
	}
	
	public Object[] darEventos( )
	{
		return mundo.darEventos( );
	}

	public void agregarCategoria(String nNombre) {
		
		mundo.agregarCategoriaNombre ( nNombre);
		
	}

	public void mostrarPanelNuevaCategoria() {
		
		remove(panelActual);
		panelActual = new PanelNuevaCategoria(this);
		add( panelActual, BorderLayout.CENTER);
		validate();
		repaint();
	}

	public void mostrarPanelInicio() {
		remove(panelActual);
		panelActual = panelInicio;
		add( panelActual, BorderLayout.CENTER);
		validate();
		repaint();
		
	}

	public void mostrarPanelVerEventos(){
		remove(panelActual);
		panelActual = new PanelVerEventos(this);
		add(panelActual, BorderLayout.CENTER);
		validate( );
		repaint( );
	}
	
	public void mostrarPanelEventosCorreo(){
		remove(panelActual);
		panelActual = new PanelEventosPorCorreo(this);
		add(panelActual, BorderLayout.CENTER);
		validate();
		repaint();
	}
	
	/**
	 * Indica al panel que se debe terminar la ejecución
	 */
	public void terminar() {
		mundo.guardar();		
	}
	
	public void mostrarPanelLugares() {
		
		remove(panelActual);
		panelActual = new PanelListaLugares(this);
		add(panelActual, BorderLayout.CENTER);
		validate();
		repaint();
	}

	public Object[] darCategorias() {
		
		return mundo.darCategorias().toArray();
	}

	public void cancelarEvento(Evento evento)
	{
		mundo.cancelarEvento(evento);
	}
	
	public void invitar(Evento evento, String[] correos)
	{
		mundo.invitar(evento, correos);
	}
	
	public void crearEvento(String text, Object selectedValue, String string)
	{
		mundo.crearEvento(new Evento(text, (Lugar)selectedValue, string));
	}


	public Object[] darEventos(Lugar lugar)
	{
		return mundo.darEventos(lugar);
	}


	public Object[] darEventosCorreo(String text)
	{
		return mundo.darEventosCorreo(text);
	}


	public Object[] darEventosCancelados(){
		return mundo.eventosCancelados();
	}


	public void mostrarPanelEventosCancelados()
	{
		remove(panelActual);
		panelActual = new PanelEventosCancelados(this);
		add(panelActual, BorderLayout.CENTER);
		validate();
		repaint();
	}


	public void mostrarPanelEventosCercanos()
	{
		remove(panelActual);
		panelActual = new PanelEventosCercanos(this);
		add(panelActual, BorderLayout.CENTER);
		validate();
		repaint();
	}

	public void mostrarPanelStreetView()
	{
		remove(panelActual);
		panelActual = new PanelStreetView(this);
		add(panelActual, BorderLayout.CENTER);
		validate();
		repaint();
	}

	public Object[] darEventosCercanos(Evento actual, int radio)
	{
		return mundo.darEventosCercanos(actual, radio);
	}


	public Evento darEventoMasInvitados()
	{
		return mundo.eventoMasInvitados();
	}
}
