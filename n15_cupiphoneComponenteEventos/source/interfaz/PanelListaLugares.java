package interfaz;

import interfaces.IListaSimple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import mundo.Lugar;
import estructuras.Arreglo;

public class PanelListaLugares extends JPanel
{
	private Object[] lugares;
	private JPanel panelLugar;
	private JPanel panelEventos;
	private JPanel panelBusqueda;
	private ComponenteEventosPanel principal;
	private JScrollPane scroll1;
	private JList listaLugares;
	private boolean modoSeleccion;
	private boolean modoVerEvento;
	private JPanel panelInferior;
	private JButton btnSeleccionar;
	private JButton eventos;
	private JButton buscar;
	private JTextField textNombreEvento;
	private JList listaEventos;
	private JLabel labelNombre;
	private Lugar lugarActual;
	private int zoom;
	private URL url;
	private BufferedImage image;
	private JTextField textBusqueda;
	private JComboBox comboBox;
	private JList listaFiltrada;
	private JLabel labImagen;
	private JPanel botonesImagen;
	private boolean modoBuscar;
	private Component actual;
	private JCheckBox checkDia;
	private JComboBox comboInicio;
	private JComboBox comboFinal;

	/**
	 * Create the panel.
	 */
	public PanelListaLugares( ComponenteEventosPanel padre)
	{
		zoom = 15;
		principal = padre;
		lugares = principal.darTodosLugares();
		modoSeleccion = false;
		modoVerEvento = false;
		modoBuscar = false;
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setPreferredSize(new Dimension(10, 40));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblLugares = new JLabel("Lugares");
		lblLugares.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLugares.setForeground(Color.WHITE);
		lblLugares.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLugares.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLugares);

		listaLugares = new JList();
		listaLugares.setCellRenderer(new MyIconListRenderer());
		listaLugares.setListData(lugares);
		listaLugares.setBackground(Color.BLACK);
		listaLugares.setForeground(Color.WHITE);

		scroll1 = new JScrollPane(listaLugares);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scroll1, BorderLayout.CENTER);
		actual = scroll1;

		panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);
		add(panelInferior, BorderLayout.SOUTH);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(modoSeleccion)
				{
					if(modoBuscar)
						buscar();
					else
						mostrarLista();
				}
				else if(modoVerEvento)
				{
					seleccionar2( );
				}
				else if(modoBuscar)
				{
					mostrarLista();
				}
				else
				{
					principal.mostrarPanelInicio();
				}
			}
		});
		btnAtras.setBackground(Color.RED);
		btnAtras.setFocusPainted(false);
		btnAtras.setBorderPainted(false);
		panelInferior.add(btnAtras);

		btnSeleccionar = new JButton("Selec");
		btnSeleccionar.setToolTipText("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar();
			}
		});
		btnSeleccionar.setBorderPainted(false);
		btnSeleccionar.setFocusPainted(false);
		btnSeleccionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSeleccionar.setBackground(Color.WHITE);
		panelInferior.add(btnSeleccionar);

		eventos = new JButton("Eventos");
		eventos.setBackground(Color.WHITE);
		eventos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0){
				verEventos( );
			}
		});
		eventos.setFocusPainted(false);
		eventos.setBorderPainted(false);

		buscar = new JButton("Buscar");
		buscar.setBackground(SystemColor.textHighlight);
		buscar.setForeground(Color.WHITE);
		buscar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		buscar.setFocusPainted(false);
		buscar.setBorderPainted(false);
		buscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelInferior.add(buscar);

		panelLugar = new JPanel();
		labelNombre = new JLabel( );
		labelNombre.setForeground(Color.WHITE);
		panelLugar.setLayout(new BorderLayout());
		panelLugar.setBackground(Color.BLACK);
		labImagen = new JLabel();
		panelLugar.add(labelNombre, BorderLayout.NORTH);
		panelLugar.add(labImagen, BorderLayout.CENTER);

		{
			panelEventos = new JPanel();
			panelEventos.setLayout(new BorderLayout(0, 0));

			JPanel panel_1 = new JPanel();
			panelEventos.add(panel_1, BorderLayout.CENTER);
			panel_1.setPreferredSize(new Dimension(10, 22));
			panel_1.setBackground(Color.BLACK);
			panel_1.setLayout(new BorderLayout(0, 0));

			JPanel panel_3 = new JPanel();
			panel_3.setPreferredSize(new Dimension(10, 23));
			panel_3.setBackground(Color.BLACK);
			panel_1.add(panel_3);
			panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

			JLabel lblCategora = new JLabel("Evento");
			panel_3.add(lblCategora);
			lblCategora.setPreferredSize(new Dimension(70, 14));
			lblCategora.setForeground(Color.WHITE);
			lblCategora.setHorizontalTextPosition(SwingConstants.CENTER);
			lblCategora.setHorizontalAlignment(SwingConstants.CENTER);

			textNombreEvento = new JTextField();
			panel_3.add(textNombreEvento);
			textNombreEvento.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			textNombreEvento.setForeground(Color.WHITE);
			textNombreEvento.setBackground(Color.BLACK);
			textNombreEvento.setPreferredSize(new Dimension(10, 10));
			textNombreEvento.setColumns(10);

			JButton btnGuardar = new JButton("Crear");
			panel_3.add(btnGuardar);
			btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnGuardar.setForeground(Color.WHITE);
			btnGuardar.setBackground(SystemColor.textHighlight);

			JPanel panel_4 = new JPanel();
			panel_4.setBorder(null);
			panel_4.setBackground(Color.BLACK);
			panel_1.add(panel_4, BorderLayout.SOUTH);
			panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

			JLabel lblHoraInicio = new JLabel(" Inicio ");
			panel_4.add(lblHoraInicio);
			lblHoraInicio.setForeground(Color.WHITE);

			comboInicio = new JComboBox();
			panel_4.add(comboInicio);
			comboInicio.setForeground(Color.WHITE);
			comboInicio.setBorder(null);
			comboInicio.setBackground(Color.BLACK);
			for(int i=0; i<=23; i++)
			{
				comboInicio.addItem(i+":00");
			}

			JLabel lblHoraFinal = new JLabel(" Final ");
			panel_4.add(lblHoraFinal);
			lblHoraFinal.setForeground(Color.WHITE);

			comboFinal = new JComboBox();
			panel_4.add(comboFinal);
			comboFinal.setBorder(null);
			comboFinal.setForeground(Color.WHITE);
			comboFinal.setBackground(Color.BLACK);
			comboFinal.setBackground(Color.BLACK);
			for(int i=0; i<=23; i++)
			{
				comboFinal.addItem(i+":00");
			}
			comboInicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comboFinal.removeAllItems();
					for(int i=comboInicio.getSelectedIndex();i<=23;i++)
					{
						comboFinal.addItem(i+":00");
					}
				}
			});
			checkDia = new JCheckBox("Dia");
			checkDia.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					if(!checkDia.isSelected())
					{
						comboInicio.setEnabled(true);
						comboFinal.setEnabled(true);
					}
					else
					{
						comboInicio.setEnabled(false);
						comboFinal.setEnabled(false);
					}
				}
			});
			panel_4.add(checkDia);
			checkDia.setBackground(Color.BLACK);
			checkDia.setForeground(Color.WHITE);

			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if ( !textNombreEvento.getText().equals(""))
					{
						String hora = "";
						if(checkDia.isSelected())
						{
							hora = "Todo el día";
						}
						else
						{
							hora = (String)comboInicio.getSelectedItem()+" - "+(String)comboFinal.getSelectedItem();
						}
						principal.crearEvento(textNombreEvento.getText(), listaLugares.getSelectedValue(),hora);
						actualizarEventos();
						textNombreEvento.setText("");
					}	
				}
			});

			JPanel panel_5 = new JPanel();
			panel_5.setPreferredSize(new Dimension(10, 218));
			panel_5.setBackground(Color.BLACK);
			panelEventos.add(panel_5, BorderLayout.SOUTH);
			panel_5.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBackground(Color.BLACK);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			panel_5.add(scrollPane);

			listaEventos = new JList();
			listaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaEventos.setCellRenderer(new MyMultilineRenderer(Color.BLACK));
			listaEventos.setForeground(Color.WHITE);
			listaEventos.setBackground(Color.BLACK);

			scrollPane.setViewportView(listaEventos);

			JPanel panel_7 = new JPanel();
			panel_7.setBackground(Color.DARK_GRAY);
			scrollPane.setColumnHeaderView(panel_7);

			JLabel lblCategorasCreadas = new JLabel("Eventos Creados");
			lblCategorasCreadas.setForeground(Color.WHITE);
			panel_7.add(lblCategorasCreadas);
			lblCategorasCreadas.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategorasCreadas.setHorizontalTextPosition(SwingConstants.CENTER);
			lblCategorasCreadas.setBackground(Color.BLACK);

			JPanel panel_6 = new JPanel();
			panel_6.setBackground(Color.BLACK);
			panel_6.setPreferredSize(new Dimension(10, 16));
			panel_5.add(panel_6, BorderLayout.NORTH);
			panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

			JPanel panel_2 = new JPanel();
			panel_2.setBackground(Color.BLACK);
			panel_2.setPreferredSize(new Dimension(10, 16));
			panelEventos.add(panel_2, BorderLayout.NORTH);
		}
		{
			panelBusqueda = new JPanel();
			panelBusqueda.setBackground(Color.BLACK);
			panelBusqueda.setLayout(new BorderLayout(0, 0));

			JPanel paneltemp = new JPanel();
			paneltemp.setLayout(new BoxLayout(paneltemp, BoxLayout.X_AXIS));
			panelBusqueda.add(paneltemp, BorderLayout.NORTH);

			comboBox = new JComboBox();
			comboBox.addItem("Categoria");
			comboBox.addItem("Nombre");
			paneltemp.add(comboBox);

			textBusqueda = new JTextField();
			paneltemp.add(textBusqueda);
			textBusqueda.setColumns(10);

			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					if(!textBusqueda.getText().equals(""))
					{
						if(comboBox.getSelectedIndex()==0)
						{
							refinarCat(textBusqueda.getText());
						}
						else if(comboBox.getSelectedIndex()==1)
						{
							refinarNomb(textBusqueda.getText());
						}
					}
				}
			});
			btnBuscar.setForeground(Color.WHITE);
			btnBuscar.setFocusPainted(false);
			btnBuscar.setBorderPainted(false);
			btnBuscar.setBackground(SystemColor.textHighlight);
			paneltemp.add(btnBuscar);

			JScrollPane scrollPane = new JScrollPane();
			panelBusqueda.add(scrollPane, BorderLayout.CENTER);

			listaFiltrada = new JList();
			listaFiltrada.setCellRenderer(new MyIconListRenderer());
			listaFiltrada.setForeground(Color.WHITE);
			listaFiltrada.setBackground(Color.BLACK);
			scrollPane.setViewportView(listaFiltrada);
		}
	}

	@SuppressWarnings("unchecked")
	private void actualizarEventos( )
	{
		listaEventos.setListData(principal.darEventos((Lugar) (modoBuscar?listaFiltrada.getSelectedValue():listaLugares.getSelectedValue())));
	}

	private void mostrarLista( )
	{
		remove(actual);
		actual = scroll1;;
		add(this.actual, BorderLayout.CENTER);
		panelInferior.remove(eventos);
		panelInferior.add(btnSeleccionar);
		panelInferior.add(buscar);
		revalidate();
		repaint();
		modoSeleccion = false;
		modoVerEvento = false;
		modoBuscar = false;
	}

	private void seleccionar2( )
	{
		if(modoBuscar)
		{
			if(0 <= listaFiltrada.getSelectedIndex())
			{
				remove(actual);
				actual = panelLugar;
				add(this.actual, BorderLayout.CENTER);
				labelNombre.setText(listaFiltrada.getSelectedValue().toString());
				panelInferior.add(eventos);
				revalidate();
				repaint();
				modoVerEvento = false;
				modoSeleccion = true;
			}
			return;
		}
		else
		{
			remove(actual);
			actual = panelLugar;
			add(this.actual, BorderLayout.CENTER);
			labelNombre.setText(listaLugares.getSelectedValue().toString());
			panelInferior.add(eventos);
			revalidate();
			repaint();
			modoVerEvento = false;
			modoSeleccion = true;
			modoBuscar = false;
		}
	}

	private void verEventos( )
	{
		actualizarEventos();
		if(modoBuscar)
		{
			remove(actual);
			actual = panelEventos;
			add(this.actual, BorderLayout.CENTER);
			panelInferior.remove(eventos);
			revalidate();
			repaint();
			modoVerEvento = true;
			modoSeleccion = false;
		}
		else
		{
			remove(actual);
			actual = panelEventos;
			add(this.actual, BorderLayout.CENTER);
			panelInferior.remove(eventos);
			revalidate();
			repaint();
			modoVerEvento = true;
			modoSeleccion = false;
			modoBuscar = false;
		}

	}

	private void seleccionar( )
	{
		if(modoBuscar)
		{
			if(0 <= listaFiltrada.getSelectedIndex())
			{
				remove(actual);
				actual = panelLugar;
				add(actual, BorderLayout.CENTER);
				lugarActual = (Lugar) listaFiltrada.getSelectedValue();
				labelNombre.setText("Nombre: "+lugarActual.getNombre());
				panelInferior.remove(btnSeleccionar);
				panelInferior.remove(buscar);
				panelInferior.add(eventos);
				mostrarImagen();
				revalidate();
				repaint();
				modoVerEvento = false;
				modoSeleccion = true;
			}
		}
		else if(0 <= listaLugares.getSelectedIndex())
		{	
			remove(actual);
			actual = panelLugar;
			add(actual, BorderLayout.CENTER);
			lugarActual = (Lugar) listaLugares.getSelectedValue();
			labelNombre.setText("Nombre: "+lugarActual.getNombre());
			panelInferior.remove(btnSeleccionar);
			panelInferior.remove(buscar);
			panelInferior.add(eventos);
			mostrarImagen();
			revalidate();
			repaint();
			modoVerEvento = false;
			modoSeleccion = true;
			modoBuscar = false;
		}
	}

	private void mostrarImagen() {	

		try {		
			if(botonesImagen!=null)
			{
				panelLugar.remove(botonesImagen);
			}
			JButton butAumentar = new JButton("+");
			butAumentar.setForeground(Color.WHITE);
			butAumentar.setBackground(Color.BLACK);
			butAumentar.setBorderPainted(false);
			butAumentar.setFocusPainted(false);
			butAumentar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					zoom++;
					actualizarImagen();
				}
			});

			JButton butDisminuir = new JButton ("-");
			butDisminuir.setForeground(Color.WHITE);
			butDisminuir.setBackground(Color.BLACK);
			butDisminuir.setBorderPainted(false);
			butDisminuir.setFocusPainted(false);
			butDisminuir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					zoom--;
					actualizarImagen();
				}
			});
			botonesImagen = new JPanel();
			botonesImagen.setBackground(Color.black);
			botonesImagen.setLayout(new GridLayout( 1, 4));
			botonesImagen.add(new JLabel(""));
			botonesImagen.add(butDisminuir);
			botonesImagen.add(butAumentar);
			botonesImagen.add(new JLabel(""));
			panelLugar.add(botonesImagen , BorderLayout.SOUTH);
			actualizarImagen();	
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void buscar()
	{
		remove(actual);
		actual = panelBusqueda;
		add(this.actual, BorderLayout.CENTER);
		panelInferior.remove(buscar);
		panelInferior.remove(eventos);
		panelInferior.add(btnSeleccionar);
		revalidate();
		repaint();
		modoVerEvento = false;
		modoSeleccion = false;
		modoBuscar = true;
	}

	private void refinarNomb(String text)
	{
		IListaSimple <Lugar> lista = new Arreglo<Lugar>();
		for (int i = 0; i < lugares.length; i++) {
			if ( ((Lugar) lugares[i]).getNombre().contains(text));
			lista.agregar((Lugar) lugares[i]);
		}

		listaFiltrada.setListData(lista.toArray());
	}

	private void refinarCat(String text)
	{
		IListaSimple <Lugar> lista = new Arreglo<Lugar>();
		for (int i = 0; i < lugares.length; i++) {
			if ( ((Lugar) lugares[i]).getCategoria().equalsIgnoreCase(text))
				lista.agregar((Lugar) lugares[i]);
		}

		listaFiltrada.setListData(lista.toArray());
	}

	private void actualizarImagen(){

		Double lat = lugarActual.getLatitud();
		Double lon = lugarActual.getLongitud();
		try
		{			
			url = new URL("http://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lon+"&zoom="+zoom+"&size=300x400&sensor=false");
			image = ImageIO.read(url);
			labImagen.setIcon(new ImageIcon(image));
			panelLugar.revalidate();
			panelLugar.repaint();
			revalidate();
			repaint();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}