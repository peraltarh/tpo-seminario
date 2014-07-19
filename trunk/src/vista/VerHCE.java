package vista;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import modelo.Consulta;
import modelo.HistoriaClinica;
import modelo.PracticaAmbulatoria;
import modelo.PracticaQuirurgica;
import modelo.Prestacion;
import modelo.itemHistoriaClinica;
import persistencia.AdministradorPersistenciaAuditoria;
import DTO.PacienteDTO;
import controlador.Sistema;

import java.awt.Font;
import java.awt.Color;

public class VerHCE extends JDialog implements FocusListener{

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
	private JTextField apellidoTextField;
	private JTextField nombreTextField;
	private JTextField edadTextField;
	private JTextPane detalleTextPane;
	private JScrollPane scrollDetalleTextPane;
	private JTextField textField_numeroDoc;
	private JButton buscarButton;
	private JComboBox comboBox_TipoDoc;
	private PacienteDTO pacienteDTOActual;
	private JTextField sexoTextField;
	private JPanel panelConsultas;
	private HistoriaClinica hceTemp;
	
	public VerHCE() {
		initGUI();
	}
	
	
	public void initGUI(){
		setTitle("Buscar HCE");
		setResizable(false);
		setBounds(100, 100, 758, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		model = new DefaultTableModel(){
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
		scrollPane.setBounds(10, 141, 146, 291);
		getContentPane().add(scrollPane);
		
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addFocusListener(this);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBotones.setBounds(582, -11, 175, 469);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton nuevaConsultaButton = new JButton("Alta Consulta");
		nuevaConsultaButton.setBounds(10, 27, 154, 32);
		panelBotones.add(nuevaConsultaButton);
		nuevaConsultaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AltaConsulta ac = new AltaConsulta();
				ac.setVisible(true);
				
			}
		});
		nuevaConsultaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/consulta.png")));
		
		boolean validarPermisoConsulta = Sistema.getInstancia().validarPermiso("Oftalmologo General");
		if (validarPermisoConsulta==true){
			nuevaConsultaButton.setEnabled(true);
		}else
			nuevaConsultaButton.setEnabled(false);
		
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 359, 154, 32);
		panelBotones.add(cancelarButton);
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/cancel.png")));
		
		nuevaPrcticaAmbulatoriaButton= new JButton("<html>Alta Practica<br>Ambulatoria</html>");
		nuevaPrcticaAmbulatoriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AltaPractica ap = new AltaPractica(pacienteDTOActual);
				ap.setVisible(true);
			}
		});
		nuevaPrcticaAmbulatoriaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/practica.png")));
		nuevaPrcticaAmbulatoriaButton.setBounds(10, 70, 154, 40);
		panelBotones.add(nuevaPrcticaAmbulatoriaButton);
		
		boolean validarPermisoPractica = Sistema.getInstancia().validarPermiso("Oftalmologo Practica Ambulatoria");
		if (validarPermisoPractica==true){
			nuevaPrcticaAmbulatoriaButton.setEnabled(true);
		}else
			nuevaPrcticaAmbulatoriaButton.setEnabled(false);
		
		JButton nuevaCirugiaButton = new JButton("<html>Alta Practica<br>Quirurgica</html>");
		nuevaCirugiaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AltaCirugia ac = new AltaCirugia(pacienteDTOActual);
				ac.setVisible(true);
				
			}
		});
		nuevaCirugiaButton.setIcon(new ImageIcon(VerHCE.class.getResource("/image/cirugia.png")));
		nuevaCirugiaButton.setBounds(10, 121, 154, 32);
		panelBotones.add(nuevaCirugiaButton);
		
		boolean validarPermisoCir = Sistema.getInstancia().validarPermiso("Oftalmologo Quirurgico");
		if (validarPermisoCir==true){
			nuevaCirugiaButton.setEnabled(true);
		}else
			nuevaCirugiaButton.setEnabled(false);
		
		
		JPanel panelDatosPaciente = new JPanel();
		panelDatosPaciente.setBorder(new TitledBorder(null, "Datos Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosPaciente.setBounds(10, 52, 562, 78);
		contentPane.add(panelDatosPaciente);
		panelDatosPaciente.setLayout(null);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setColumns(10);
		apellidoTextField.setBounds(328, 22, 197, 20);
		panelDatosPaciente.add(apellidoTextField);
		
		nombreTextField = new JTextField();
		nombreTextField.setColumns(10);
		nombreTextField.setBounds(78, 22, 184, 20);
		panelDatosPaciente.add(nombreTextField);
		
		JLabel label = new JLabel("Nombre");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(20, 25, 46, 14);
		panelDatosPaciente.add(label);
		
		JLabel label_1 = new JLabel("Edad");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(20, 50, 46, 14);
		panelDatosPaciente.add(label_1);
		
		edadTextField = new JTextField();
		edadTextField.setColumns(10);
		edadTextField.setBounds(78, 47, 184, 20);
		panelDatosPaciente.add(edadTextField);
		
		JLabel label_4 = new JLabel("Apellido");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(272, 25, 46, 14);
		panelDatosPaciente.add(label_4);
		
		JLabel label_3 = new JLabel("Sexo");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(282, 50, 69, 14);
		panelDatosPaciente.add(label_3);
		
		sexoTextField = new JTextField();
		sexoTextField.setColumns(10);
		sexoTextField.setBounds(328, 46, 197, 22);
		panelDatosPaciente.add(sexoTextField);

		
		panelConsultas = new JPanel();
		panelConsultas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsultas.setBounds(166, 130, 406, 303);
		contentPane.add(panelConsultas);
		panelConsultas.setLayout(null);
		
		detalleTextPane = new JTextPane();
		scrollDetalleTextPane = new JScrollPane(detalleTextPane);
		scrollDetalleTextPane.setBounds(10, 17, 386, 272);
		scrollDetalleTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelConsultas.add(scrollDetalleTextPane);
		detalleTextPane.setContentType("text/html");
		
		
		JLabel label_2 = new JLabel("Buscar");
		label_2.setBounds(23, 23, 46, 14);
		contentPane.add(label_2);
		
		textField_numeroDoc = new JTextField();
		textField_numeroDoc.setColumns(10);
		textField_numeroDoc.setBounds(79, 18, 217, 25);
		contentPane.add(textField_numeroDoc);
		
		comboBox_TipoDoc = new JComboBox();
		comboBox_TipoDoc.setModel(new DefaultComboBoxModel(new String[] {"DNI", "LU", "LE"}));
		comboBox_TipoDoc.setBounds(306, 16, 114, 25);
		contentPane.add(comboBox_TipoDoc);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setIcon(new ImageIcon(BuscardorHCE.class.getResource("/image/search.png")));
		buscarButton.setBounds(456, 15, 116, 32);
		contentPane.add(buscarButton);
		buscarButton.addActionListener(new ActionListener() {
			


			public void actionPerformed(ActionEvent e) {
				pacienteDTOActual = null;
				
				pacienteDTOActual = Sistema.getInstancia().getPaciente(textField_numeroDoc.getText(),comboBox_TipoDoc.getSelectedItem().toString());
				//TODO la auditoria deberia registrarla el sistema
			//	String auditar="Se busco HCE con dni\t"+textField_numeroDoc.getText()+"\ttipo documento\t"+comboBox_TipoDoc.getSelectedItem().toString();
			//	AdministradorPersistenciaAuditoria.getInstancia().auditar(Sistema.getInstancia().getUsuarioActual(),auditar);

				
				if(pacienteDTOActual != null){
				
				hceTemp = Sistema.getInstancia().buscarHCE(pacienteDTOActual.getDni(), pacienteDTOActual.getTipoDoc());

				model = new DefaultTableModel(){
					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				
				
				model.addColumn("Fecha");
				model.addColumn("Tipo");
				
				for (itemHistoriaClinica itemHCETemp : hceTemp.getPreacticas()) {
						
						 model.addRow(new Object[]{itemHCETemp.getFecha(), itemHCETemp.getPractica().getDescripcion()});        

				}

				table.setModel(model);
				sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				apellidoTextField.setText(pacienteDTOActual.getApellido());
				nombreTextField.setText(pacienteDTOActual.getNombre());
				edadTextField.setText(pacienteDTOActual.calcularEdad().substring(0, 2));
				String sexo=pacienteDTOActual.getSexo();
				if(sexo.equalsIgnoreCase("f"))
					sexoTextField.setText("Femenino");				
				else
					sexoTextField.setText("Masculino");	


				}
				else
				{
					System.out.println("error");

				}

			}
		});

	
		
		this.setLocationRelativeTo(null);
		//setAlwaysOnTop(true);
		setModal(true);
	}


	@Override
	public void focusGained(FocusEvent event) {
		if (event.getSource() == this.table){

			itemHistoriaClinica itemTemp = hceTemp.getPreacticas().get(table.getSelectedRow());
			Prestacion pTemp = itemTemp.getPractica();
			
			String detalle = null;
	
			if (pTemp instanceof Consulta) {
				
				
				detalle = "<html>"+
						"<br><b>Fecha: </b>" + itemTemp.getFecha().toString() + "</br>" +
						"<br><b>Tipo: </b>" + ((Consulta)pTemp).getDescripcion() + "</br>" +
						"<br><b>Observacion general: </b>" + ((Consulta)pTemp).getObservacionGeneral() + "</br>" +
						"<br><b>Tratamiento: </b> " + ((Consulta)pTemp).getTratamiento() + "</br>" +
						"<br><b>Motivo: </b>" + ((Consulta)pTemp).getMotivo() + "</br>" +
						"<br><b>Observacion Ojo Der: </b>" + ((Consulta)pTemp).getObservacionOjoDer() + "</br>" +
						"<br><b>Observacion Ojo Izq: </b>" + ((Consulta)pTemp).getObservacionOjoIzq() + "</br>" +
						"<br><b>Observacion General: </b>" + ((Consulta)pTemp).getObservacionGeneral() + "</br>" +
						"</html>";
				}
			else if (pTemp instanceof PracticaAmbulatoria) {
				
				
				detalle = "<html>"+
						"<br><b>Fecha: </b>" + itemTemp.getFecha().toString() + "</br>" +
						"<br><b>Tipo: </b>" + ((PracticaAmbulatoria)pTemp).getDescripcion() + "</br>" +
						"<br><b>Ojo: </b>" + ((PracticaAmbulatoria)pTemp).getOjo() + "</br>" +
						"<br><b>Diagnostico: </b> " + ((PracticaAmbulatoria)pTemp).getDiagnostico() + "</br>" +
						"</html>";
				}
			else if (pTemp instanceof PracticaQuirurgica) {
				

				detalle = "<html>"+
						"<br><b>Fecha: </b>" + itemTemp.getFecha().toString() + "</br>" +
						"<br><b>Tipo: </b>" + ((PracticaQuirurgica)pTemp).getDescripcion() + "</br>" +
						"<br><b>Ojo: </b>" + ((PracticaQuirurgica)pTemp).getOjo() + "</br>" +
						"<br><b>Diagnostico: </b> " + ((PracticaQuirurgica)pTemp).getDiagnostico() + "</br>" +
						"<br><b>Monitoreo: </b>" + ((PracticaQuirurgica)pTemp).getMonitoreo() + "</br>" +
						"<br><b>Hora Inicio: </b>" + ((PracticaQuirurgica)pTemp).getHoraInicio().toString().substring(11) + "</br>" +
						"<br><b>Hora Fin: </b>" + ((PracticaQuirurgica)pTemp).getHoraFin().toString().substring(11) + "</br>" +
						"<br><b>Anestecia: </b>" + ((PracticaQuirurgica)pTemp).getAnestesia() + "</br>" +
						"</html>";
				}
			
			

			detalleTextPane.setText(detalle);
			
			detalleTextPane.requestFocus();
		}
		
	}


	@Override
	public void focusLost(FocusEvent event) {

	}
	

}

