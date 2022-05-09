package grafo;

import java.util.ArrayList;

public class ArbolGeneradorMinimo<T1> {

	private Grafo<T1> grafo;
	private Grafo<T1> arbol;
	private ArrayList<Arista<T1>> aristas;
	int contadorVertices;
	
	
	public ArbolGeneradorMinimo(Grafo<T1> grafo) {
		this.grafo = grafo;
		this.contadorVertices = this.grafo.tamano();
		
	}
	
	
	public void generarMinimo() {
		
		this.aristas = new ArrayList<Arista<T1>>();
		
		this.contadorVertices = 0;
		
	
		
		Nodo<T1> Vt = this.grafo.obtenerVertice(0);
		arbol.agregarVertice(Vt);
		
		
		for (Distancia<T1> i : this.grafo.vecinos(Vt)) {
			Arista<T1> arista = new Arista(Vt,i.getDestino(),i.getPeso());
			this.aristas.add(arista);
		}
		
		
		
		
		
		
		
		float pesoMinimoActual = this.aristas.get(0).getPeso();
		Arista<T1> Et = this.aristas.get(0);
		
		for (Arista<T1> e : aristas) {
			
				if (pesoMinimoActual >= e.getPeso()) {
					pesoMinimoActual = e.getPeso();
					Et = e;
				}
			}
		
		this.arbol.agregarArista(Vt, Et.getDestino(), Et.getPeso());
		
		
		
		this.contadorVertices++;
		
		
		
		
		
		}
	
		
		
		
		
	
	
	
	
	
	
}