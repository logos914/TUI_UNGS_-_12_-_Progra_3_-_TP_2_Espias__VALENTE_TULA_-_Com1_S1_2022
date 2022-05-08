package controlador;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import grafo.Grafo;
import modelo.Agencia;
import modelo.Espia;
import visual.Bienvenida;
import visual.CreandoAgencia;
import visual.CreandoComunicacion;
import visual.UbicandoEspias;

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

	
	
	
	
	// Aquí se activan se activan los eventos en las diferentes GUI
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
		((UbicandoEspias) gui).lanzarEventoClicParaAgregarMarcador(new OyenteClicEnMapaParaUbicarEspia());
	}

	
	
	
	
	// Aquí están las operaciones del controlador respecto a cambiar de ventana
	
	private void irHaciaCrearAgencia() {

		this.gui.dispose();
		this.gui = new CreandoAgencia();
		actualizarGUI();
		escucharEventosAlCrearAgencia();
		escucharEventoIrPantallaComunicaciones();
		
	}
	
	private void irHaciaUbicarEspiasEnMapa() {
		
		this.gui.dispose();
		this.gui = new UbicandoEspias();
		actualizarGUI();
		escucharAgregarMarcador();
		
	}
	
	
	
	private void irHaciaCrearComunicacionesEntreEspias() {
		this.gui.dispose();
		this.gui = new CreandoComunicacion();
		
		((CreandoComunicacion) gui).colocarMarcadorDeLosEspias(this.agencia.obtenerTodosLosEspias());
		((CreandoComunicacion) gui).lanzarEventoClic(new OyenteClicEnMapaParaSeleccionarEspia());
		((CreandoComunicacion) gui).completarListadoDeEspias(this.agencia.obtenerTodosLosEspias());
		
		espias = new Grafo<Espia>();
		
		for (Espia e : this.agencia.obtenerTodosLosEspias()) {
			espias.agregarVertice(e);
		}
		
		
		
		actualizarGUI();
	}
	
	
	
	
	// Aquí están las operaciones del controlador
	// relacionadas con el modelo y la gui mostrada en pantalla

	private void agregarEspia() {

		String alias = ((CreandoAgencia) gui).obtenerTxtAlias();
		String codigo = ((CreandoAgencia) gui).obtenerTxtCodigo();

		Espia nuevoEspia = new Espia(alias, codigo);
		this.agencia.reclutarEspia(nuevoEspia);

		((CreandoAgencia) gui).borrarFormularioEspia();
		((CreandoAgencia) gui).agregarFilaEnTabla(alias, codigo);
		this.actualizarGUI();

	}

	
	private void colocarCoordenadasEnEspia(MouseEvent e) {
		
		if (espiasConPosicion < agencia.cantidadDeEspias()) {
			Coordinate coordenada = ((UbicandoEspias) gui).obtenerCoordenadaDePosicionPunteroMouse(e.getPoint());
			agencia.ubicarEspia(espiasConPosicion,coordenada);
			((UbicandoEspias) gui).CrearMarcador(agencia.obtenerEspia(espiasConPosicion).getCodigo() ,coordenada);
			espiasConPosicion++;
			System.out.print(coordenada);
		}
		
		if (espiasConPosicion == agencia.cantidadDeEspias() ) {
			irHaciaCrearComunicacionesEntreEspias();
		}
		
		
		
	}
	
	
	
	
	private void actualizarGUI() {
		this.gui.setVisible(true);
		this.gui.revalidate();
		this.gui.repaint();
	}

	
	
	
	
	// Listado de Oyentes de eventos del controlador
	
	class OyenteNuevaAgencia implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			irHaciaCrearAgencia();
		}
	}

	class OyenteNuevoAgente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			agregarEspia();
		}

	}
	
	class OyenteIrAPantallaCrearComunicaciones implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			irHaciaUbicarEspiasEnMapa();
		}
	}
		
		
		
	class OyenteClicEnMapaParaUbicarEspia implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			colocarCoordenadasEnEspia(e);
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
	
	class OyenteClicEnMapaParaSeleccionarEspia implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			Point puntoClickeado = e.getPoint();
			MapMarkerDot marcadorPresionado = null;
			
			   for (MapMarkerDot marcador : ((CreandoComunicacion)gui).obtenerMarcadores()) {
				   
		            Point puntoDelMarcador = ((CreandoComunicacion)gui).obtenerPosicion(marcador.getLat(), marcador.getLon());
		            if (puntoDelMarcador != null) {
		                int radioDelMarcador = ((CreandoComunicacion)gui).obtenerRadio(marcador, puntoDelMarcador);
		                Rectangle areaDelMarcador = new Rectangle(puntoDelMarcador.x - radioDelMarcador, puntoDelMarcador.y - radioDelMarcador, radioDelMarcador + radioDelMarcador, radioDelMarcador + radioDelMarcador);
		                if (areaDelMarcador.contains(puntoClickeado)) {
		                	marcadorPresionado = marcador;
		                    break;
		                }
		            }
		            
		        }
			   if (marcadorPresionado != null) {
				   System.out.println(marcadorPresionado.getName());
				   
				   Espia espiaOrigen = agencia.obtenerEspia(((CreandoComunicacion)gui).obtenerEspiaOrigen());
				   Espia espiaDestino = agencia.obtenerEspia(marcadorPresionado.getName());
				   Float peso = ((CreandoComunicacion)gui).obtenerProbabilidadIntercepcion();
				   
				   
				   ((CreandoComunicacion)gui).dibujarAristaEnMapa( espiaOrigen.obtenerPosicion(), espiaDestino.obtenerPosicion(), peso.toString()); 
	   
			   }
			   
			   
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
	

	}


