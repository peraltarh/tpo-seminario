package modelo;

public class ObraSocial {

	private int idObrasocial;
	private static int idObrasocialInc = 0;
	private String razonSocial;
	
	
	
	public ObraSocial(String razonSocial) {
		super();
		this.idObrasocial = ObraSocial.idObrasocialInc ++;
		this.razonSocial = razonSocial;
	}



	public int getIdObrasocial() {
		return idObrasocial;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	
	
	
}
