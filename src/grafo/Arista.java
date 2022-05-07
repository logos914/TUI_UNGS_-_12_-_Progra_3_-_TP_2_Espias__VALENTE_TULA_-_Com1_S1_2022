package grafo;

public class Arista<T1> {

	private Nodo<T1> a;
	private Nodo<T1> b;
	private Float peso;
	
	
	public Arista(Nodo<T1> nodoA, Nodo<T1> nodoB, Float peso) {
		
		this.a = nodoA;
		this.b = nodoB;
		this.peso = peso;
		
	}


	public Nodo<T1> getA() {
		return a;
	}


	public void setA(Nodo<T1> a) {
		this.a = a;
	}


	public Nodo<T1> getB() {
		return b;
	}


	public void setB(Nodo<T1> b) {
		this.b = b;
	}


	public Float getPeso() {
		return peso;
	}


	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	
	
	
	
	
}
