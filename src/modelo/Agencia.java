package modelo;

import java.util.LinkedList;

public class Agencia {
	
	private LinkedList<Espia> EspiasDesplegados;
	
	public Agencia() {
		this.EspiasDesplegados = new LinkedList<Espia>();
	}
	
	
	public void reclutarEspia(Espia espia) {
		this.EspiasDesplegados.add(espia);
	}
	
	
	public void licenciarEspia(Espia espia) {
		this.EspiasDesplegados.remove(espia);
	}
	
	public int obtenerIndiceDeEspia(Espia espia) {
		return this.EspiasDesplegados.indexOf(espia);
	}
	
	public int cantidadDeEspias() {
		return this.EspiasDesplegados.size();
	}
	
	public Espia obtenerEspia(int indice) {
		return this.EspiasDesplegados.get(indice);
	}
	
	public Espia obtenerEspia(String codigo) {
		
		Espia EspiaObtenido = this.buscarCodigo(codigo);
		
		if (EspiaObtenido == null) {
			throw new IllegalArgumentException("Ese código no es un agente válido. ¿Usted es de contraespionaje?");
		}
		
		
		return EspiaObtenido;
	}
		
		
	
	
	
	private Espia buscarCodigo(String codigo) {
		for (Espia e : this.EspiasDesplegados) {
			if (e.getCodigo().equals(codigo)) {
				return e;
			}
		}
		return null;
		
	}
	

}
