package vista;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DTO.PacienteDTO;
import persistencia.AdministradorPersistenciaAuditoria;
import persistencia.AdministradorPersistenciaPracticaAmbulatoria;
import controlador.Sistema;



import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import java.awt.Toolkit;

public class AltaPractica extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private JPanel DiagnosticoPanel;
	private JTextPane diagnositicoTextPane;
	private JScrollPane scrollTratamientoTextPane;
	private JButton cancelarButton;
	private JXDatePicker fechaPractica;
	private JComboBox ojoComboBox;
	private JComboBox prestacionComboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNombre;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField edadTextField;
	private JComboBox obraSocialComboBox;
	private JTextField numeroAfiliadoTextField;
	private PacienteDTO pacienteDTOAct;
	private JLabel lblObraSocial;
	private JLabel lblNewLabel_2;
	private JLabel lblEdad;
	private JLabel label;
	private JComboBox comboBox;
	private JPanel practicaPanel;




	public AltaPractica(PacienteDTO pacienteDTOActual) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaPractica.class.getResource("/image/practica.png")));
		setTitle("Pr\u00E1ctica Ambulatoria");
		pacienteDTOAct=pacienteDTOActual;
		initGUI();
	}
	
	private void initGUI(){
		setBounds(100, 100, 470, 565);
		getContentPane().setLayout(null);
		
		JPanel datosPacientePanel = new JPanel();
		datosPacientePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datos Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		datosPacientePanel.setBounds(10, 11, 441, 116);
		getContentPane().add(datosPacientePanel);
		datosPacientePanel.setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 22, 46, 14);
		datosPacientePanel.add(lblNombre);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(68, 19, 137, 20);
		datosPacientePanel.add(nombreTextField);
		nombreTextField.setColumns(10);
		nombreTextField.setText(pacienteDTOAct.getNombre());
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(226, 22, 46, 14);
		datosPacientePanel.add(lblApellido);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setBounds(282, 19, 137, 20);
		datosPacientePanel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		apellidoTextField.setText(pacienteDTOAct.getApellido());
		
		lblObraSocial = new JLabel("<html>Obra<br>Social</html>");
		lblObraSocial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObraSocial.setBounds(10, 71, 33, 28);
		datosPacientePanel.add(lblObraSocial);
		
		lblNewLabel_2 = new JLabel("<html>N\u00FAmero<br>Afiliado</html>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(226, 75, 59, 24);
		datosPacientePanel.add(lblNewLabel_2);
		
		lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdad.setBounds(10, 47, 46, 14);
		datosPacientePanel.add(lblEdad);
		
		
		
		edadTextField = new JTextField();
		edadTextField.setBounds(68, 48, 137, 20);
		datosPacientePanel.add(edadTextField);
		edadTextField.setColumns(10);
		edadTextField.setText(pacienteDTOAct.calcularEdad().substring(0, 2));
		
		obraSocialComboBox = new JComboBox();
		obraSocialComboBox.setBounds(68, 75, 137, 22);
		datosPacientePanel.add(obraSocialComboBox);
		
		ArrayList<String> obrasSocialesPaciente =  pacienteDTOAct.getObrasSociales();
		for (String string : obrasSocialesPaciente) {
			obraSocialComboBox.addItem(string);
		}

		numeroAfiliadoTextField = new JTextField();
		numeroAfiliadoTextField.setBounds(282, 77, 137, 22);
		datosPacientePanel.add(numeroAfiliadoTextField);
		
		//TODO esto va tambien en el focus listener para cuando selecciona otra obra social
		ArrayList<Integer> numerosDeAfiliadoPaciente = pacienteDTOAct.getNroAfiliado();
		numeroAfiliadoTextField.setText(numerosDeAfiliadoPaciente.get(obraSocialComboBox.getSelectedIndex()).toString());
		
		
		label = new JLabel("Sexo");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(226, 51, 69, 14);
		datosPacientePanel.add(label);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBox.setBounds(282, 47, 137, 22);
		datosPacientePanel.add(comboBox);
		if(pacienteDTOAct.getSexo().equalsIgnoreCase("f"))
			comboBox.setSelectedIndex(1);
		else
			comboBox.setSelectedIndex(0);
		
		practicaPanel = new JPanel();
		practicaPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Pr\u00E1ctica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		practicaPanel.setBounds(10, 127, 441, 100);
		getContentPane().add(practicaPanel);
		practicaPanel.setLayout(null);
		
		prestacionComboBox = new JComboBox();
		prestacionComboBox.setBounds(66, 25, 339, 22);

		//TODO esto va tambien en el focus listener para cuando selecciona otra obra social
		ArrayList<String>prestacionesDesc=Sistema.getInstancia().getPracticasAmbulatoriasObraSocial(this.obraSocialComboBox.getSelectedItem().toString());;
		
		for (String string : prestacionesDesc) {
			prestacionComboBox.addItem(string);
		}
		
		AutoCompleteDecorator.decorate(this.prestacionComboBox);
		practicaPanel.add(prestacionComboBox);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(10, 29, 46, 14);
		practicaPanel.add(lblTipo);
		
		
		fechaPractica = new JXDatePicker();
		fechaPractica.setDate(Calendar.getInstance().getTime());
		fechaPractica.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		fechaPractica.setBounds(66, 58, 126, 22);
		practicaPanel.add(fechaPractica);
				
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(10, 62, 46, 14);
		practicaPanel.add(lblFecha);
		
		JLabel lblOjo = new JLabel("OJO");
		lblOjo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOjo.setBounds(220, 62, 46, 14);
		practicaPanel.add(lblOjo);
		
		ojoComboBox = new JComboBox();
		ojoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Izquierdo", "Derecho", "Ambos"}));
		ojoComboBox.setBounds(276, 58, 129, 22);
		practicaPanel.add(ojoComboBox);
		
		
		
		
		DiagnosticoPanel = new JPanel();
		DiagnosticoPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Diagnostico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DiagnosticoPanel.setBounds(10, 234, 441, 208);
		getContentPane().add(DiagnosticoPanel);
		DiagnosticoPanel.setLayout(null);
		
		
		diagnositicoTextPane = new JTextPane();
		scrollTratamientoTextPane = new JScrollPane(diagnositicoTextPane);
		scrollTratamientoTextPane.setBounds(10, 25, 424, 171);
		scrollTratamientoTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		DiagnosticoPanel.add(scrollTratamientoTextPane);
		
		JButton guardarButton = new JButton("Guardar");
		guardarButton.setSelectedIcon(new ImageIcon(AltaPractica.class.getResource("/image/practica.png")));
		guardarButton.setMnemonic(KeyEvent.VK_G);
		guardarButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/guardar.png")));
		guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = format.format(fechaPractica.getDate());
				
				
				Sistema.getInstancia().altaPracticaAmbulatoria(prestacionComboBox.getSelectedItem().toString(),Sistema.getInstancia().getUsuarioActual(),ojoComboBox.getSelectedItem().toString(),diagnositicoTextPane.getText(),dateString,pacienteDTOAct.getDni(),pacienteDTOAct.getTipoDoc());

			//	AdministradorPersistenciaPracticaAmbulatoria.getInstancia().altaAmbulatoria(prestacionComboBox.getSelectedItem().toString(),Sistema.getInstancia().getUsuarioActual(),ojoComboBox.getSelectedItem().toString(),diagnositicoTextPane.getText(),dateString,pacienteDTOAct.getDni(),pacienteDTOAct.getTipoDoc());
				
				/*String auditar="Se creo una Practica Ambulatoria y se asocio al Paciente \t"+pacienteDTOAct.getNombre()+"\t"+pacienteDTOAct.getApellido();
				AdministradorPersistenciaAuditoria.getInstancia().registrar(Sistema.getInstancia().getUsuarioActual(),auditar);*/
				
				dispose();
				
			}
		});
		guardarButton.setBounds(211, 453, 116, 32);
		getContentPane().add(guardarButton);
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setMnemonic(KeyEvent.VK_C);
		cancelarButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/cancel.png")));
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VerHCE vhce = new VerHCE();
				vhce.setVisible(true);
			}
		});
		cancelarButton.setBounds(335, 453, 116, 32);
		getContentPane().add(cancelarButton);
		
		lblNewLabel = new JLabel("Secreto m\u00E9dico dentro de los alcances del Art. 156 del CODIGO PENAL.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(10, 510, 441, 19);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("INFORME CONFIDENCIAL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 496, 148, 14);
		getContentPane().add(lblNewLabel_1);
		
		this.setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModal(true);
	}
}
