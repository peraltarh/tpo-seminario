package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import modelo.Permiso;
import modelo.Usuario;


public class AdministradorPersistenciaPermiso {

	static AdministradorPersistenciaPermiso pool;
	
	public AdministradorPersistenciaPermiso() {
		
	}
	
	public static AdministradorPersistenciaPermiso getInstancia(){
		if (pool == null)
			pool = new AdministradorPersistenciaPermiso();
		return pool;
	}
	
	public Vector<Permiso> buscarAll() {
		Permiso p = null;
		Vector<Permiso> pv = new Vector<Permiso>();
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
				
		try
		{
			String senten = "SELECT * FROM permiso where borrado  = 0" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ResultSet result = ps.executeQuery();
			while (result.next())
			{
				p = new Permiso();
				p.setIdPermiso(result.getInt("idPermiso"));
				p.setCode(result.getString("codigo"));
				p.setDescripcion(result.getString("descripcion"));
				p.setBorrado(result.getBoolean("borrado"));
				pv.add(p);
				
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return pv;
		}
	      catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error - buscarAll: " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
	      return pv;

		
	}
	
	public int buscarIdPermiso (String descripcion){
		Usuario u = null;
		Connection con = PoolConnection.getPoolConnection().getConnection();
		int resultado = 0;
		
		if(con==null){
			return resultado;
		}
	
		
		try
		{
			String senten = "SELECT idPermiso from permiso where descripcion = ?" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setString(1,descripcion);
			ResultSet result = ps.executeQuery();
			while (result.next())
			{
//				u = new Usuario();
//				u.setIdUsuario(result.getInt("idUsuario"));
//				u.setNombre(result.getString("nombre"));
//				u.setApellido(result.getString("apellido"));
//				u.setFechaNacimiento(result.getDate("fechaNac"));
//				u.setDni(result.getInt("dni"));
//				u.setUserName(result.getString("username"));
//				u.setPassword(result.getString("password"));
//				u.setActivo(result.getBoolean("borrado"));
				resultado = result.getInt("idPermiso");
				
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return resultado;
		}
	      catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error (buscarIdPermiso): " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
	      return resultado;
	}
	
	
	
	public Permiso buscarPermiso (int id){
		Permiso p = null;
		Connection con = PoolConnection.getPoolConnection().getConnection();
		
		if(con==null){
			return p;
		}
	
		
		try
		{
			String senten = "SELECT * from permiso where idPermiso = ?" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setInt(1,id);
			ResultSet result = ps.executeQuery();
			while (result.next())
			{
				p = new Permiso();
				p.setIdPermiso(result.getInt("idPermiso"));
				p.setDescripcion(result.getString("descipcion"));
				p.setCode(result.getString("codigo"));
				p.setBorrado(result.getBoolean("borrado"));
				
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return p;
		}
	      catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error (buscarIdPermiso): " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
	      return p;
	}
	
	
		
}