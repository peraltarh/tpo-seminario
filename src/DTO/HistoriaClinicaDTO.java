package DTO;

import java.util.ArrayList;

import modelo.HistoriaClinica;
import modelo.itemHistoriaClinica;



public class HistoriaClinicaDTO {

	private PacienteDTO paciente;
	private ArrayList<ItemHistoriaClinicaDTO> practicas;
	
	
	
	public HistoriaClinicaDTO (HistoriaClinica hce){
		
		this.paciente = new PacienteDTO(hce.getPaciente());
		this.practicas = new ArrayList<ItemHistoriaClinicaDTO>();
		
		
		for (itemHistoriaClinica ihcTemp : hce.getPreacticas()) {
			
			this.practicas.add(new ItemHistoriaClinicaDTO(ihcTemp));	
		}

	}
	
	public PacienteDTO getPaciente(){
		return paciente;
	}
	
	public ArrayList<ItemHistoriaClinicaDTO> getPracticas(){
		return practicas;
	}
	
}
