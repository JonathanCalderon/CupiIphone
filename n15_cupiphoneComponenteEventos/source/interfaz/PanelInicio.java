package interfaz;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import mundo.ComponenteEventos;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInicio extends JPanel implements Observer{

	
	private ComponenteEventos mundo;
	
	private ComponenteEventosPanel principal;
	
	/**
	 * Constructor del Panel.
	 * @param reloj Mundo del componente.
	 */
	public PanelInicio( ComponenteEventos xMundo , ComponenteEventosPanel padre) 
	{
		principal = padre;
		setBackground(Color.WHITE);
		mundo = xMundo;
		mundo.addObserver(this);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblEventos = new JLabel("Eventos");
		lblEventos.setForeground(Color.WHITE);
		lblEventos.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEventos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEventos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEventos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(8, 0, 0, 0));
		
		JButton btnBuscarSitiosDe = new JButton("Buscar Sitios De Interes");
		btnBuscarSitiosDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				principal.mostrarPanelBusquedaSitios();
			}
		});
		btnBuscarSitiosDe.setBackground(Color.DARK_GRAY);
		btnBuscarSitiosDe.setForeground(Color.WHITE);
		panel_1.add(btnBuscarSitiosDe);
		
		JButton btnCrearNuevaCategora = new JButton("Crear Nueva Categor\u00EDa");
		btnCrearNuevaCategora.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				principal.mostrarPanelNuevaCategoria();
			}
		});
		btnCrearNuevaCategora.setBackground(Color.DARK_GRAY);
		btnCrearNuevaCategora.setForeground(Color.white);
		panel_1.add(btnCrearNuevaCategora);
		
		JButton btnVerLugares = new JButton("Ver Lugares");
		btnVerLugares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				principal.mostrarPanelLugares();
			}
		});
		btnVerLugares.setBackground(Color.DARK_GRAY);
		btnVerLugares.setForeground(Color.white);
		panel_1.add(btnVerLugares);
		
		JButton btnVerEventos = new JButton("Ver Eventos");
		btnVerEventos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				principal.mostrarPanelVerEventos();				
			}
		});
		btnVerEventos.setBackground(Color.DARK_GRAY);
		btnVerEventos.setForeground(Color.WHITE);
		panel_1.add(btnVerEventos);
		
		JButton btnVerEventosCancelados = new JButton("Ver Eventos Cancelados");
		btnVerEventosCancelados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				principal.mostrarPanelEventosCancelados();
			}
		});
		btnVerEventosCancelados.setBackground(Color.DARK_GRAY);
		btnVerEventosCancelados.setForeground(Color.white);
		panel_1.add(btnVerEventosCancelados);
		
		JButton btnBuscarEventosPor = new JButton("Buscar Eventos Por Correo");
		btnBuscarEventosPor.setBackground(Color.DARK_GRAY);
		btnBuscarEventosPor.setForeground(Color.WHITE);
		btnBuscarEventosPor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				principal.mostrarPanelEventosCorreo();
			}
		});
		
		JButton btnEventoConMs = new JButton("Eventos cercanos");
		btnEventoConMs.setBackground(Color.DARK_GRAY);
		btnEventoConMs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				principal.mostrarPanelEventosCercanos();
			}
		});
		btnEventoConMs.setForeground(Color.white);
		panel_1.add(btnEventoConMs);
		panel_1.add(btnBuscarEventosPor);
		
		JButton btnStreetView = new JButton("Street View");
		btnStreetView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				principal.mostrarPanelStreetView();
			}
		});
		btnStreetView.setForeground(Color.WHITE);
		btnStreetView.setBackground(Color.DARK_GRAY);
		panel_1.add(btnStreetView);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
