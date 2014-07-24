package modelo;

public class ObraSocial {

	private int idObrasocial;
	private static int idObrasocialInc = 0;
	private String razonSocial;
	
	
	public ObraSocial(String razonSocial, int id) {
		super();
		this.idObrasocial = id;
		this.razonSocial = razonSocial;
	}
	
	public ObraSocial(String razonSocial) {
		super();
		this.idObrasocial = ObraSocial.idObrasocialInc ++;
		this.razonSocial = razonSocial;
	}

	public ObraSocial() {
		
	}


	public void setIdObrasocial(int idObrasocial) {
		this.idObrasocial = idObrasocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public int getIdObrasocial() {
		return idObrasocial;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	
	
	
}
