package DTO;

import java.util.Date;

import modelo.ObraSocial;
import modelo.Paciente;

public class PacienteDTO {

	private long dni;
	private String tipoDoc;
	private String sexo;
	private String nombre;
	private String apellido;
	private long celular;
	private long telefono;
	private Date fechaNaciemiento;
	private String email;
	private ObraSocial obraSocial;
	private float nroAfiliado;
	
	
	public PacienteDTO(Paciente pacienteTemp) {
		
		this.dni = pacienteTemp.getDni();
		this.tipoDoc =  pacienteTemp.getTipoDoc();
		this.sexo =  pacienteTemp.getSexo();
		this.nombre = pacienteTemp.getNombre();
		this.apellido = pacienteTemp.getApellido();
		this.celular =  pacienteTemp.getCelular();
		this.telefono = pacienteTemp.getTelefono();
		this.fechaNaciemiento = pacienteTemp.getFechaNaciemiento();
		this.email = pacienteTemp.getEmail();
		this.obraSocial = pacienteTemp.getObraSocial();
		this.nroAfiliado =  pacienteTemp.getNroAfiliado();

	}


	public long getDni() {
		return dni;
	}


	public void setDni(long dni) {
		this.dni = dni;
	}


	public String getTipoDoc() {
		return tipoDoc;
	}


	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
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


	public long getCelular() {
		return celular;
	}


	public void setCelular(long celular) {
		this.celular = celular;
	}


	public long getTelefono() {
		return telefono;
	}


	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}


	public Date getFechaNaciemiento() {
		return fechaNaciemiento;
	}


	public void setFechaNaciemiento(Date fechaNaciemiento) {
		this.fechaNaciemiento = fechaNaciemiento;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public ObraSocial getObraSocial() {
		return obraSocial;
	}


	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}


	public float getNroAfiliado() {
		return nroAfiliado;
	}


	public void setNroAfiliado(float nroAfiliado) {
		this.nroAfiliado = nroAfiliado;
	}


	

}
