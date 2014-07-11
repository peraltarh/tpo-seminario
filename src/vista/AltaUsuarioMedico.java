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

public class AltaUsuarioMedico extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField dniTextField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField matriculaTextField;
	private JTextField usuarioTextField;
	private JTextField pswTextField;
	private DefaultListModel asignadoModel;
	private DefaultListModel disponibleModel;
	private JList asignadoList;
	private JList disponibleList;
	private JLabel lblMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaUsuarioMedico dialog = new AltaUsuarioMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaUsuarioMedico() {
		initGUI();
	}
	
	public void initGUI(){
		setTitle("Alta Usuario Medico");
		setBounds(100, 100, 445, 587);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Identificaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 0, 419, 163);
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
		
		lblMatricula = new JLabel("Matr\u00EDcula (*)");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(10, 135, 76, 14);
		panel.add(lblMatricula);
		//lblMatricula.setVisible(false);
		
		matriculaTextField = new JTextField();
		matriculaTextField.setBounds(103, 132, 210, 20);
		panel.add(matriculaTextField);
		
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
		//matriculaTextField.setColumns(10);
		//matriculaTextField.setVisible(false);
		{
			JButton guardarButton = new JButton("Guardar");
			guardarButton.setIcon(new ImageIcon(AltaUsuarioMedico.class.getResource("/image/guardar.png")));
			guardarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ArrayList<Integer> vecPermisos = new ArrayList<Integer>();
					
					ListModel a = asignadoList.getModel(); 
				      for (int i = 0; i < a.getSize(); i++){
				    	  //System.out.println("Descripcion---->"+a.getElementAt(i));
				  //  	  int id = Sistema.getInstancia().getIdPermiso(a.getElementAt(i).toString());
				    	  //System.out.println("idPermiso--->"+id);
				    //	  vecPermisos.add(id);
				      }
					
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
					}else if(matriculaTextField.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "La Matricula es un dato Obligatorio", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						matriculaTextField.requestFocus();
					}else if (vecPermisos.size()==0){
						JOptionPane.showMessageDialog(null, "Debe Asginar al menos una Especialidad", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
					}else{
//						int matricula;
//						if (!chckbxEsMdico.isSelected()){
//							matricula = 0;
//						}else
							String matricula = matriculaTextField.getText();
				//TODO
							boolean alta = Sistema.getInstancia().altaUsuario(nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(dniTextField.getText()), matricula , usuarioTextField.getText(), pswTextField.getText(), "a");
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
			guardarButton.setBounds(187, 517, 116, 32);
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
			cancelarButton.setBounds(313, 517, 116, 32);
			getContentPane().add(cancelarButton);
			cancelarButton.setActionCommand("Cancel");
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Datos de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 167, 419, 91);
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Especialidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 263, 419, 245);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblEspecialidadDisponibles = new JLabel("Especialidad Disponibles");
		lblEspecialidadDisponibles.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEspecialidadDisponibles.setBounds(10, 23, 150, 14);
		panel_2.add(lblEspecialidadDisponibles);
		
		JLabel lblEspecialidadAsignados = new JLabel("Especialidad Asignadas");
		lblEspecialidadAsignados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEspecialidadAsignados.setBounds(251, 23, 143, 14);
		panel_2.add(lblEspecialidadAsignados);
		
	
		asignadoModel =  new DefaultListModel();
		disponibleModel =  new DefaultListModel();
		
		JButton agregarButton = new JButton("");
		agregarButton.setIcon(new ImageIcon(AltaUsuarioMedico.class.getResource("/image/der.png")));
		agregarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i;
				  int[] fromindex = disponibleList.getSelectedIndices();
			      Object[] from = disponibleList.getSelectedValues();

			      for(i = 0; i < from.length; i++){
			    	  asignadoModel.addElement(from[i]);
			      }
			     
			      for(i = (fromindex.length-1); i >=0; i--){
			    	  disponibleModel.remove(fromindex[i]);
			      }
				
				
			}
		});
		agregarButton.setBounds(179, 61, 55, 32);
		panel_2.add(agregarButton);
		
		JButton removerButton = new JButton("");
		removerButton.setIcon(new ImageIcon(AltaUsuarioMedico.class.getResource("/image/izq.png")));
		removerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				  int[] fromindex = asignadoList.getSelectedIndices();
			      Object[] from = asignadoList.getSelectedValues();

			      for(i = 0; i < from.length; i++){
			    	  disponibleModel.addElement(from[i]);
			      }
			     
			      for(i = (fromindex.length-1); i >=0; i--){
			    	  asignadoModel.remove(fromindex[i]);
			      }
			}
		});
		removerButton.setBounds(178, 104, 55, 32);
		panel_2.add(removerButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 155, 163);
		panel_2.add(scrollPane);
		
		disponibleList = new JList(disponibleModel);
		scrollPane.setViewportView(disponibleList);
		disponibleList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(251, 41, 155, 163);
		panel_2.add(scrollPane_1);
		
		asignadoList = new JList(asignadoModel);
		scrollPane_1.setViewportView(asignadoList);
		asignadoList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblDatosObligatorios = new JLabel("(*) Datos Obligatorios");
		lblDatosObligatorios.setBounds(10, 526, 146, 14);
		getContentPane().add(lblDatosObligatorios);
	   
		llenarDisponibleList ();
		
		this.setLocationRelativeTo(null);
		//setAlwaysOnTop(true);
		setModal(true);
	}
	
	
	public void llenarDisponibleList (){
		
		ArrayList<String> vecPermisoDTO  = Sistema.getInstancia().getAllPermisos();
		
		for (int i = 0; i < vecPermisoDTO.size(); i++) {
			disponibleModel.addElement(vecPermisoDTO.get(i));
		}
		
	}
}
