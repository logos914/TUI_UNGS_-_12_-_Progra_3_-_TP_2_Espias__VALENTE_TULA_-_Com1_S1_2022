package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSlider;


public class CreandoComunicacion extends JFrame {

	private JPanel contentPane;
	private JMapViewer mapa;
	private JFrame frame;
	private JLabel lblEspiaOrigen;
	private JComboBox comboBoxEspiaOrigen;
	private JLabel lblEspiaProba;
	private Component horizontalStrut;
	private JSlider slider;

	
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
		
		JPanel panelComunicacion = new JPanel();
		contentPane.add(panelComunicacion, BorderLayout.NORTH);
		panelComunicacion.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelComunicacion.add(panel, BorderLayout.NORTH);
		
		lblEspiaOrigen = new JLabel("Espía de origen:");
		panel.add(lblEspiaOrigen);
		
		comboBoxEspiaOrigen = new JComboBox();
		panel.add(comboBoxEspiaOrigen);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		lblEspiaProba = new JLabel("Probabilidad de ser descubierto:");
		panel.add(lblEspiaProba);
		
		slider = new JSlider();
		panel.add(slider);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		
		JPanel panel_1 = new JPanel();
		panelComunicacion.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblseleccioneDestino = new JLabel("Clic en el espía de destino:");
		panel_1.add(lblseleccioneDestino);
		
		JPanel panelMapa = new JPanel();
		contentPane.add(panelMapa, BorderLayout.CENTER);
		panelMapa.setLayout(new GridLayout(0, 1, 0, 0));
		
		mapa = new JMapViewer();
	
		
		panelMapa.add(mapa);
		
		Coordinate coordenadaInicial= new Coordinate(-34.5331,-58.7007);
		
		
		mapa.setLayout(null);
		mapa.setDisplayPosition(coordenadaInicial,14);
		
		
		
		mapa.setDisplayToFitMapMarkers();
		
		
		
		
		
	}
	

	public Coordinate obtenerCoordenadaDePosicionPunteroMouse(Point punto) {

		return (Coordinate) mapa.getPosition(punto);
		
	}

	public void CrearMarcador(String codigo, Coordinate coord) {
		
		mapa.addMapMarker(new MapMarkerDot(codigo, coord));
	}
	
	public void lanzarEventoClicParaAgregarMarcador(MouseListener escucharClic) {
		mapa.addMouseListener(escucharClic);
		
	}
}
