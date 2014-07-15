package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


	public int altaAmbulatoria(String prestacion, UsuarioDTO usuarioDTO, String ojo, String diagnostico) 
	{
		Connection con = PoolConnection.getPoolConnection().getConnection();
		int idPracticaAmbulatoria=-1;//falta obtener el id que se inserto y devolverlo
		try
		{
			//int idPrestacion=AdministradorPersistenciaPrestacion.getInstancia().buscarPrestacion(prestacion);
	//test
			int idPrestacion=2;
			
			
			String senten = "INSERT INTO practicaAmbulatoria" +
					"(idPrestacion,usuarioLogin,ojo,diagnostico) "
					+ "VALUES (?,?,?,?);SELECT @@IDENTITY";
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setInt(1,idPrestacion);
			//ps.setString(2,usuarioDTO.getUserName().toString());
	//test
			ps.setString(2,"dmoretti");
			ps.setString(3,ojo);
			ps.setString(4,diagnostico);
			ps.execute();
			
			
			int iUpdCount = ps.getUpdateCount();
			System.out.print(idPracticaAmbulatoria);
			boolean bMoreResults = true;
			ResultSet rs = null;
			while (bMoreResults || iUpdCount!=-1)
			{
				rs = ps.getResultSet();
			
				if (rs != null)
				{
					rs.next();
					idPracticaAmbulatoria = rs.getInt(0);
				}
				bMoreResults = ps.getMoreResults();
				iUpdCount = ps.getUpdateCount();
			}
			
			
			System.out.print(idPracticaAmbulatoria);
		//copied
			
			
			
			PoolConnection.getPoolConnection().realeaseConnection(con);


		}
		catch( SQLException e ) 
		{
			System.out.println("Mensaje Error -Auditar-: " + e.getMessage());
			e.printStackTrace();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}



		return idPracticaAmbulatoria;
	}
}
