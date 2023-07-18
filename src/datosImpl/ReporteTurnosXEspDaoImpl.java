package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.ReporteTurnosXEspDao;
import entidad.Especialidad;
import entidad.ReporteTurnosXEsp;

public class ReporteTurnosXEspDaoImpl implements ReporteTurnosXEspDao {

	private Conexion cn;
	
	public ReporteTurnosXEspDaoImpl() {
		
	}

	public ArrayList<ReporteTurnosXEsp> obtenerReporte()
	{
		cn = new Conexion();
		cn.Open();
		
		ArrayList<ReporteTurnosXEsp> reporte = new ArrayList<ReporteTurnosXEsp>();
		
		try
		{
			ResultSet rs = cn.query("select e.Nombre, count(IDTurno) as cantidad from turnos t inner join medicos m on t.DNIMedico = m.DNI inner join especialidades e on m.IDEspecialidad = e.IDEspecialidad where t.IDEstado <> 0 group by e.Nombre order by cantidad desc ");
			
			while(rs.next())
			{
				ReporteTurnosXEsp r = new ReporteTurnosXEsp();
				
					Especialidad esp = new Especialidad();
					esp.setDescripcion(rs.getString("Nombre"));
				r.setEspecialidad(esp);		
				r.setCantidad(rs.getInt("cantidad"));
				
				reporte.add(r);
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

		return reporte;
		
	}
}
