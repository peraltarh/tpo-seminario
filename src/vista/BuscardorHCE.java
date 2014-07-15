package vista;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;

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

import controlador.Sistema;
import modelo.Paciente;
import persistencia.AdministradorPersistenciaPaciente;

public class BuscardorHCE extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField buscarTextField;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	//private Vector <UsuarioDTO> usuarios;
	private JComboBox comboBox;
	
	
	private TableRowSorter<TableModel> sorter;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscardorHCE frame = new BuscardorHCE();
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
	public BuscardorHCE() {
		initGUI();
	}
	
	
	public void initGUI(){
		setTitle("Buscar HCE");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(10, 19, 46, 14);
		contentPane.add(lblBuscar);
		
		buscarTextField = new JTextField();
		buscarTextField.setBounds(53, 14, 217, 25);
		contentPane.add(buscarTextField);
		buscarTextField.setColumns(10);
		
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
		
		//TODO Ver que columnas hay que agregar
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Tipo");
		model.addColumn("DNI");
		model.addColumn("Sexo");
		model.addColumn("Edad");
		
		
		table.setBounds(10, 65, 510, 202);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 64, 510, 224);
		getContentPane().add(scrollPane);
		
		//table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JButton buscarButton = new JButton("Buscar");
		buscarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Paciente p=AdministradorPersistenciaPaciente.getInstancia().buscarPaciente(buscarTextField.getText(),comboBox.getSelectedItem().toString());
				cargarTabla(p);
			}
		});
		buscarButton.setBounds(404, 7, 116, 32);
		contentPane.add(buscarButton);
		buscarButton.setIcon(new ImageIcon(BuscardorHCE.class.getResource("/image/search.png")));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 47, 510, 6);
		contentPane.add(separator);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DNI", "LU", "LR"}));
		comboBox.setBounds(280, 14, 114, 25);
		contentPane.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(530, -12, 146, 322);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton verHCEButton = new JButton("Ver HCE");
		verHCEButton.setBounds(10, 27, 116, 32);
		panel.add(verHCEButton);
		verHCEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VerHCE vhce = new VerHCE();
				vhce.setVisible(true);
				
			}
		});
		verHCEButton.setIcon(new ImageIcon(BuscardorHCE.class.getResource("/image/HCE.png")));
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 265, 116, 32);
		panel.add(cancelarButton);
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarButton.setIcon(new ImageIcon(BuscardorHCE.class.getResource("/image/cancel.png")));
		
		//llenarTabla();
		
		this.setLocationRelativeTo(null);
		//setAlwaysOnTop(true);
		setModal(true);
	}
	
	protected void cargarTabla(Paciente p) {
		model.setNumRows(1);
		model.setValueAt(p.getNombre(), 0, 0);
		model.setValueAt(p.getApellido(), 0,1);
		model.setValueAt(p.getTipoDoc(), 0, 2);	
		model.setValueAt(p.getDni(), 0, 3);	
		model.setValueAt(p.getSexo(), 0, 4);	
		model.setValueAt((p.calcularEdad(p.getFechaNaciemiento()).substring(0, 2)), 0, 5);
		table.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
	}

	private void filtrar() {
		ArrayList<RowFilter<Object, Object>> rfs = new ArrayList<RowFilter<Object,Object>>();
		RowFilter<TableModel, Object> rf = null;
		int indiceColumnaTabla = 2;
		switch (comboBox.getSelectedIndex()) {
		case 0: indiceColumnaTabla = 3;break;//DNI
		case 1: indiceColumnaTabla = 4;break;//Apellido
		case 2: indiceColumnaTabla = 2;break;//Afiliado
		}
		try {
			String text = buscarTextField.getText();
		    String[] textArray = text.split(" ");

		    for (int i = 0; i < textArray.length; i++) {
		        rfs.add(RowFilter.regexFilter("(?i)" + textArray[i], 0, 1, 2, 4));
		    }	
			
		    rf = RowFilter.andFilter(rfs);	
			//rf = RowFilter.regexFilter( Pattern.compile(buscarTextField.getText(), Pattern.CASE_INSENSITIVE).toString(),indiceColumnaTabla );
		} catch (java.util.regex.PatternSyntaxException e) {
		}
		sorter.setRowFilter(rf);
		}
	
	


	
}

