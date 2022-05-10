package grafo;

import java.util.ArrayList;

public class ArbolGeneradorMinimo<T1> {

	private Grafo<T1> grafo;
	private Grafo<T1> arbol;
	private ArrayList<Arista<T1>> aristas;
	
	Nodo<T1> nodoActualDelGrafo;
	Nodo<T1> nodoActualParaArbol;
	Arista<T1> Et;
	Nodo<T1> nodoParaAgregarDelGrafo;
	Nodo<T1> nodoParaAgregarParaArbol;
	
	
	public ArbolGeneradorMinimo(Grafo<T1> grafo) {
		this.grafo = grafo;
		
		this.arbol = new Grafo<T1>();
		
	}
	
	
	public Grafo<T1> generarMinimo() {
		
		this.aristas = new ArrayList<Arista<T1>>();
		
		
	
		nodoActualDelGrafo = this.grafo.obtenerVertice(0);
		nodoActualParaArbol = new Nodo(nodoActualDelGrafo.getInformacion());
		this.arbol.agregarVertice(nodoActualParaArbol);
		
		
		while (this.arbol.tamano() != this.grafo.tamano()) {
			
			
		this.agregarAristasParaVerificar();
		Et = encontrarLaAristaDeMenorPeso();
			
		nodoParaAgregarDelGrafo = Et.getB();
		nodoParaAgregarParaArbol =  new Nodo(nodoParaAgregarDelGrafo.getInformacion());
		
		nodoActualParaArbol =  this.arbol.obtenerVertice(Et.getA().getInformacion());
		
		
		
			this.arbol.agregarVertice(nodoParaAgregarParaArbol);
			this.arbol.agregarArista(nodoActualParaArbol, nodoParaAgregarParaArbol, Et.getPeso());
			this.aristas.remove(Et);
			
			nodoActualDelGrafo = nodoParaAgregarDelGrafo;
			nodoActualParaArbol = nodoParaAgregarParaArbol;
			
		}
		
		
		return this.arbol;
		
		
		}
	
		
		
	private boolean estaAristaYaExiste(Arista<T1> a) {
		
		
		
		return this.arbol.existeArista(a);
		
		
		
	}
		
		
	private void agregarAristasParaVerificar() {
		
		for (Distancia<T1> i : this.grafo.vecinos(nodoActualDelGrafo)) {
			
			
			Nodo<T1> nodoDestinoTemporal = new Nodo(i.getDestino().getInformacion());
			
			
			Arista<T1> arista = new Arista(nodoActualParaArbol,nodoDestinoTemporal,i.getPeso());
			Arista<T1> aristaInvertida = new Arista(nodoDestinoTemporal, nodoActualParaArbol ,i.getPeso());
			if (!this.estaAristaYaExiste(arista) && !this.estaAristaYaExiste(aristaInvertida)) {
				this.aristas.add(arista);
			}
		}
	}
	
	
	private Arista<T1> encontrarLaAristaDeMenorPeso() {
		
		
		float pesoMinimoActual = this.aristas.get(0).getPeso();
		Arista<T1> Et = this.aristas.get(0);
		
		for (Arista<T1> e : aristas) {
			
				if (pesoMinimoActual >= e.getPeso()) {
					pesoMinimoActual = e.getPeso();
					Et = e;
				}
			}
		
		return Et;
	}
	
	
	
	
	
}
