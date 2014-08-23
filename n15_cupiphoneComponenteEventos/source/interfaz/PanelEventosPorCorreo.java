package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelEventosPorCorreo extends JPanel {
	private JTextField textField;
	private JList listaEventos;
	private ComponenteEventosPanel principal;

	/**
	 * Create the panel.
	 */
	public PanelEventosPorCorreo(ComponenteEventosPanel interfaz) {
		principal = interfaz;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Eventos Correo");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setPreferredSize(new Dimension(75, 50));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel);
		
		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.mostrarPanelInicio();
			}
		});
		btnAtras.setBackground(Color.RED);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBorderPainted(false);
		btnAtras.setFocusPainted(false);
		panelInferior.setBackground(Color.DARK_GRAY);
		panelInferior.add(btnAtras);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.BLACK);
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarEventos();
			}
		});
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBackground(SystemColor.textHighlight);
		btnBuscar.setBorderPainted(false);
		panel_1.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		listaEventos = new JList();
		listaEventos.setForeground(Color.WHITE);
		listaEventos.setBackground(Color.BLACK);
		listaEventos.setCellRenderer(new MyMultilineRenderer(Color.BLACK));
		scrollPane.setViewportView(listaEventos);
		
		JLabel lblCorreo = new JLabel("");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setBackground(Color.BLACK);
		lblCorreo.setOpaque(true);
		scrollPane.setColumnHeaderView(lblCorreo);

	}
	
	private void buscarEventos()
	{
		Object[] lista = principal.darEventosCorreo(textField.getText());
		if(lista!=null)
		{
			listaEventos.setListData(lista);
		}
	}

}
