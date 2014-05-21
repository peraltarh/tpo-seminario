package modelo;

import java.util.ArrayList;
import java.util.Vector;

import DTO.PermisoDTO;

import persistencia.AdministradorPersistenciaPermiso;



public class Permiso {
	private int idPermiso;
	private String code;
	private String descripcion;
	private boolean borrado;

	public Permiso() {
		// TODO Auto-generated constructor stub
	}

		
	public Permiso(int idPermiso, String code, String descripcion,boolean borrado) {
		super();
		this.idPermiso = idPermiso;
		this.code = code;
		this.descripcion = descripcion;
		this.borrado = borrado;
	}


	public boolean isBorrado() {
		return borrado;
	}


	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}


	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}


	public int getIdPermiso() {
		return idPermiso;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public ArrayList<Permiso> buscarAll() {
		return AdministradorPersistenciaPermiso.getInstancia().buscarAll();
	}


	public PermisoDTO getView() {
		return new PermisoDTO(idPermiso, code, descripcion, borrado);
	}


	public int buscarIdPermiso(String desc) {
		return AdministradorPersistenciaPermiso.getInstancia().buscarIdPermiso(desc);
		
	}
	
	public Permiso buscarPermiso (int id) {
		return AdministradorPersistenciaPermiso.getInstancia().buscarPermiso(id);
	}

}
