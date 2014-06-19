package vista;


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

import DTO.PermisoDTO;

import controlador.Sistema;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class AltaUsuarioAdministrativo extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField dniTextField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField usuarioTextField;
	private JTextField pswTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaUsuarioAdministrativo dialog = new AltaUsuarioAdministrativo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaUsuarioAdministrativo() {
		initGUI();
	}
	
	public void initGUI(){
		setTitle("Alta Usuario Administrativo");
		setBounds(100, 100, 445, 329);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Identificaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 0, 419, 138);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDni = new JLabel("Tipo Doc.");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDni.setBounds(10, 19, 76, 14);
		panel.add(lblDni);
		
		dniTextField = new JTextField();
		dniTextField.setBounds(103, 45, 210, 20);
		panel.add(dniTextField);
		dniTextField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre (*)");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 76, 76, 14);
		panel.add(lblNombre);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(103, 73, 294, 20);
		panel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido (*)");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(10, 107, 76, 14);
		panel.add(lblApellido);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setBounds(103, 104, 294, 20);
		panel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		JLabel label = new JLabel("DNI (*)");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 48, 46, 14);
		panel.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DNI", "LE", "LC"}));
		comboBox.setBounds(103, 15, 109, 22);
		panel.add(comboBox);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexo.setBounds(244, 20, 69, 14);
		panel.add(lblSexo);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBox_1.setBounds(311, 15, 86, 22);
		panel.add(comboBox_1);
		{
			JButton guardarButton = new JButton("Guardar");
			guardarButton.setIcon(new ImageIcon(AltaUsuarioAdministrativo.class.getResource("/image/guardar.png")));
			guardarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
										
					if (dniTextField.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "El DNI es un dato Obligatorio", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						dniTextField.requestFocus();
					}else if (nombreTextField.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "El NOMBRE es un dato Obligatorio", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						nombreTextField.requestFocus();
						
					}else if(apellidoTextField.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "El APELLIDO es un dato Obligatorio", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						apellidoTextField.requestFocus();
					}else if(usuarioTextField.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "El USUARIO es un dato Obligatorio", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						usuarioTextField.requestFocus();
					}else if(pswTextField.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "La CONTRASEÑA es un dato Obligatorio", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						pswTextField.requestFocus();
						
					}else{
						boolean alta = true;
					//boolean alta = Sistema.getInstancia().altaUsuario(nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(dniTextField.getText()), matricula , usuarioTextField.getText(), pswTextField.getText(), vecPermisos);
						if (alta){
							JOptionPane.showMessageDialog(null, "El Usuario con DNI " + dniTextField.getText() + " fue creado.");
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "No se pudo dar de Alta el Usuario", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					 
					
//				      dispose();
//				      BuscardorUsuario bu = new BuscardorUsuario();
//				      bu.setVisible(true);
				      
				      
				}
			});
			guardarButton.setBounds(189, 251, 116, 32);
			getContentPane().add(guardarButton);
			guardarButton.setActionCommand("OK");
			getRootPane().setDefaultButton(guardarButton);
		}
		{
			JButton cancelarButton = new JButton("Cancelar");
			cancelarButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/cancel.png")));
			cancelarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					BuscardorUsuario bu = new BuscardorUsuario();
					bu.setVisible(true);
				}
			});
			cancelarButton.setBounds(313, 251, 116, 32);
			getContentPane().add(cancelarButton);
			cancelarButton.setActionCommand("Cancel");
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Datos de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 149, 419, 91);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario (*)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 25, 75, 14);
		panel_1.add(lblNewLabel);
		
		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(106, 22, 289, 20);
		panel_1.add(usuarioTextField);
		usuarioTextField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a (*)");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContrasea.setBounds(10, 58, 86, 14);
		panel_1.add(lblContrasea);
		
		pswTextField = new JTextField();
		pswTextField.setBounds(106, 55, 289, 20);
		panel_1.add(pswTextField);
		pswTextField.setColumns(10);
		
	
		
		JLabel lblDatosObligatorios = new JLabel("(*) Datos Obligatorios");
		lblDatosObligatorios.setBounds(10, 269, 146, 14);
		getContentPane().add(lblDatosObligatorios);
	   
		this.setLocationRelativeTo(null);
		//setAlwaysOnTop(true);
		setModal(true);
	}
	
	
}
