package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mundo.Lugar;

public class MyIconListRenderer extends DefaultListCellRenderer
{
	private JPanel panel;
	private JTextArea texto;
	private JLabel icono;
	
	public MyIconListRenderer()
	{
		super();
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BorderLayout());
		texto = new JTextArea();
		texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setForeground(Color.WHITE);
        texto.setBackground(Color.BLACK);
        panel.add(texto, BorderLayout.CENTER);	
        icono = new JLabel();
        panel.add(icono, BorderLayout.WEST);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		Icon icon = ((Lugar) value).getIcon();
		icono.setIcon(icon);
		if(isSelected)
		{
			panel.setBackground(SystemColor.textHighlight);
			texto.setBackground(SystemColor.textHighlight);
		}
		else
		{
			panel.setBackground(Color.BLACK);
			texto.setBackground(Color.BLACK);
		}
        texto.setText(value.toString());
        int width = list.getWidth();
        if (width > 0)
            texto.setSize(width, Short.MAX_VALUE);
		return panel;
	}
}