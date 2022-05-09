package grafo;

import java.util.ArrayList;

public class BFS<T1> {

	private Grafo<T1> grafo;
	ArrayList<Nodo<T1>> nodosPendientes;
	boolean[] nodosProcesados; // no los toco más, ya me paré en ellos y obtuve sus vecinos y los marqué
	boolean[] nodosMarcados; // Este nodo es alcanzable

	public BFS(Grafo<T1> grafo) {
		this.grafo = grafo;
	}

	public boolean esConexo() {

		Nodo<T1> i;

		nodosPendientes = this.grafo.obtenerTodosLosVertices();
		nodosProcesados = new boolean[this.grafo.tamano()];
		nodosMarcados = new boolean[this.grafo.tamano()];

		
		i = this.nodosPendientes.get(0);
		this.nodosMarcados[0] = true;
		this.nodosProcesados[0] = true;
		
		for (Distancia<T1> e : this.grafo.vecinos(i)) {

			int pos = this.nodosPendientes.indexOf(e.getDestino());

			this.nodosMarcados[pos] = true;

		}
		
		while (!this.seRecorrieronTodosLosVertices() && this.hayMasParaProcesar()) {

			int pos = this.proximoParaProcesar();
			i = this.nodosPendientes.get(pos);
			this.nodosMarcados[pos] = true;
			this.nodosProcesados[pos] = true;

			for (Distancia<T1> e : this.grafo.vecinos(i)) {

				int indiceVecinoParaMarcar = this.nodosPendientes.indexOf(e.getDestino());

				this.nodosMarcados[indiceVecinoParaMarcar] = true;

			}

		}

		return seRecorrieronTodosLosVertices();

	}

	private boolean seRecorrieronTodosLosVertices() {
		boolean acumulador = true;

		for (boolean i : this.nodosMarcados) {
			acumulador = acumulador && i;
		}

		return acumulador;

	}
	
	private boolean hayMasParaProcesar() {
		if (this.proximoParaProcesar() == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	private int proximoParaProcesar() {

		for (int i = 0; i < this.nodosPendientes.size(); i++) {

			if (this.nodosMarcados[i] && !this.nodosProcesados[i]) {
				return i;
			}

		}

		return -1;

	}
}
