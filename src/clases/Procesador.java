package clases;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;

public class Procesador {
	private String id;
	private String codigo;
	private boolean estaRefrigerado;
	private Integer anio;
	private LinkedList<Tarea> tareasAsignadas;
	private float tiempoEjecucion;
	private int cantDeTareasCriticas;
	
	public Procesador(String id, String codigo, boolean refrigerado, Integer anio) {
		this.id=id;
		this.codigo=codigo;
		this.estaRefrigerado=refrigerado;
		this.anio=anio;
		this.tareasAsignadas=new LinkedList<>();
		tiempoEjecucion=0;
		this.cantDeTareasCriticas=0;
	}
	
	//Se genera este constructor para obtener una copia de un procesador
	 public Procesador(Procesador procesador) {
	        this.id = procesador.getId();
	        this.codigo = procesador.getCodigo();
	        this.estaRefrigerado = procesador.isEstaRefrigerado();
	        this.anio = procesador.getAnio();
	        this.tareasAsignadas=new LinkedList<>(procesador.getTareasAsignadas()); // Copia de toda la lista de tareas
	        this.tiempoEjecucion=procesador.obtenerTiempoEjecucion();
	        this.cantDeTareasCriticas=procesador.getCantTareasCriticas();
	    }

	private int getCantTareasCriticas() {
		return this.cantDeTareasCriticas;
	}
	public String getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public boolean isEstaRefrigerado() {
		return estaRefrigerado;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setEstaRefrigerado(boolean estaRefrigerado) {
		this.estaRefrigerado = estaRefrigerado;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	//Agrega la tarea, suma el tiempo de ejecución y, si la tarea es critica, aumenta la cantidad de tareas criticas
	public void agregarTarea(Tarea tarea) {
		tareasAsignadas.add(tarea);
		tiempoEjecucion+=tarea.getTiempoEjecucion();
		if(tarea.isCritica()) {
			cantDeTareasCriticas++;
		}
		
	}
	public float obtenerTiempoEjecucion() {
		return tiempoEjecucion;
	}
	
	//Remueve la tarea, descuenta el tiempo de ejecución y, si la tarea es critica, descuenta la cantidad de tareas criticas
	public void eliminarTarea(Tarea tarea) {
		tareasAsignadas.remove(tarea);
		tiempoEjecucion=tiempoEjecucion-tarea.getTiempoEjecucion();
		if(tarea.isCritica()) {
			cantDeTareasCriticas--;
		}
		
	}

	//Verifica que la cantidad de tarea criticas agregadas sea menor a dos
	public boolean controlDeEjecutarTareasCriticas() {
		if(this.cantDeTareasCriticas<2) {
			return true;
		}
		return false;
	}
	
	public LinkedList<Tarea> getTareasAsignadas(){
		return this.tareasAsignadas;
	}
	
	/*
	 * Se declara el toString para una mejor lectura sobre los datos de un procesador en el main
	 */
    public String toString() {
        return "\n ID de procesador: "+this.id+".\n " +
                "Codigo:"+this.codigo+".\n " +
                "Se encuentra refrigerado:"+this.estaRefrigerado+".\n " +
                "Año de funcionamiento:"+this.anio+".\n " +
                "Tareas asignadas: "+this.tareasAsignadas+". \n";
    }
}
