package DTO;

public class PermisoDTO {
	private int idPermiso;
	private String code;
	private String descripcion;
	private boolean borrado;

	public PermisoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public PermisoDTO(int idPermiso, String code, String descripcion, boolean borrado) {
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



	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
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

	
}
