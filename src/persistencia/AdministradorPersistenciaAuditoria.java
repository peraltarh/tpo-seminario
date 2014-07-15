package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.UsuarioDTO;



public class AdministradorPersistenciaAuditoria {

	static AdministradorPersistenciaAuditoria pool;
	
		public AdministradorPersistenciaAuditoria() {
		
	}
	
		public static AdministradorPersistenciaAuditoria getInstancia(){
			if (pool == null)
				pool = new AdministradorPersistenciaAuditoria();
		return pool;
	}
		
		public void registrar(UsuarioDTO usuarioDTO, Date date, String auditar) {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			try
			{
				String senten = "INSERT INTO auditoria" +
						"(usuarioLogin,fechaHs,descripcion) VALUES (?,?,?)" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				
				ps.setString(1,usuarioDTO.getUserName().toString());
				ps.setString(2,date.toString());
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

}