package modelo;

public class Nomenclador {

	private int idNomenclador;
	private static int idNomencladorInc = 0;
	
	private ObraSocial obraSocial;
	private Prestacion prestacion;
	private float precio;
	
	public Nomenclador(ObraSocial obraSocial,
			Prestacion prestacion, float precio) {
		super();
		this.idNomenclador = Nomenclador.idNomencladorInc ++;
		this.obraSocial = obraSocial;
		this.prestacion = prestacion;
		this.precio = precio;
	}

	public int getIdNomenclador() {
		return idNomenclador;
	}

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public Prestacion getPrestacion() {
		return prestacion;
	}

	public float getPrecio() {
		return precio;
	}
	
	
	
	
	
}
