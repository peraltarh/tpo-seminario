package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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
		
		public void auditar(UsuarioDTO usuarioDTO, String auditar) {
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
				ps.setString(2, dateString);
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