package DTO;

import java.util.ArrayList;

import modelo.Permiso;


public class UsuarioDTO {
	
	private String nombre;
	private String apellido;
	private int matricula;
	private int dni;
	private String userName;
	private String password;
	private boolean borrado;
	private ArrayList<Permiso> permisos;
	
	public UsuarioDTO(){
		
	}
	
	public UsuarioDTO(String nombre, String apellido, int matricula,
				int dni,String userName, String password,boolean borrado,ArrayList<Permiso> permisos) {
		super(); 
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.matricula = matricula;
		this.dni = dni;
		this.userName = userName;
		this.password = password;
		this.borrado = borrado;
		this.permisos= permisos;
		
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
	
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
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
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public ArrayList<PermisoDTO> getPermisos() {
		ArrayList<PermisoDTO> vpDTO = new ArrayList<PermisoDTO>();
		for (int i = 0; i < permisos.size(); i++) {
				PermisoDTO permisoDTO = permisos.get(i).getView();
				vpDTO.add(permisoDTO);
		}
		return vpDTO;
	}

	public void setPermisos(ArrayList<Permiso> permisos) {
		this.permisos = permisos;
	}

	
}