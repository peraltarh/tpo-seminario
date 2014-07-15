package vista;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import modelo.Paciente;
import controlador.Sistema;

import java.awt.Font;
import java.awt.Color;

public class VerHCE extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JButton nuevaPrcticaAmbulatoriaButton;
	
	
	private TableRowSorter<TableModel> sorter;
	private JTextField apellidoTextField;
	private JTextField nombreTextField;
	private JTextField edadTextField;
	private JTextPane detalleTextPane;
	private JScrollPane scrollDetalleTextPane;
	private JTextField textField;
	private JButton buscarButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerHCE frame = new VerHCE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VerHCE() {
		initGUI();
	}
	
	
	public void initGUI(){
		setTitle("Buscar HCE");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		model = new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		model.addColumn("Fecha");
		model.addColumn("Tipo");
		
		table.setBounds(10, 65, 510, 202);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 141, 146, 291);
		getContentPane().add(scrollPane);
		
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBotones.setBounds(582, -11, 175, 469);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton nuevaConsultaButton = new JButton("Alta Consulta");
		nuevaConsultaButton.setBounds(10, 27, 154, 32);
		panelBotones.add(nuevaConsultaButton);
		nuevaConsultaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AltaConsulta ac = new AltaConsulta();
				ac.setVisible(true);
				
			}
		});
		nuevaConsultaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/consulta.png")));
		
		boolean validarPermisoConsulta = Sistema.getInstancia().validarPermiso("NUEVA_CONSULTA");
		if (validarPermisoConsulta==true){
			nuevaConsultaButton.setEnabled(true);
		}else
			nuevaConsultaButton.setEnabled(false);
		
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 359, 154, 32);
		panelBotones.add(cancelarButton);
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/cancel.png")));
		
		nuevaPrcticaAmbulatoriaButton= new JButton("<html>Alta Práctica<br>Ambulatoria</html>");
		nuevaPrcticaAmbulatoriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AltaPractica ap = new AltaPractica();
				ap.setVisible(true);
			}
		});
		nuevaPrcticaAmbulatoriaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/practica.png")));
		nuevaPrcticaAmbulatoriaButton.setBounds(10, 70, 154, 40);
		panelBotones.add(nuevaPrcticaAmbulatoriaButton);
		
		boolean validarPermisoPractica = Sistema.getInstancia().validarPermiso("NUEVA_PRACTICA");
		if (validarPermisoPractica==true){
			nuevaPrcticaAmbulatoriaButton.setEnabled(true);
		}else
			nuevaPrcticaAmbulatoriaButton.setEnabled(false);
		
		JButton nuevaCirugiaButton = new JButton("<html>Alta Práctica<br>Quirurgíca</html>");
		nuevaCirugiaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AltaCirugia ac = new AltaCirugia();
				ac.setVisible(true);
				
			}
		});
		nuevaCirugiaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/cirugia.png")));
		nuevaCirugiaButton.setBounds(10, 121, 154, 32);
		panelBotones.add(nuevaCirugiaButton);
		
		boolean validarPermisoCir = Sistema.getInstancia().validarPermiso("NUEVA_CIRUGIA");
		if (validarPermisoCir==true){
			nuevaCirugiaButton.setEnabled(true);
		}else
			nuevaCirugiaButton.setEnabled(false);
		
		
		JPanel panelDatosPaciente = new JPanel();
		panelDatosPaciente.setBorder(new TitledBorder(null, "Datos Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosPaciente.setBounds(10, 52, 562, 78);
		contentPane.add(panelDatosPaciente);
		panelDatosPaciente.setLayout(null);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setColumns(10);
		apellidoTextField.setBounds(328, 22, 197, 20);
		panelDatosPaciente.add(apellidoTextField);
		
		nombreTextField = new JTextField();
		nombreTextField.setColumns(10);
		nombreTextField.setBounds(78, 22, 184, 20);
		panelDatosPaciente.add(nombreTextField);
		
		JLabel label = new JLabel("Nombre");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(20, 25, 46, 14);
		panelDatosPaciente.add(label);
		
		JLabel label_1 = new JLabel("Edad");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(20, 50, 46, 14);
		panelDatosPaciente.add(label_1);
		
		edadTextField = new JTextField();
		edadTextField.setColumns(10);
		edadTextField.setBounds(78, 47, 184, 20);
		panelDatosPaciente.add(edadTextField);
		
		JLabel label_4 = new JLabel("Apellido");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(272, 25, 46, 14);
		panelDatosPaciente.add(label_4);
		
		JLabel label_3 = new JLabel("Sexo");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(282, 50, 69, 14);
		panelDatosPaciente.add(label_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBox_1.setBounds(328, 46, 197, 22);
		panelDatosPaciente.add(comboBox_1);
		
		JPanel panelConsultas = new JPanel();
		panelConsultas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsultas.setBounds(166, 130, 406, 303);
		contentPane.add(panelConsultas);
		panelConsultas.setLayout(null);
		
		detalleTextPane = new JTextPane();
		scrollDetalleTextPane = new JScrollPane(detalleTextPane);
		scrollDetalleTextPane.setBounds(10, 17, 386, 272);
		scrollDetalleTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelConsultas.add(scrollDetalleTextPane);
		detalleTextPane.setContentType("text/html");
		
		
		//String detalle = "<html><b style=\"color:pink\">" + myBirthday + "</span></html>";
		
		
		//TODO Solo para imprimir las pantallas
		String detalle = "<html><b>Obra Social:</b>OSECAC - <b>Numero Afiliado:</b>11111111 <br><b>Observaciones:</b></br><br>El paciente evoluciona correctamente</br></html>";
		detalleTextPane.setText(detalle);
		
		JLabel label_2 = new JLabel("Buscar");
		label_2.setBounds(23, 23, 46, 14);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(79, 18, 217, 25);
		contentPane.add(textField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DNI", "LC"}));
		comboBox.setBounds(306, 16, 114, 25);
		contentPane.add(comboBox);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setIcon(new ImageIcon(BuscardorHCE.class.getResource("/image/search.png")));
		buscarButton.setBounds(456, 15, 116, 32);
		contentPane.add(buscarButton);
		
		llenarTabla();
		
		this.setLocationRelativeTo(null);
		//setAlwaysOnTop(true);
		setModal(true);
	}
	
	public void llenarTabla(){
//		usuarios = Sistema.getInstancia().getUsuarios();
//
//		model.setNumRows(usuarios.size());
//		for (int i = 0; i < usuarios.size(); i++) {
//			model.setValueAt(usuarios.elementAt(i).getIdUsuario(), i, 0);
//			model.setValueAt(usuarios.elementAt(i).getNombre(), i, 1);
//			model.setValueAt(usuarios.elementAt(i).getApellido(), i, 2);
//			model.setValueAt(usuarios.elementAt(i).getDni(), i, 3);
//			model.setValueAt(usuarios.elementAt(i).getUserName(), i, 4);
//			if (usuarios.elementAt(i).isBorrado() == true){
//				model.setValueAt("SI", i, 5);	
//			}else
//				model.setValueAt("NO", i, 5);
//				
//		}
		
		//TODO Solo para imprimir pantallas
		 model.addRow(new Object[]{"28/05/2013", "Consulta"});        
	    	table.setModel(model);
		
		table.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
	}
}

