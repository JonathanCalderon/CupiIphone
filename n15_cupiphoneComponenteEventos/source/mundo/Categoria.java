package mundo;



import java.io.Serializable;
import java.util.Iterator;

import interfaces.IListaSimple;
import estructuras.ArbolAVL;
import estructuras.Arreglo;

/**
 * Clase que modela las categorias del cupiEvento
 * @author Jonathan
 */
public class Categoria implements Serializable {


	private String nombre;

	private ArbolAVL<String, Lugar> lugares;

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
		lugares = new ArbolAVL <String ,Lugar>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArbolAVL<String, Lugar> darLugares ()
	{
		return lugares;
	}
	
	public IListaSimple <Lugar> darListaLugares(){
		
		IListaSimple<Lugar> listaLugares = new Arreglo<Lugar>();
		
		Iterator <Lugar> i = lugares.iterator();
		while ( i.hasNext()){
			
			Lugar actual = i.next();
			listaLugares.agregar(actual);
		}
		
		return listaLugares;
	}
	public String toString ()
	{
		return nombre;
	}
	
	public void adicionarLugar(Lugar lugar)
	{
		lugares.agregar(lugar.getNombre(), lugar);
		lugar.setCategoria(this.nombre);
	}
}
