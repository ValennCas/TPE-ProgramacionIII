package clases;

import java.util.LinkedList;
import java.util.List;

import utils.CSVReader;

public class Backtracking {

	private LinkedList<Tarea> todasLasTareas;
	private LinkedList<Procesador> procesadores;
	
	public Backtracking(String pathProcesadores, String pathTareas) {
		CSVReader reader = new CSVReader();
		this.procesadores=reader.readProcessors(pathProcesadores);
		this.todasLasTareas=reader.readTasks(pathTareas);
	}
	
	/*
	 * Crea un nuevo Estado, donde setea los procesadores y las tareas.
	 * Crea una solucion donde se guardará el mejor resultado obtenido (si existe) por backtracking.
	 * Si la solucion no se encuentra vacía va a guardar los estados totales que se han generado y se retornará la solución
	 * De contrario, si no hay solución retornará null.
	 */
	public Solucion asignarTareasBacktraking(int tiempoMax){
		Estado e=new Estado(tiempoMax);
		e.setProcesadores(procesadores);
		e.setTareas(todasLasTareas);
		Solucion solucion= new Solucion();
		asignarTareasBacktraking(e, solucion);
		if(solucion.NoSeEncuentraVacia()){
			solucion.setEstadosTotales(e.getEstadosGenerados());
			return solucion;
		}
		else {
			return null;
		}
	}

	/*Se lleva registro de la cantidad de estados que se van generando, además la estrategia utilizada es recorrer todas
	  las posibles soluciones y comprueba siempre quedarse con la que su tiempo máximo de ejecución resulta ser el menor
	  En caso de encontrar una mejor solucion, se le actualizará los procesadores a solución.
	  Si sucede de que una tarea no se puede asignar, hace un return para cortar la ejecución de la función.
	 */
	private void asignarTareasBacktraking(Estado e, Solucion solucion) {
		e.setEstadosGenerados(e.getEstadosGenerados()+1); //Actualiza la cantidad de estados
		if(e.todasLasTareasAsignadas()) { //Comprueba que todas las tareas han sido asignadas
			if(e.getTiempoEjecucion()<solucion.tiempoMasAltoEjecucion() || solucion.tiempoMasAltoEjecucion()==0){ //Comprueba si el tiempo de ejecucion es mejor que el que se guardo en solucion
				solucion.actualizarProcesadores(e.devolverProcesadores());
			}
		}
		else {
			Tarea tareaAAsignar=e.obtenerPrimeraTarea();
			for(Procesador procesador:e.devolverProcesadores()){
				if(e.esFactible(tareaAAsignar, procesador)) { //Verifica si la tarea es factible para el procesador
					e.borrarTarea();
					e.agregarTarea(procesador, tareaAAsignar); //agrega la tarea al procesador
					asignarTareasBacktraking(e, solucion);
					e.eliminarTarea(procesador, tareaAAsignar); //Elimina la tarea del procesador
					e.agregarNuevamenteTarea(tareaAAsignar); //Vuelve a agregar la tarea a la lista de tarea
				}
			}
			if(e.noSeEncuentraAsignada(tareaAAsignar)) {
				return;
			}
		}
	}
	
}
