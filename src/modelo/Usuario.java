package modelo;

import java.util.ArrayList;
import java.util.ArrayList;
import DTO.UsuarioDTO;
import persistencia.AdministradorPersistenciaUsuario;

public class Usuario {
	
	private int dni;
	private String nombre;
	private String apellido;
	private int matricula;
	private String userName;
	private String password;
	private boolean borrado;
	private ArrayList<Permiso> permisos;
	
	public Usuario(){
		permisos =  new ArrayList<Permiso>();
	}
	
	public Usuario(String nombre, String apellido,int matricula,
				int dni,String userName, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.userName = userName;
		this.password = password;
		this.matricula = matricula;
		this.borrado = false;
		permisos =  new ArrayList<Permiso>();
		
		AdministradorPersistenciaUsuario.getInstancia().insert(this);
		
	}
	
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean activo) {
		this.borrado = activo;
	}
	
	public UsuarioDTO getView(){
		return new UsuarioDTO(nombre, apellido, matricula, dni, userName, password, borrado,permisos);
		
	}

	public void modificarUsuario (String nombre, String apellido,int matricula,String userName, String password, boolean borrado) {
			
			this.nombre = nombre;
			this.apellido = apellido;
			this.password = password;
			this.matricula = matricula;
			this.userName = userName;
			this.password = password;
			this.borrado = borrado;
			
			
			AdministradorPersistenciaUsuario.getInstancia().update(this);
		
	}

	public void borradoLogico() {
		borrado = false;
		AdministradorPersistenciaUsuario.getInstancia().borradoLogico(this);
		
	}
	
	public ArrayList<Usuario> buscarAll (){
		return AdministradorPersistenciaUsuario.getInstancia().buscarAll();
	}
	
	public Usuario buscarUsuario (String userName){
		return AdministradorPersistenciaUsuario.getInstancia().buscarUsuario(userName);
	}

	public Usuario buscarUsuario(int dni) {
		return AdministradorPersistenciaUsuario.getInstancia().buscarUsuario(dni);
	}
	
//	public Usuario buscarUsuarioId (int id) {
//		return AdministradorPersistenciaUsuario.getInstancia().buscar(id);
//	}
	
	public void updateContrasena () {
		AdministradorPersistenciaUsuario.getInstancia().updateContrasena(this);
	}

	public void updateBorrado(int dni) {
		AdministradorPersistenciaUsuario.getInstancia().updateBorrar(this);
	}

	public ArrayList<Permiso> getPermisos() {
		//return permisos;
		
		return AdministradorPersistenciaUsuario.getInstancia().getPermisos(dni);
	}

	public void setPermisos(ArrayList<Permiso> permisos) {
		this.permisos = permisos;
		//savePermisos();
	}
	
	public void savePermisos(){
		for (int i = 0; i < permisos.size(); i++) {
			AdministradorPersistenciaUsuario.getInstancia().insertPermisos(permisos.get(i), dni);
		}
		
	}

	public void updatePermisos() {
		AdministradorPersistenciaUsuario.getInstancia().borrarPermisos(dni);
		savePermisos();
	}
	
}