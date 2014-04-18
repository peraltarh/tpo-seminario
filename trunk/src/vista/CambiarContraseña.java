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

public class CambiarContrase�a extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField contrase�aActualtextField;
	private JTextField contrase�aNuevatextField;
	private JTextField contrase�aConfirmartextField;
	private UsuarioDTO usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarContrase�a dialog = new CambiarContrase�a();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public CambiarContrase�a(UsuarioDTO usuario) {
		this.usuario = usuario;
		initGUI();
	}
	
		
	public CambiarContrase�a() {
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
		
		contrase�aActualtextField = new JTextField();
		contrase�aActualtextField.setBounds(140, 21, 175, 20);
		contentPanel.add(contrase�aActualtextField);
		contrase�aActualtextField.setColumns(10);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a");
		lblNuevaContrasea.setBounds(10, 66, 110, 14);
		contentPanel.add(lblNuevaContrasea);
		{
			contrase�aNuevatextField = new JTextField();
			contrase�aNuevatextField.setBounds(140, 64, 175, 20);
			contentPanel.add(contrase�aNuevatextField);
			contrase�aNuevatextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Confirmar Contrase\u00F1a");
			lblNewLabel.setBounds(10, 101, 147, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			contrase�aConfirmartextField = new JTextField();
			contrase�aConfirmartextField.setBounds(140, 98, 174, 20);
			contentPanel.add(contrase�aConfirmartextField);
			contrase�aConfirmartextField.setColumns(10);
		}
		{
			JButton aceptarButton = new JButton("Aceptar");
			aceptarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//boolean validar = Sistema.getInstancia().validarContrase�aActual(idUsuario, contrase�aActualtextField.getText());
					if(usuario.getPassword().equalsIgnoreCase(contrase�aActualtextField.getText())){
						
						if (contrase�aNuevatextField.getText().length() == 0){
							JOptionPane.showMessageDialog(null, "La contrase�a no puede ser vac�a", "Cambiar Contrase�a", JOptionPane.ERROR_MESSAGE);								
							}else{
								if(contrase�aNuevatextField.getText().equals(contrase�aConfirmartextField.getText())){
									boolean cambiar = Sistema.getInstancia().cambiarContrase�a(usuario.getIdUsuario(), contrase�aNuevatextField.getText());
									if(cambiar){
										JOptionPane.showMessageDialog(null, "Se cambio correctamente la contrase�a", "Cambiar Contrase�a", JOptionPane.INFORMATION_MESSAGE);
										dispose();
									}else
										JOptionPane.showMessageDialog(null, "No se pudo cambiar la contrase�a", "Cambiar Contrase�a", JOptionPane.ERROR_MESSAGE);
									
							}else{
								JOptionPane.showMessageDialog(null, "Las contrase�as no coinciden", "Cambiar Contrase�a", JOptionPane.ERROR_MESSAGE);
								contrase�aNuevatextField.requestFocus();
								contrase�aNuevatextField.setBorder(new LineBorder(Color.RED, 2));
								contrase�aConfirmartextField.setBorder(new LineBorder(Color.RED, 2));
							}
								
							}
					}else{
						JOptionPane.showMessageDialog(null, "La Contrase�a Actual no es correcta", "Cambiar Contrase�a", JOptionPane.ERROR_MESSAGE);
						contrase�aActualtextField.requestFocus();
						contrase�aActualtextField.setBorder(new LineBorder(Color.RED, 2));
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
