package modelo;

import java.sql.*;
import java.util.Vector;

import DTO.UsuarioDTO;

import persistencia.AdministradorPersistenciaUsuario;

public class Usuario {
	
	private int idUsuario;
	private String nombre;
	private String apellido;
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	private int matricula;
	private int dni;
	private String userName;
	private String password;
	private boolean borrado;
	
	
	public Usuario(){
		
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
		
		AdministradorPersistenciaUsuario.getInstancia().insert(this);
		
		
	}
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
		return new UsuarioDTO(idUsuario, nombre, apellido, matricula, dni, userName, password, borrado);
		
	}

	public void modificarUsuario (String nombre, String apellido,String password) {
			
			this.nombre = nombre;
			this.apellido = apellido;
			this.password = password;
			
			AdministradorPersistenciaUsuario.getInstancia().update(this);
		
	}

	public void borradoLogico() {
		borrado = false;
		AdministradorPersistenciaUsuario.getInstancia().borradoLogico(this);
		
	}
	
	public Vector<Usuario> buscarAll (){
		return AdministradorPersistenciaUsuario.getInstancia().buscarAll();
	}
	
	public Usuario buscarUsuario (String userName){
		return AdministradorPersistenciaUsuario.getInstancia().buscarUsuario(userName);
	}

	public Usuario buscarUsuario(int dni) {
		return AdministradorPersistenciaUsuario.getInstancia().buscarUsuario(dni);
	}
	
	public Usuario buscarUsuarioId (int id) {
		return AdministradorPersistenciaUsuario.getInstancia().buscar(id);
	}
	
	public void updateContraseña (int id, String psw) {
		AdministradorPersistenciaUsuario.getInstancia().updateContraseña(this);
	}

	public void updateBorrado(int id) {
		AdministradorPersistenciaUsuario.getInstancia().updateBorrar(this);
	}
	
}