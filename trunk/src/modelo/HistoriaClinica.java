package modelo;

import java.util.ArrayList;

public class HistoriaClinica {
	
	private int idHistoriaClinica;
	private static int idHistoriaClinicaInc = 0;
	
	private Paciente paciente;
	private ArrayList<itemHistoriaClinica> preacticas;
	
	
	public HistoriaClinica( Paciente paciente) {
		super();
		this.idHistoriaClinica = HistoriaClinica.idHistoriaClinicaInc++;
		this.paciente = paciente;
		this.preacticas = new ArrayList<itemHistoriaClinica>();
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


	public void addPractica(itemHistoriaClinica itemHistoriaClinica) {
		this.preacticas.add(itemHistoriaClinica);
		
	}


	public boolean tenesPaciente(String nroDoc, String tipoDoc) {
		if(paciente.sosPaciente(nroDoc,tipoDoc))return true;
		return false;
	}



}
