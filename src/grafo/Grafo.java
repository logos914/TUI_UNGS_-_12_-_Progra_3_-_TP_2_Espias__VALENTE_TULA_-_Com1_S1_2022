package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Grafo<T1> {
	
	private ArrayList<Nodo<T1>> vertices;
	
	
	public Grafo() {
		
		vertices = new ArrayList<Nodo<T1>>();
		
		
		
	}
	
	public void agregarVertice(T1 informacion) {
		Nodo<T1> nuevoNodo = new Nodo<T1>(informacion);
		this.vertices.add(nuevoNodo);
	}
	
	public void agregarVertice(Nodo<T1> nodo) {
	
		this.vertices.add(nodo);
	}
	
	
	
	public void agregarArista(Arista<T1> arista)
	{
		this.verificarVerticesDeArista(arista);
		
		this.verificarExisteArista(arista);
		
		this.verificarQueLosNodosNoSeanElMismo(arista);
			
		arista.getA().agregarVecino(arista.getB(), arista.getPeso());
		arista.getB().agregarVecino(arista.getA(), arista.getPeso());
	}
	
	public void agregarArista(Nodo<T1> a, Nodo<T1> b, float peso) {
		try {
			Arista<T1> arista = new Arista(a,b,peso);
			this.agregarArista(arista);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public void agregarArista(T1 a, T1 b, float peso) {
		try {
			Nodo<T1> nodoA = this.obtenerVertice(a);
			Nodo<T1> nodoB = this.obtenerVertice(b);
			
			if (nodoA != null && nodoB != null) {
				Arista<T1> arista = new Arista(nodoA,nodoB,peso);
				this.agregarArista(arista);
			} else {
				
				if (nodoA == null && nodoB != null) {
					throw new IllegalArgumentException("No se puede agregar arista. "
							+ "No existe un nodo A que contenga dicha información\n A:" + a.toString());
				}
				if (nodoB == null && nodoA != null) {
					throw new IllegalArgumentException("No se puede agregar arista. "
							+ "No existe un nodo B que contenga dicha información\n B:" + b.toString());
				}
				
				throw new IllegalArgumentException("No se puede agregar arista. "
						+ "No existe un nodo A y B que contenga dicha información\n"
						+ "A:" + a.toString() + "\n"
						+ "B:" + b.toString() + "\n");

			}
			
			
		
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void verificarQueLosNodosNoSeanElMismo(Arista<T1> arista) {
		
		
		
		if (arista.getA().equals(arista.getB())) {
			throw new IllegalArgumentException("Un nodo no puede ser vecino de sí mismo\n Arista: " + arista.toString());

		}
	}


	
	public void verificarVerticesDeArista(Arista<T1> arista) {
		if (!verificarVertice(arista.getA())) {
			throw new IllegalArgumentException("Este nodo, no forma parte de este gráfo.\n Nodo: " + arista.getA().toString());
		}
		
		if (!verificarVertice(arista.getB())) {
			throw new IllegalArgumentException("Este nodo, no forma parte de este gráfo.\n Nodo: " + arista.getB().toString());
		}
	}
	

	private void verificarExisteArista(Arista<T1> arista) {
		if (this.existeArista(arista)) {
			throw new IllegalArgumentException("Esta arista ya existe, y no puede agregarse al grafo: \n" + arista.toString());
		}
	}
	
	public void eliminarArista(Arista<T1> arista)
	{
		if (this.existeArista(arista)) {
			
		}
	}

	
	
	
	
	// Informa si existe la arista especificada
	public boolean existeArista(Arista<T1> arista)
	{
		if (arista.getA().esVecino(arista.getB()) && arista.getB().esVecino(arista.getA())) {
			return true;
		} else {
			return false;
		}
	}

	// Cantidad de vertices
	public int tamano()
	{
		return this.vertices.size();
	}
	
	// Vecinos de un vertice
	public ArrayList<Distancia<T1>> vecinos(Nodo<T1> nodo)
	{
	
		if (this.verificarVertice(nodo)) {
			return nodo.obtenerTodosLosVecinos();
		} else {
			throw new IllegalArgumentException("Este nodo, no forma parte de este gráfo.\n Nodo: " + nodo.toString());
		}
		
	}
	
	// Verifica que sea un vertice valido
	public boolean verificarVertice(Nodo<T1> a)
	{
		for (Nodo<T1> e : vertices) {
			if ((e.equals(a)) ) {
				return true;
			}
		}
		return false;
	}

	// Verifica que i y j sean distintos
	private boolean verificarDistintos(Nodo<T1> a, Nodo<T1> b)
	{
		return  a.equals(b) ? true : false;
	}

	public ArrayList<Nodo<T1>> obtenerTodosLosVertices() {
		
		return this.vertices;
	}
	
	
	public Nodo<T1> obtenerVertice(int i) {
		return this.vertices.get(i);
	}
	
	public Nodo<T1> obtenerVertice(T1 e) {
		
		for (Nodo<T1> i : this.obtenerTodosLosVertices()) {
			if (i.getInformacion().equals(e)) {
				return i;
			}
		}
		return null;
	}
	
}
