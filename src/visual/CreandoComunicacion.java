package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import modelo.Espia;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSlider;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;


public class CreandoComunicacion extends JFrame {

	private JPanel contentPane;
	private JMapViewer mapa;
	private JFrame frame;
	private JLabel lblEspiaOrigen;
	private JComboBox comboBoxEspiaOrigen;
	private DefaultComboBoxModel modeloEspiaOrigen;
	private JLabel lblEspiaProba;
	private Component horizontalStrut;
	private JSlider slider;
	private ArrayList<MapMarkerDot> marcadores;
	private JPanel panelMensajes;
	private JLabel lblMensajes;
	private JButton btnGenerar;
	

	
	/**
	 * Create the frame.
	 */
	public CreandoComunicacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInstrucciones = new JPanel();
		panelInstrucciones.setBackground(Color.DARK_GRAY);
		contentPane.add(panelInstrucciones, BorderLayout.NORTH);
		panelInstrucciones.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panelInstrucciones.add(panel, BorderLayout.NORTH);
		
		lblEspiaOrigen = new JLabel("Espia de origen:");
		lblEspiaOrigen.setForeground(Color.GREEN);
		panel.add(lblEspiaOrigen);
		
		modeloEspiaOrigen = new DefaultComboBoxModel();
		comboBoxEspiaOrigen = new JComboBox();
		comboBoxEspiaOrigen.setBackground(Color.GRAY);
		comboBoxEspiaOrigen.setForeground(Color.GREEN);
		comboBoxEspiaOrigen.setModel(modeloEspiaOrigen);
		panel.add(comboBoxEspiaOrigen);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		lblEspiaProba = new JLabel("Probabilidad de ser descubierto:");
		lblEspiaProba.setForeground(Color.GREEN);
		panel.add(lblEspiaProba);
		
		slider = new JSlider();
		slider.setForeground(Color.GREEN);
		slider.setBackground(Color.GRAY);
		panel.add(slider);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panelInstrucciones.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblseleccioneDestino = new JLabel("Clic en el esp?a de destino:");
		lblseleccioneDestino.setForeground(Color.GREEN);
		panel_1.add(lblseleccioneDestino);
		
		JPanel panelMapa = new JPanel();
		contentPane.add(panelMapa, BorderLayout.CENTER);
		panelMapa.setLayout(new GridLayout(0, 1, 0, 0));
		
		mapa = new JMapViewer();
	
		
		panelMapa.add(mapa);
		
		Coordinate coordenadaInicial= new Coordinate(-34.52202,-58.70002);
		
		
		mapa.setLayout(null);
		
		panelMensajes = new JPanel();
		panelMensajes.setBackground(Color.DARK_GRAY);
		contentPane.add(panelMensajes, BorderLayout.SOUTH);
		panelMensajes.setLayout(new BorderLayout(0, 0));
		
		lblMensajes = new JLabel("Podr?s enviar el mensaje a todos los esp?as, cuando el grafo que forman los nodos sea conexo");
		lblMensajes.setHorizontalAlignment(SwingConstants.LEFT);
		lblMensajes.setForeground(Color.RED);
		panelMensajes.add(lblMensajes, BorderLayout.WEST);
		
		btnGenerar = new JButton("Comunicar");
		btnGenerar.setEnabled(false);
		btnGenerar.setBackground(Color.DARK_GRAY);
		btnGenerar.setForeground(Color.GREEN);
		panelMensajes.add(btnGenerar, BorderLayout.EAST);
		mapa.setDisplayPosition(coordenadaInicial,18);
		
		
		
		mapa.setDisplayToFitMapMarkers();
		
		
		
		
		
	}
	
	public void colocarMarcadorDeLosEspias(LinkedList<Espia> espias) {
		
		marcadores = new ArrayList<MapMarkerDot>();
		
		for (Espia e : espias) {
			MapMarkerDot marcador = new MapMarkerDot(e.getCodigo(), e.getCoordenadas());
			marcadores.add(marcador);
			mapa.addMapMarker(marcador);
			
		}
	}
	
	
	
	
	public void completarListadoDeEspias(LinkedList<Espia> espias) {
		
		for (Espia e : espias ) {
			comboBoxEspiaOrigen.addItem(e.getCodigo());
			//modeloEspiaOrigen.addAll(espias);
		}
		
	}

	
	
	
	public Point obtenerPosicion(Double latitud, Double longitud) {

		return mapa.getMapPosition(latitud, longitud, true);
	}
	
	
	
	
	public int obtenerRadio(MapMarkerDot marcador, Point punto) {
		return mapa.getRadius(marcador, punto);
	}

	
	
	public void CrearMarcador(String codigo, Coordinate coord) {
		
		mapa.addMapMarker(new MapMarkerDot(codigo, coord));
	}
	
	
	
	public void lanzarEventoClic(MouseListener escucharClic) {
		mapa.addMouseListener(escucharClic);
		
	}
	
	
	public void lanzarEventoClicSiguiente(ActionListener accion) {
		btnGenerar.addActionListener(accion);
		
	}
	
	
	public ArrayList<MapMarkerDot> obtenerMarcadores() {
		 return this.marcadores;
	}
	
	
	public String obtenerEspiaOrigen() {
		return (String) comboBoxEspiaOrigen.getSelectedItem();
	}
	
	
	
	public float obtenerProbabilidadIntercepcion() {
		float valorSlider = (float) slider.getValue();
		return (float) (valorSlider / 100.0);
	}
	
	public void dibujarAristaEnMapa(Coordinate desde, Coordinate hasta, String peso, boolean esArbol) {
		
		List<Coordinate> ruta = new ArrayList<Coordinate>(Arrays.asList(desde, hasta, hasta));
		MapPolygonImpl trazo = new MapPolygonImpl(ruta);
		mapa.addMapPolygon(trazo);
		
		if (esArbol) {
			trazo.setColor(Color.GREEN);
		} else {
			trazo.setColor(Color.GRAY);
		}
		
		trazo.setName(peso);
		
	}
	
	public void habilitarBotonSiguiente() {
		this.btnGenerar.setEnabled(true);
	}
	
	public void cambiarMensaje(String texto) {
		lblMensajes.setText(texto);
	}
	
}
