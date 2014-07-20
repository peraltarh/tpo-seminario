package modelo;


public class Consulta extends Prestacion{

	
	private int idConsulta;
	private static int idConsultaInc = 0;
	
	private String observacionGeneral;
	private String tratamiento;
	private String motivo;
	private String observacionOjoIzq;
	private String observacionOjoDer;
	private String medico;
	
	
	public Consulta( String descripcionPractica, String observacionGeneral,
			String tratamiento, String motivo, String observacionOjoIzq,
			String observacionOjoDer, String medico) {
		super(descripcionPractica);
		this.idConsulta = Consulta.idConsultaInc++;
		this.observacionGeneral = observacionGeneral;
		this.tratamiento = tratamiento;
		this.motivo = motivo;
		this.observacionOjoIzq = observacionOjoIzq;
		this.observacionOjoDer = observacionOjoDer;
		this.medico = medico;
	}


	
	public int getIdConsulta() {
		return idConsulta;
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
