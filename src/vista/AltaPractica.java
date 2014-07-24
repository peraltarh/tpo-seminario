package vista;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;

import modelo.ObraSocial;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DTO.PacienteDTO;
import controlador.Sistema;








import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import java.awt.Toolkit;

public class AltaPractica extends JDialog{

	
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
	private JTextField sexoTextField;
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
		nombreTextField.setEditable(false);
		nombreTextField.setBounds(68, 19, 137, 20);
		datosPacientePanel.add(nombreTextField);
		nombreTextField.setColumns(10);
		nombreTextField.setText(pacienteDTOAct.getNombre());
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(226, 22, 46, 14);
		datosPacientePanel.add(lblApellido);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setEditable(false);
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
		edadTextField.setEditable(false);
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
		numeroAfiliadoTextField.setEditable(false);
		numeroAfiliadoTextField.setBounds(282, 77, 137, 22);
		datosPacientePanel.add(numeroAfiliadoTextField);
		
		ArrayList<Integer> numerosDeAfiliadoPaciente = pacienteDTOAct.getNroAfiliado();
		numeroAfiliadoTextField.setText(numerosDeAfiliadoPaciente.get(obraSocialComboBox.getSelectedIndex()).toString());
		
		
		label = new JLabel("Sexo");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(226, 51, 69, 14);
		datosPacientePanel.add(label);
		
		sexoTextField = new JTextField();
		sexoTextField.setEditable(false);
		sexoTextField.setBounds(282, 47, 137, 22);
		datosPacientePanel.add(sexoTextField);
		
		if(pacienteDTOAct.getSexo().equalsIgnoreCase("f"))
			sexoTextField.setText("Femenino");
		else
			sexoTextField.setText("Masculino");
		
		
		practicaPanel = new JPanel();
		practicaPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Pr\u00E1ctica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		practicaPanel.setBounds(10, 127, 441, 100);
		getContentPane().add(practicaPanel);
		practicaPanel.setLayout(null);
		
		prestacionComboBox = new JComboBox();
		prestacionComboBox.setBounds(66, 25, 339, 22);

		ArrayList<String>prestacionesDesc=Sistema.getInstancia().getPracticasAmbulatoriasObraSocial(this.obraSocialComboBox.getSelectedItem().toString());;
		
		for (String string : prestacionesDesc) {
			prestacionComboBox.addItem(string.substring(5));
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
				


				
				if (diagnositicoTextPane.getText().equals("")){
					JOptionPane.showMessageDialog(null, "El campo Diagnostico es un dato obligatorio", "Alta Practica Ambulatoria", JOptionPane.ERROR_MESSAGE);
				}else{
					
					String currentDate = format.format(GregorianCalendar.getInstance().getTime());
					
					if (GregorianCalendar.getInstance().getTime().before(fechaPractica.getDate())){
						String msj = "La fecha de la Practica no puede ser superior a "+ currentDate;
						JOptionPane.showMessageDialog(null, msj , "Alta Practica Ambulatoria", JOptionPane.ERROR_MESSAGE);	
					}else{
						String pa = "PA - " + prestacionComboBox.getSelectedItem().toString();
						
						ObraSocial os = Sistema.getInstancia().buscarObrasocial(obraSocialComboBox.getSelectedItem().toString());
						boolean alta = Sistema.getInstancia().altaPracticaAmbulatoria(pa,Sistema.getInstancia().getUsuarioActual(),ojoComboBox.getSelectedItem().toString(),diagnositicoTextPane.getText(),fechaPractica.getDate(),pacienteDTOAct.getDni(),pacienteDTOAct.getTipoDoc(),os.getIdObrasocial());
						if(alta){
							JOptionPane.showMessageDialog(null, "Se creó la Practica Ambulatoria", "Alta Practica Ambulatoria", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							VerHCE vhce = new VerHCE(pacienteDTOAct.getDni(),pacienteDTOAct.getTipoDoc());
							vhce.setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "Error al crear Practica Ambulatoria", "Alta Practica Ambulatoria", JOptionPane.ERROR_MESSAGE);
						}
							
					}
					
				}
						
					
					

				
				
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
				VerHCE vhce = new VerHCE(pacienteDTOAct.getDni(),pacienteDTOAct.getTipoDoc());
				vhce.setVisible(true);
			}
		});
		cancelarButton.setBounds(335, 453, 116, 32);
		
		
		
		
		obraSocialComboBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
               if(e.getStateChange() == ItemEvent.SELECTED){
				ArrayList<Integer> numerosDeAfiliadoPaciente = pacienteDTOAct.getNroAfiliado();
				numeroAfiliadoTextField.setText(numerosDeAfiliadoPaciente.get(obraSocialComboBox.getSelectedIndex()).toString());
				
				
				prestacionComboBox.removeAllItems();
				ArrayList<String>prestacionesDesc=Sistema.getInstancia().getPracticasAmbulatoriasObraSocial(obraSocialComboBox.getSelectedItem().toString());;
				
				for (String string : prestacionesDesc) {
					prestacionComboBox.addItem(string.substring(5));
				}
				if(prestacionComboBox.getItemCount()>0)prestacionComboBox.setSelectedIndex(0);
				diagnositicoTextPane.requestFocus();
               
               
               }  
            }
        });
		
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
		//setAlwaysOnTop(true);
		setModal(true);
	}

}
