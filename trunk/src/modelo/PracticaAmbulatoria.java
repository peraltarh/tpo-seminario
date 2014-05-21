package modelo;

public class PracticaAmbulatoria extends Prestacion{
	

	private int idPracticaAmbulatoria;
	private static int idPracticaAmbulatoriaInc = 0;

	private String ojo;
	private String diagnostico;
	
	
	
	public PracticaAmbulatoria(String descripcion, int idPracticaAmbulatoria,
			String ojo, String diagnostico) {
		super(descripcion);
		this.idPracticaAmbulatoria = PracticaAmbulatoria.idPracticaAmbulatoriaInc++;
		this.ojo = ojo;
		this.diagnostico = diagnostico;
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



	
}
