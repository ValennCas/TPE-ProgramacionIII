package clases;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import utils.CSVReader;

public class Greedy {

	
	private LinkedList<Tarea> todasLasTareas;
	private LinkedList<Procesador> procesadores;
	
	public Greedy(String pathProcesadores, String pathTareas) {
		CSVReader reader = new CSVReader();
		this.procesadores=reader.readProcessors(pathProcesadores);
		this.todasLasTareas=reader.readTasks(pathTareas);
	}
	
	/*
	  La estrategia greedy fue: 
	  Primero ordenar las tareas de menor a mayor segun su tiempo de ejecución y en el bucle, obtener siempre la primera (y a continuación eliminarla)
	  Obtener el mejor procesador factible para la tarea para lograr la mejor optimización posible (el mejor procesador será
	  el que, respetando los limites que se tiene(no más de dos tareas criticas, si el procesador se encuentra refrigerado o
	  si no se encuentra no puede pasar cierto tiempo de ejecución pasado por parametro) de un tiempo de ejecución total (el tiempo 
	  de ejecución que va guardando el procesador + el tiempo de ejecución de la tarea) más bajo).
	  En caso de no poder asignarse la tarea, se retornará directamente null para cortar el bucle e informar que no se encontró una solución.
	  Si se encuentra una solución se guardan los procesadores, la cantidad de candidatos que se tuvieron en cuenta y se retorna solución.
	 */

	public Solucion asignarTareasGreedy(int tiempoMax){
		Solucion solucion=new Solucion();
		Candidatos candidatos=new Candidatos(tiempoMax);
		candidatos.setProcesadores(procesadores); //Setea los proesadores a candidatos
		Collections.sort(this.todasLasTareas);
		candidatos.setTareas(todasLasTareas); //Setea tareas a candidatos
		while(candidatos.sizeTareas()>0){
			Tarea tarea=candidatos.obtenerPrimeraTarea(); //Obtiene la primera tarea
			candidatos.borrarTarea(); //Borra la tarea de la lista de tareas
			Procesador p=candidatos.obtenerMejorProcesadorParaLaTarea(tarea);
			if(p!=null) {
				candidatos.agregarTarea(p, tarea); //Agrega la tarea al procesador
			}
			else {
				return null;
			}
		}
		solucion.actualizarProcesadores(candidatos.devolverProcesadores());//Se guardan los procesadores
		solucion.setEstadosTotales(candidatos.getCandidatos());
		return solucion;
	}
}
