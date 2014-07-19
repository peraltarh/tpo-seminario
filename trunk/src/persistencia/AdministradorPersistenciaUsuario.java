package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String senten = "SELECT * from usuario where usuarioLogin = ? and borrado = 0" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setString(1,userName);
			ResultSet result = ps.executeQuery();
			while (result.next())
			{
				u = new Usuario();
				u.setNombre(result.getString("nombre"));
				u.setApellido(result.getString("apellido"));
				u.setMatricula(result.getString("matricula"));
				u.setDni(result.getInt("nro"));
				u.setUserName(result.getString("usuarioLogin"));
				u.setPassword(result.getString("clave"));
				u.setBorrado(result.getBoolean("borrado"));
				u.setEspecialidades(this.getEspecialidadesUsuario(result.getString("nro"),result.getString("tipoDoc")));
				
		
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
			String senten = "SELECT * from usuario where nro = ? and borrado = 0" ;
			PreparedStatement ps = null;
			ps = con.prepareStatement(senten);
			ps.setInt(1,dni);
			ResultSet result = ps.executeQuery();
			while (result.next())
			{
				u = new Usuario();
				u.setNombre(result.getString("nombre"));
				u.setApellido(result.getString("apellido"));
				u.setMatricula(result.getString("matricula"));
				u.setDni(result.getInt("nro"));
				u.setUserName(result.getString("usuarioLogin"));
				u.setPassword(result.getString("clave"));
				u.setBorrado(result.getBoolean("borrado"));
				u.setEspecialidades(this.getEspecialidadesUsuario(result.getString("nro"),result.getString("tipoDoc")));
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
				String senten = "UPDATE usuario set nombre= ? , apellido = ? , matricula = ?, clave = ? , borrado = ? where nro = ?" ;
				PreparedStatement ps = null;
				
				ps = con.prepareStatement(senten);
				ps.setString(1,usuario.getNombre());
				ps.setString(2,usuario.getApellido());
				ps.setString(3,usuario.getMatricula());
				ps.setString(4,usuario.getPassword());
				ps.setBoolean(5,usuario.isBorrado());
				ps.setInt(6,usuario.getDni());
				
				ps.execute();
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error -Update Usuario- : " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		}
		
		
		public void updateContrasena (Usuario usuario) {
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "UPDATE usuario set clave = ? where nro = ?" ;
				PreparedStatement ps = null;
				
				ps = con.prepareStatement(senten);
				ps.setString(1,usuario.getPassword());
				ps.setInt(2,usuario.getDni());
				
				ps.execute();
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error -updateContrasena- : " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		}
		
		
		public void updateBorrar (Usuario usuario) {
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "UPDATE usuario set borrado = ? where nro = ?" ;
				PreparedStatement ps = null;
				
				ps = con.prepareStatement(senten);
				ps.setBoolean(1,usuario.isBorrado());
				ps.setInt(2,usuario.getDni());
				
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
						"(nombre,apellido,matricula,nro,usuarioLogin,password,borrado) VALUES (?,?,?,?,?,?,?)" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				
				ps.setString(1,usuario.getNombre());
				ps.setString(2,usuario.getApellido());
				ps.setString(3, usuario.getMatricula());
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
				String senten = "UPDATE usuario set borrado = 1 where nro = ?" ;
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
		
		
		public ArrayList<Usuario> buscarAll() {
			Usuario u = null;
			ArrayList<Usuario> vecUser = new ArrayList<Usuario>();
			
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
					//u.setIdUsuario(result.getInt("idUsuario"));
					u.setNombre(result.getString("nombre"));
					u.setApellido(result.getString("apellido"));
					u.setMatricula(result.getString("matricula"));
					u.setDni(result.getInt("nro"));
					u.setUserName(result.getString("usuarioLogin"));
					u.setPassword(result.getString("clave"));
					u.setBorrado(result.getBoolean("borrado"));
				//  u.setEspecialidad(this.getEspecialidadUsuario(result.getInt("idEspecialidad")));
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

		

		public ArrayList<String> getEspecialidadesUsuario(String nroDoc, String tipoDoc){
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			ArrayList <String> resultado = new ArrayList();
			try
			{
				String senten = "SELECT e.descripcion from usuario as u INNER JOIN Usuario_Especialidad as ue ON u.nro=ue.nro INNER JOIN Especialidad as e ON e.idEspecialidad= ue.idEspecialidad where u.nro =? and u.tipoDoc=? and borrado = 0 " ;
				PreparedStatement ps = null;

				ps = con.prepareStatement(senten);
				ps.setString(1, nroDoc);
				ps.setString(2, tipoDoc);

				ResultSet result = ps.executeQuery();
				while (result.next()){

					resultado.add(result.getString("descripcion"));
				}
				PoolConnection.getPoolConnection().realeaseConnection(con);


			}
			catch( SQLException e ) 
			{
				System.out.println("Mensaje Error -Especialidad- : " + e.getMessage());
				e.printStackTrace();
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return resultado;

			}		
			
		

			return resultado;
			
		}

		
		
		public ArrayList<String> getEspecialidades() {
			
			ArrayList<String> especialidades = new ArrayList<String>();
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			try
			{
				String senten = "SELECT * from especialidad" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				ResultSet result = ps.executeQuery();
				while (result.next())
				{
					especialidades.add(result.getString("descripcion"));
				}
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return especialidades;
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		      return especialidades;
		}
		
		
		
}