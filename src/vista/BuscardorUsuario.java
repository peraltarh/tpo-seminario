package vista;

import java.util.ArrayList;

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

import DTO.UsuarioDTO;

import controlador.Sistema;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import java.awt.Font;

public class BuscardorUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField buscarTextField;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private ArrayList <UsuarioDTO> usuarios;
	private JComboBox comboBox;
	
	
	private TableRowSorter<TableModel> sorter;
	
	public BuscardorUsuario() {
		initGUI();
	}
	
	

	public void initGUI(){
		setTitle("Buscar Usuarios");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("DNI");
		model.addColumn("Nombre Usuario");
		model.addColumn("Borrado");
	
		
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
							filtrar();
				
			}
		});
		buscarButton.setBounds(404, 7, 116, 32);
		contentPane.add(buscarButton);
		buscarButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/search.png")));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 47, 510, 6);
		contentPane.add(separator);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DNI", "Nombre", "Apellido"}));
		comboBox.setBounds(280, 14, 114, 25);
		contentPane.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(530, -12, 176, 322);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton nuevoAdministrativoButton = new JButton("<html>Usuario <br>Administrativo</br></html>");
		nuevoAdministrativoButton.setBounds(10, 23, 140, 40);
		panel.add(nuevoAdministrativoButton);
		nuevoAdministrativoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AltaUsuarioAdministrativo au = new AltaUsuarioAdministrativo();
				au.setVisible(true);
				
			}
		});
		nuevoAdministrativoButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/plus.png")));
		
		JButton modificarButton = new JButton("Modificar");
		modificarButton.setBounds(10, 130, 140, 32);
		panel.add(modificarButton);
		modificarButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/modificar.png")));
		//contentPane.add(table);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 265, 140, 32);
		panel.add(cancelarButton);
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/cancel.png")));
		
		JButton nuevoMedicobutton = new JButton("<html>Usuario <br>M\u00E9dico</br></html>");
		nuevoMedicobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AltaUsuarioMedico au = new AltaUsuarioMedico();
				au.setVisible(true);
			}
		});
		nuevoMedicobutton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/plus.png")));
		nuevoMedicobutton.setBounds(10, 74, 140, 40);
		panel.add(nuevoMedicobutton);
		modificarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int registro = table.getSelectedRow();
				if (registro == -1){
					JOptionPane.showMessageDialog(null, "Debe Selccionar un usuario para Modificar", "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
				}else{
					int dniUsuario = (Integer) table.getValueAt(registro, 2);
					
				//	System.out.println("----->"+dniUsuario);
						UsuarioDTO user = Sistema.getInstancia().getUsuario(dniUsuario);
						ModificarUsuario mu = new ModificarUsuario(user);
						mu.setVisible(true);
						dispose();
						
				}
				
			}
		});
		
		llenarTabla();
		
		this.setLocationRelativeTo(null);
		//setAlwaysOnTop(true);
		setModal(true);
	}
	
	private void filtrar() {
		
		ArrayList<RowFilter<Object, Object>> rfs = new ArrayList<RowFilter<Object,Object>>();
		
		RowFilter<TableModel, Object> rf = null;
		@SuppressWarnings("unused")
		int indiceColumnaTabla = 2;
		switch (comboBox.getSelectedIndex()) {
		case 0: indiceColumnaTabla = 2;break;//DNI
		case 1: indiceColumnaTabla = 3;break;//Usuario
		case 2: indiceColumnaTabla = 1;break;//Apellido
		}
		
		try {
			String text = buscarTextField.getText();
		    String[] textArray = text.split(" ");

		    for (int i = 0; i < textArray.length; i++) {
		        rfs.add(RowFilter.regexFilter("(?i)" + textArray[i], 0, 1, 2, 4));
		    }	
			
		    rf = RowFilter.andFilter(rfs);	
		
		} catch (java.util.regex.PatternSyntaxException e) {
			
		}
		
		sorter.setRowFilter(rf);
		}
	
	
	
	public void llenarTabla(){
		usuarios = Sistema.getInstancia().getUsuarios();

		model.setNumRows(usuarios.size());
		for (int i = 0; i < usuarios.size(); i++) {
			model.setValueAt(usuarios.get(i).getNombre(), i, 0);
			model.setValueAt(usuarios.get(i).getApellido(), i, 1);
			model.setValueAt(usuarios.get(i).getDni(), i, 2);
			model.setValueAt(usuarios.get(i).getUserName(), i, 3);
			if (usuarios.get(i).isBorrado() == true){
				model.setValueAt("SI", i, 4);	
			}else
				model.setValueAt("NO", i, 4);
			
		}
		
		table.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
	}
}

