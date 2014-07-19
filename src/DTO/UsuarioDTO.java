package DTO;

import java.util.ArrayList;


public class UsuarioDTO {
	
	private String nombre;
	private String apellido;
	private String matricula;
	private int dni;
	private String userName;
	private String password;
	private boolean borrado;
	private ArrayList<String> especialidades;
	
	public UsuarioDTO(){
		
	}
	
	public UsuarioDTO(String nombre, String apellido, String matricula,
				int dni,String userName, String password,boolean borrado,ArrayList<String> especialiadades) {
		super(); 
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.matricula = matricula;
		this.dni = dni;
		this.userName = userName;
		this.password = password;
		this.borrado = borrado;
		this.especialidades= especialiadades;
		
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
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getDni() {
		return dni;
	}
	
	public ArrayList<String> getEspecialidades() {
		return especialidades;
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
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}

	
}