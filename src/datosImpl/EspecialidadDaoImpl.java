package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.EspecialidadDao;
import entidad.Especialidad;


public class EspecialidadDaoImpl implements EspecialidadDao {
	
	private Conexion cn;

	@Override
	public ArrayList<Especialidad> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		
		ArrayList<Especialidad> list = new ArrayList<Especialidad>();
		 try
		 {
			 ResultSet rs= cn.query("Select * from especialidades");
			 while(rs.next())
			 {
				 Especialidad esp = new Especialidad();
				 esp.setIdEspecialidad(rs.getInt(1));
				 esp.setDescripcion(rs.getString(2));
				 list.add(esp);
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
	public Especialidad obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
		
		Especialidad esp = new Especialidad();
		 
		try
		 {
			 ResultSet rs= cn.query("Select * from especialidades");
			 esp.setIdEspecialidad(rs.getInt(1));
			 esp.setDescripcion(rs.getString(2));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return esp;
	}

}
