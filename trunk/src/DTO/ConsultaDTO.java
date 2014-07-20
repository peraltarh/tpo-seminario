package DTO;

import modelo.Consulta;
import modelo.Prestacion;

public class ConsultaDTO extends PrestacionDTO {

	
	private String observacionGeneral;
	private String tratamiento;
	private String motivo;
	private String observacionOjoIzq;
	private String observacionOjoDer;
	private String medico;
	
	
	public ConsultaDTO(Prestacion practica) {
		super(practica.getDescripcion());
		this.observacionGeneral = ((Consulta)practica).getObservacionGeneral();
		this.tratamiento = ((Consulta)practica).getTratamiento();
		this.motivo = ((Consulta)practica).getMotivo();
		this.observacionOjoIzq = ((Consulta)practica).getObservacionOjoIzq();
		this.observacionOjoDer = ((Consulta)practica).getObservacionOjoDer();		
		this.medico = ((Consulta)practica).getMedico();
	}



	public String getObservacionGeneral() {
		return observacionGeneral;
	}



	public String getTratamiento() {
		return tratamiento;
	}



	public String getMotivo() {
		return motivo;
	}



	public String getObservacionOjoIzq() {
		return observacionOjoIzq;
	}



	public String getObservacionOjoDer() {
		return observacionOjoDer;
	}
	

	public String getMedico() {
		return medico;
	}

	
	
	

}
