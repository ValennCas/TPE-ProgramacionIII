package clases;

import java.util.LinkedList;
import java.util.List;

public class Candidatos {
	private LinkedList<Procesador> procesadores;
	private LinkedList<Tarea> tareas;
	private int tiempoMax;
	private int candidatosEnCuenta;
	
	public Candidatos(int tiempoMax) {
		this.procesadores=new LinkedList<>();
		this.tareas=new LinkedList<>();
		this.tiempoMax=tiempoMax;
		this.candidatosEnCuenta=0;
	}
    
	/*Verifica que la tarea sea apta para el procesador a través de los pasos:
	  ve si la tarea no es critica ya que no será una limitación en agregarla en un procesador que no puede tener más de dos tareas criticas
	  Además, controla la cantidad de tareas criticas que tiene el procesador
	  Segundo, pregunta si el procesador esta refrigerado, si lo está retornará true.
	  En caso de no estarlo, sumará los tiempo de ejecucion del procesador + la tarea nueva 
	  para comprobar que no pasa un tiempo especifico
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
	public int getCandidatos() {
		return this.candidatosEnCuenta;
	}
	public void setCandidatos(int candidatos) {
		this.candidatosEnCuenta=candidatos;
	}
	public void setTareas(List<Tarea> tareas) {
		this.tareas.addAll(tareas);
	}
	
	//Le encarga al procesador que agregue una tarea
	public void agregarTarea(Procesador p, Tarea tarea) {
		p.agregarTarea(tarea);
	}

	public Tarea obtenerPrimeraTarea() {
		return this.tareas.get(0);
	}

	public void borrarTarea() {
		this.tareas.remove(0);
	}


	public int sizeProceadores() {
		return procesadores.size();
	}
	public int sizeTareas() {
		return tareas.size();
	}

	/*Se va incrementando la cantidad de candidatos que se tienen en cuenta.
	 * Recorre los procesadores donde, comprueba de que, si la tarea en factible para ese procesador pasará a la siguiente
	 * comprobación donde se verifica si el tiempo de ejecucion total 
	 * (la suma de=  procesador.obtenerTiempoEjecucion()+tarea.getTiempoEjecucion()) es menor al tiempoEjecucion que se va guardando
	 * (o si tiempoEjecucion es cero, donde seria la primera iteración); si resulta ser verdadero, guardará el procesador y el
	 * tiempo de ejecución total.
	 * Si nunca se encontró un procesador que sea factible retornará null, o si lo encontró retornará el procesador correspondiente.
	 */
	public Procesador obtenerMejorProcesadorParaLaTarea(Tarea tarea) {
		Procesador p=null;
		float tiempoEjecucion=0;
		for(Procesador procesador:this.devolverProcesadores()){
			this.candidatosEnCuenta++;
			if(this.esFactible(tarea, procesador)) { //Verifica si la tarea es factible para el procesador
				if(tiempoEjecucion==0 || (procesador.obtenerTiempoEjecucion()+tarea.getTiempoEjecucion())<tiempoEjecucion){
					p=procesador;
					tiempoEjecucion=procesador.obtenerTiempoEjecucion()+tarea.getTiempoEjecucion();
				}
			}
		}
		if(p!=null) {
			return p;
		}
		return null;
	}
}
