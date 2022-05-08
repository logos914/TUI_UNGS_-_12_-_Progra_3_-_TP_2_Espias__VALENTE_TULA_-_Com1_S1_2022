package modelo;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Espia {
	
	private String alias;
	private String codigo;
	private Coordinate coordenadas;
	
	
	public Espia(String alias, String codigo) {
		this.alias = alias;
		this.codigo = codigo;
	}
	
	public Espia(String alias, String codigo, Coordinate coordenadas) {
		this.alias = alias;
		this.codigo = codigo;
		this.coordenadas = coordenadas;
	}
	
	public Coordinate getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordinate coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void posicionar(Coordinate coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	public Coordinate obtenerPosicion() {
		return this.coordenadas;
	}
	
	
	public boolean equals(Espia e) {
		if (this.alias.equals(e.getAlias()) && this.codigo.equals(e.getCodigo())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public String toString() {
		return "Agente: " + this.alias + " | Código: " + this.codigo + "\nUbicado en:" + this.coordenadas.toString();		
	}
}
