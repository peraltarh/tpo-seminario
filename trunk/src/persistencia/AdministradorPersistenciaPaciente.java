package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Paciente;


public class AdministradorPersistenciaPaciente {

	static AdministradorPersistenciaPaciente pool;
	
		public AdministradorPersistenciaPaciente() {
		
	}
	
		public static AdministradorPersistenciaPaciente getInstancia(){
			if (pool == null)
				pool = new AdministradorPersistenciaPaciente();
		return pool;
	}

		public ArrayList<Paciente> buscarAll() {
			Paciente p = null;
			ArrayList<Paciente> vecPac = new ArrayList<Paciente>();
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "SELECT * FROM paciente" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				ResultSet result = ps.executeQuery();
				while (result.next())
				{
					p = new Paciente();
					
					p.setNombre(result.getString("nombre"));
					p.setApellido(result.getString("apellido"));
					p.setDni(result.getString("nro"));
					p.setTipoDoc(result.getString("tipoDoc"));
					p.setSexo(result.getString("sexo"));
					p.setEmail(result.getString("email"));
					p.setCelular(result.getLong("celular")); 
					p.setTelefono(result.getLong("telefono"));
					p.setFechaNaciemiento(result.getDate("fechaNacimiento"));
					
					
					//Cargo las obras sociales
					senten = "SELECT nro,tipoDoc,nroAfiliado,razonSocial FROM ObraSocial_Paciente,ObraSocial WHERE ObraSocial_Paciente.idObraSocial = ObraSocial.idObraSocial AND nro = ? AND tipoDoc = ?";
					ps = con.prepareStatement(senten);
					ps.setString(1,p.getDni());
					ps.setString(2,p.getTipoDoc());
					
					ResultSet result2 = ps.executeQuery();
					while (result2.next())
					{
						p.addObraSocial(result2.getString("razonSocial"), result2.getString("nroAfiliado"));
					}
					vecPac.add(p);
				}
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return vecPac;
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error - buscarAll: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		      return vecPac;

			
		}

		public Paciente buscarPaciente(String nro, String tipo) {
			Paciente p = null;
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "SELECT * FROM paciente WHERE nro=? AND tipoDoc=?" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				ps.setString(1,nro);
				ps.setString(2, tipo);
				
				ResultSet result = ps.executeQuery();
				while (result.next())
				{
					p = new Paciente();
					
					p.setNombre(result.getString("nombre"));
					p.setApellido(result.getString("apellido"));
					p.setDni(result.getString("nro"));
					p.setTipoDoc(result.getString("tipoDoc"));
					p.setSexo(result.getString("sexo"));
					p.setEmail(result.getString("email"));
					p.setCelular(result.getLong("celular")); 
					p.setTelefono(result.getLong("telefono"));
					p.setFechaNaciemiento(result.getDate("fechaNacimiento"));
					
				}
				//Cargo las obras sociales
				senten = "SELECT nro,tipoDoc,nroAfiliado,razonSocial FROM ObraSocial_Paciente,ObraSocial WHERE ObraSocial_Paciente.idObraSocial = ObraSocial.idObraSocial AND nro = ? AND tipoDoc = ?";
				ps = con.prepareStatement(senten);
				ps.setString(1,nro);
				ps.setString(2,tipo);
				
				result = ps.executeQuery();
				while (result.next())
				{
					p.addObraSocial(result.getString("razonSocial"), result.getString("nroAfiliado"));
				}

				
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return p;
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error - buscarAll: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
				return p;
			
			
		}

		
		
}