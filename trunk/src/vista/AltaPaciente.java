package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class AltaPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField dniTextField;
	private JTextField telefonoTextField;
	private JTextField celularTextField;
	private JTextField emialTextField;
	private JXDatePicker fechaNacimiento;
	private JTextField nroAfiliadoTextField;

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
		setBounds(100, 100, 529, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton guardarButton = new JButton("Guardar");
			guardarButton.setIcon(new ImageIcon(BuscardorPaciente.class.getResource("/image/guardar.png")));
			guardarButton.setBounds(267, 280, 116, 32);
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
			cancelarButton.setBounds(393, 280, 116, 32);
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
		lblDni.setBounds(10, 46, 46, 14);
		panel.add(lblDni);
		
		dniTextField = new JTextField();
		dniTextField.setBounds(98, 43, 152, 20);
		panel.add(dniTextField);
		dniTextField.setColumns(10);
		
		JLabel lblFechaNacimeitno = new JLabel("<html>Fecha<br>Nacimiento  (*)</html>");
		lblFechaNacimeitno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaNacimeitno.setBounds(10, 71, 92, 28);
		panel.add(lblFechaNacimeitno);
		
		JLabel lblSexo = new JLabel("Sexo  (*)");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexo.setBounds(260, 42, 69, 14);
		panel.add(lblSexo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBox.setBounds(337, 42, 86, 22);
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
		fechaNacimiento.setBounds(98, 77, 153, 22);
		panel.add(fechaNacimiento);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Obra Social", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 195, 499, 63);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 27, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"PAMI", "OSECAC"}));
		comboBox_1.setBounds(77, 23, 143, 22);
		panel_1.add(comboBox_1);
		
		JLabel lblNumeroAfiliado = new JLabel("<html>Numero<br>Afiliado  (*)</html>");
		lblNumeroAfiliado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroAfiliado.setBounds(240, 22, 70, 25);
		panel_1.add(lblNumeroAfiliado);
		
		nroAfiliadoTextField = new JTextField();
		nroAfiliadoTextField.setBounds(346, 24, 143, 20);
		panel_1.add(nroAfiliadoTextField);
		nroAfiliadoTextField.setColumns(10);
		
		JLabel lblDatosObligatorios = new JLabel("(*) Datos Obligatorios");
		lblDatosObligatorios.setBounds(10, 298, 139, 14);
		contentPanel.add(lblDatosObligatorios);
		
		this.setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModal(true);
		
}

}
