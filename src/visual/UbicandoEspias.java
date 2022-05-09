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
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import java.awt.Color;


public class UbicandoEspias extends JFrame {

	private JPanel contentPane;
	private JMapViewer mapa;
	private JFrame frame;

	
	/**
	 * Create the frame.
	 */
	public UbicandoEspias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblinstrucciones = new JLabel("Hacé clic en el mapa para ubicar a cada espía en el órden en que fueron creados");
		lblinstrucciones.setForeground(Color.GREEN);
		panel.add(lblinstrucciones);
		
		JPanel panelMapa = new JPanel();
		contentPane.add(panelMapa, BorderLayout.CENTER);
		panelMapa.setLayout(new GridLayout(0, 1, 0, 0));
		
		mapa = new JMapViewer();
	
		
		panelMapa.add(mapa);
		
		Coordinate coordenadaInicial= new Coordinate(-34.5331,-58.7007);
		
		
		mapa.setLayout(null);
		mapa.setDisplayPosition(coordenadaInicial,16);
		
		
		
		
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
