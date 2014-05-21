package modelo;

import java.util.Date;

public class PracticaQuirurgica extends Prestacion{

	private int idPracticaQuirurgica;
	private static int idPracticaQuirurgicaInc = 0;
	
	private String ojo;
	private String diagnostico;
	private String monitoreo;
	private Date horaInicio;
	private Date horaFin;
	private String anestesia;
	
	
	public PracticaQuirurgica(String descripcion, int idPracticaQuirurgica,
			String ojo, String diagnostico, String monitoreo, Date horaInicio,
			Date horaFin, String anestesia) {
		super(descripcion);
		this.idPracticaQuirurgica = PracticaQuirurgica.idPracticaQuirurgicaInc++;
		this.ojo = ojo;
		this.diagnostico = diagnostico;
		this.monitoreo = monitoreo;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.anestesia = anestesia;
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


	public Date getHoraInicio() {
		return horaInicio;
	}


	public Date getHoraFin() {
		return horaFin;
	}


	public String getAnestesia() {
		return anestesia;
	}

	
	
}
