package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import modelo.Permiso;
import modelo.Usuario;

public class AdministradorPersistenciaUsuario {

	static AdministradorPersistenciaUsuario pool;
	
	public AdministradorPersistenciaUsuario() {
		
	}
	
	public static AdministradorPersistenciaUsuario getInstancia(){
		if (pool == null)
			pool = new AdministradorPersistenciaUsuario();
		return pool;
	}

	public Usuario buscarUsuario(String userName){
		Usuario u = null;
		Connection con = PoolConnection.getPoolConnection().getConnection();
		
		if(con==null){
			return u;
		}
	
		
		try
		{
			String senten = "SELECT * from usuario where userName = ? and borrado = 0" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setString(1,userName);
			ResultSet result = ps.executeQuery();
			while (result.next())
			{
				u = new Usuario();
				u.setIdUsuario(result.getInt("idUsuario"));
				u.setNombre(result.getString("nombre"));
				u.setApellido(result.getString("apellido"));
				u.setMatricula(result.getInt("matricula"));
				u.setDni(result.getInt("dni"));
				u.setUserName(result.getString("username"));
				u.setPassword(result.getString("password"));
				u.setBorrado(result.getBoolean("borrado"));
		
				
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return u;
		}
	      catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error (buscarUsuario): " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
	      return u;
	}

		public Usuario buscarUsuario (int dni){
		
			Usuario u = null;
		Connection con = PoolConnection.getPoolConnection().getConnection();
				
		try
		{
			String senten = "SELECT * from usuario where dni = ? and borrado = 0" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setInt(1,dni);
			ResultSet result = ps.executeQuery();
			while (result.next())
			{
				u = new Usuario();
				u.setIdUsuario(result.getInt("idUsuario"));
				u.setNombre(result.getString("nombre"));
				u.setApellido(result.getString("apellido"));
				u.setMatricula(result.getInt("matricula"));
				u.setDni(result.getInt("dni"));
				u.setUserName(result.getString("username"));
				u.setPassword(result.getString("password"));
				u.setBorrado(result.getBoolean("borrado"));
				
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return u;
		}
	      catch( SQLException e ) 
	      {
				System.out.println("Mensaje Error: " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
	      }
	      return u;
	}

		public Usuario buscar(int id){
			
			Usuario u = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "SELECT * from usuario where idUsuario = ? and borrado = 0" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				ps.setInt(1,id);
				ResultSet result = ps.executeQuery();
				while (result.next())
				{
					u = new Usuario();
					u.setIdUsuario(result.getInt("idUsuario"));
					u.setNombre(result.getString("nombre"));
					u.setApellido(result.getString("apellido"));
					u.setMatricula(result.getInt("matricula"));
					u.setDni(result.getInt("dni"));
					u.setUserName(result.getString("username"));
					u.setPassword(result.getString("password"));
					u.setBorrado(result.getBoolean("borrado"));
					
				}
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return u;
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		      return u;
		}
		
		
		public void update(Usuario usuario) {
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "UPDATE usuario set nombre= ? , apellido = ? , matricula = ?, password = ? where idUsuario = ?" ;
				PreparedStatement ps = null;
				
				ps = con.prepareStatement(senten);
				ps.setString(1,usuario.getNombre());
				ps.setString(2,usuario.getApellido());
				ps.setInt(3,usuario.getMatricula());
				ps.setString(4,usuario.getPassword());
				ps.setInt(5,usuario.getIdUsuario());
				
				ps.execute();
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		}
		
		
		public void updateContrase�a (Usuario usuario) {
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "UPDATE usuario set password = ? where idUsuario = ?" ;
				PreparedStatement ps = null;
				
				ps = con.prepareStatement(senten);
				ps.setString(1,usuario.getPassword());
				ps.setInt(2,usuario.getIdUsuario());
				
				ps.execute();
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error -updateContrase�a- : " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		}
		
		
public void updateBorrar (Usuario usuario) {
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "UPDATE usuario set borrado = ? where idUsuario = ?" ;
				PreparedStatement ps = null;
				
				ps = con.prepareStatement(senten);
				ps.setBoolean(1,usuario.isBorrado());
				ps.setInt(2,usuario.getIdUsuario());
				
				ps.execute();
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error -updateBorrar- : " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		}
		
		
		public void insert(Usuario usuario) {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			try
			{
				String senten = "INSERT INTO usuario" +
						"(nombre,apellido,matricula,dni,username,password,borrado) VALUES (?,?,?,?,?,?,?)" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				
				ps.setString(1,usuario.getNombre());
				ps.setString(2,usuario.getApellido());
				ps.setInt(3,usuario.getMatricula());
				ps.setInt(4,usuario.getDni());
				ps.setString(5,usuario.getUserName());
				ps.setString(6,usuario.getPassword());
				if (usuario.isBorrado() == true){
					ps.setInt(7,1);
				}else ps.setInt(7,0);
				
				ps.execute();
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error -Update Usuario-: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		}

		public void borradoLogico(Usuario usuario) {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			try
			{
				String senten = "UPDATE usuario set borrado = 1 where dni = ?" ;
				PreparedStatement ps = null;
				
				ps = con.prepareStatement(senten);
				ps.setInt(1,usuario.getDni());
				
				ps.execute();
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }

		}
		
		public Vector<Usuario> buscarAll() {
			Usuario u = null;
			Vector<Usuario> vecUser = new Vector<Usuario>();
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "SELECT * FROM usuario" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				ResultSet result = ps.executeQuery();
				while (result.next())
				{
					u = new Usuario();
					u.setIdUsuario(result.getInt("idUsuario"));
					u.setNombre(result.getString("nombre"));
					u.setApellido(result.getString("apellido"));
					u.setMatricula(result.getInt("matricula"));
					u.setDni(result.getInt("dni"));
					u.setUserName(result.getString("username"));
					u.setPassword(result.getString("password"));
					u.setBorrado(result.getBoolean("borrado"));
					
					vecUser.add(u);
					
				}
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return vecUser;
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error - buscarAll: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		      return vecUser;

			
		}
		
		
}