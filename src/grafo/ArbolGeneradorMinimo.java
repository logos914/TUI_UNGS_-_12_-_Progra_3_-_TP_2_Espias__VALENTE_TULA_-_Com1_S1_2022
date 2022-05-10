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
		
		
	
		nodoActualDelGrafo = this.grafo.obtenerVerticeConVecinos(0);
		nodoActualParaArbol = this.grafo.obtenerNodoSinVecinos(nodoActualDelGrafo);
		
		this.arbol.agregarVertice(nodoActualParaArbol);
		this.agregarAristasParaVerificar();
		int contador = 1;
		while (this.aristas.size() > 0) {
			
			System.out.println("While: " + contador);
			

			Et = encontrarLaAristaDeMenorPeso();
			
		
			nodoParaAgregarDelGrafo = Et.getB();
		
			nodoParaAgregarParaArbol = this.grafo.obtenerNodoSinVecinos(nodoParaAgregarDelGrafo);
		
			nodoActualParaArbol =  this.arbol.obtenerVerticeConVecinos(Et.getA());
			
		
			if (this.arbol.obtenerVerticeConVecinos(nodoParaAgregarParaArbol) == null) {
				this.arbol.agregarVertice(nodoParaAgregarParaArbol);
			}
			
			nodoParaAgregarParaArbol = this.arbol.obtenerVerticeConVecinos(nodoParaAgregarParaArbol);
			this.arbol.agregarArista(nodoActualParaArbol, nodoParaAgregarParaArbol, Et.getPeso());
			this.aristas.remove(Et);
			
			nodoActualDelGrafo = this.grafo.obtenerVerticeConVecinos(nodoParaAgregarDelGrafo);
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
			
			Nodo<T1> nodoOrigenTemporal = this.arbol.obtenerVerticeConVecinos(this.nodoActualDelGrafo);
			Nodo<T1> nodoDestinoTemporal = this.grafo.obtenerNodoSinVecinos(i.getDestino());
					
			
			
			
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
			
			Nodo<T1> a = this.arbol.obtenerVerticeConVecinos(e.getA());
			Nodo<T1> b = this.arbol.obtenerVerticeConVecinos(e.getB());

			
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
