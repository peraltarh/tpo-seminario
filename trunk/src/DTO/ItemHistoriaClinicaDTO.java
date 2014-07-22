package DTO;

import java.util.Date;

import modelo.Consulta;
import modelo.PracticaAmbulatoria;
import modelo.PracticaQuirurgica;
import modelo.itemHistoriaClinica;

public class ItemHistoriaClinicaDTO {
	
	
	private Date fecha;
	private PrestacionDTO practica;
	
	

	public ItemHistoriaClinicaDTO(itemHistoriaClinica ihcTemp) {
		
		this.fecha = ihcTemp.getFecha();
		
		if(ihcTemp.getPractica() instanceof Consulta){
			this.practica = new ConsultaDTO(ihcTemp.getPractica());
			
		}else if(ihcTemp.getPractica() instanceof PracticaAmbulatoria){
			this.practica = new PracticaAmbulatoriaDTO(ihcTemp.getPractica());
			
		}else if(ihcTemp.getPractica() instanceof PracticaQuirurgica){
			this.practica = new PracticaQuirurgicaDTO(ihcTemp.getPractica());
			
		}
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public PrestacionDTO getPractica(){
		return this.practica;
	}
	
	

}
