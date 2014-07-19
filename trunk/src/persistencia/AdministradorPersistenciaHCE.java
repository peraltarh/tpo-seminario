package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import controlador.Sistema;
import modelo.Consulta;
import modelo.HistoriaClinica;
import modelo.Paciente;
import modelo.PracticaAmbulatoria;
import modelo.PracticaQuirurgica;
import modelo.Prestacion;
import modelo.itemHistoriaClinica;
import DTO.UsuarioDTO;

public class AdministradorPersistenciaHCE {

	static AdministradorPersistenciaHCE pool = null;
	
	private AdministradorPersistenciaHCE() {
	
	}

	public static AdministradorPersistenciaHCE getInstancia(){
		if (pool == null)
			pool = new AdministradorPersistenciaHCE();
	return pool;
}
	
	public void registrar(UsuarioDTO usuarioDTO, String auditar) {
		Connection con = PoolConnection.getPoolConnection().getConnection();
		
		try
		{
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = format.format(GregorianCalendar.getInstance().getTime());
			
			
			String senten = "INSERT INTO auditoria" +
					"(usuarioLogin,fechaHs,descripcion) VALUES (?,?,?)" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			
			ps.setString(1,usuarioDTO.getUserName().toString());
			ps.setString(2,dateString);
			ps.setString(3,auditar);
			ps.execute();
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
	      catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error -Auditar-: " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
	}


	public HistoriaClinica buscarHistoriaClinica (String tipoDoc, String nro){
		

		
		Paciente p = Sistema.getInstancia().buscarPaciente(tipoDoc, nro);
		
		HistoriaClinica hceTemp = new HistoriaClinica(p);
		

		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		
		try
		{
			String senten = "SELECT fecha,practica,idPracticaAmbulatoria,idConsulta,idPracticaQuirurgica FROM ItemHCE, (SELECT idHCE FROM HCE WHERE nroDoc = ? AND tipoDoc = ?) AS idHCEBuscado WHERE idHCEBuscado.idHCE = ItemHCE.idHCE ORDER BY fecha desc";
			
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setString(1, nro);
			ps.setString(2, tipoDoc);
			ResultSet result1 = ps.executeQuery();
			while (result1.next())
			{
				Prestacion practicatemp = null;
				
				if(result1.getString("practica").equals("Consulta")){
					
					senten = "SELECT descripcion,usuarioLogin,observacionGeneral,tratamiento,motivo,observacionOjoDer,observacionOjoIzq FROM Consulta,Prestacion WHERE idConsulta = ? AND Consulta.idPrestacion = Prestacion.idPrestacion";
					ps = con.prepareStatement(senten);
					ps.setInt(1, result1.getInt("idConsulta"));
					ResultSet result2 = ps.executeQuery();
					while (result2.next()){
						
						practicatemp = new Consulta(result2.getString("descripcion"), 
								result2.getString("observacionGeneral"), 
								result2.getString("tratamiento"), 
								result2.getString("motivo"), 
								result2.getString("observacionOjoIzq"), 
								result2.getString("observacionOjoDer"));
					}
					
				}
				else if(result1.getString("practica").equals("PracticaQuirurgica")){
					
					senten = "SELECT descripcion,usuarioLogin,ojo,diagnostico,monitoreo,horaInicio,horaFin,anestesia FROM PracticaQuirurgica,Prestacion WHERE idPracticaQuirurgica = ? AND PracticaQuirurgica.idPrestacion = Prestacion.idPrestacion";
					ps = con.prepareStatement(senten);
					ps.setInt(1, result1.getInt("idPracticaQuirurgica"));
					ResultSet result2 = ps.executeQuery();
					while (result2.next()){
						
						practicatemp = new PracticaQuirurgica(result2.getString("descripcion"), 
								result2.getString("ojo"), 
								result2.getString("diagnostico"), 
								result2.getString("monitoreo"), 
								result2.getString("horaInicio"), 
								result2.getString("horaFin"), 
								result2.getString("anestesia"));
					}
					
					
					
				}
				else if(result1.getString("practica").equals("PracticaAmbulatoria")){
					
					senten = "SELECT descripcion,usuarioLogin,ojo,diagnostico FROM PracticaAmbulatoria,Prestacion WHERE idPracticaAmbulatoria = ? AND PracticaAmbulatoria.idPrestacion = Prestacion.idPrestacion";
					ps = con.prepareStatement(senten);
					ps.setInt(1, result1.getInt("idPracticaAmbulatoria"));
					ResultSet result2 = ps.executeQuery();
					while (result2.next()){
						
						practicatemp = new PracticaAmbulatoria(result2.getString("descripcion"), 
								result2.getString("ojo"), 
								result2.getString("diagnostico"));
						
					}
					
				}
				
				hceTemp.addPractica(new itemHistoriaClinica(result1.getDate("fecha"), practicatemp));
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);

	
		}catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error - Administrador Persistencia HCE: " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
		
		return hceTemp;
		
	}

}
