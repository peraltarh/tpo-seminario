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

public class AltaConsulta extends JDialog {
	
	private JTextPane consultaTextPane;
	private JScrollPane scrollConsultaTextPane;
	private JTextPane ojoIzqTextPane;
	private JScrollPane scrollOjoIzq;
	private JTextPane ojoDerTextPane;
	private JScrollPane scrollOjoDer;
	private JLabel lblOjoDerecho;
	private JPanel tratamientoPanel;
	private JTextPane tratamientoTextPane;
	private JScrollPane scrollTratamientoTextPane;
	private JPanel observacionPanel;
	private JTextPane observacionTextPane;
	private JScrollPane scrollObservacionTextPane;
	private JButton cancelarButon;
	private JSeparator separator;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaConsulta dialog = new AltaConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaConsulta() {
		initGUI();
	}
	
	private void initGUI(){
		setBounds(100, 100, 677, 664);
		getContentPane().setLayout(null);
		
		JPanel motivoPanel = new JPanel();
		motivoPanel.setBorder(new TitledBorder(null, "Motivo de Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		motivoPanel.setBounds(10, 11, 650, 136);
		getContentPane().add(motivoPanel);
		motivoPanel.setLayout(null);
		
		consultaTextPane = new JTextPane();
		scrollConsultaTextPane = new JScrollPane(consultaTextPane);
		scrollConsultaTextPane.setBounds(10, 25, 630, 100);
		scrollConsultaTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		motivoPanel.add(scrollConsultaTextPane);
		
		JPanel panelDiagnostico = new JPanel();
		panelDiagnostico.setBorder(new TitledBorder(null, "Diagnostico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDiagnostico.setBounds(10, 158, 650, 163);
		getContentPane().add(panelDiagnostico);
		panelDiagnostico.setLayout(null);
		
		lblOjoDerecho = new JLabel("OJO DERECHO");
		lblOjoDerecho.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOjoDerecho.setBounds(340, 23, 117, 14);
		panelDiagnostico.add(lblOjoDerecho);
		
		JLabel lblOjoIzquierdo = new JLabel("OJO IZQUIERDO");
		lblOjoIzquierdo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOjoIzquierdo.setBounds(15, 23, 134, 14);
		panelDiagnostico.add(lblOjoIzquierdo);

		ojoIzqTextPane = new JTextPane();
		scrollOjoIzq = new JScrollPane(ojoIzqTextPane);
		scrollOjoIzq.setBounds(15, 48, 300, 100);
		scrollOjoIzq.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDiagnostico.add(scrollOjoIzq);
		
		
		ojoDerTextPane = new JTextPane();
		scrollOjoDer = new JScrollPane(ojoDerTextPane);
		scrollOjoDer.setBounds(340, 48, 300, 100);
		scrollOjoDer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDiagnostico.add(scrollOjoDer);
		
//		separator = new JSeparator();
//		separator.setOrientation(SwingConstants.VERTICAL);
//		separator.setBounds(325, 23, 1, 130);
//		panelDiagnostico.add(separator);
		
		tratamientoPanel = new JPanel();
		tratamientoPanel.setBorder(new TitledBorder(null, "Tratamiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tratamientoPanel.setBounds(10, 332, 650, 136);
		getContentPane().add(tratamientoPanel);
		tratamientoPanel.setLayout(null);
		
		
		tratamientoTextPane = new JTextPane();
		scrollTratamientoTextPane = new JScrollPane(tratamientoTextPane);
		scrollTratamientoTextPane.setBounds(10, 25, 630, 100);
		scrollTratamientoTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tratamientoPanel.add(scrollTratamientoTextPane);
		
		observacionPanel = new JPanel();
		observacionPanel.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		observacionPanel.setBounds(10, 479, 650, 104);
		getContentPane().add(observacionPanel);
		observacionPanel.setLayout(null);
		
	    
		observacionTextPane = new JTextPane();
		scrollObservacionTextPane = new JScrollPane(observacionTextPane);
		scrollObservacionTextPane.setBounds(10, 25, 630, 64);
		scrollObservacionTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		observacionPanel.add(scrollObservacionTextPane);
		
		JButton guardarButton = new JButton("Guardar");
		guardarButton.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/guardar.png")));
		guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		guardarButton.setBounds(403, 594, 116, 32);
		getContentPane().add(guardarButton);
		
		cancelarButon = new JButton("Cancelar");
		cancelarButon.setIcon(new ImageIcon(BuscardorUsuario.class.getResource("/image/cancel.png")));
		cancelarButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelarButon.setBounds(543, 594, 116, 32);
		getContentPane().add(cancelarButon);
		
		this.setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModal(true);
	}
}
