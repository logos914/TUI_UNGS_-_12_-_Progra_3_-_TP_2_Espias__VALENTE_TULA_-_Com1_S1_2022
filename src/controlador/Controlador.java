package controlador;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import modelo.Agencia;
import modelo.Espia;
import visual.Bienvenida;
import visual.CreandoAgencia;
import visual.CreandoComunicacion;

public class Controlador {
	private Agencia agencia;
	private JFrame gui;

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
		((Bienvenida) gui).lanzarEventoNuevaAgencia(new OyenteNuevaAgencia());
	}

	private void crearAgencia() {

		this.gui.dispose();
		this.gui = new CreandoAgencia();
		actualizarGUI();
		escucharEventosAlCrearAgencia();
		;
				

	}
	
	private void crearComunicacionesPosibles() {
		
		this.gui.dispose();
		this.gui = new CreandoComunicacion();
		actualizarGUI();
		
		//@TODO: Poner la escucha de eventos
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

}
