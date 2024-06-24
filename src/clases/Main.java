package clases;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");
		System.out.println("\n Servicio 1:");
		Tarea tarea=servicios.servicio1("T3");
		if(tarea!=null) {
			System.out.println(tarea.toString());
		}
		else {
			System.out.println("No existe esa tarea.");
		}
		
		
		System.out.println("\n Servicio 2:");
		List<Tarea> listaTareasCriticas=servicios.servicio2(true);
		for(int i=0;i<listaTareasCriticas.size();i++) {
			System.out.println(listaTareasCriticas.get(i).toString());
		}
		
		
		System.out.println("\n Servicio 3:");
		List<Tarea> tareasPorRango=servicios.servicio3(20, 40);
		if(tareasPorRango!=null) {
			for(int i=0;i<tareasPorRango.size();i++) {
				Tarea t=tareasPorRango.get(i);
				System.out.println(t.toString());
			}
		}
		else {
			System.out.println("No hay tareas que se encuentren en ese rango.");
		}
		
		System.out.println("- - - - - - - - - - - - - -");
		System.out.println("\n Solucion Backtracking: ");
		Backtracking backtracking=new Backtracking("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");
		Solucion s=backtracking.asignarTareasBacktraking(30);
		if(s!=null) {
			List<Procesador> procesadores=s.getProcesadores();
			for(int i=0;i<procesadores.size();i++) {
				Procesador p=procesadores.get(i);
					System.out.println(p.toString());
				System.out.println("- - - - - - - - - - - - - -");
			}
			System.out.println("El tiempo máximo de ejecución será: "+s.getTiempoMax());
			System.out.println("La cantidad de estados generados son: "+s.getEstadosTotales());
		}
		else {
			System.out.println("No se encontró una solución");
		}
		
		
		System.out.println("- - - - - - - - - - - - - -");
		
		System.out.println("\n Solucion Greedy: ");
		Greedy greedy= new Greedy("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");
		Solucion solucionGreedy=greedy.asignarTareasGreedy(20);
		if(solucionGreedy!=null) {
			
			List<Procesador> procesadoresGreedy=solucionGreedy.getProcesadores();
			
			for(Procesador procesador:procesadoresGreedy) {
				System.out.println(procesador.toString());
				System.out.println("- - - - - - - - - - - - - -");
			}
			
			System.out.println("El tiempo máximo de ejecución será: "+solucionGreedy.getTiempoMax());
			System.out.println("La cantidad de candidatos considerados son: "+solucionGreedy.getEstadosTotales());
			
			System.out.println("- - - - - - - - - - - - - -");
		}
		else {
			System.out.println("No se encontró una solución");
		}
		
	}
}
