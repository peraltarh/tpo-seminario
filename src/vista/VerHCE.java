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
		scrollPane.setBounds(10, 145, 207, 224);
		getContentPane().add(scrollPane);
		
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBotones.setBounds(582, 0, 190, 324);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton nuevaConsultaButton = new JButton("Nueva Consulta");
		nuevaConsultaButton.setBounds(10, 27, 154, 32);
		panelBotones.add(nuevaConsultaButton);
		nuevaConsultaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		nuevaConsultaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/HCE.png")));
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 265, 154, 32);
		panelBotones.add(cancelarButton);
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/cancel.png")));
		
		nuevaPrcticaAmbulatoriaButton= new JButton("<html>Nueva Práctica<br>Ambulatoria</html>");
		nuevaPrcticaAmbulatoriaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/HCE.png")));
		nuevaPrcticaAmbulatoriaButton.setBounds(10, 70, 154, 40);
		panelBotones.add(nuevaPrcticaAmbulatoriaButton);
		
		JButton nuevaCirugiaButton = new JButton("Nueva Cirug\u00EDa");
		nuevaCirugiaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/HCE.png")));
		nuevaCirugiaButton.setBounds(10, 121, 154, 32);
		panelBotones.add(nuevaCirugiaButton);
		
		JPanel panelDatosPaciente = new JPanel();
		panelDatosPaciente.setBorder(new TitledBorder(null, "Datos Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosPaciente.setBounds(10, 11, 519, 113);
		contentPane.add(panelDatosPaciente);
		
		JPanel panelConsultas = new JPanel();
		panelConsultas.setBorder(new TitledBorder(null, "Detalle Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConsultas.setBounds(252, 139, 320, 185);
		contentPane.add(panelConsultas);
		
		JPanel panelPracticaAmbulatoria = new JPanel();
		panelPracticaAmbulatoria.setBorder(new TitledBorder(null, "Detalle Pr\u00E1ctica Ambulatoria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPracticaAmbulatoria.setBounds(10, 386, 146, 50);
		contentPane.add(panelPracticaAmbulatoria);
		
		JPanel panelCirugia = new JPanel();
		panelCirugia.setBorder(new TitledBorder(null, "Detalle Cirug\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCirugia.setBounds(227, 341, 190, 84);
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

