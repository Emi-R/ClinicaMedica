package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.ReporteTurnosXMedDao;
import entidad.ReporteTurnosXMed;

public class ReporteTurnosXMedDaoImpl implements ReporteTurnosXMedDao {

	private Conexion cn;
	
	public ReporteTurnosXMedDaoImpl() {

	}

	@Override
	public ArrayList<ReporteTurnosXMed> obtenerReporte() {

		cn = new Conexion();
		cn.Open();
		
		ArrayList<ReporteTurnosXMed> reporte = new ArrayList<ReporteTurnosXMed>();
		
		try
		{
			ResultSet rs = cn.query("select m.DNI, m.Apellido, m.Nombres, count(t.DNIMedico) as cantidad from turnos t inner join medicos m on t.DNIMedico = m.DNI where t.IDEstado <> 0 group by t.DNIMedico");
			
			while(rs.next())
			{
				ReporteTurnosXMed r = new ReporteTurnosXMed();
				
				r.setApellido(rs.getNString("Apellido"));	
				r.setNombre(rs.getString("Nombres"));	
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
