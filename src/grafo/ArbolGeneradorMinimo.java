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
		this.agregarAristasParaVerificar();
		int contador = 1;
		while (this.aristas.size() > 0) {
			
			System.out.println("While: " + contador);
			
			
			
			
			Et = encontrarLaAristaDeMenorPeso();
			
		
			nodoParaAgregarDelGrafo = Et.getB();
		
			nodoParaAgregarParaArbol =  new Nodo(nodoParaAgregarDelGrafo.getInformacion());
		
			nodoActualParaArbol =  this.arbol.obtenerVertice(Et.getA().getInformacion());
		
		
		if (this.arbol.obtenerVertice(nodoParaAgregarParaArbol.getInformacion()) == null) {
			this.arbol.agregarVertice(nodoParaAgregarParaArbol);
		}
			
			nodoParaAgregarParaArbol = this.arbol.obtenerVertice(nodoParaAgregarParaArbol.getInformacion());
			this.arbol.agregarArista(nodoActualParaArbol, nodoParaAgregarParaArbol, Et.getPeso());
			this.aristas.remove(Et);
			
			nodoActualDelGrafo = this.grafo.obtenerVertice(nodoParaAgregarDelGrafo.getInformacion());
			nodoActualParaArbol = nodoParaAgregarParaArbol;
			quitarAristasQueNoRequierenProcesar();
			this.agregarAristasParaVerificar();
			quitarAristasQueNoRequierenProcesar();
			contador++;
		}
		
		
		
		
		return this.arbol;
		
		
		}
	
		
		
	private boolean estaAristaYaExiste(Arista<T1> a) {
		
		
		
		return this.arbol.existeArista(a);
		
		
		
	}
		
		
	private void agregarAristasParaVerificar() {
		
		for (Distancia<T1> i : this.grafo.vecinos(nodoActualDelGrafo)) {
			
			Nodo<T1> nodoOrigenTemporal = this.arbol.obtenerVertice(this.nodoActualDelGrafo.getInformacion());
			Nodo<T1> nodoDestinoTemporal = new Nodo<T1>(i.getDestino().getInformacion());
			
			
			
			
				Arista<T1> arista = new Arista(nodoOrigenTemporal,nodoDestinoTemporal,i.getPeso());
				if (!this.arbol.existeArista(arista)) {
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
	
	
	private void quitarAristasQueNoRequierenProcesar() {
		
		ArrayList<Arista<T1>> listadoParaQuitar = new ArrayList<Arista<T1>>();
		
		for (Arista<T1> e : aristas) {
			
			Nodo<T1> a = this.arbol.obtenerVertice(e.getA().getInformacion());
			Nodo<T1> b = this.arbol.obtenerVertice(e.getB().getInformacion());

			
			if (this.arbol.verificarVertice(e.getA()) && this.arbol.verificarVertice(e.getB())
				&&	a.obtenerCantidadDeVecinos() != 0 && b.obtenerCantidadDeVecinos() != 0 	){
				listadoParaQuitar.add(e);
				
				
				
			}
		}
		
		if (listadoParaQuitar.size() > 0) {
			for (Arista<T1> e : listadoParaQuitar) {
				this.aristas.remove(e);
			}
		}
	}
	
	
	
	
}
