package interfaz;

import interfaces.IListaSimple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import estructuras.Arreglo;

import mundo.Lugar;

import java.awt.Cursor;

public class PanelBusquedaSitios extends JPanel
{
	private static final long serialVersionUID = 6684686604572327594L;
	
	private JTextField txtLat;
	private JTextField txtLong;
	private JTextField txtRadio;
	private JScrollPane scroll;
	@SuppressWarnings("rawtypes")
	private JList list;
	private ComponenteEventosPanel principal;
	private JTextField textRefinar;
	private IListaSimple <Lugar> listaLugares;
	
	private JPanel lista;
	private JPanel panelCentral;
	private JPanel panel_2;
	
	private boolean modoLista;

	private JButton btnGuardar;

	private JPanel panelInferior;
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("rawtypes")
	public PanelBusquedaSitios( ComponenteEventosPanel padre)
	{
		modoLista = false;
		listaLugares = new Arreglo<Lugar>();
		principal = padre;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBackground(Color.DARK_GRAY);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSitiosDeInters = new JLabel("Sitios De Inter\u00E9s");
		lblSitiosDeInters.setForeground(Color.WHITE);
		lblSitiosDeInters.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSitiosDeInters.setHorizontalAlignment(SwingConstants.CENTER);
		lblSitiosDeInters.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblSitiosDeInters);
		
		lista = new JPanel();
		lista.setBackground(Color.BLACK);
		lista.setLayout(new BorderLayout(0,0));
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 200));
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 0, 0));
		panel_5.setPreferredSize(new Dimension(10, 25));
		lista.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		textRefinar = new JTextField();
		panel_5.add(textRefinar);
		textRefinar.setColumns(10);
		
		JButton btnRefinarCategoria = new JButton("Refinar");
		btnRefinarCategoria.setBackground(new Color(255, 255, 255));
		btnRefinarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cat = textRefinar.getText().trim();
				if ( cat != "")
				{
					refinarLista(cat);
				}				
			}
		});
		btnRefinarCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_5.add(btnRefinarCategoria);
		
		panelInferior = new JPanel();
		panel_2.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setBackground(Color.DARK_GRAY);
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBorderPainted(false);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarLugares();
			}
		});
		
		JButton btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.setBorderPainted(false);
		btnAtrs.setForeground(Color.WHITE);
		btnAtrs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtrs.setBackground(Color.RED);
		btnAtrs.setFocusPainted(false);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modoLista)
				{
					panel_2.remove(lista);
					panel_2.add(panelCentral, BorderLayout.CENTER);
					panelInferior.remove(btnGuardar);
					revalidate();
					repaint();
					modoLista = false;
				}
				else
				{
					principal.mostrarPanelInicio();
				}
			}
		});
		panelInferior.add(btnAtrs);
		
		panelCentral = new JPanel();
		panel_2.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(Color.BLACK);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(10, 80));
		panel_4.setBackground(Color.BLACK);
		panelCentral.add(panel_4, BorderLayout.SOUTH);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_4.add(btnBuscar);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorder(new LineBorder(SystemColor.textHighlight, 3));
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscar.setVerticalTextPosition(SwingConstants.CENTER);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String lat = txtLat.getText().trim();
				String lon = txtLong.getText().trim();
				String rad = txtRadio.getText().trim();
				panelInferior.add(btnGuardar);
				try{
					
					double inLat = Double.parseDouble(lat);
					double inLon = Double.parseDouble(lon);
					double inRad = Double.parseDouble(rad);
					
					
					listaLugares = principal.darListaSitios(inLat, inLon, inRad);
					actualizarList(listaLugares);
				}
				catch (Exception ex) {
					
				}
			}
		});
		btnBuscar.setBackground(SystemColor.textHighlight);
		
		JPanel panel_7 = new JPanel();
		panelCentral.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_7.add(panel_1, BorderLayout.CENTER);
		panel_1.setBackground(Color.BLACK);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setForeground(Color.WHITE);
		lblLatitud.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblLatitud);
		
		txtLat = new JTextField();
		txtLat.setBorder(new LineBorder(Color.BLACK, 3));
		panel_1.add(txtLat);
		txtLat.setColumns(10);
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setForeground(Color.WHITE);
		lblLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblLongitud);
		
		txtLong = new JTextField();
		txtLong.setBorder(new LineBorder(Color.BLACK, 3));
		panel_1.add(txtLong);
		txtLong.setColumns(10);
		
		JLabel lblRadio = new JLabel("Radio");
		lblRadio.setForeground(Color.WHITE);
		lblRadio.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblRadio);
		
		txtRadio = new JTextField();
		txtRadio.setSelectionColor(Color.CYAN);
		txtRadio.setBorder(new LineBorder(Color.BLACK, 3));
		panel_1.add(txtRadio);
		txtRadio.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setForeground(Color.DARK_GRAY);
		panel_8.setBackground(Color.BLACK);
		panel_8.setPreferredSize(new Dimension(10, 60));
		panel_7.add(panel_8, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.BLACK);
		panel_9.setPreferredSize(new Dimension(10, 50));
		panel_7.add(panel_9, BorderLayout.SOUTH);
		
		
		list = new JList();
		list.setCellRenderer(new MyIconListRenderer());
		list.setForeground(Color.WHITE);
		list.setBackground(Color.BLACK);
		scroll = new JScrollPane(list);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		lista.add(scroll, BorderLayout.CENTER);
	}
	
	@SuppressWarnings("unchecked")
	private void refinarLista (String categoria)
	{
		IListaSimple <Lugar> lista = new Arreglo<Lugar>();
		for (int i = 0; i < listaLugares.tamano(); i++) {
			if ( listaLugares.obtener(i).getCategoria().equalsIgnoreCase(categoria))
				lista.agregar(listaLugares.obtener(i));
		}
		
		list.setListData(lista.toArray());
		listaLugares = lista;
	}
	
	@SuppressWarnings("unchecked")
	private void actualizarList ( IListaSimple <Lugar> lista)
	{
		panel_2.remove(panelCentral);
		list.setListData(lista.toArray());
		panel_2.add(this.lista, BorderLayout.CENTER);
		revalidate();
		repaint();
		modoLista = true;
	}
	
	private void guardarLugares ()
	{
		principal.guardarLugares( listaLugares );
		principal.mostrarPanelInicio();
	}
}
