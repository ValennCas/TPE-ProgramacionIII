package clases;

public class Tarea implements Comparable{
	private String ID;
	private String nombre;
	private float tiempoEjecucion;
	private boolean critica;
	private int nivelPrioridad;
	
	public Tarea(String ID, String nombre, float tiempoEjecucion, boolean critica, int nivelPrioridad) {
		this.ID = ID;
		this.nombre = nombre;
		this.tiempoEjecucion = tiempoEjecucion;
		this.critica = critica;
		this.nivelPrioridad = nivelPrioridad;
	}
	public String getID() {
		return ID;
	}
	public String getNombre() {
		return nombre;
	}
	public float getTiempoEjecucion() {
		return tiempoEjecucion;
	}
	public boolean isCritica() {
		return critica;
	}
	public int getNivelPrioridad() {
		return nivelPrioridad;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTiempoEjecucion(float tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}
	public void setCritica(boolean critica) {
		this.critica = critica;
	}
	public void setNivelPrioridad(int nivelPrioridad) {
		this.nivelPrioridad = nivelPrioridad;
	}
	
	/*
	 * Se declara el toString para una mejor lectura sobre los datos de una tarea en el main
	 */
    public String toString() {
        return "\n Tarea: "+this.ID+"." +
                "\n Nombre: "+this.nombre+"." +
                "\n Tiempo de ejecucion: "+this.tiempoEjecucion+ "." +
                "\n Es critica: "+this.critica+"." +
                "\n Nivel de prioridad: "+this.nivelPrioridad+"\n";
    }
    
    /*
     * Se le implementa a la clase la interfaz "Comparable" y se declara el compareTo
     * para ordenar las tareas según su tiempo de ejecución (de menor a mayor)
     * y así obtener una lista ordenada para usarse en el Greedy
     */
	@Override
	public int compareTo(Object o) {
		Tarea t=(Tarea) o;
		return (int) (this.getTiempoEjecucion()-t.getTiempoEjecucion());
	}

}
