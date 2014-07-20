package modelo;

public class PracticaAmbulatoria extends Prestacion{
	

	private int idPracticaAmbulatoria;
	private static int idPracticaAmbulatoriaInc = 0;

	private String ojo;
	private String diagnostico;
	private String medico;
	
	
	public PracticaAmbulatoria(String descripcion,
			String ojo, String diagnostico, String medico) {
		super(descripcion);
		this.idPracticaAmbulatoria = PracticaAmbulatoria.idPracticaAmbulatoriaInc++;
		this.ojo = ojo;
		this.diagnostico = diagnostico;
		this.medico = medico;
	}

	
	public int getIdPracticaAmbulatoria() {
		return idPracticaAmbulatoria;
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
