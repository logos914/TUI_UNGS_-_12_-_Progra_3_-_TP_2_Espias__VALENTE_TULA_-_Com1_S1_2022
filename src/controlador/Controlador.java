package controlador;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import modelo.Agencia;
import visual.Bienvenida;
import visual.CreandoAgencia;
import visual.CreandoComunicacion;

public class Controlador {
	private Agencia modelo;
	private JFrame gui;
	
	
	
	public Controlador() {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modelo = new Agencia();
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
		((Bienvenida) gui).lanzarEventoNuevaAgencia(new EscucharNuevaAgencia());
	}
	
	
	private void crearAgencia() {
		System.out.println("hola");
		this.gui.dispose();
		this.gui = new CreandoAgencia();
		actualizarGUI();
		
	}
	
	private void actualizarGUI() {
		this.gui.setVisible(true);
		this.gui.revalidate();
		this.gui.repaint();
	}
	
	
	
class EscucharNuevaAgencia implements ActionListener {
		
	
		public void actionPerformed(ActionEvent e) {
	
			crearAgencia();

		}
	}


		

	
}
