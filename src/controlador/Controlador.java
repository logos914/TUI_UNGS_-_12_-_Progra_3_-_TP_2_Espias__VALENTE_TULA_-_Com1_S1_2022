package controlador;

import java.awt.EventQueue;

import modelo.Agencia;
import visual.Bienvenida;

public class Controlador {
	private Agencia modelo;
	private Bienvenida guiBienvenida;
	
	public Controlador() {
		modelo = new Agencia();
		guiBienvenida
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenida window = new Bienvenida();
					
					
					
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}

	
}
