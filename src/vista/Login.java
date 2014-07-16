package vista;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controlador.Sistema;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import junit.framework.Test;
import java.awt.event.KeyAdapter;


public class Login extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	{
		
		//Set Look & Feel
		try {
			//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//javax.swing.UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
			//UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
			//UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.OfficeBlue2007Skin");
			//SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.OfficeBlue2007Skin");
			//SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin");
			//SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.BusinessSkin");
			//SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.ModerateSkin");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel5;
	private JSeparator jSeparator2;
	private JButton aceptarButton;
	private JButton cancelButton;
	private JPasswordField passwordText;
	private JTextField usuarioText;
	private JLabel lblSolpaSa;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login inst = new Login();
				inst.setVisible(true);
			}
		});
	}
	
	public Login() {
		super();
		initGUI();
	}
	
	

	
	@SuppressWarnings("serial")
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setBounds(273, 83, 157, 128);
					jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/login.png")));
				}
				{
					jLabel2 = new JLabel();
					jLabel2.setForeground(Color.WHITE);
					jLabel2.setFont(new Font("Tahoma", Font.BOLD, 11));
					getContentPane().add(jLabel2);
					jLabel2.setText("Usuario");
					jLabel2.setBounds(25, 89, 82, 14);
				}
				{
					jLabel3 = new JLabel();
					jLabel3.setForeground(Color.WHITE);
					jLabel3.setFont(new Font("Tahoma", Font.BOLD, 11));
					getContentPane().add(jLabel3);
					jLabel3.setText("Contrase\u00F1a");
					jLabel3.setBounds(25, 151, 82, 14);
				}
				{
					//usuarioText = new JTextField();
					
					final BufferedImage image = ImageIO.read(Test.class.getClassLoader().getResource("image/user_16.png"));
					usuarioText = new JTextField() {
					    /**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						protected void paintComponent(Graphics g) {
					        super.paintComponent(g);
					        int centerPoint = (getHeight() - image.getHeight())/2;
					        g.drawImage(image, 0, centerPoint, this);
					    }
					};
					
					usuarioText.setMargin(new Insets(0, image.getWidth(), 0, 0));
					getContentPane().add(usuarioText);
					usuarioText.setBounds(25, 114, 228, 26);
				}
				{
					final BufferedImage image1 = ImageIO.read(Test.class.getClassLoader().getResource("image/key_16.png"));
					passwordText = new JPasswordField(){
						protected void paintComponent(Graphics g) {
					        super.paintComponent(g);
					        int centerPoint = (getHeight() - image1.getHeight())/2;
					        g.drawImage(image1, 0, centerPoint, this);
					    }
					};
					passwordText.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent evt) {
							if(evt.getKeyCode() == KeyEvent.VK_ENTER){
								
							}
						}
					});
					passwordText.setMargin(new Insets(0, image1.getWidth(), 0, 0));
					getContentPane().add(passwordText);
					passwordText.setBounds(25, 170, 228, 26);
				}
				{
					cancelButton = new JButton();
					getContentPane().add(cancelButton);
					cancelButton.setText("Cancelar");
					cancelButton.setMnemonic(KeyEvent.VK_C);
					cancelButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/cancel.png")));
					cancelButton.setBounds(226, 246, 118, 32);
					cancelButton.setFont(new java.awt.Font("Tahoma",1,11));
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.exit(0);
						}
					});
				}
				{
					aceptarButton = new JButton();
					getContentPane().add(aceptarButton);
					aceptarButton.setText("Aceptar");
					aceptarButton.setMnemonic(KeyEvent.VK_A);
					aceptarButton.setBounds(54, 246, 123, 32);
					aceptarButton.setFont(new java.awt.Font("Tahoma",1,11));
					aceptarButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/ok.png")));
					aceptarButton.addActionListener(new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent evt) {
							
							if (usuarioText.getText().equalsIgnoreCase("")){
								JOptionPane.showMessageDialog(null, "Debe Ingresar un usuario", "Login", JOptionPane.INFORMATION_MESSAGE);
							}else
								if (passwordText.getText().equalsIgnoreCase("")){
									JOptionPane.showMessageDialog(null, "Debe Ingresar una contraseña", "Login", JOptionPane.INFORMATION_MESSAGE);
								}else{
									
									boolean con = Sistema.getInstancia().probarConexion();
									
									if (con == false){
										JOptionPane.showMessageDialog(null, "No se pudo establecer conexion con la Base de Datos, consulte con el Administrador del Sistema", "Login", JOptionPane.ERROR_MESSAGE);
									}else{
										
										boolean validar = Sistema.getInstancia().validarLogin(usuarioText.getText(), passwordText.getText());
										
										if (validar == true){
											MenuPrincipal mpc = new MenuPrincipal ();
											mpc.setVisible(true);
											dispose();
										}else{
											JOptionPane.showMessageDialog(null, "Usuario y Contraseña Invalidos", "Login", JOptionPane.ERROR_MESSAGE);
										}
										
									}
									
									
									
								}
						}
					});
				}
				{
					jSeparator2 = new JSeparator();
					getContentPane().add(jSeparator2);
					jSeparator2.setBounds(0, 222, 401, 10);
				}
			}
			
			JLabel lblNewLabel = new JLabel("Sistema de Atenci\u00F3n a Paciente");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(97, 303, 218, 14);
			getContentPane().add(lblNewLabel);
			{
				lblSolpaSa = new JLabel("Solpa SA");
				lblSolpaSa.setForeground(new Color(255, 255, 255));
				lblSolpaSa.setFont(new Font("Tahoma", Font.BOLD, 45));
				lblSolpaSa.setBounds(97, 11, 218, 67);
				getContentPane().add(lblSolpaSa);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setBounds(0, 0, 420, 385);
				jLabel5.setIcon(new ImageIcon(Login.class.getResource("/image/blue-energy.jpg")));
			}
		
			
			this.setTitle("Login");
			this.setSize(425, 366);
			this.setLocationRelativeTo(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
}
