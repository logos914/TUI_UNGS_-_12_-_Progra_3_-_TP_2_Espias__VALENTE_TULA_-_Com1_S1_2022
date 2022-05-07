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


public class CreandoComunicacion extends JFrame {

	private JPanel contentPane;
	private JMapViewer mapa;
	private JFrame frame;

	
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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
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
