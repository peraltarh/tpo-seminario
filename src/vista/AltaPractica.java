package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JSpinner.DateEditor;

import javax.swing.JComboBox;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;

public class AltaPractica extends JDialog {
	private JPanel DiagnosticoPanel;
	private JTextPane diagnositicoTextPane;
	private JScrollPane scrollTratamientoTextPane;
	private JButton cancelarButon;
	private JSeparator separator;
	private JXDatePicker fechaPractica;
	private JComboBox ojoComboBox;
	private JComboBox practicaComboBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaPractica dialog = new AltaPractica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaPractica() {
		initGUI();
	}
	
	private void initGUI(){
		setBounds(100, 100, 470, 664);
		getContentPane().setLayout(null);
		
		JPanel datosPacientePanel = new JPanel();
		datosPacientePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datos Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		datosPacientePanel.setBounds(10, 11, 441, 136);
		getContentPane().add(datosPacientePanel);
		datosPacientePanel.setLayout(null);
		
		JPanel practicaPanel = new JPanel();
		practicaPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Pr\u00E1ctica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		practicaPanel.setBounds(10, 158, 441, 100);
		getContentPane().add(practicaPanel);
		practicaPanel.setLayout(null);
		
		practicaComboBox = new JComboBox();
		practicaComboBox.setBounds(66, 25, 339, 22);
		//TODO
		practicaComboBox.setModel(new DefaultComboBoxModel(new String[] {"CURVA TENSIONAL OCULAR CON REPOSO MATINAL", "TEST OJO SECO SCHIMER, ROSA DE BENGALA", "CAMPIMETRIA COMPUT.BILATERAL (CAMPO VISUAL)", "FONDO DE OJO"}));
		AutoCompleteDecorator.decorate(this.practicaComboBox);
		practicaPanel.add(practicaComboBox);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 29, 46, 14);
		practicaPanel.add(lblTipo);
		
		
		fechaPractica = new JXDatePicker();
		fechaPractica.setDate(Calendar.getInstance().getTime());
		fechaPractica.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		fechaPractica.setBounds(66, 58, 126, 22);
		practicaPanel.add(fechaPractica);
				
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 62, 46, 14);
		practicaPanel.add(lblFecha);
		
		JLabel lblOjo = new JLabel("OJO");
		lblOjo.setBounds(220, 62, 46, 14);
		practicaPanel.add(lblOjo);
		
		ojoComboBox = new JComboBox();
		ojoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Izquierdo", "Derecho", "Ambos"}));
		ojoComboBox.setBounds(276, 58, 129, 22);
		practicaPanel.add(ojoComboBox);
		
		
		
		
		DiagnosticoPanel = new JPanel();
		DiagnosticoPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Diagnostico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DiagnosticoPanel.setBounds(10, 269, 441, 225);
		getContentPane().add(DiagnosticoPanel);
		DiagnosticoPanel.setLayout(null);
		
		
		diagnositicoTextPane = new JTextPane();
		scrollTratamientoTextPane = new JScrollPane(diagnositicoTextPane);
		scrollTratamientoTextPane.setBounds(10, 25, 424, 171);
		scrollTratamientoTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		DiagnosticoPanel.add(scrollTratamientoTextPane);
		
		JButton guardarButton = new JButton("Guardar");
		guardarButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/guardar.png")));
		guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		guardarButton.setBounds(210, 505, 116, 32);
		getContentPane().add(guardarButton);
		
		cancelarButon = new JButton("Cancelar");
		cancelarButon.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/cancel.png")));
		cancelarButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelarButon.setBounds(335, 505, 116, 32);
		getContentPane().add(cancelarButon);
		
		this.setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModal(true);
	}
}
