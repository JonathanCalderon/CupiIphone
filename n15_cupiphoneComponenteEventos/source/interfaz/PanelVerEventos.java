package interfaz;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import mundo.Evento;
import mundo.Lugar;

import java.awt.SystemColor;

public class PanelVerEventos extends JPanel
{
	private ComponenteEventosPanel principal;
	private JScrollPane scrollCentral;
	private JPanel panelCentral;
	private JPanel panelEvento;
	private JList listaEventos;
	private JPanel panelInferior;
	private JTextField txtInvitado;
	private boolean modoLista;
	private boolean modoEventos;
	private JButton btnSeleccionar;
	private JList invitadosEvento;
	private JLabel lblEvento;
	private Evento actual;
	private JButton btnCancelar;
	private JButton btnMas;
	
	/**
	 * Create the panel.
	 */
	public PanelVerEventos(ComponenteEventosPanel princip)
	{
		principal = princip;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		
		modoLista = true;
		modoEventos = false;
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setPreferredSize(new Dimension(10, 40));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblVerEventos = new JLabel("Ver Eventos");
		lblVerEventos.setForeground(Color.WHITE);
		lblVerEventos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVerEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerEventos.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(lblVerEventos);
		
		panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);
		add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(modoEventos)
				{
					mostrarLista();
				}
				else
				{
					principal.mostrarPanelInicio();
				}
			}
		});
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setFocusPainted(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setBackground(Color.RED);
		btnAtras.setForeground(Color.WHITE);
		panelInferior.add(btnAtras);
		
		btnSeleccionar = new JButton("Selec");
		btnSeleccionar.setBorderPainted(false);
		btnSeleccionar.setFocusPainted(false);
		btnSeleccionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				seleccionar(null);
			}
		});
		btnSeleccionar.setBackground(Color.WHITE);
		btnSeleccionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelInferior.add(btnSeleccionar);
		
		btnMas = new JButton("Mas");
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMasInvitados();
			}
		});
		btnMas.setFocusPainted(false);
		btnMas.setBorderPainted(false);
		btnMas.setForeground(Color.WHITE);
		btnMas.setBackground(new Color(0, 128, 0));
		panelInferior.add(btnMas);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		scrollCentral = new JScrollPane();
		panelCentral.add(scrollCentral);
		
		listaEventos = new JList();
		listaEventos.setCellRenderer(new MyMultilineRenderer(Color.BLACK));
		listaEventos.setForeground(Color.WHITE);
		listaEventos.setBackground(Color.BLACK);
		scrollCentral.setViewportView(listaEventos);
		listaEventos.setListData(principal.darEventos());
		
		{
			panelEvento = new JPanel();
			panelEvento.setBackground(Color.BLACK);
			panelEvento.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelEvento.add(scrollPane, BorderLayout.CENTER);
			
			invitadosEvento = new JList();
			invitadosEvento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			invitadosEvento.setForeground(Color.WHITE);
			invitadosEvento.setBackground(Color.BLACK);
			scrollPane.setViewportView(invitadosEvento);
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cancelarEvento();
				}
			});
			btnCancelar.setBorderPainted(false);
			btnCancelar.setFocusPainted(false);
			
			JLabel lblListaDeInvitados = new JLabel("Lista de invitados");
			lblListaDeInvitados.setBackground(Color.BLACK);
			scrollPane.setColumnHeaderView(lblListaDeInvitados);
			
			lblEvento = new JLabel();
			lblEvento.setOpaque(true);
			lblEvento.setBackground(Color.BLACK);
			lblEvento.setForeground(Color.WHITE);
			panelEvento.add(lblEvento, BorderLayout.CENTER);
			
			JPanel panel_1 = new JPanel();
			panelEvento.add(panel_1, BorderLayout.SOUTH);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
			
			txtInvitado = new JTextField();
			panel_1.add(txtInvitado);
			txtInvitado.setColumns(10);
			
			JButton btnEnviarInvitacin = new JButton("Enviar");
			btnEnviarInvitacin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					enviarCorreos();
				}
			});
			btnEnviarInvitacin.setBackground(SystemColor.textHighlight);
			btnEnviarInvitacin.setForeground(Color.WHITE);
			btnEnviarInvitacin.setFocusPainted(false);
			btnEnviarInvitacin.setBorderPainted(false);
			panel_1.add(btnEnviarInvitacin);
		}
	}
	
	private void mostrarLista()
	{
		remove(panelEvento);
		add(panelCentral, BorderLayout.CENTER);
		panelInferior.remove(btnCancelar);
		panelInferior.add(btnSeleccionar);
		panelInferior.add(btnMas);
		modoLista = true;
		modoEventos = false;
		revalidate();
		repaint();
	}
	
	private void seleccionar(Evento actual)
	{
		remove(panelCentral);
		add(panelEvento, BorderLayout.CENTER);
		panelInferior.remove(btnSeleccionar);
		panelInferior.remove(btnMas);
		panelInferior.add(btnCancelar);
		modoLista = false;
		modoEventos = true;
		actualizarEvento(actual);
		revalidate();
		repaint();
	}
	
	private void cancelarEvento()
	{
		principal.cancelarEvento(actual);
		actualizarEventos();
		mostrarLista();
	}
	
	private void actualizarEventos()
	{
		listaEventos.setListData(principal.darEventos());
	}
	
	private void actualizarEvento(Evento act)
	{
		actual = act!=null?act:(Evento)listaEventos.getSelectedValue();
		lblEvento.setText(actual.darNombre());
		actualizarLista();
	}
	
	private void actualizarLista()
	{
		invitadosEvento.setListData(actual.darInvitados());
	}
	
	private void enviarCorreos()
	{
		String[] correos = txtInvitado.getText().split(";");
		principal.invitar((Evento)listaEventos.getSelectedValue(),correos);
		actualizarLista();
	}
	
	private void mostrarMasInvitados()
	{
		seleccionar(principal.darEventoMasInvitados());
	}
}
