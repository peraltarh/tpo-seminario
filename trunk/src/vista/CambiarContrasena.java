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

import modelo.Encriptacion;

import DTO.UsuarioDTO;

import controlador.Sistema;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CambiarContrasena extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField contrasenaActualtextField;
	private JTextField contrasenaNuevatextField;
	private JTextField contrasenaConfirmartextField;
	private UsuarioDTO usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarContrasena dialog = new CambiarContrasena();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public CambiarContrasena(UsuarioDTO usuario) {
		this.usuario = usuario;
		initGUI();
	}
	
		
	public CambiarContrasena() {
		setTitle("Cambiar Contrase\u00F1a");
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
			lblContraseaActual.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblContraseaActual.setBounds(10, 24, 120, 14);
			contentPanel.add(lblContraseaActual);
		}
		
		contrasenaActualtextField = new JTextField();
		contrasenaActualtextField.setBounds(140, 21, 175, 20);
		contentPanel.add(contrasenaActualtextField);
		contrasenaActualtextField.setColumns(10);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a");
		lblNuevaContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNuevaContrasea.setBounds(10, 66, 110, 14);
		contentPanel.add(lblNuevaContrasea);
		{
			contrasenaNuevatextField = new JTextField();
			contrasenaNuevatextField.setBounds(140, 64, 175, 20);
			contentPanel.add(contrasenaNuevatextField);
			contrasenaNuevatextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Confirmar Contrase\u00F1a");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(10, 101, 156, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			contrasenaConfirmartextField = new JTextField();
			contrasenaConfirmartextField.setBounds(140, 98, 174, 20);
			contentPanel.add(contrasenaConfirmartextField);
			contrasenaConfirmartextField.setColumns(10);
		}
		{
			JButton aceptarButton = new JButton("Aceptar");
			aceptarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//boolean validar = Sistema.getInstancia().validarContrasenaActual(idUsuario, contrasenaActualtextField.getText());
					String pswEnc = Encriptacion.Encriptar(contrasenaActualtextField.getText());
					if(usuario.getPassword().equalsIgnoreCase(pswEnc)){
						
						if (contrasenaNuevatextField.getText().length() == 0){
							JOptionPane.showMessageDialog(null, "La contrasena no puede ser vacna", "Cambiar Contrasena", JOptionPane.ERROR_MESSAGE);								
							}else{
								if(contrasenaNuevatextField.getText().equals(contrasenaConfirmartextField.getText())){
									boolean cambiar = Sistema.getInstancia().cambiarContrasena(usuario.getDni(), contrasenaNuevatextField.getText());
									if(cambiar){
										JOptionPane.showMessageDialog(null, "Se cambio correctamente la contrasena", "Cambiar Contrasena", JOptionPane.INFORMATION_MESSAGE);
										dispose();
									}else
										JOptionPane.showMessageDialog(null, "No se pudo cambiar la contrasena", "Cambiar Contrasena", JOptionPane.ERROR_MESSAGE);
									
							}else{
								JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden", "Cambiar Contrasena", JOptionPane.ERROR_MESSAGE);
								contrasenaNuevatextField.requestFocus();
								contrasenaNuevatextField.setBorder(new LineBorder(Color.RED, 2));
								contrasenaConfirmartextField.setBorder(new LineBorder(Color.RED, 2));
							}
								
							}
					}else{
						JOptionPane.showMessageDialog(null, "La Contrasena Actual no es correcta", "Cambiar Contrasena", JOptionPane.ERROR_MESSAGE);
						contrasenaActualtextField.requestFocus();
						contrasenaActualtextField.setBorder(new LineBorder(Color.RED, 2));
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
		//setAlwaysOnTop(true);
		setModal(true);
	}
	
}
