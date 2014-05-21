package modelo;

import java.util.ArrayList;

public class HistoriaClinica {
	
	private int idHistoriaClinica;
	private static int idHistoriaClinicaInc = 0;
	
	private Paciente paciente;
	private ArrayList<itemHistoriaClinica> preacticas;
	
	
	public HistoriaClinica( Paciente paciente,
			ArrayList<itemHistoriaClinica> preacticas) {
		super();
		this.idHistoriaClinica = HistoriaClinica.idHistoriaClinicaInc++;
		this.paciente = paciente;
		this.preacticas = preacticas;
	}


	public int getIdHistoriaClinica() {
		return idHistoriaClinica;
	}


	public Paciente getPaciente() {
		return paciente;
	}


	public ArrayList<itemHistoriaClinica> getPreacticas() {
		return preacticas;
	}



}
