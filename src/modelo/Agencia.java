package modelo;

import java.util.LinkedList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Agencia {
	
	private LinkedList<Espia> espiasDesplegados;
	
	public Agencia() {
		this.espiasDesplegados = new LinkedList<Espia>();
	}
	
	
	public void reclutarEspia(Espia espia) {
		this.espiasDesplegados.add(espia);
	}
	
	
	public void licenciarEspia(Espia espia) {
		this.espiasDesplegados.remove(espia);
	}
	
	public int obtenerIndiceDeEspia(Espia espia) {
		return this.espiasDesplegados.indexOf(espia);
	}
	
	public int cantidadDeEspias() {
		return this.espiasDesplegados.size();
	}
	
	public Espia obtenerEspia(int indice) {
		return this.espiasDesplegados.get(indice);
	}
	
	public Espia obtenerEspia(String codigo) {
		
		Espia EspiaObtenido = this.buscarCodigo(codigo);
		
		if (EspiaObtenido == null) {
			throw new IllegalArgumentException("Ese código no es un agente válido. ¿Usted es de contraespionaje?");
		}
		
		
		return EspiaObtenido;
	}
		
	
	public void ubicarEspia(int indice, Coordinate coordenadas) {
		this.espiasDesplegados.get(indice).setCoordenadas(coordenadas);
	}
	
	private Espia buscarCodigo(String codigo) {
		for (Espia e : this.espiasDesplegados) {
			if (e.getCodigo().equals(codigo)) {
				return e;
			}
		}
		return null;
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


