package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.UsuarioDTO;

public class AdministradorPersistenciaHCE {

	static AdministradorPersistenciaHCE pool;
	
	public AdministradorPersistenciaHCE() {
	
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
			String senten = "INSERT INTO auditoria" +
					"(usuarioLogin,fechaHs,descripcion) VALUES (?,getdate(),?)" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			
			ps.setString(1,usuarioDTO.getUserName().toString());
			ps.setString(2,auditar);
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
}
