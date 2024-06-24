package clases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	
	private Hashtable <String, Tarea> tareas;
	private List<Tarea> tareasCriticas;
	private List<Tarea> tareasNoCriticas;
	private LinkedList<Procesador> procesadores;
	

	/*
     * Expresar la complejidad temporal del constructor. La complejidad es O(n) ya que recorre todas las tareas y los procesadores
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		procesadores=reader.readProcessors(pathProcesadores);
		this.tareas= new Hashtable<>();
		this.tareasCriticas=new LinkedList<>();
		this.tareasNoCriticas=new LinkedList<>();
		List<Tarea> todasLasTareas=reader.readTasks(pathTareas);
		agregarTareas(todasLasTareas);
	}
	
	/*Se encarga de recorrer todas las tareas, agregarlas tanto en el hashtable como en la lista de criticas o no 
	 * dependiendo del valor que tenga la tarea
	 * */
	private void agregarTareas(List<Tarea> todasLasTareas) {
		Iterator iterador=todasLasTareas.iterator();
		while(iterador.hasNext()) {
			Tarea t=(Tarea) iterador.next();
			tareas.put(t.getID(), t);
			if(t.isCritica()) {
				tareasCriticas.add(t);
			}
			else {
				tareasNoCriticas.add(t);
			}
		}
		
	}

	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	//La complejidad será constante O(1) porque las tareas tienen un ID único por lo cual busco por este.
	public Tarea servicio1(String ID) {
		return tareas.get(ID);
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
	//Tiene una complejidad temporal constante de O(1) ya que solo se le pide la lista correspondiente
	//si desea que sea critica o no
	public List<Tarea> servicio2(boolean esCritica) {
		if(esCritica) {
			return this.tareasCriticas;
		}
		return this.tareasNoCriticas;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     * 
     */
	//Tiene una complejidad de O(n) donde n es la cantidad de tareas que contiene la lista, ya que se debe recorrer todos los elementos 
	
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		Iterator iterador=this.tareas.values().iterator();
		List<Tarea> tareasQueCumplen=new ArrayList<>();
		while(iterador.hasNext()) {
			Tarea t=(Tarea) iterador.next();
			if(t.getNivelPrioridad()>prioridadInferior && t.getNivelPrioridad()<prioridadSuperior) {
				tareasQueCumplen.add(t);
			}
			
		}
		//En caso de encontrar una tarea que cumpla con el rango pasado por los parametros.
		if(tareasQueCumplen.size()>0) {
			return tareasQueCumplen;
		}
		//Si no encontró ninguna tarea según el rango
		else {
			return null;
		}
		
	}
}
