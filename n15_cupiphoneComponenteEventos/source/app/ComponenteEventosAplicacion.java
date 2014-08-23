package app;

import interfaz.ComponenteEventosPanel;
import interfaz.PanelInicio;

import javax.swing.JPanel;

import mundo.ComponenteEventos;
import uniandes.cupi2.cupIphone.componentes.IAplicacion;
import uniandes.cupi2.cupIphone.core.ICore;

public class ComponenteEventosAplicacion implements IAplicacion
{
	/**
	 * Referencia al core del cupiPhone, para localizar otros componentes y acceder al directorio de datos
	 */
	private ICore core;

	/**
	 * Panel principal del componente
	 */
	private ComponenteEventosPanel panel;

	/**
	 * Clase principal del mundo del componente
	 */
	private ComponenteEventos mundo;

	/**
	 * Esta aplicación se implementa como un singleton
	 */
	private static ComponenteEventosAplicacion instancia;

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#darPanelPrincipal()
	 */
	public JPanel darPanelPrincipal()
	{

		return panel;
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#terminarEjecucion()
	 */
	public void terminarEjecucion()
	{
		panel.terminar();
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#darInstanciaModelo()
	 */
	public Object darInstanciaModelo()
	{
		return mundo;
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#cambiarCore(uniandes.cupi2.cupIphone.core.ICore)
	 */
	public void cambiarCore(ICore c)
	{
		core = c;
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#iniciarEjecucion()
	 */
	public void iniciarEjecucion()
	{
		mundo = new ComponenteEventos(core);
		panel = new ComponenteEventosPanel(mundo);
	}

	/**
	 * Método necesario para interactuar con el core
	 * del teléfono
	 * @return La instancia de la clase
	 */
	public static IAplicacion darInstancia()
	{
		return instancia!=null? instancia: new ComponenteEventosAplicacion();
	}
}