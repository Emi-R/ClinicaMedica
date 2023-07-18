package datosImpl;

import java.sql.ResultSet;

import Exceptions.UsuarioRegistrado;
import datos.UsuarioDao;
import entidad.Usuario;
import entidad.TipoUsuario;


public class UsuarioDaoImpl implements UsuarioDao{

	private Conexion cn;
	
	public UsuarioDaoImpl() {
		
	}
	
	@Override
	public Usuario obtenerUsuario(String pass, int dni) {
		
		cn = new Conexion();
		cn.Open();
		Usuario usuario = null;
		
		try {
			ResultSet rs = cn.query("select DNI, IDTipoUsuario, Contraseña, Estado from usuarios where DNI = " + dni + " and Contraseña = '" + pass + "'");
			
			while(rs.next()) {
				
			usuario = new Usuario();
			usuario.setDNI(rs.getInt("DNI"));
			usuario.setContraseña(rs.getString("Contraseña"));

			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setIdTipoUsuario(rs.getInt("IDTipoUsuario"));
			
			if (rs.getInt("IDTipoUsuario") == 0)
				tipoUsuario.setDescripcion("Médico");
			else 
				tipoUsuario.setDescripcion("Administrador");
				
			usuario.setTipo(tipoUsuario);
			usuario.setEstado(rs.getInt("Estado"));
			
				}
			
			} 
			catch (Exception e) 
		    {
			e.printStackTrace();
			} 
			finally
			{
			cn.close();
			}
		
		return usuario;

	}

	@Override
	public boolean insertarUsuario(String pass, int dni, int tipoUsuario) {

		boolean estado = true;

		cn = new Conexion();
		cn.Open();
		
		try {
			
			String query = "Insert into usuarios (DNI, IDTipoUsuario, Contraseña, Estado) VALUES ('"+dni+"','"+tipoUsuario+"','"+pass+"', 1)";
			estado=cn.execute(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			cn.close();
		}
		
		return estado;
	}

	@Override
	public boolean editarUsuario(String pass, int dni) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();
		
		String query = "UPDATE usuarios SET Contraseña='"+pass+"' where DNI="+dni;
				
		try
		 {
			estado = cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}
	
	public boolean eliminarUsuario(int dni) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();
		
		String query = "UPDATE usuarios SET estado = 0 where DNI="+dni;
				
		try
		 {
			estado = cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}

	
	

}