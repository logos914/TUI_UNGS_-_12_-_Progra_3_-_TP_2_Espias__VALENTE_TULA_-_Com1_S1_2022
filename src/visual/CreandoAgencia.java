package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class CreandoAgencia extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAlias;
	private JTextField textFieldCodigo;
	private JTable table;
	private JButton botonAgregar;
	private DefaultTableModel datosTabla;
	private JButton btnSiguiente;
	


	/**
	 * Create the frame.
	 */
	public CreandoAgencia() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 25, 25));
		
		JPanel panelFormularioNuevoAgente = new JPanel();
		panelFormularioNuevoAgente.setBackground(Color.DARK_GRAY);
		panelFormularioNuevoAgente.setForeground(Color.LIGHT_GRAY);
		contentPane.add(panelFormularioNuevoAgente);
		panelFormularioNuevoAgente.setLayout(new GridLayout(9, 1, 0, 0));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelFormularioNuevoAgente.add(verticalStrut);
		
		JLabel lblIngresarAlias = new JLabel("Ingresá el Alias del Espía");
		lblIngresarAlias.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresarAlias.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIngresarAlias.setForeground(Color.RED);
		lblIngresarAlias.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		panelFormularioNuevoAgente.add(lblIngresarAlias);
		
		textFieldAlias = new JTextField();
		textFieldAlias.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAlias.setToolTipText("Ingresá el Alias del Espía");
		lblIngresarAlias.setLabelFor(textFieldAlias);
		textFieldAlias.setBackground(Color.BLACK);
		textFieldAlias.setForeground(Color.GREEN);
		textFieldAlias.setFont(new Font("Dialog", Font.PLAIN, 20));
		panelFormularioNuevoAgente.add(textFieldAlias);
		textFieldAlias.setColumns(30);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelFormularioNuevoAgente.add(verticalStrut_1);
		
		JLabel lblIngresElCdigo = new JLabel("Ingresá el código del espía (\"007\")");
		lblIngresElCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIngresElCdigo.setForeground(Color.RED);
		lblIngresElCdigo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		panelFormularioNuevoAgente.add(lblIngresElCdigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodigo.setToolTipText("Ingresá el código del espía, por ej. \"007\"");
		textFieldCodigo.setForeground(Color.GREEN);
		textFieldCodigo.setFont(new Font("Dialog", Font.PLAIN, 20));
		textFieldCodigo.setColumns(6);
		textFieldCodigo.setBackground(Color.BLACK);
		panelFormularioNuevoAgente.add(textFieldCodigo);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panelFormularioNuevoAgente.add(verticalStrut_2);
		
		botonAgregar = new JButton("Incorporar Agente");
		botonAgregar.setForeground(Color.GREEN);
		botonAgregar.setBackground(Color.BLACK);
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFormularioNuevoAgente.add(botonAgregar);
		
		JPanel panelListadoActualAgentes = new JPanel();
		panelListadoActualAgentes.setBackground(Color.DARK_GRAY);
		contentPane.add(panelListadoActualAgentes);
		panelListadoActualAgentes.setLayout(new BorderLayout(0, 0));
		
		
		datosTabla = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Agente", "Código"
				}
			); 
		
		
		
		table = new JTable(datosTabla);
		JTableHeader columnas = table.getTableHeader();
		columnas.setVisible(true);
		table.add(columnas);
		
		
		JScrollPane scroll =new JScrollPane(table);
		
		darFormatoOscuro(scroll);
		darFormatoOscuro(columnas);
		darFormatoOscuro(table);
		
		table.setForeground(Color.RED);
		panelListadoActualAgentes.add(scroll, BorderLayout.CENTER);
		
		
		
		btnSiguiente = new JButton("Siguiente");
		darFormatoOscuro(btnSiguiente);
		panelListadoActualAgentes.add(btnSiguiente, BorderLayout.SOUTH);
		
		
		
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
	       renderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		
		
		
		
	}
	
	static void darFormatoOscuro(JComponent comp) {
		comp.setForeground(Color.GREEN);
		comp.setFont(new Font("Dialog", Font.BOLD, 18));
		comp.setBackground(Color.BLACK);
	}
	
	
	public String obtenerTxtAlias() {
		return textFieldAlias.getText();
	}
	
	public String obtenerTxtCodigo() {
		return textFieldCodigo.getText();
	}
	
	public void borrarFormularioEspia() {
		textFieldAlias.setText("");
		textFieldCodigo.setText("");
	}
	
	
	public void agregarFilaEnTabla(String alias, String codigo) {
		Object[] fila = {alias, codigo};
		datosTabla.addRow(fila);
		//System.out.println(datosTabla.getValueAt(table.getRowCount() - 1, 0));
		table.setAlignmentY(CENTER_ALIGNMENT);
		

	}
	
	
	
	
	
	public void lanzarEventoNuevoEspia(ActionListener escucharClic) {
		botonAgregar.addActionListener(escucharClic);
	}
	
	public void lanzarEventoIrAPantallaCrearComunicaciones(ActionListener escucharClic) {
		btnSiguiente.addActionListener(escucharClic);
	}
}
