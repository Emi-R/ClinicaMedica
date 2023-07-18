package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.LocalidadDao;
import entidad.Localidad;
import entidad.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {
	
	private Conexion cn;

	@Override
	public ArrayList<Localidad> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		
		ArrayList<Localidad> list = new ArrayList<Localidad>();
		 try
		 {
			 ResultSet rs= cn.query("Select * from localidades");
			 while(rs.next())
			 {
				 Localidad loc = new Localidad();
				 loc.setIDLocalidad(rs.getInt(1));
				 loc.setDescripcion(rs.getString(2));
				 list.add(loc);
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return list;
	}

	@Override
	public Localidad obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
		
		Localidad loc = new Localidad();
		 try
		 {
			 ResultSet rs= cn.query("Select * from Localidad");
			 loc.setIDLocalidad(rs.getInt(1));
			 loc.setDescripcion(rs.getString(2));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return loc;
	}
	
	
}
