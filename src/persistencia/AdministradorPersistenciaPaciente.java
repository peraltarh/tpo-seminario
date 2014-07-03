package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Paciente;


public class AdministradorPersistenciaPaciente {

	static AdministradorPersistenciaPaciente pool;
	
		public AdministradorPersistenciaPaciente() {
		
	}
	
		public static AdministradorPersistenciaPaciente getInstancia(){
			if (pool == null)
				pool = new AdministradorPersistenciaPaciente();
		return pool;
	}

//		public Usuario buscarUsuario(String userName){
//		Usuario u = null;
//		Connection con = PoolConnection.getPoolConnection().getConnection();
//		
//		if(con==null){
//			return u;
//		}
//	
//		
//		try
//		{
//			String senten = "SELECT * from usuario where usuarioLogin = ? and borrado = 0" ;
//			PreparedStatement ps = null;
//			ps = con.prepareStatement(senten);
//			ps.setString(1,userName);
//			ResultSet result = ps.executeQuery();
//			while (result.next())
//			{
//				u = new Usuario();
//				u.setNombre(result.getString("nombre"));
//				u.setApellido(result.getString("apellido"));
//				u.setMatricula(result.getInt("matricula"));
//				u.setDni(result.getInt("nro"));
//				u.setUserName(result.getString("usuarioLogin"));
//				u.setPassword(result.getString("clave"));
//				u.setBorrado(result.getBoolean("borrado"));
//				u.setEspecialidad(this.getEspecialidadUsuario(result.getInt("idEspecialidad")));
//				
//		
//			}
//			
//			PoolConnection.getPoolConnection().realeaseConnection(con);
//			return u;
//		}
//	      catch( SQLException e ) 
//	      {
//				System.out.println("Mensaje Error (buscarUsuario): " + e.getMessage());
//				e.printStackTrace();
//				PoolConnection.getPoolConnection().realeaseConnection(con);
//	      }
//	      return u;
//	}
//
//		
//		public Usuario buscarUsuario (int dni){
//		
//			Usuario u = null;
//		Connection con = PoolConnection.getPoolConnection().getConnection();
//				
//		try
//		{
//			String senten = "SELECT * from usuario where nro = ? and borrado = 0" ;
//			PreparedStatement ps = null;
//			ps = con.prepareStatement(senten);
//			ps.setInt(1,dni);
//			ResultSet result = ps.executeQuery();
//			while (result.next())
//			{
//				u = new Usuario();
//				u.setNombre(result.getString("nombre"));
//				u.setApellido(result.getString("apellido"));
//				u.setMatricula(result.getInt("matricula"));
//				u.setDni(result.getInt("nro"));
//				u.setUserName(result.getString("usuarioLogin"));
//				u.setPassword(result.getString("clave"));
//				u.setBorrado(result.getBoolean("borrado"));
//				u.setEspecialidad(this.getEspecialidadUsuario(result.getInt("idEspecialidad")));
//			}
//			
//			PoolConnection.getPoolConnection().realeaseConnection(con);
//			return u;
//		}
//	      catch( SQLException e ) 
//	      {
//				System.out.println("Mensaje Error: " + e.getMessage());
//				e.printStackTrace();
//				PoolConnection.getPoolConnection().realeaseConnection(con);
//	      }
//	      return u;
//	}
//
//		
//		public void update(Usuario usuario) {
//			
//			Connection con = PoolConnection.getPoolConnection().getConnection();
//					
//			try
//			{
//				String senten = "UPDATE usuario set nombre= ? , apellido = ? , matricula = ?, clave = ? , borrado = ? where nro = ?" ;
//				PreparedStatement ps = null;
//				
//				ps = con.prepareStatement(senten);
//				ps.setString(1,usuario.getNombre());
//				ps.setString(2,usuario.getApellido());
//				ps.setInt(3,usuario.getMatricula());
//				ps.setString(4,usuario.getPassword());
//				ps.setBoolean(5,usuario.isBorrado());
//				ps.setInt(6,usuario.getDni());
//				
//				ps.execute();
//				
//				PoolConnection.getPoolConnection().realeaseConnection(con);
//			}
//		      catch( SQLException e ) 
//		      {
//					System.out.println("Mensaje Error -Update Usuario- : " + e.getMessage());
//					e.printStackTrace();
//					PoolConnection.getPoolConnection().realeaseConnection(con);
//		      }
//		}
//		
//		
//		public void updateContrasena (Usuario usuario) {
//			Connection con = PoolConnection.getPoolConnection().getConnection();
//					
//			try
//			{
//				String senten = "UPDATE usuario set clave = ? where nro = ?" ;
//				PreparedStatement ps = null;
//				
//				ps = con.prepareStatement(senten);
//				ps.setString(1,usuario.getPassword());
//				ps.setInt(2,usuario.getDni());
//				
//				ps.execute();
//				
//				PoolConnection.getPoolConnection().realeaseConnection(con);
//			}
//		      catch( SQLException e ) 
//		      {
//					System.out.println("Mensaje Error -updateContrasena- : " + e.getMessage());
//					e.printStackTrace();
//					PoolConnection.getPoolConnection().realeaseConnection(con);
//		      }
//		}
//		
//		
//		public void updateBorrar (Usuario usuario) {
//			
//			Connection con = PoolConnection.getPoolConnection().getConnection();
//					
//			try
//			{
//				String senten = "UPDATE usuario set borrado = ? where nro = ?" ;
//				PreparedStatement ps = null;
//				
//				ps = con.prepareStatement(senten);
//				ps.setBoolean(1,usuario.isBorrado());
//				ps.setInt(2,usuario.getDni());
//				
//				ps.execute();
//				
//				PoolConnection.getPoolConnection().realeaseConnection(con);
//			}
//		      catch( SQLException e ) 
//		      {
//					System.out.println("Mensaje Error -updateBorrar- : " + e.getMessage());
//					e.printStackTrace();
//					PoolConnection.getPoolConnection().realeaseConnection(con);
//		      }
//		}
//		
//		
//		public void insert(Usuario usuario) {
//			Connection con = PoolConnection.getPoolConnection().getConnection();
//			
//			try
//			{
//				String senten = "INSERT INTO usuario" +
//						"(nombre,apellido,matricula,nro,usuarioLogin,password,borrado) VALUES (?,?,?,?,?,?,?)" ;
//				PreparedStatement ps = null;
//				ps = con.prepareStatement(senten);
//				
//				ps.setString(1,usuario.getNombre());
//				ps.setString(2,usuario.getApellido());
//				ps.setInt(3, usuario.getMatricula());
//				ps.setInt(4,usuario.getDni());
//				ps.setString(5,usuario.getUserName());
//				ps.setString(6,usuario.getPassword());
//				if (usuario.isBorrado() == true){
//					ps.setInt(7,1);
//				}else ps.setInt(7,0);
//				
//				ps.execute();
//				
//				PoolConnection.getPoolConnection().realeaseConnection(con);
//			}
//		      catch( SQLException e ) 
//		      {
//					System.out.println("Mensaje Error -Update Usuario-: " + e.getMessage());
//					e.printStackTrace();
//					PoolConnection.getPoolConnection().realeaseConnection(con);
//		      }
//		}
//
//		
//		public void borradoLogico(Usuario usuario) {
//			Connection con = PoolConnection.getPoolConnection().getConnection();
//			
//			try
//			{
//				String senten = "UPDATE usuario set borrado = 1 where nro = ?" ;
//				PreparedStatement ps = null;
//				
//				ps = con.prepareStatement(senten);
//				ps.setInt(1,usuario.getDni());
//				
//				ps.execute();
//				
//				PoolConnection.getPoolConnection().realeaseConnection(con);
//			}
//		      catch( SQLException e ) 
//		      {
//					System.out.println("Mensaje Error: " + e.getMessage());
//					e.printStackTrace();
//					PoolConnection.getPoolConnection().realeaseConnection(con);
//		      }
//
//		}
//		
		
		public ArrayList<Paciente> buscarAll() {
			Paciente p = null;
			ArrayList<Paciente> vecPac = new ArrayList<Paciente>();
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
					
			try
			{
				String senten = "SELECT * FROM paciente" ;
				PreparedStatement ps = null;
				ps = con.prepareStatement(senten);
				ResultSet result = ps.executeQuery();
				while (result.next())
				{
					p = new Paciente();
					
					p.setNombre(result.getString("nombre"));
					p.setApellido(result.getString("apellido"));
					p.setDni(result.getInt("nro"));
					p.setTipoDoc(result.getString("tipoDoc"));
					p.setSexo(result.getString("sexo"));
					p.setEmail(result.getString("email"));
					p.setCelular(result.getLong("celular")); 
					p.setTelefono(result.getLong("telefono"));
					p.setFechaNaciemiento(result.getDate("fechaNacimiento"));
					vecPac.add(p);
					
				}
				
				PoolConnection.getPoolConnection().realeaseConnection(con);
				return vecPac;
			}
		      catch( SQLException e ) 
		      {
					System.out.println("Mensaje Error - buscarAll: " + e.getMessage());
					e.printStackTrace();
					PoolConnection.getPoolConnection().realeaseConnection(con);
		      }
		      return vecPac;

			
		}

		
		
}