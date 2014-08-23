package interfaz;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;

import mundo.Evento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelEventosCercanos extends JPanel {

	private JPanel panelCentral;
	private JTextField textField;
	private ComponenteEventosPanel principal;
	private boolean modoBuscar;
	private JList listaEventos;
	private JScrollPane scrollResultado;
	private JLabel lblHeader;
	private JList listaResultado;

	/**
	 * Create the panel.
	 */
	public PanelEventosCercanos(ComponenteEventosPanel princip)
	{
		principal = princip;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		modoBuscar = false;
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblEventosCercanos = new JLabel("Eventos Cercanos");
		lblEventosCercanos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEventosCercanos.setForeground(Color.WHITE);
		lblEventosCercanos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventosCercanos.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(lblEventosCercanos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!modoBuscar)
				{
					principal.mostrarPanelInicio();
				}
				else
				{
					principal.mostrarPanelEventosCercanos();
				}
			}
		});
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFocusPainted(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setBackground(Color.RED);
		panel_1.add(btnAtras);
		
		scrollResultado = new JScrollPane();		
		listaResultado = new JList();
		listaResultado.setCellRenderer(new MyMultilineRenderer(Color.BLACK));
		listaResultado.setForeground(Color.WHITE);
		listaResultado.setBackground(Color.BLACK);
		scrollResultado.setViewportView(listaResultado);
		
		lblHeader = new JLabel();
		lblHeader.setBackground(Color.BLACK);
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setOpaque(true);
		scrollResultado.setColumnHeaderView(lblHeader);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panelCentral.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBackground(SystemColor.textHighlight);
		btnBuscar.setBorderPainted(false);
		panel_2.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		listaEventos = new JList();
		listaEventos.setForeground(Color.WHITE);
		listaEventos.setCellRenderer(new MyMultilineRenderer(Color.BLACK));
		listaEventos.setBackground(Color.BLACK);
		listaEventos.setListData(principal.darEventos());
		scrollPane.setViewportView(listaEventos);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
	}
	
	private void buscar()
	{
		remove(panelCentral);
		add(scrollResultado, BorderLayout.CENTER);
		actualizar();
		revalidate();
		repaint();
		modoBuscar = true;
	}
	
	private void actualizar()
	{
		Evento actual = (Evento)listaEventos.getSelectedValue();
		int radio = Integer.parseInt(textField.getText());
		lblHeader.setText("Eventos cercanos a "+actual.darNombre());
		Object[] resultado = principal.darEventosCercanos(actual, radio);
		listaResultado.setListData(resultado);
	}
}
