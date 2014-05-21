package modelo;

public class Prestacion {
	
	private int idPrestacion;
	private static int idPrestacionInc = 0;
	
	private String descripcion;

	public Prestacion(String descripcion) {
		super();
		this.idPrestacion = Prestacion.idPrestacionInc++;
		this.descripcion = descripcion;
	}

	public int getIdPrestacion() {
		return idPrestacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	
}
