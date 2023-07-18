package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {
	
	private Conexion cn;
	
	@Override
	public ArrayList<Provincia> obtenerTodos() {
		
		cn = new Conexion();
		cn.Open();
		
		ArrayList<Provincia> list = new ArrayList<Provincia>();
		 try
		 {
			 ResultSet rs= cn.query("Select * from provincias");
			 while(rs.next())
			 {
				 Provincia prov = new Provincia();
				 prov.setIDProvincia(rs.getInt(1));
				 prov.setDescripcion(rs.getString(2));
				 list.add(prov);
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
	public Provincia obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
		
		Provincia prov = new Provincia();
		 try
		 {
			 ResultSet rs= cn.query("Select * from Provincias");
			 prov.setIDProvincia(rs.getInt("provincias.IDProvincia"));
			 prov.setDescripcion(rs.getString("provincias.Nombre"));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return prov;
	}

}
