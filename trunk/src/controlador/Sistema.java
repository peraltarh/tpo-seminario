package controlador;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



import DTO.PacienteDTO;
import DTO.UsuarioDTO;


import persistencia.AdministradorPersistenciaPaciente;
import persistencia.AdministradorPersistenciaUsuario;
import persistencia.PoolConnection;



import modelo.*;


public class Sistema{
	
	
	
	private static Sistema instancia;
	private ArrayList<Usuario> usuarios;
	private ArrayList<String> permisos;
	private ArrayList<HistoriaClinica> historiasClinicas;
	private ArrayList<ObraSocial> obrasSociales;
	private ArrayList<Prestacion> prestaciones;
	private ArrayList<Nomenclador> nomencladores;
	private ArrayList<Paciente> pacientes;
	
	
	private UsuarioDTO usuarioActual;
	
	//OK
	private Sistema() {
		usuarios = new ArrayList<Usuario>();
		permisos = new ArrayList<String>();
		historiasClinicas = new ArrayList<HistoriaClinica>();
		obrasSociales = new ArrayList<ObraSocial>();
		prestaciones = new ArrayList<Prestacion>();
		nomencladores = new ArrayList<Nomenclador>();
		pacientes = new ArrayList<Paciente>();
	}
	
	//OK
	public static Sistema getInstancia(){
		if(instancia == null)
			instancia = new Sistema();
		return instancia;
	}
	
	//OK
	public java.sql.Date fechaActual(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		java.sql.Date f = new java.sql.Date(calendar.getTime().getTime());
		return f;
				
	}
	
	//OK
	public Date fechaActualMasUnDia(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DATE,1);//Le suma una dia
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	//OK
	public boolean validarContrasenaActual(int dni , String psw){
		
		Usuario u = buscarUsuario(dni) ;
				
		if (u!=null){
			if(psw.equals(u.getPassword())){
				return true;
			}else
				return false;
		}else
			return false;
	}

	//OK
	public boolean validarLogin(String userName, String password){
		
		
		Usuario u = this.buscarUsuario(userName, password) ;
		
		
		if (u!=null){
			
			if(password.equals(u.getPassword())){
				usuarioActual = u.getView();
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	//OK
	public boolean cambiarContrasena(int dni, String psw){
		
		Usuario u = buscarUsuario(dni) ;
			
			if (u!=null){
				u.setPassword(psw);
				u.updateContrasena();
				return true;
			}else
				return false;
	}
	
	//OK
	public boolean borrarUsuario(int dni){
		Usuario u = buscarUsuario(dni) ;
		
			if (u!=null){
				u.setBorrado(true);
				u.updateBorrado(dni);
				return true;
			}else
				return false;
	}
	
	//OK
	public boolean probarConexion(){
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		if (con == null){
			return false;
		}else
		return true;
	}
	
	//OK
	public Usuario buscarUsuario(String userName, String password){
		
		Usuario u = null ;
		
		if (!usuarios.isEmpty()){
		for (Usuario usuarioTemp : this.usuarios) {
			if (usuarioTemp.getUserName().equals(userName)){
				u = usuarioTemp;
				break;
			}
		}
		}
		if (u == null){
			
		u = new Usuario().buscarUsuario(userName);
		this.usuarios.add(u);
		
		}
		
		if (u!=null){
			return u;
		}else
		return null;
	}
	
	//OK
	public Usuario buscarUsuario(int dni){
		
		Usuario u = null ;
		
		if (!usuarios.isEmpty()){
		for (Usuario usuarioTemp : this.usuarios) {
			if (usuarioTemp.getDni() == dni)
			{
				u = usuarioTemp;
				break;
			}
		}
		}
		
		if (u == null){
		u = new Usuario().buscarUsuario(dni);
		this.usuarios.add(u);
		}
		
		return u;
	}
	
	
	//OK
	@SuppressWarnings("unchecked")
	public ArrayList<String> getAllPermisos(){
			
			ArrayList<String> vecPermisoDTO = new ArrayList<String>();
			
			if(permisos.size() == 0){
				
			}

			if (!permisos.isEmpty()) {return (ArrayList<String>)permisos.clone();}
			else{permisos = AdministradorPersistenciaUsuario.getInstancia().getEspecialidades();	}

			return vecPermisoDTO;
		}
		
	
	public ArrayList<UsuarioDTO> getUsuarios (){
		ArrayList<UsuarioDTO> vecUsuarioDTO = new ArrayList<UsuarioDTO>();
		
		if(usuarios.size() == 0){
			usuarios = new Usuario().buscarAll();	
		}
		
		for(int i=0;i<usuarios.size();i++){
			vecUsuarioDTO.add(usuarios.get(i).getView());	
		}
			
		return vecUsuarioDTO;
		
	}
	
	
	public UsuarioDTO getUsuario(int dni){
		Usuario u = buscarUsuario(dni);
		if (u==null){
			return null;
		}else{
			usuarioActual  = u.getView();
			return usuarioActual;	
		}
		
	}
	
	
	public UsuarioDTO getUsuarioActual() {
		return usuarioActual;
	}

	
	public boolean altaUsuario(String nombre, String apellido, int dni, String matricula, String userName, String password, String Permisos ){
		
		Usuario u = buscarUsuario(dni);
		String vp = Permisos;
		
		if(u == null){
			u = new Usuario(nombre, apellido, matricula, dni, userName, password);

			u.setEspecialidad(vp);
			u.savePermisos();
			usuarios.add(u);
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean modificarUsuario(String nombre, String apellido, int dni, String matricula, String userName, 
		String password, String Permisos, boolean borrado){
		
		Usuario u = buscarUsuario(dni);
		String vp = Permisos;
		
		if(u != null){
			u.modificarUsuario(nombre, apellido, matricula, userName, password, borrado);
					
			u.setEspecialidad(vp);
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean validarPermiso (String permiso){
		String vp = usuarioActual.getEspecialidad();

			if (vp.contains(permiso))
				return true;
		
		return false;
		
	}
	
	
	public boolean backup(){
		
		String path = "C:/";
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		

		if (con!=null){
			String dataBaseName =  PoolConnection.getPoolConnection().getDataBaseName();
			String comand ="BACKUP DATABASE " +  dataBaseName + " TO DISK = "; 
			
			
			 File miDir = new File (".");
			 try {
				  path = miDir.getCanonicalPath()+"/";
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
			Date date = new Date(); 
			dateFormat.format(date); 
			
			String nameBackup = dataBaseName+ "_"+dateFormat.format(date)+".BAK"; 
					
			String dbackup =comand + "'"+ path +nameBackup+"'";
		
			Statement callBackupDbase = null;
			
			try {
				con.setAutoCommit(true);
				callBackupDbase = con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(callBackupDbase != null){
				try {
					callBackupDbase.execute(dbackup);
					return true;
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}else return false;
			
			
		}else
			return false;
		
		
	}

	
	
	public ArrayList<PacienteDTO> getPacientes() {

		ArrayList<PacienteDTO> pacientesDTO = new ArrayList<PacienteDTO>();
		
		if(this.pacientes.isEmpty()){
			this.pacientes = AdministradorPersistenciaPaciente.getInstancia().buscarAll();
		}
		
		for (Paciente pacienteTemp : this.pacientes) {
			pacientesDTO.add(new PacienteDTO(pacienteTemp));
		}
		
		return pacientesDTO;
	}


}
	
