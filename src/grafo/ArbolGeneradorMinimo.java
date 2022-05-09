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
		this.arbol = new Grafo<T1>();
		
	}
	
	
	public Grafo<T1> generarMinimo() {
		
		this.aristas = new ArrayList<Arista<T1>>();
		this.contadorVertices = 1;
		
	
		
		Nodo<T1> Vt = this.grafo.obtenerVertice(0).getInformacion();
		this.arbol.agregarVertice(Vt);
		
		
		while (this.arbol.tamano() != this.grafo.tamano()) {
			
			
			agregarAristasParaVerificar(Vt) ;
			
			
			Arista<T1> Et = encontrarLaAristaDeMenorPeso();
			
			Nodo<T1> Ut = new Nodo(Et.getB().getInformacion());
			this.arbol.agregarVertice(Ut);
			this.arbol.agregarArista(Vt, Ut, Et.getPeso());
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
			Arista<T1> aristaInvertida = new Arista(i.getDestino(), Vt ,i.getPeso());
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
