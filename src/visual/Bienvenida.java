package visual;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.awt.Button;
import java.awt.Font;

public class Bienvenida {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public Bienvenida() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		BackgroundPane background = new BackgroundPane();
		try {
			background.setBackground(ImageIO.read(new File("src/visual/assets/fondo_inicial.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setContentPane(background);
		frame.getContentPane().setLayout(null);
		
		Button btnCrearRed = new Button("Nueva Red de Espías");
		btnCrearRed.setActionCommand("crearRedDeEspias");
		btnCrearRed.setFont(new Font("Dialog", Font.BOLD, 17));
		btnCrearRed.setForeground(Color.GREEN);
		btnCrearRed.setBackground(Color.BLACK);
		btnCrearRed.setBounds(476, 168, 241, 71);
		background.add(btnCrearRed);
		
		Button btnCargarRed = new Button("Cargar Red de Espías");
		btnCargarRed.setForeground(Color.GREEN);
		btnCargarRed.setFont(new Font("Dialog", Font.BOLD, 17));
		btnCargarRed.setBackground(Color.BLACK);
		btnCargarRed.setBounds(476, 349, 241, 71);
		background.add(btnCargarRed);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
	}
}
