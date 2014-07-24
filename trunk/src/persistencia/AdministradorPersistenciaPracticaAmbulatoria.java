package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.UsuarioDTO;

public class AdministradorPersistenciaPracticaAmbulatoria {

	static AdministradorPersistenciaPracticaAmbulatoria pool;

	public AdministradorPersistenciaPracticaAmbulatoria() {

	}

	public static AdministradorPersistenciaPracticaAmbulatoria getInstancia(){
		if (pool == null)
			pool = new AdministradorPersistenciaPracticaAmbulatoria();
		return pool;
	}


	public void altaAmbulatoria(String prestacion, UsuarioDTO usuarioDTO, String ojo, String diagnostico, String dateString, String nroDoc, String tipoDoc, int idOS) 
	{
		Connection con = PoolConnection.getPoolConnection().getConnection();
		long idPracticaAmbulatoria=-1;
		int idHCE=-1;
		try
		{
			int idPrestacion=AdministradorPersistenciaPrestacion.getInstancia().buscarPrestacion(prestacion);
			


			String senten = "INSERT INTO practicaAmbulatoria" +
					"(idPrestacion,usuarioLogin,ojo,diagnostico,idOS) "
					+ "VALUES (?,?,?,?,?); SELECT @@IDENTITY";
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setInt(1,idPrestacion);
			ps.setString(2,usuarioDTO.getUserName().toString());
			ps.setString(3,ojo);
			ps.setString(4,diagnostico);
			ps.setInt(5,idOS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idPracticaAmbulatoria = rs.getLong(1);
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
					"(idHCE,fecha,practica,idPracticaAmbulatoria) "
					+ "VALUES (?,?,?,?)";
			
			PreparedStatement ps3 = null;
			ps3 = con.prepareStatement(senten3);
			ps3.setInt(1,idHCE);
			ps3.setString(2,dateString);
			ps3.setString(3,"PracticaAmbulatoria");
			ps3.setInt(4,(int)idPracticaAmbulatoria);
			ps3.execute();
			
			
			
			
			PoolConnection.getPoolConnection().realeaseConnection(con);


		}
		catch( SQLException e ) 
		{
			System.out.println("Mensaje Error -Auditar-: " + e.getMessage());
			e.printStackTrace();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}

		

		
	}
}
