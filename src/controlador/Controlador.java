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

import grafo.ArbolGeneradorMinimo;
import grafo.BFS;
import grafo.Distancia;
import grafo.Grafo;
import grafo.Nodo;
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
	private ArbolGeneradorMinimo<Espia> agm;
	private Grafo<Espia> grafoAgm;

	public Controlador() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agencia = new Agencia();
					gui = new Bienvenida();
					espias = new Grafo();
					actualizarGUI();
					escucharEventosBienvenida();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	
	
	
	
	// Aqu√≠ se activan se activan los eventos en las diferentes GUI
	private void escucharEventosBienvenida() {
		((Bienvenida) gui).lanzarEventoNuevaAgencia(new OyenteNuevaAgencia());
	}
	
	private void escucharEventosBienvenidaCarga() {
		((Bienvenida) gui).lanzarEventoCargarAgencia(new OyenteCargarAgencia());
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

	private void escucharClicEnMapaParaSeleccionarEspia() {
		((CreandoComunicacion) gui).lanzarEventoClic(new OyenteClicEnMapaParaSeleccionarEspia());
	}
	
	private void escucharClicCrearArbolGeneradorMinimo() {
		((CreandoComunicacion) gui).lanzarEventoClicSiguiente(new OyenteCrearArbolGeneradorMinimo());
	}
	
	
	
	
	// Aqu√≠ est√°n las operaciones del controlador respecto a cambiar de ventana
	
	private void irHaciaCrearAgencia() {

		this.gui.dispose();
		this.gui = new CreandoAgencia();
		actualizarGUI();
		escucharEventosAlCrearAgencia();
		escucharEventoIrPantallaComunicaciones();
		
	}
	
	private void irHaciaCargarAgencia() {

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
		((CreandoComunicacion) gui).completarListadoDeEspias(this.agencia.obtenerTodosLosEspias());
		escucharClicEnMapaParaSeleccionarEspia();
		
		
		espias = new Grafo<Espia>();
		
		for (Espia e : this.agencia.obtenerTodosLosEspias()) {
			espias.agregarVertice(e);
		}
		
		
		
		actualizarGUI();
	}
	
	
	
	
	// Aqu√≠ est√°n las operaciones del controlador
	// relacionadas con el modelo y la gui mostrada en pantalla

	private void agregarEspia() {

		String alias = ((CreandoAgencia) gui).obtenerTxtAlias();
		String codigo = ((CreandoAgencia) gui).obtenerTxtCodigo();
		
		if (!alias.equals("") && !codigo.equals("")) {
			Espia nuevoEspia = new Espia(alias, codigo);
			this.agencia.reclutarEspia(nuevoEspia);

			((CreandoAgencia) gui).borrarFormularioEspia();
			((CreandoAgencia) gui).agregarFilaEnTabla(alias, codigo);
			this.actualizarGUI();

		}

		
	}

	
	private void colocarCoordenadasEnEspia(MouseEvent e) {
		
		if (espiasConPosicion < agencia.cantidadDeEspias()) {
			Coordinate coordenada = ((UbicandoEspias) gui).obtenerCoordenadaDePosicionPunteroMouse(e.getPoint());
			agencia.ubicarEspia(espiasConPosicion,coordenada);
			((UbicandoEspias) gui).CrearMarcador(agencia.obtenerEspia(espiasConPosicion).getCodigo() ,coordenada);
			espiasConPosicion++;
			System.out.println(coordenada);
		}
		
		if (espiasConPosicion == agencia.cantidadDeEspias() ) {
			irHaciaCrearComunicacionesEntreEspias();
		}
		
		
		
	}
	
	
	private void crearArista(MouseEvent e) {
		
		
		Point puntoClickeado = e.getPoint();
		
		MapMarkerDot marcadorPresionado = this.obtenerMarcadorPresionado(puntoClickeado);
		   
		   if (marcadorPresionado != null) {
			   System.out.println(marcadorPresionado.getName());
			   
			   Espia espiaOrigen = agencia.obtenerEspia(((CreandoComunicacion)gui).obtenerEspiaOrigen());
			   Espia espiaDestino = agencia.obtenerEspia(marcadorPresionado.getName());
			   Float peso = ((CreandoComunicacion)gui).obtenerProbabilidadIntercepcion();
			   
			   try {
				   
				   this.espias.agregarArista(espiaOrigen, espiaDestino, peso);
				   ((CreandoComunicacion)gui).dibujarAristaEnMapa( espiaOrigen.obtenerPosicion(), espiaDestino.obtenerPosicion(), peso.toString(), false); 

				   BFS bfs = new BFS(this.espias);
				   
				   if (bfs.esConexo()) {
					   ((CreandoComunicacion)gui).cambiarMensaje("Ya puedes generar la comunicaciÛn");
					   ((CreandoComunicacion)gui).habilitarBotonSiguiente();
					   escucharClicCrearArbolGeneradorMinimo();

				   } else {
					   ((CreandoComunicacion)gui).cambiarMensaje("Podr·s enviar el mensaje a todos los espÌas, cuando el grafo que forman los nodos sea conexo");
				   }
			   
			   } catch (Exception excepcion) {
				   System.out.println("No se puede crear esta arista\n" + excepcion);
				   ((CreandoComunicacion)gui).cambiarMensaje("No se puede crear esta arista");
			   }
			   
			   
			   
			   
   
		   }
	}
	
	
	private void arbolGeneradorMinimo() {
		
		this.agm = new ArbolGeneradorMinimo(this.espias);
		this.grafoAgm = this.agm.generarMinimo();
		
		for (Nodo<Espia> V : this.grafoAgm.obtenerTodosLosVertices()) {
			for (Distancia<Espia> E : V.obtenerTodosLosVecinos()) {
				((CreandoComunicacion)gui).dibujarAristaEnMapa( V.getInformacion().getCoordenadas(), E.getDestino().getInformacion().getCoordenadas(), E.getPeso().toString(), true); 
			}
		}
		
		
		
		
		
	}
	
	
	private MapMarkerDot obtenerMarcadorPresionado(Point puntoClickeado) {
		
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
		
		return marcadorPresionado;
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
	
	class OyenteCargarAgencia implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			irHaciaCargarAgencia();
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
			
			crearArista(e);
			   
			   
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
	
	
	class OyenteCrearArbolGeneradorMinimo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			arbolGeneradorMinimo();
		}

	}
	

	}


