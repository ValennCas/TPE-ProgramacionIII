package clases;

import java.util.LinkedList;
import java.util.List;

public class Solucion {
	private LinkedList<Procesador> procesadores;
	private float tiempoMaxEjecucion;
	private int estadosTotales;
	
	public Solucion() {
		this.procesadores=new LinkedList<>();
		this.tiempoMaxEjecucion=0;
		this.estadosTotales=0;
	}
	
	public float tiempoMasAltoEjecucion() {
		return tiempoMaxEjecucion;
	}

	/*
	 * Primero limpia la lista de procesadores en caso de ya tener elementos y vuelve el tiempoMaxEjecucion en cero
	 * Recorre los procesadores pasados por parametro, los va guardando en la lista de procesadores en Solucion y 
	 * va actualizando el tiempo de ejecucion para quedarse con el mayor tiempo de ejecucion que haya
	 */
	public void actualizarProcesadores(LinkedList<Procesador> procesadores) {
		this.procesadores.clear();
		this.tiempoMaxEjecucion=0;
		for(int i=0;i<procesadores.size();i++) {
			Procesador p=procesadores.get(i);
			Procesador pNuevo=new Procesador(p);
			this.procesadores.add(pNuevo);
			if(pNuevo.obtenerTiempoEjecucion()>tiempoMaxEjecucion) {
				tiempoMaxEjecucion=pNuevo.obtenerTiempoEjecucion();
			}
		}
	}
	
	public List<Procesador> getProcesadores() {
		return procesadores;
	}

	public float getTiempoMax() {
		return this.tiempoMaxEjecucion;
	}

	public int getEstadosTotales() {
		return estadosTotales;
	}

	public void setEstadosTotales(int estadosTotales) {
		this.estadosTotales = estadosTotales;
	}

	public boolean NoSeEncuentraVacia() {
		return this.getProcesadores().size()>0;
	}
	
	
}
