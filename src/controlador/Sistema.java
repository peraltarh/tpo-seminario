package controlador;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import DTO.PermisoDTO;
import DTO.UsuarioDTO;


import persistencia.PoolConnection;



import modelo.Paciente;
import modelo.Permiso;
import modelo.Usuario;

public class Sistema{
	
	
	
	private static Sistema instancia;
	private Vector<Usuario> usuarios;
	private Vector<Permiso> permisos;
	private UsuarioDTO usuarioActual;
	
	public static Sistema getInstancia(){
		if(instancia == null)
			instancia = new Sistema();
		return instancia;
	}
	
	private Sistema() {
		usuarios = new Vector<Usuario>();
		permisos = new Vector<Permiso>();
		
	}
	
	public java.sql.Date fechaActual(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		java.sql.Date f = new java.sql.Date(calendar.getTime().getTime());
		
		return f;
				
	}
	
public Date fechaActualMasUnDia(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DATE,1);//Le suma una dia
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
		
				
	}
	
	public boolean validarContraseñaActual(int dni , String psw){
		Usuario u = new Usuario().buscarUsuario(dni);
		
		if (u!=null){
			if(psw.equals(u.getPassword())){
				return true;
			}else
				return false;
		}else
			return false;
	}

	public boolean validarLogin(String userName, String password){
		
		Usuario u = new Usuario().buscarUsuario(userName);
		
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
	
	
	public boolean cambiarContraseña(int dni, String psw){
			Usuario u = buscarUsuario(dni);
			if (u!=null){
				u.setPassword(psw);
				u.updateContraseña();
				return true;
			}else
				return false;
	}
	

	public boolean borrarUsuario(int dni){
			Usuario u = buscarUsuario(dni);
			if (u!=null){
				u.setBorrado(true);
				u.updateBorrado(dni);
				return true;
			}else
				return false;
	}
	
	
	
	public boolean probarConexion(){
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		if (con == null){
			return false;
		}else
		return true;
	}
	
	public Usuario buscarUsuario(String userName, String password){
		
		Usuario u = new Usuario().buscarUsuario(userName);
		
		if (u!=null){
			return u;
		}else
		return null;
	}
	
	private Usuario buscarUsuario(int dni){
		
		Usuario a = null;
		
		for(int i = 0; i<usuarios.size(); i++){
			if(usuarios.elementAt(i).getDni() == dni)
				return usuarios.elementAt(i);
		}
		
		a = new Usuario().buscarUsuario(dni);
//		if(a==null){
//			return a;
//		}else{
//			//usuarios.add(a);
//			return a;
//		}
		return a;
	}
	
	
private Permiso buscarPermiso(int id){
		
		Permiso p = null;
		
		for(int i = 0; i<permisos.size(); i++){
			if(permisos.elementAt(i).getIdPermiso() == id)
				return permisos.elementAt(i);
		}
		
		p = new Permiso().buscarPermiso(id);

		return p;
	}
	
	
	public Vector<PermisoDTO> getAllPermisos(){
			
			Vector<PermisoDTO> vecPermisoDTO = new Vector<PermisoDTO>();
			
			if(permisos.size() == 0){
				permisos = new Permiso().buscarAll();	
			}
			
			for(int i=0;i<permisos.size();i++){
				if(permisos.elementAt(i).isBorrado() == false){
					vecPermisoDTO.add(permisos.elementAt(i).getView());	
				}
				
			}
			
			return vecPermisoDTO;
		}
		
	
	public Vector<UsuarioDTO> getUsuarios (){
		Vector<UsuarioDTO> vecUsuarioDTO = new Vector<UsuarioDTO>();
		
		if(usuarios.size() == 0){
			usuarios = new Usuario().buscarAll();	
		}
		
		for(int i=0;i<usuarios.size();i++){
			vecUsuarioDTO.add(usuarios.elementAt(i).getView());	
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

	public int getIdPermiso(String desc) {
		
		if(permisos.size() == 0){
			return new Permiso().buscarIdPermiso(desc);	
		}
		
		for(int i=0;i<permisos.size();i++){
			if(permisos.elementAt(i).getDescripcion().equals(desc)){
					return permisos.elementAt(i).getIdPermiso();
			}
			
		}
		return 0;
	}
	
	public boolean altaUsuario(String nombre, String apellido, int dni, int matricula, String userName, String password, Vector<Integer> vecPermisos ){
		
		Usuario u = buscarUsuario(dni);
		Permiso p;
		Vector<Permiso> vp = new Vector<Permiso>();
		
		if(u == null){
			u = new Usuario(nombre, apellido, matricula, dni, userName, password);
			for (int i = 0; i < vecPermisos.size(); i++) {
				p = buscarPermiso(vecPermisos.elementAt(i));
				if (p!=null){
					vp.add(p);
				}
			}
			u.setPermisos(vp);
			u.savePermisos();
			usuarios.add(u);
			return true;
		}else{
			return false;
		}
	}
	
	
public boolean modificarUsuario(String nombre, String apellido, int dni, int matricula, String userName, 
		String password, Vector<Integer> vecPermisos, boolean borrado){
		
		Usuario u = buscarUsuario(dni);
		Permiso p;
		Vector<Permiso> vp = new Vector<Permiso>();
		
		if(u != null){
			u.modificarUsuario(nombre, apellido, matricula, userName, password, borrado);
			
			for (int i = 0; i < vecPermisos.size(); i++) {
				p = buscarPermiso(vecPermisos.elementAt(i));
				if (p!=null){
					vp.add(p);
				}
			}
			
			u.setPermisos(vp);
			u.updatePermisos();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean validarPermiso (String permiso){
		Vector<PermisoDTO> vp = usuarioActual.getPermisos();
		for (int i = 0; i < vp.size(); i++) {
			if (vp.elementAt(i).getCode().equalsIgnoreCase(permiso))
				return true;
		}
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
}
	
