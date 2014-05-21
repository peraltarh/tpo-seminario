package modelo;

import java.util.Date;

public class itemHistoriaClinica {

	private Date fecha;
	private Prestacion practica;
	
	
	public itemHistoriaClinica(Date fecha, Prestacion practica) {
		super();
		this.fecha = fecha;
		this.practica = practica;
	}


	public Date getFecha() {
		return fecha;
	}


	public Prestacion getPractica() {
		return practica;
	}
	
	
}
