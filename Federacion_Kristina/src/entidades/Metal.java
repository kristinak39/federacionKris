package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import utils.Datos;

//Ejercicio 5, parte A
public abstract class Metal {
	public abstract float maximaPurezaAlcanzada();
	public abstract float[] cotasPurezaEfectiva();
	public abstract float getPureza();
	
	public LocalDate fecha;
	public boolean asignada = false;
	
	
	public Metal() {
	}
	
	public Metal(LocalDate f, boolean b) {
		this.fecha = f;
		this.asignada = b;
	}
	
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public boolean isAsignada() {
		return asignada;
	}
	public void setAsignada(boolean asignada) {
		this.asignada = asignada;
	}
	
	
	
	
	
	
	public void mostrarMedallasOrdenadas() {
		ArrayList<Metal> listadoMedallas = new ArrayList<>();
		
		listadoMedallas.addAll(Arrays.asList(Datos.OROS));
		listadoMedallas.addAll(Arrays.asList(Datos.PLATAS));
		listadoMedallas.addAll(Arrays.asList(Datos.BRONCES));
		
		System.out.println("Listado de medallas desordenadas\n");  
		
		for (Iterator iterator = listadoMedallas.iterator(); iterator.hasNext();) {
			Metal metal = (Metal) iterator.next();
			System.out.println(metal.toString());
		}
		
		System.out.println("\n\nListado de medallas ordenadas segun su pureza\\n");
		
		
		listadoMedallas.sort(new ComparadorMedallas());
		
		for (Iterator iterator = listadoMedallas.iterator(); iterator.hasNext();) {
			Metal metal = (Metal) iterator.next();
			System.out.println(metal.toString());
		}
		
		
	}
	
}