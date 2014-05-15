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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.Sistema;

import java.awt.Font;

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
	private JTextField ObraSocialTextField;
	private JTextField numeroAfiliadoTextField;
	
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
		setBounds(100, 100, 800, 600);
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
		scrollPane.setBounds(10, 135, 146, 224);
		getContentPane().add(scrollPane);
		
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBotones.setBounds(582, 0, 190, 324);
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
		cancelarButton.setBounds(10, 265, 154, 32);
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
		panelDatosPaciente.setBounds(10, 11, 562, 113);
		contentPane.add(panelDatosPaciente);
		panelDatosPaciente.setLayout(null);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setColumns(10);
		apellidoTextField.setBounds(372, 22, 137, 20);
		panelDatosPaciente.add(apellidoTextField);
		
		nombreTextField = new JTextField();
		nombreTextField.setColumns(10);
		nombreTextField.setBounds(78, 22, 137, 20);
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
		edadTextField.setBounds(78, 51, 137, 20);
		panelDatosPaciente.add(edadTextField);
		
		JLabel label_2 = new JLabel("<html>Obra<br>Social</html>");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(20, 74, 33, 28);
		panelDatosPaciente.add(label_2);
		
		ObraSocialTextField = new JTextField();
		ObraSocialTextField.setColumns(10);
		ObraSocialTextField.setBounds(78, 82, 137, 20);
		panelDatosPaciente.add(ObraSocialTextField);
		
		JLabel label_3 = new JLabel("<html>N\u00FAmero<br>Afiliado</html>");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(236, 78, 59, 24);
		panelDatosPaciente.add(label_3);
		
		numeroAfiliadoTextField = new JTextField();
		numeroAfiliadoTextField.setColumns(10);
		numeroAfiliadoTextField.setBounds(292, 82, 137, 20);
		panelDatosPaciente.add(numeroAfiliadoTextField);
		
		JLabel label_4 = new JLabel("Apellido");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(236, 25, 46, 14);
		panelDatosPaciente.add(label_4);
		
		JPanel panelConsultas = new JPanel();
		panelConsultas.setBorder(new TitledBorder(null, "Detalle Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConsultas.setBounds(166, 128, 406, 263);
		contentPane.add(panelConsultas);
		
		JPanel panelPracticaAmbulatoria = new JPanel();
		panelPracticaAmbulatoria.setBorder(new TitledBorder(null, "Detalle Pr\u00E1ctica Ambulatoria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPracticaAmbulatoria.setBounds(10, 480, 146, 50);
		contentPane.add(panelPracticaAmbulatoria);
		
		JPanel panelCirugia = new JPanel();
		panelCirugia.setBorder(new TitledBorder(null, "Detalle Cirug\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCirugia.setBounds(511, 480, 190, 84);
		contentPane.add(panelCirugia);
		
		//llenarTabla();
		
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
		
		table.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
	}
}

