package modelo;

import java.util.Date;

public class PracticaQuirurgica<Datetime> extends Prestacion{

	private int idPracticaQuirurgica;
	private static int idPracticaQuirurgicaInc = 0;
	
	private String ojo;
	private String diagnostico;
	private String monitoreo;
	private Datetime horaInicio;
	private Datetime horaFin;
	private String anestesia;
	private String medico;
	
	
	public PracticaQuirurgica(String descripcion,
			String ojo, String diagnostico, String monitoreo, Datetime horaInicio,
			Datetime horaFin, String anestesia, String medico) {
		super(descripcion);
		this.idPracticaQuirurgica = PracticaQuirurgica.idPracticaQuirurgicaInc++;
		this.ojo = ojo;
		this.diagnostico = diagnostico;
		this.monitoreo = monitoreo;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.anestesia = anestesia;
		this.medico = medico;
	}


	public int getIdPracticaQuirurgica() {
		return idPracticaQuirurgica;
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
