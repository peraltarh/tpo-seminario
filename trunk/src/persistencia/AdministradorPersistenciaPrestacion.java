package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Paciente;
import modelo.Prestacion;


public class AdministradorPersistenciaPrestacion {

	static AdministradorPersistenciaPrestacion pool;
	
		public AdministradorPersistenciaPrestacion() {
		
	}
	
		public static AdministradorPersistenciaPrestacion getInstancia(){
			if (pool == null)
				pool = new AdministradorPersistenciaPrestacion();
		return pool;
	}


		public int buscarPrestacion(String descripcion) {
			int idPrestacion = -1;
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "SELECT * FROM prestacion WHERE descripcion=?" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				ps.setString(1,descripcion);
				
				ResultSet result = ps.executeQuery();
				
				while (result.next())
				{
					idPrestacion = result.getInt(1);				
				}
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return idPrestacion;
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error - buscarPrestacion: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
				return idPrestacion;
		}

		public ArrayList<String> getDescripcionPrestaciones() 
		{
			ArrayList<String> prestacionesDesc=new ArrayList<String>();
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "SELECT * FROM prestacion" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				
				ResultSet result = ps.executeQuery();
				
				while (result.next())
				{
					prestacionesDesc.add(result.getString(2));				
				}
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error - buscar Descripcion Prestaciones: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
				return prestacionesDesc;
		}

		
		
}