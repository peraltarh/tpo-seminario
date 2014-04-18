package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;

import DTO.PermisoDTO;

import controlador.Sistema;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;

public class AltaUsuario extends JDialog {
	
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
			AltaUsuario dialog = new AltaUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaUsuario() {
		initGUI();
	}
	
	public void initGUI(){
		setTitle("Alta Usuario");
		setBounds(100, 100, 445, 587);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Identificaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 419, 152);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI (*)");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDni.setBounds(10, 24, 46, 14);
		panel.add(lblDni);
		
		dniTextField = new JTextField();
		dniTextField.setBounds(103, 21, 210, 20);
		panel.add(dniTextField);
		dniTextField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre (*)");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 50, 76, 14);
		panel.add(lblNombre);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(103, 47, 294, 20);
		panel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido (*)");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(10, 75, 76, 14);
		panel.add(lblApellido);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setBounds(103, 78, 294, 20);
		panel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		final JCheckBox chckbxEsMdico = new JCheckBox("Es M\u00E9dico");
		chckbxEsMdico.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxEsMdico.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (chckbxEsMdico.isSelected()){
					matriculaTextField.setVisible(true);
					lblMatricula.setVisible(true);
				}else{
					matriculaTextField.setVisible(false);
					lblMatricula.setVisible(false);	
				}
			}
		});
		chckbxEsMdico.setBounds(6, 96, 97, 23);
		panel.add(chckbxEsMdico);
		
		lblMatricula = new JLabel("Matr\u00EDcula (*)");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(10, 125, 76, 14);
		panel.add(lblMatricula);
		lblMatricula.setVisible(false);
		
		matriculaTextField = new JTextField();
		matriculaTextField.setBounds(103, 122, 210, 20);
		panel.add(matriculaTextField);
		//matriculaTextField.setColumns(10);
		matriculaTextField.setVisible(false);
		{
			JButton guardarButton = new JButton("Guardar");
			guardarButton.setIcon(new ImageIcon(AltaUsuario.class.getResource("/image/guardar.png")));
			guardarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Vector<Integer> vec = new Vector<Integer>();

					ListModel a = asignadoList.getModel(); 
				      for (int i = 0; i < a.getSize(); i++){
				    	  System.out.println("Descripcion---->"+a.getElementAt(i));
				    	  int id = Sistema.getInstancia().getIdPermiso(a.getElementAt(i).toString());
				    	  System.out.println("idPermiso--->"+id);
				    	  vec.add(id);
				      }
				 
				      dispose();
				      BuscardorUsuario bu = new BuscardorUsuario();
				      bu.setVisible(true);
				      
				      
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
		panel_2.setBorder(new TitledBorder(null, "Permisos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 263, 419, 245);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblPermisosDisponibles = new JLabel("Permisos Disponibles");
		lblPermisosDisponibles.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPermisosDisponibles.setBounds(10, 23, 150, 14);
		panel_2.add(lblPermisosDisponibles);
		
		JLabel lblPermisosAsignados = new JLabel("Permisos Asignados");
		lblPermisosAsignados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPermisosAsignados.setBounds(251, 23, 143, 14);
		panel_2.add(lblPermisosAsignados);
		
	
		asignadoModel =  new DefaultListModel();
		disponibleModel =  new DefaultListModel();
		
		JButton agregarButton = new JButton("");
		agregarButton.setIcon(new ImageIcon(AltaUsuario.class.getResource("/image/der.png")));
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
		removerButton.setIcon(new ImageIcon(AltaUsuario.class.getResource("/image/izq.png")));
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
		setAlwaysOnTop(true);
		setModal(true);
	}
	
	
	public void llenarDisponibleList (){
		
		Vector<PermisoDTO> vecPermisoDTO  = Sistema.getInstancia().getAllPermisos();
		
		for (int i = 0; i < vecPermisoDTO.size(); i++) {
			disponibleModel.addElement(vecPermisoDTO.elementAt(i).getDescripcion());
		}
		
	}
}
