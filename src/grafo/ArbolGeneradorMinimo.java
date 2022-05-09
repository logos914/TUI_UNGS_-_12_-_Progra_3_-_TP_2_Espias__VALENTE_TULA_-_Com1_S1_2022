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
	
	
	public Grafo<T1> generarMinimo() {
		
		this.aristas = new ArrayList<Arista<T1>>();
		this.contadorVertices = 1;
		
	
		
		Nodo<T1> Vt = this.grafo.obtenerVertice(0);
		arbol.agregarVertice(Vt);
		
		
		while (this.contadorVertices < this.grafo.tamano()) {
			
			
			agregarAristasParaVerificar(Vt) ;
			
			
			Arista<T1> Et = encontrarLaAristaDeMenorPeso();
			
			this.arbol.agregarVertice(Et.getB());
			this.arbol.agregarArista(Vt, Et.getB(), Et.getPeso());
			this.aristas.remove(Et);
			
			Vt = Et.getB();
			this.contadorVertices++;
		}
		
		
		return this.arbol;
		
		
		}
	
		
		
	private boolean estaAristaYaExiste(Arista<T1> a) {
		
		boolean encontrada = false;
		
		for (Arista<T1> e : this.aristas) 
		{
			if (e.equals(a)) {
				encontrada = true;
			}
		}
		return encontrada;
	}
		
		
	private void agregarAristasParaVerificar(Nodo<T1> Vt) {
		
		for (Distancia<T1> i : this.grafo.vecinos(Vt)) {
			Arista<T1> arista = new Arista(Vt,i.getDestino(),i.getPeso());
			if (!this.estaAristaYaExiste(null)) {
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
