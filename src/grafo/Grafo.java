package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Grafo<T1> {
	
	ArrayList<Nodo<T1>> vertices;
	
	
	public Grafo() {
		
		vertices = new ArrayList<Nodo<T1>>();
		
		
		
	}
	
	public void agregarVertice(T1 informacion) {
		Nodo<T1> nuevoNodo = new Nodo<T1>(informacion);
		this.vertices.add(nuevoNodo);
	}
	
	
	
	public void agregarArista(Arista<T1> arista)
	{
		this.verificarVerticesDeArista(arista);
		
		this.verificarExisteArista(arista);
		
		this.verificarQueLosNodosNoSeanElMismo(arista);
			
		arista.getA().agregarVecino(arista.getB(), arista.getPeso());
		arista.getB().agregarVecino(arista.getA(), arista.getPeso());
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

	
}
