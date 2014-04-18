package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import DTO.UsuarioDTO;

import controlador.Sistema;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambiarContraseña extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField contraseñaActualtextField;
	private JTextField contraseñaNuevatextField;
	private JTextField contraseñaConfirmartextField;
	private UsuarioDTO usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarContraseña dialog = new CambiarContraseña();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public CambiarContraseña(UsuarioDTO usuario) {
		this.usuario = usuario;
		initGUI();
	}
	
		
	public CambiarContraseña() {
		usuario = Sistema.getInstancia().getUsuario(1);//TEST
		initGUI();
	}
	
	public void initGUI(){
		setBounds(100, 100, 471, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblContraseaActual = new JLabel("Contrase\u00F1a Actual");
			lblContraseaActual.setBounds(10, 24, 120, 14);
			contentPanel.add(lblContraseaActual);
		}
		
		contraseñaActualtextField = new JTextField();
		contraseñaActualtextField.setBounds(140, 21, 175, 20);
		contentPanel.add(contraseñaActualtextField);
		contraseñaActualtextField.setColumns(10);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a");
		lblNuevaContrasea.setBounds(10, 66, 110, 14);
		contentPanel.add(lblNuevaContrasea);
		{
			contraseñaNuevatextField = new JTextField();
			contraseñaNuevatextField.setBounds(140, 64, 175, 20);
			contentPanel.add(contraseñaNuevatextField);
			contraseñaNuevatextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Confirmar Contrase\u00F1a");
			lblNewLabel.setBounds(10, 101, 147, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			contraseñaConfirmartextField = new JTextField();
			contraseñaConfirmartextField.setBounds(140, 98, 174, 20);
			contentPanel.add(contraseñaConfirmartextField);
			contraseñaConfirmartextField.setColumns(10);
		}
		{
			JButton aceptarButton = new JButton("Aceptar");
			aceptarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//boolean validar = Sistema.getInstancia().validarContraseñaActual(idUsuario, contraseñaActualtextField.getText());
					if(usuario.getPassword().equalsIgnoreCase(contraseñaActualtextField.getText())){
						
						if (contraseñaNuevatextField.getText().length() == 0){
							JOptionPane.showMessageDialog(null, "La contraseña no puede ser vacía", "Cambiar Contraseña", JOptionPane.ERROR_MESSAGE);								
							}else{
								if(contraseñaNuevatextField.getText().equals(contraseñaConfirmartextField.getText())){
									boolean cambiar = Sistema.getInstancia().cambiarContraseña(usuario.getIdUsuario(), contraseñaNuevatextField.getText());
									if(cambiar){
										JOptionPane.showMessageDialog(null, "Se cambio correctamente la contraseña", "Cambiar Contraseña", JOptionPane.INFORMATION_MESSAGE);
										dispose();
									}else
										JOptionPane.showMessageDialog(null, "No se pudo cambiar la contraseña", "Cambiar Contraseña", JOptionPane.ERROR_MESSAGE);
									
							}else{
								JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Cambiar Contraseña", JOptionPane.ERROR_MESSAGE);
								contraseñaNuevatextField.requestFocus();
								contraseñaNuevatextField.setBorder(new LineBorder(Color.RED, 2));
								contraseñaConfirmartextField.setBorder(new LineBorder(Color.RED, 2));
							}
								
							}
					}else{
						JOptionPane.showMessageDialog(null, "La Contraseña Actual no es correcta", "Cambiar Contraseña", JOptionPane.ERROR_MESSAGE);
						contraseñaActualtextField.requestFocus();
						contraseñaActualtextField.setBorder(new LineBorder(Color.RED, 2));
					}
						
				}
			});
			aceptarButton.setBounds(168, 149, 118, 32);
			contentPanel.add(aceptarButton);
			aceptarButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/ok.png")));
			getRootPane().setDefaultButton(aceptarButton);
		}
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setBounds(296, 149, 118 ,32);
			contentPanel.add(cancelButton);
			cancelButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/cancel.png")));
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(326, 0, 137, 138);
			lblNewLabel_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/login.png")));
			contentPanel.add(lblNewLabel_1);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 129, 305, 2);
		contentPanel.add(separator);
		{
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(10, 49, 307, 2);
			contentPanel.add(separator_1);
		}
		this.setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModal(true);
	}
	
}
