package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


	public void altaCirugia(String prestacion, UsuarioDTO usuarioDTO, String ojo, String diagnostico, String monitoreo, String hsInicio, String hsFin, String anestesia, String dateString, String nroDoc, String tipoDoc) 
	{
		Connection con = PoolConnection.getPoolConnection().getConnection();
		long idPracticaQuirurgica=-1;
		int idHCE=-1;
		try
		{
			int idPrestacion=AdministradorPersistenciaPrestacion.getInstancia().buscarPrestacion(prestacion);

			String senten = "INSERT INTO PracticaQuirurgica" +
					"(idPrestacion,usuarioLogin,ojo,diagnostico,monitoreo,horaInicio,horaFin,anestesia) "
					+ "VALUES (?,?,?,?,?,?,?,?);SELECT @@IDENTITY";
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setInt(1,idPrestacion);
			ps.setString(2,usuarioDTO.getUserName().toString());
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
			System.out.println("Mensaje Error -Alta Practica Quirurgica-: " + e.getMessage());
			e.printStackTrace();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}

		con = PoolConnection.getPoolConnection().getConnection();
		try
		{

			String senten2 = "SELECT (idHCE) FROM HCE WHERE nroDoc=? AND tipoDoc=?";
			PreparedStatement ps2 = null;
			ps2 = con.prepareStatement(senten2);
			ps2.setString(1,nroDoc);
			ps2.setString(2, tipoDoc);

			ResultSet result = ps2.executeQuery();

			while (result.next())
			{
				idHCE=(result.getInt(1));
			}	


			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch( SQLException e ) 
		{
			System.out.println("Mensaje Error -Auditar-: " + e.getMessage());
			e.printStackTrace();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}


		con = PoolConnection.getPoolConnection().getConnection();

		try
		{
			String senten3 = "INSERT INTO itemHCE" +
					"(idHCE,fecha,practica,idPracticaQuirurgica) "
					+ "VALUES (?,?,?,?)";

			PreparedStatement ps3 = null;
			ps3 = con.prepareStatement(senten3);
			ps3.setInt(1,idHCE);
			ps3.setString(2,dateString);
			ps3.setString(3,"PracticaQuirurgica");
			ps3.setInt(4,(int)idPracticaQuirurgica);
			ps3.execute();




			PoolConnection.getPoolConnection().realeaseConnection(con);


		}
		catch( SQLException e ) 
		{
			System.out.println("Mensaje Error -Practica Quirurgica-: " + e.getMessage());
			e.printStackTrace();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
	}
}
