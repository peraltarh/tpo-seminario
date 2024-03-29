package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXDatePicker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

public class AltaPaciente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField dniTextField;
	private JTextField telefonoTextField;
	private JTextField celularTextField;
	private JTextField emialTextField;
	private JXDatePicker fechaNacimiento;
	private JTextField nroAfiliadoTextField;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	JComboBox comboBoxObraSocial;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaPaciente dialog = new AltaPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaPaciente() {
		initGUI();
		
	}
	
	public void initGUI(){
		setTitle("Alta Paciente");
		setBounds(100, 100, 529, 505);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton guardarButton = new JButton("Guardar");
			guardarButton.setIcon(new ImageIcon(BuscardorPaciente.class.getResource("/image/guardar.png")));
			guardarButton.setBounds(262, 435, 116, 32);
			contentPanel.add(guardarButton);
			getRootPane().setDefaultButton(guardarButton);
		}
		{
			JButton cancelarButton = new JButton("Cancelar");
			cancelarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					BuscardorPaciente bp = new BuscardorPaciente();
					bp.setVisible(true);
				}
			});
			cancelarButton.setIcon(new ImageIcon(BuscardorPaciente.class.getResource("/image/cancel.png")));
			cancelarButton.setBounds(393, 435, 116, 32);
			contentPanel.add(cancelarButton);
			cancelarButton.setActionCommand("Cancel");
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 499, 173);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre (*)");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 21, 69, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido  (*)");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(260, 17, 69, 14);
		panel.add(lblApellido);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(98, 18, 152, 20);
		panel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setBounds(339, 18, 152, 20);
		panel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI  (*)");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDni.setBounds(260, 46, 46, 14);
		panel.add(lblDni);
		
		dniTextField = new JTextField();
		dniTextField.setBounds(339, 43, 152, 20);
		panel.add(dniTextField);
		dniTextField.setColumns(10);
		
		JLabel lblFechaNacimeitno = new JLabel("<html>Fecha<br>Nacimiento  (*)</html>");
		lblFechaNacimeitno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaNacimeitno.setBounds(237, 71, 92, 28);
		panel.add(lblFechaNacimeitno);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexo.setBounds(10, 81, 69, 14);
		panel.add(lblSexo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBox.setBounds(98, 77, 86, 22);
		panel.add(comboBox);
		
		JLabel lblTelefonoFijo = new JLabel("Telefono");
		lblTelefonoFijo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefonoFijo.setBounds(10, 113, 69, 14);
		panel.add(lblTelefonoFijo);
		
		telefonoTextField = new JTextField();
		telefonoTextField.setBounds(98, 110, 152, 20);
		panel.add(telefonoTextField);
		telefonoTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Celular");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(260, 113, 69, 14);
		panel.add(lblNewLabel);
		
		celularTextField = new JTextField();
		celularTextField.setBounds(339, 110, 152, 20);
		panel.add(celularTextField);
		celularTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 144, 46, 14);
		panel.add(lblEmail);
		
		emialTextField = new JTextField();
		emialTextField.setBounds(98, 141, 152, 20);
		panel.add(emialTextField);
		emialTextField.setColumns(10);
		
		fechaNacimiento = new JXDatePicker();
		fechaNacimiento.setDate(Calendar.getInstance().getTime());
		fechaNacimiento.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		fechaNacimiento.setBounds(338, 77, 153, 22);
		panel.add(fechaNacimiento);
		
		JLabel label = new JLabel("Tipo Doc.");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 50, 76, 14);
		panel.add(label);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"DNI", "LE", "LC"}));
		comboBox_1.setBounds(98, 49, 109, 22);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Obra Social", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 195, 499, 229);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 27, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		comboBoxObraSocial = new JComboBox();
		comboBoxObraSocial.setModel(new DefaultComboBoxModel(new String[] {"PAMI", "OSECAC"}));
		comboBoxObraSocial.setBounds(77, 23, 143, 22);
		panel_1.add(comboBoxObraSocial);
		
		JLabel lblNumeroAfiliado = new JLabel("<html>Numero<br>Afiliado</html>");
		lblNumeroAfiliado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroAfiliado.setBounds(240, 22, 70, 25);
		panel_1.add(lblNumeroAfiliado);
		
		nroAfiliadoTextField = new JTextField();
		nroAfiliadoTextField.setBounds(297, 24, 143, 20);
		panel_1.add(nroAfiliadoTextField);
		nroAfiliadoTextField.setColumns(10);
		
		table = new JTable();
		model = new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column==2)
					return true;
				return false;
			}
		};
		
		model.addColumn("Numero Afiliado");
		model.addColumn("Obra Social");
		model.addColumn("");
		
		table.setBounds(10, 65, 510, 202);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 60, 479, 144);
		panel_1.add(scrollPane);
		
		//table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		final AbstractAction actionEliminar = new AbstractAction() {

		       /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		       public void actionPerformed(ActionEvent e) {
		           table = (JTable) e.getSource();
		           int modelRow = Integer.valueOf(e.getActionCommand());
		           ((DefaultTableModel) table.getModel()).removeRow(modelRow);
		    	
		       }
		   };
		
		
		
		JButton agregarButton = new JButton("");
		agregarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				        model.addRow(new Object[]{nroAfiliadoTextField.getText(), comboBoxObraSocial.getSelectedItem().toString(),"Eliminar"});        
				    	table.setModel(model);

				    	nroAfiliadoTextField.setText(null);  
				    	nroAfiliadoTextField.requestFocus();
						@SuppressWarnings("unused")
						ButtonColumn buttonColumn = new ButtonColumn (table, actionEliminar, 2);
			}
		});
		
	
		agregarButton.setIcon(new ImageIcon(AltaPaciente.class.getResource("/image/add.png")));
		agregarButton.setBounds(450, 11, 40, 34);
		panel_1.add(agregarButton);
		
		JLabel lblDatosObligatorios = new JLabel("(*) Datos Obligatorios");
		lblDatosObligatorios.setBounds(10, 453, 139, 14);
		contentPanel.add(lblDatosObligatorios);
		
		this.setLocationRelativeTo(null);
		setModal(true);
		
}
}
