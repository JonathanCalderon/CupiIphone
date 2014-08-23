package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import mundo.Categoria;
import mundo.Lugar;

public class PanelNuevaCategoria extends JPanel
{
	private ComponenteEventosPanel principal;
	private JTextField textField;
	private JList listaCategorias;
	private JPanel panelCentral;
	private boolean modoCategoria;
	private JPanel panelInferior;
	private JButton btnSeleccionar;
	private JButton btnAdicionar;
	private JPanel panelAdicionar;
	private JList listaLugares;
	private JLabel lblLugaresEnLa;
	private Categoria catActual;
	
	/**
	 * Create the panel.
	 */
	public PanelNuevaCategoria( ComponenteEventosPanel padre ) {

		principal = padre;
		modoCategoria = false;
		//		lugares = principal.darTodosLugares();
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNuevaCategoria = new JLabel("Nueva Categoria");
		lblNuevaCategoria.setBackground(Color.DARK_GRAY);
		lblNuevaCategoria.setForeground(Color.WHITE);
		lblNuevaCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNuevaCategoria.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNuevaCategoria.setMinimumSize(new Dimension(81, 18));
		lblNuevaCategoria.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNuevaCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNuevaCategoria, BorderLayout.CENTER);

		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1, BorderLayout.CENTER);
		panel_1.setPreferredSize(new Dimension(10, 22));
		panel_1.setBackground(Color.BLACK);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setPreferredSize(new Dimension(70, 14));
		lblCategora.setForeground(Color.WHITE);
		lblCategora.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCategora.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblCategora);

		textField = new JTextField();
		textField.setBorder(new LineBorder(Color.DARK_GRAY));
		textField.setForeground(Color.WHITE);
		textField.setBackground(Color.BLACK);
		textField.setPreferredSize(new Dimension(10, 10));
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ( !textField.getText().equals(""))
				{

					principal.agregarCategoria(textField.getText());
					actualizarCategorias();
					textField.setText("");
				}	
			}
		});
		btnGuardar.setForeground(Color.WHITE);
		panel_1.add(btnGuardar);
		btnGuardar.setBackground(SystemColor.textHighlight);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setPreferredSize(new Dimension(10, 33));
		panelCentral.add(panel_2, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(10, 218));
		panel_5.setBackground(Color.BLACK);
		panelCentral.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.BLACK);
		panel_5.add(scrollPane);

		listaCategorias = new JList();
		listaCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actualizarCategorias();
		listaCategorias.setForeground(Color.WHITE);
		listaCategorias.setBackground(Color.BLACK);

		scrollPane.setViewportView(listaCategorias);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.DARK_GRAY);
		scrollPane.setColumnHeaderView(panel_7);

		JLabel lblCategorasCreadas = new JLabel("Categor\u00EDas Creadas");
		lblCategorasCreadas.setForeground(Color.WHITE);
		panel_7.add(lblCategorasCreadas);
		lblCategorasCreadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategorasCreadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCategorasCreadas.setBackground(Color.BLACK);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.BLACK);
		panel_6.setPreferredSize(new Dimension(10, 23));
		panel_5.add(panel_6, BorderLayout.NORTH);

		panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setBackground(Color.DARK_GRAY);
		panelInferior.setPreferredSize(new Dimension(10, 33));
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modoCategoria)
				{
					volverPanelInicio( );
				}
				else
				{
					principal.mostrarPanelInicio();
				}
			}
		});
		btnAtras.setFocusPainted(false);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.RED);
		btnAtras.setBorderPainted(false);
		panelInferior.add(btnAtras);

		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionar();
			}
		});
		btnSeleccionar.setBorderPainted(false);
		btnSeleccionar.setBackground(Color.WHITE);
		btnSeleccionar.setFocusPainted(false);
		panelInferior.add(btnSeleccionar);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setBackground(Color.GREEN);
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setFocusPainted(false);

		panelAdicionar = new JPanel();
		panelAdicionar.setLayout(new BorderLayout(0, 0));

		listaLugares = new JList();
		listaLugares.setCellRenderer(new MyIconListRenderer());
		listaLugares.setBackground(Color.BLACK);
		listaLugares.setForeground(Color.white);
		scrollPane = new JScrollPane(listaLugares);


		panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());


		lblLugaresEnLa = new JLabel();
		panel_1.add(lblLugaresEnLa,BorderLayout.NORTH);
		panel_1.add(scrollPane, BorderLayout.CENTER);

		panelAdicionar.add(panel_1, BorderLayout.CENTER);
	}





	private void seleccionar( )
	{
		catActual = (Categoria)listaCategorias.getSelectedValue();

		if ( catActual != null){

			this.remove(panelCentral);
			lblLugaresEnLa.setText("Agregar lugares en la Categoria: "+(catActual!=null?catActual.toString():""));
			listaLugares.setListData(principal.darTodosLugares());
			panelInferior.remove(btnSeleccionar);
			panelInferior.add(btnAdicionar);

			this.add(panelAdicionar, BorderLayout.CENTER);
			revalidate();
			repaint();
			modoCategoria = true;
		}
	}

	private void adicionar()
	{
		Lugar lug = (Lugar)listaLugares.getSelectedValue();
		if ( lug != null){
			try{
				catActual.adicionarLugar(lug);
				actualizarListaLugares();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void actualizarListaLugares( )
	{
		listaLugares.setListData(principal.darTodosLugares());
	}

	@SuppressWarnings("unchecked")
	private void actualizarCategorias( )
	{
		listaCategorias.setListData(principal.darCategorias());
	}

	private void volverPanelInicio()
	{
		this.remove(panelAdicionar);
		this.add(panelCentral);
		panelInferior.remove(btnAdicionar);
		panelInferior.add(btnSeleccionar);
		modoCategoria = false;
		revalidate();
		repaint();
	}
}
