package vista;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import DTO.UsuarioDTO;
import controlador.Sistema;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;

public class MenuPrincipal extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem cambiarContrasena;
	private JMenu jmenuMicuenta;
	private JMenuItem reportePlanillaFacturacion;
	private JMenuItem buscarHCE;
	private JMenu jMenuFacturacion;
	private JMenu jMenuHCE;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem usuarioMenuItem;
	private JMenuItem pacienteMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	protected TimerThread timerThread;
	private UsuarioDTO usuarioActual;
	private JLabel solpaLabel;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MenuPrincipal inst = new MenuPrincipal();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				
			}
		});
	}
	
	public MenuPrincipal() {
		super();
		getContentPane().setBackground(Color.LIGHT_GRAY);
		usuarioActual =  Sistema.getInstancia().getUsuarioActual();
		
		// SOLO PARA TEST
//		if(usuarioActual== null){
//			usuarioActual =  Sistema.getInstancia().getUsuario(30489590);
//		}
		
		initGUI();
	}
	
//	public MenuPrincipal(int id) {
//		super();
//		idUsuario = id;
//		usuarioActual =  Sistema.getInstancia().getUsuario(idUsuario);
//		initGUI();
//		
//	}
	
	private void initGUI() {
		try {
			{
				menuAdministracion();
				menuHCE();
				menuFacturacion();
				menuMicuenta();
			}
			
			JToolBar toolbar = new JToolBar("Botonera");
			addButtons(toolbar);
			getContentPane().add(toolbar, BorderLayout.NORTH);
			
						
			JStatusBar statusBar = new JStatusBar();
		    JLabel leftLabel = new JLabel("Usuario Logueado: "+ usuarioActual.getNombre() + " " + usuarioActual.getApellido());
		    statusBar.setLeftComponent(leftLabel);

		    final JLabel dateLabel = new JLabel();
		    dateLabel.setHorizontalAlignment(JLabel.CENTER);
		    statusBar.addRightComponent(dateLabel);
		        

		    final JLabel timeLabel = new JLabel();
		    timeLabel.setHorizontalAlignment(JLabel.CENTER);
		    statusBar.addRightComponent(timeLabel);
		        
		    getContentPane().add(statusBar, BorderLayout.SOUTH);
		    {
		    	solpaLabel = new JLabel("SOLPA S.A");
		    	solpaLabel.setFont(new Font("Tahoma", Font.BOLD, 60));
		    	solpaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		    	getContentPane().add(solpaLabel, BorderLayout.CENTER);
		    }
		        
		     timerThread = new TimerThread(dateLabel, timeLabel);
		     timerThread.start();
			
			setSize(640, 480);
			setTitle("SOLPA S.A");
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setLocationRelativeTo(null);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
					
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( e.getMessage());
		}
	}
	
	public void menuMicuenta(){
		jmenuMicuenta = new JMenu();
		jMenuBar1.add(jmenuMicuenta);
		jmenuMicuenta.setText("Mi Cuenta");
		jmenuMicuenta.setMnemonic(KeyEvent.VK_M);
		{
			cambiarContrasena = new JMenuItem();
			jmenuMicuenta.add(cambiarContrasena);
			cambiarContrasena.setText("Cambiar Contrasena");
			cambiarContrasena.setMnemonic(KeyEvent.VK_C);
			cambiarContrasena.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/key.png")));
			cambiarContrasena.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CambiarContrasena cc = new CambiarContrasena(usuarioActual);
					cc.setVisible(true);
				}
			});
			
		}
		{
			jSeparator2 = new JSeparator();
			jmenuMicuenta.add(jSeparator2);
		}
		{
			exitMenuItem = new JMenuItem();
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			jmenuMicuenta.add(exitMenuItem);
			exitMenuItem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/exit.png")));
			exitMenuItem.setMnemonic(KeyEvent.VK_S);
			exitMenuItem.setText("Salir");
		}
	}
	
	public void menuFacturacion(){
		
			jMenuFacturacion = new JMenu();
			jMenuBar1.add(jMenuFacturacion);
			jMenuFacturacion.setText("Facturacion");
			jMenuFacturacion.setMnemonic(KeyEvent.VK_F);
			{
				reportePlanillaFacturacion = new JMenuItem();
				reportePlanillaFacturacion.setText("Exportar Planilla Facturacinn");
				reportePlanillaFacturacion.setMnemonic(KeyEvent.VK_E);
				reportePlanillaFacturacion.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/report.png")));	
				reportePlanillaFacturacion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PlanillaMensualDialog pmd = new PlanillaMensualDialog();
						pmd.setVisible(true);
					}
				});
				
				jMenuFacturacion.add(reportePlanillaFacturacion);
				
				boolean validarPermiso = Sistema.getInstancia().validarPermiso("Administrativo");
				if (validarPermiso==true){
					reportePlanillaFacturacion.setEnabled(true);
				}else
					reportePlanillaFacturacion.setEnabled(false);
				
				
			}
		}


	public void menuHCE(){
		
		jMenuHCE = new JMenu();
		jMenuBar1.add(jMenuHCE);
		jMenuHCE.setText("Historia Clinica");
		jMenuHCE.setMnemonic(KeyEvent.VK_H);
		{
			buscarHCE = new JMenuItem();
			buscarHCE.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/HCE.png")));
			buscarHCE.setText("Buscar Historia Clinica");
			buscarHCE.setMnemonic(KeyEvent.VK_B);
			buscarHCE.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//BuscardorHCE b = new  BuscardorHCE();
					//b.setVisible(true);
					VerHCE vh = new VerHCE();
					vh.setVisible(true);
				}
			});
			
			jMenuHCE.add(buscarHCE);
			
			boolean validarPermiso = Sistema.getInstancia().validarPermiso("Oftalmologo");
			if (validarPermiso==true){
				buscarHCE.setEnabled(true);
			}else
				buscarHCE.setEnabled(false);
			
			
		}
	}

	
	public void menuAdministracion(){
		jMenuBar1 = new JMenuBar();
		setJMenuBar(jMenuBar1);
		{
			jMenu3 = new JMenu();
			jMenuBar1.add(jMenu3);
			jMenu3.setText("Administracion");
			jMenu3.setMnemonic(KeyEvent.VK_A);
			{
				usuarioMenuItem = new JMenuItem();
				jMenu3.add(usuarioMenuItem);
				usuarioMenuItem.setText("Usuarios");
				usuarioMenuItem.setMnemonic(KeyEvent.VK_U);
				usuarioMenuItem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/users.png")));
				usuarioMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						BuscardorUsuario bu = new BuscardorUsuario();
						bu.setVisible(true);
					}
				});
				
				
				boolean validarPermiso = Sistema.getInstancia().validarPermiso("Administrativo");
				if (validarPermiso==true){
					usuarioMenuItem.setEnabled(true);
				}else
					usuarioMenuItem.setEnabled(false);
				
			}
			
			{
				pacienteMenuItem = new JMenuItem();
				jMenu3.add(pacienteMenuItem);
				pacienteMenuItem.setText("Pacientes");
				pacienteMenuItem.setMnemonic(KeyEvent.VK_P);
				pacienteMenuItem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/paciente.png")));
				pacienteMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						BuscardorPaciente bp = new BuscardorPaciente();
						bp.setVisible(true);
					}
				});
				boolean validarPermiso = Sistema.getInstancia().validarPermiso("Administrativo");
				if (validarPermiso==true){
					pacienteMenuItem.setEnabled(true);
				}else
					pacienteMenuItem.setEnabled(false);
				
			}
			
		}
		
		
	}
	
	
	private void addButtons(JToolBar toolBar) {
		// TODO Auto-generated method stub
	}
	
	public class TimerThread extends Thread {

        protected boolean isRunning;

        protected JLabel dateLabel;
        protected JLabel timeLabel;

        protected SimpleDateFormat dateFormat = 
                new SimpleDateFormat("EEE, d MMM yyyy");
        protected SimpleDateFormat timeFormat =
                new SimpleDateFormat("h:mm a");

        public TimerThread(JLabel dateLabel, JLabel timeLabel) {
            this.dateLabel = dateLabel;
            this.timeLabel = timeLabel;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Calendar currentCalendar = Calendar.getInstance();
                        Date currentTime = currentCalendar.getTime();
                        dateLabel.setText(dateFormat.format(currentTime));
                        timeLabel.setText(timeFormat.format(currentTime));
                    }
                });

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                }
            }
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

    }
	
}
