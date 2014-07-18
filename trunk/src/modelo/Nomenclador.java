package modelo;

import controlador.Sistema;

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

	public Nomenclador(String obraSocial, String prestacion, String precio) {
		this.idNomenclador = Nomenclador.idNomencladorInc ++;
		this.obraSocial = Sistema.getInstancia().buscarObrasocial(obraSocial);
		this.prestacion = Sistema.getInstancia().buscarPrestaion(prestacion);
		this.precio = Float.valueOf(precio);
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
