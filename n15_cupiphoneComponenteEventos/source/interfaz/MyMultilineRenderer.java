package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyMultilineRenderer extends DefaultListCellRenderer
{
	private JTextArea texto;
	private JPanel panel;
	private Color color;
	
	public MyMultilineRenderer(Color color)
	{
		super();
		this.color = color;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setLayout(new BorderLayout());
		texto = new JTextArea();
		texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setForeground(Color.WHITE);
        texto.setBackground(color);
        panel.add(texto, BorderLayout.CENTER);
	}
	
	@Override
    public Component getListCellRendererComponent(final JList list,
            final Object value, final int index, final boolean isSelected,
            final boolean hasFocus)
	{
		if(isSelected)
			texto.setBackground(SystemColor.textHighlight);
		else
			texto.setBackground(color);
        texto.setText(value.toString());
        int width = list.getWidth();
        if (width > 0)
            texto.setSize(width, Short.MAX_VALUE);
        return panel;

    }
}