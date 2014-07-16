package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.UsuarioDTO;

public class AdministradorPersistenciaPracticaQuirurgica {

	static AdministradorPersistenciaPracticaQuirurgica pool;

	public AdministradorPersistenciaPracticaQuirurgica() {

	}

	public static AdministradorPersistenciaPracticaQuirurgica getInstancia(){
		if (pool == null)
			pool = new AdministradorPersistenciaPracticaQuirurgica();
		return pool;
	}


	public long altaCirugia(String prestacion, UsuarioDTO usuarioDTO, String ojo, String diagnostico, String monitoreo, String hsInicio, String hsFin, String anestesia) 
	{
		Connection con = PoolConnection.getPoolConnection().getConnection();
		long idPracticaQuirurgica=-1;//falta obtener el id que se inserto y devolverlo
		try
		{
			//int idPrestacion=AdministradorPersistenciaPrestacion.getInstancia().buscarPrestacion(prestacion);
	//test
	
			int idPrestacion=2;
			String senten = "INSERT INTO practicaQuirurgica" +
					"(idPrestacion,usuarioLogin,ojo,diagnostico,monitoreo,horaInicio,horaFin,anestesia) "
					+ "VALUES (?,?,?,?,?,?,?,?);SELECT @@IDENTITY";
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setInt(1,idPrestacion);
			//ps.setString(2,usuarioDTO.getUserName().toString());
	//test
			ps.setString(2, "dmoretti");
			ps.setString(3,ojo);
			ps.setString(4,diagnostico);
			ps.setString(5,monitoreo);
			ps.setString(6,hsInicio);
			ps.setString(7,hsFin);
			ps.setString(8,anestesia);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idPracticaQuirurgica = rs.getLong(1);
			}		
			
		
			
			
			
			PoolConnection.getPoolConnection().realeaseConnection(con);


		}
		catch( SQLException e ) 
		{
			System.out.println("Mensaje Error -Auditar-: " + e.getMessage());
			e.printStackTrace();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}



		return idPracticaQuirurgica;
	}
}
