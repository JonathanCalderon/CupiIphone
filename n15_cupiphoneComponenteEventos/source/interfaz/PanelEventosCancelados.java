package interfaz;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class PanelEventosCancelados extends JPanel {

	private ComponenteEventosPanel principal;
	/**
	 * Create the panel.
	 */
	public PanelEventosCancelados(ComponenteEventosPanel princ) {
		principal = princ;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("EventosCancelados");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				principal.mostrarPanelInicio();	
			}
		});
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFocusPainted(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setBackground(Color.RED);
		panel_1.add(btnAtras);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList();
		list.setCellRenderer(new MyMultilineRenderer(Color.BLACK));
		list.setListData(principal.darEventosCancelados());
		list.setForeground(Color.WHITE);
		list.setBackground(Color.BLACK);
		scrollPane.setViewportView(list);

	}

}
