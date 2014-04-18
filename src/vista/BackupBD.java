package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import controlador.Sistema;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BackupBD extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackupBD frame = new BackupBD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BackupBD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton backupButton = new JButton("BackUp");
		backupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			boolean bk = Sistema.getInstancia().backup();
			if (bk == true){
				JOptionPane.showMessageDialog(null, "Se realizó el Resguardo Satisfactoriamente", "Resguardo de Base de datos", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "No se pudo realizar el Resguardo de la Base de Datos", "Resguardo de Base de datos", JOptionPane.INFORMATION_MESSAGE);
			}
			
			}
		});
		backupButton.setBounds(82, 89, 91, 23);
		contentPane.add(backupButton);
	}
}
