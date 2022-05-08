package grafo;

import java.util.ArrayList;

public class Nodo<T1> {

	private T1 informacion;
	private ArrayList<Distancia<T1>> vecinos;


	
	public Nodo(T1 informacion) {
		this.informacion = informacion;
	}


	public T1 getInformacion() {
		return informacion;
	}
	
	public String toString() {
		return informacion.toString();
	}


	public void setInformacion(T1 informacion) {
		this.informacion = informacion;
	}
	
	public void agregarVecino(Nodo<T1> vecino, Float peso) {
		Distancia<T1> distancia = new Distancia<T1>(vecino, peso);
		this.vecinos.add(distancia);
	}
	
	public ArrayList<Distancia<T1>> obtenerTodosLosVecinos() {
		return this.vecinos;
	}
	
	public int obtenerCantidadDeVecinos() {
		return this.vecinos.size();
	}
	
	public Distancia<T1> obtenerDistancia(int indice) {
		return this.vecinos.get(indice);
	}
	
	public Distancia<T1> obtenerDistancia(Nodo<T1> nodoVecino) {
		
		Distancia<T1> vecinoEncontrado = null;
		
		for (Distancia<T1> d : vecinos) {
			if (d.getDestino().equals(nodoVecino)) {
				vecinoEncontrado = d;
			}
		}
		
		return vecinoEncontrado;
	}
	
	public boolean esVecino(Nodo<T1> nodoVecino) {
		
		Distancia<T1> encontrado = null;
		
		encontrado = this.obtenerDistancia(nodoVecino);
		
		if (encontrado == null) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void eliminarVecino(int indice) {
		this.vecinos.remove(indice);
	}
	
	public void eliminarVecino(T1 informacion) {
		this.vecinos.remove(informacion);
	}
	
	
	
	
	
}
