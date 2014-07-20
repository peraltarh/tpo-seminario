package DTO;

import java.util.Date;

import modelo.PracticaQuirurgica;
import modelo.Prestacion;

public class PracticaQuirurgicaDTO<Datetime> extends PrestacionDTO {

	private String ojo;
	private String diagnostico;
	private String monitoreo;
	private Datetime horaInicio;
	private Datetime horaFin;
	private String anestesia;
	private String medico;
	

	@SuppressWarnings("unchecked")
	public PracticaQuirurgicaDTO(Prestacion practica) {
		super(practica.getDescripcion());
		this.ojo = ((PracticaQuirurgica<Datetime>)practica).getOjo();
		this.diagnostico = ((PracticaQuirurgica<Datetime>)practica).getDiagnostico();
		this.monitoreo = ((PracticaQuirurgica<Datetime>)practica).getMonitoreo();
		this.horaInicio = (Datetime) ((PracticaQuirurgica<Datetime>)practica).getHoraInicio();
		this.horaFin = (Datetime) ((PracticaQuirurgica<Datetime>)practica).getHoraFin();
		this.anestesia = ((PracticaQuirurgica<Datetime>)practica).getAnestesia();
		this.medico = ((PracticaQuirurgica<Datetime>)practica).getMedico();
	}


	public String getOjo() {
		return ojo;
	}


	public String getDiagnostico() {
		return diagnostico;
	}


	public String getMonitoreo() {
		return monitoreo;
	}


	public Datetime getHoraInicio() {
		return horaInicio;
	}


	public Datetime getHoraFin() {
		return horaFin;
	}


	public String getAnestesia() {
		return anestesia;
	}

	
	public String getMedico() {
		return medico;
	}
	
	
}
