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

}
