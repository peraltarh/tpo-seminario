package modelo;

import java.util.ArrayList;

import DTO.UsuarioDTO;
import persistencia.AdministradorPersistenciaUsuario;

public class Usuario {
	
	private ArrayList<String> especialiadades;
	private int dni;
	private String nombre;
	private String apellido;
	private String matricula;
	private String userName;
	private String password;
	private boolean borrado;
		
	
	public Usuario(){};
	
	public Usuario(String nombre, String apellido,String matricula,
				int dni,String userName, String password ) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.userName = userName;
		this.password = password;
		this.matricula = matricula;
		this.borrado = false;
		this.especialiadades =  new ArrayList();
		
		AdministradorPersistenciaUsuario.getInstancia().insert(this);
		
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
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
		return new UsuarioDTO(nombre, apellido, matricula, dni, userName, password, borrado, especialiadades);
		
	}

	public void modificarUsuario (String nombre, String apellido,String matricula,String userName, String password, boolean borrado) {
			
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

	public ArrayList<String> getEspecialidades() {

		return this.especialiadades;
	}

	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialiadades = especialidades;
	}
	
	public void savePermisos(){
//		for (int i = 0; i < permisos.size(); i++) {
//			AdministradorPersistenciaUsuario.getInstancia().insertPermisos(permisos.get(i), dni);
//		}
//		
	}


	
}