package interfaz;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.JButton;

import mundo.Lugar;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class PanelStreetView extends JPanel {

	private ComponenteEventosPanel principal;
	private JList list;
	private JScrollPane scroll;
	private boolean estadoLista;
	private JPanel panelInferior;
	private JButton btnAtrs;
	private JButton btnSeleccionar;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private int grados;
	private JLabel labImagen;
	private double lat;
	private double lon;
	private BufferedImage image;
	
	/**
	 * Create the panel.
	 */
	public PanelStreetView( ComponenteEventosPanel padre) {
		
		
		grados = 0;
		estadoLista = true;

		principal = padre;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.DARK_GRAY);
		panelTitulo.setPreferredSize(new Dimension(10, 40));
		add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblStreetView = new JLabel("Street View");
		lblStreetView.setForeground(Color.WHITE);
		lblStreetView.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStreetView.setHorizontalTextPosition(SwingConstants.CENTER);
		lblStreetView.setHorizontalAlignment(SwingConstants.CENTER);
		lblStreetView.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTitulo.add(lblStreetView);
		
		panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);
		panelInferior.setPreferredSize(new Dimension(10, 50));
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("");
		panelInferior.add(label);
		
		btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( estadoLista)
					principal.mostrarPanelInicio();
				else{
					
					cambiarPanel();
					grados = 0;
				}					
			}
		});
		btnAtrs.setBackground(Color.RED);
		panelInferior.add(btnAtrs);
		
		JLabel label_1 = new JLabel("");
		panelInferior.add(label_1);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ( list.getSelectedValue() != null){
					
					Lugar lug = principal.buscarLugar(list.getSelectedValue().toString());
					
					if ( lug!= null)
						mostrarMapa( lug);
				
				}
			}
		});
		btnSeleccionar.setBackground(new Color(0, 153, 255));
		panelInferior.add(btnSeleccionar);
		
		JLabel label_2 = new JLabel("");
		panelInferior.add(label_2);
		
		list = new JList();
		list.setForeground(new Color(255, 255, 255));
		list.setBackground(Color.BLACK);
		actualizarLugares();
		
		scroll = new JScrollPane(list);	
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll, BorderLayout.CENTER);
		
		btnDerecha = new JButton(">");
		btnDerecha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ( grados == 360){
					grados = 20;
					actualizarMapa();
				}
				else{
					grados += 20;
					actualizarMapa();
				}
			}

			
		});

		btnIzquierda = new JButton("<");
		btnIzquierda.setBackground(Color.BLACK);
		btnIzquierda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ( grados == 0){
					grados = 340;
					actualizarMapa();
				}
				else{
					grados -= 20;
					actualizarMapa();
				}
			}

			
		});
		
		labImagen = new JLabel();
	}
	
	private void actualizarMapa( ) {
		
		try {
			URL url = new URL("http://maps.googleapis.com/maps/api/streetview?size=300x400&location="+lat+",%20"+lon+""
					+ "&heading="+grados+"&sensor=false");
			
			image = ImageIO.read(url);
			labImagen.setIcon(new ImageIcon(image));
			labImagen.revalidate();
			labImagen.repaint();
			revalidate();
			repaint();
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void mostrarMapa ( Lugar lugar){
		
		cambiarPanel();
		
		lat = lugar.getLatitud();
		lon = lugar.getLongitud();
		
		actualizarMapa();
		
	}

	private void cambiarPanel() {
		
		if ( estadoLista){
			
			panelInferior.remove(btnSeleccionar);
			panelInferior.add(btnIzquierda);
			panelInferior.add(btnDerecha);
			remove(scroll);
			add( labImagen, BorderLayout.CENTER);
			estadoLista = false;
		}
		else{
		
			panelInferior.remove(btnIzquierda);
			panelInferior.remove(btnDerecha);
			panelInferior.add(btnSeleccionar);			
			remove(labImagen);
			add( scroll, BorderLayout.CENTER);
			estadoLista = true;
			
		}
		revalidate();
		repaint();
	}

	private void actualizarLugares() {
		
		if ( principal.darLugares()!= null)
			list.setListData(principal.darLugares().toArray());
		
	}

}
