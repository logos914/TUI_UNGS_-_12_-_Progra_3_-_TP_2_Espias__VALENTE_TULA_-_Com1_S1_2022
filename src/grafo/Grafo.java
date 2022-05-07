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
		//if (verificarVertice(arista.getA().es))
		arista.getA().agregarVecino(arista.getB(), arista.getPeso());
		arista.getB().agregarVecino(arista.getA(), arista.getPeso());
	}
	
	
	public void eliminarArista(Arista<T1> arista)
	{
		
	}

	// Informa si existe la arista especificada
	public boolean existeArista(Arista<T1> arista)
	{
		return true;
	}

	// Cantidad de vertices
	public int tamano()
	{
		return 0;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		
		
		Set<Integer> ret = new HashSet<Integer>();
		
		
		return ret;		
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
