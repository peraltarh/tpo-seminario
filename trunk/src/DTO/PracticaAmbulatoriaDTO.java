package DTO;

import modelo.PracticaAmbulatoria;
import modelo.Prestacion;

public class PracticaAmbulatoriaDTO extends PrestacionDTO {

	
	private String ojo;
	private String diagnostico;
	private String medico;
	
	
	
	public PracticaAmbulatoriaDTO(Prestacion practica) {
		super(practica.getDescripcion());
		
		this.ojo = ((PracticaAmbulatoria)practica).getOjo();
		this.diagnostico = ((PracticaAmbulatoria)practica).getDiagnostico();
		this.medico = ((PracticaAmbulatoria)practica).getMedico();
	}


	public String getOjo() {
		return ojo;
	}


	public String getDiagnostico() {
		return diagnostico;
	}


	public String getMedico() {
		return medico;
	}

	
	
}
