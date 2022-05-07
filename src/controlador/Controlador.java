package controlador;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.Grafo;
import modelo.Agencia;
import modelo.Espia;
import visual.Bienvenida;
import visual.CreandoAgencia;
import visual.CreandoComunicacion;

public class Controlador {
	private Agencia agencia;
	private JFrame gui;
	private Grafo<Espia> espias;
	private int espiasConPosicion = 0;

	public Controlador() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agencia = new Agencia();
					gui = new Bienvenida();
					actualizarGUI();
					escucharEventosBienvenida();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	private void escucharEventosBienvenida() {
		((Bienvenida) gui).lanzarEventoNuevaAgencia(new OyenteNuevaAgencia());
	}

	private void escucharEventosAlCrearAgencia() {
		((CreandoAgencia) gui).lanzarEventoNuevoEspia(new OyenteNuevoAgente());
	}
	
	private void escucharEventoIrPantallaComunicaciones() {
		((CreandoAgencia) gui).lanzarEventoIrAPantallaCrearComunicaciones(new OyenteIrAPantallaCrearComunicaciones());
	}

	private void escucharAgregarMarcador() {
		((CreandoComunicacion) gui).lanzarEventoClicParaAgregarMarcador(new OyenteClicEnMapaParaUbicarEspia());
	}

	
	
	
	
	private void crearAgencia() {

		this.gui.dispose();
		this.gui = new CreandoAgencia();
		actualizarGUI();
		escucharEventosAlCrearAgencia();
		escucharEventoIrPantallaComunicaciones();
		
		
				

	}
	
	private void crearComunicacionesPosibles() {
		
		this.gui.dispose();
		this.gui = new CreandoComunicacion();
		actualizarGUI();
		escucharAgregarMarcador();
		
		
		
	}

	private void agregarEspia() {

		String alias = ((CreandoAgencia) gui).obtenerTxtAlias();
		String codigo = ((CreandoAgencia) gui).obtenerTxtCodigo();

		Espia nuevoEspia = new Espia(alias, codigo);
		this.agencia.reclutarEspia(nuevoEspia);

		((CreandoAgencia) gui).borrarFormularioEspia();
		((CreandoAgencia) gui).agregarFilaEnTabla(alias, codigo);
		this.actualizarGUI();

	}

	
	
	
	
	
	private void actualizarGUI() {
		this.gui.setVisible(true);
		this.gui.revalidate();
		this.gui.repaint();
	}

	
	
	
	
	
	
	class OyenteNuevaAgencia implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			crearAgencia();
		}
	}

	class OyenteNuevoAgente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			agregarEspia();
		}

	}
	
	class OyenteIrAPantallaCrearComunicaciones implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			crearComunicacionesPosibles();
		}
	}
		
		
		
	class OyenteClicEnMapaParaUbicarEspia implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
			if (espiasConPosicion < agencia.cantidadDeEspias()) {
				Coordinate coordenada = ((CreandoComunicacion) gui).obtenerCoordenadaDePosicionPunteroMouse(e.getPoint());
				agencia.ubicarEspia(espiasConPosicion,coordenada);
				((CreandoComunicacion) gui).CrearMarcador(agencia.obtenerEspia(espiasConPosicion).getCodigo() ,coordenada);
				espiasConPosicion++;
				System.out.print(coordenada);
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	}


