package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ObraSocial;

public class AdministradorPresistenciaObrasSociales {

	static AdministradorPresistenciaObrasSociales pool = null;

	private AdministradorPresistenciaObrasSociales() {
	};

	public static AdministradorPresistenciaObrasSociales getInstancia() {
		if (pool == null)
			pool = new AdministradorPresistenciaObrasSociales();
		return pool;
	}
	
	

	public ArrayList<ObraSocial> getObrasSociales() {
	
		ArrayList<ObraSocial> obrasSociales = new ArrayList<ObraSocial>();
		Connection con = PoolConnection.getPoolConnection().getConnection();
				
		try
		{
			String senten = "SELECT * FROM ObraSocial" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			
			ResultSet result = ps.executeQuery();
			
			while (result.next())
			{
				ObraSocial os = new ObraSocial();
				os.setIdObrasocial(result.getInt("idObraSocial"));
				os.setRazonSocial(result.getString("razonSocial"));
				obrasSociales.add(os);				
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
	      catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error - buscar Obras Sociales: " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
			return obrasSociales;
	}
		

}
