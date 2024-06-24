package clases;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import utils.CSVReader;

public class Estado {
	private LinkedList<Procesador> procesadores;
	private LinkedList<Tarea> tareas;
	private int tiempoMax;
	private int estadosGenerados;
	private float tiempoMaxEjecucion;
	
	public Estado(int tiempoMax) {
		this.procesadores=new LinkedList<>();
		this.tareas=new LinkedList<>();
		this.tiempoMax=tiempoMax;
		this.estadosGenerados=0;
		this.tiempoMaxEjecucion=0;
	}
    
	/*Verifica que la tarea sea apta para el procesador a través de los pasos:
	  ve si la tarea no es critica ya que no será una limitación en agregarla en un procesador que no puede tener más de dos tareas criticas
	  Además, controla la cantidad de tareas criticas que tiene el procesador
	  Segundo, pregunta si el procesador esta refrigerado, si lo está retornará true.
	  En caso de no estarlo, sumará los tiempo de ejecucion del procesador + la tarea nueva para comprobar que no pasa un tiempo especifico
	  Si no cumple, retornará false.
	*/
	public boolean esFactible(Tarea tareaAAsignar, Procesador procesador) {
		if(tareaAAsignar.isCritica()==false || procesador.controlDeEjecutarTareasCriticas()){
			if(procesador.isEstaRefrigerado()){
				return true;
			}
			else {
				if((procesador.obtenerTiempoEjecucion()+tareaAAsignar.getTiempoEjecucion())<=tiempoMax) {
					return true;
				}
			}
		}
		return false;
	}
	
	public LinkedList<Procesador> devolverProcesadores() {
		return this.procesadores;
	}
	
	public void setProcesadores(List<Procesador> procesadores) {
		for (Procesador p : procesadores) {
            this.procesadores.add(new Procesador(p));
        }
	}
	
	public void setTareas(List<Tarea> tareas) {
		this.tareas.addAll(tareas);
	}

	/*Recorre los procesadores para encontrar el que tenga el mayor tiempo de ejecución
	 * y se lo asignará a tiempoMaxEjecución.
	 */
	public void actualizarTiempoEjecucion() {
		this.tiempoMaxEjecucion=0;
		for(int i=0;i<procesadores.size();i++) {
			Procesador p=procesadores.get(i);
			if(this.tiempoMaxEjecucion<p.obtenerTiempoEjecucion() || this.tiempoMaxEjecucion==0) {
				this.tiempoMaxEjecucion=p.obtenerTiempoEjecucion();
			}
		}
	}
	
	public float getTiempoEjecucion() {
		return this.tiempoMaxEjecucion;
	}

	//Le encarga al procesador que elimine una tarea en especifico y actualiza el tiempo de ejecución
	public void eliminarTarea(Procesador p, Tarea tarea) {
		p.eliminarTarea(tarea);
		this.actualizarTiempoEjecucion();
	}
	
	//Le encarga al procesador que agregue una tarea, además comprueba, una vez que se agregó la tarea,
	//si el tiempo de ejecución de este procesador, es mayor al tiempoMaxEjecucion que se tiene guardado.
	public void agregarTarea(Procesador p, Tarea tarea) {
		p.agregarTarea(tarea);
		if(this.tiempoMaxEjecucion<p.obtenerTiempoEjecucion() || this.tiempoMaxEjecucion==0) {
			this.tiempoMaxEjecucion=p.obtenerTiempoEjecucion();
		}
	}

	public boolean todasLasTareasAsignadas() {
		return tareas.size()==0;
	}

	public Tarea obtenerPrimeraTarea() {
		return this.tareas.get(0);
	}

	public void borrarTarea() {
		this.tareas.remove(0);
	}

	public void agregarNuevamenteTarea(Tarea tareaAAsignar) {
		this.tareas.add(0,tareaAAsignar);
	}

	public int getEstadosGenerados() {
		return estadosGenerados;
	}

	public void setEstadosGenerados(int estadosGenerados) {
		this.estadosGenerados = estadosGenerados;
	}

	//Comprueba si, una tarea dada por parametro, fue asigna a un procesador, en caso de estarlo retorna true,
	//En caso de no estarlo retornará false.
	public boolean noSeEncuentraAsignada(Tarea tareaAAsignar) {
		for(Procesador p: this.procesadores) {
			if(p.getTareasAsignadas().contains(tareaAAsignar)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
}
