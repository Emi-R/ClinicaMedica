package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.HorarioDao;
import entidad.Especialidad;
import entidad.Horario;

public class HorarioDaoImpl implements HorarioDao {
	
	private Conexion cn;
	
	public HorarioDaoImpl() {
		
	}

	@Override
	public boolean InsertarHorario(Horario horario, int dni) {
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	
		
		String query = "Insert into horariosxmedicos (DNIMedico, HoraInicio, HoraFin, DiaAtencion, Estado) VALUES ('"+dni+"','"+horario.getHoraInicio()+"','"+horario.getHoraFin()+"','"+horario.getDiaAtencion()+"','"+horario.getEstado()+"')";
		
		try
		 {
			estado=cn.execute(query);
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

	@Override
	public ArrayList<Horario> ListarTodos(int dni) {
		
		cn = new Conexion();
		cn.Open();
			
		ArrayList<Horario> list = new ArrayList<Horario>();
			
		try
			{
				ResultSet rs= cn.query("SELECT idHorario, HoraInicio, HoraFin, DiaAtencion, Estado FROM horariosxmedicos where Estado=1 && DNIMedico="+dni);
				while(rs.next())
				{
					Horario horario = new Horario();
					horario.setIdHorario(rs.getInt("idHorario"));
					horario.setDNIMedico(dni);
					horario.setHoraInicio(rs.getInt("HoraInicio"));
					horario.setHoraFin(rs.getInt("HoraFin"));
					horario.setDiaAtencion(rs.getString("DiaAtencion"));
					horario.setEstado(rs.getInt("Estado"));
					list.add(horario);
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
	
	public Horario ListarUno(int idHorario) {
		
		cn = new Conexion();
		cn.Open();
		Horario horario = new Horario();
		try
			{
				ResultSet rs= cn.query("SELECT idHorario, HoraInicio, HoraFin, DiaAtencion, Estado FROM horariosxmedicos where Estado=1 && idHorario="+idHorario);
				while(rs.next())
				{
					
					horario.setIdHorario(rs.getInt("idHorario"));
					horario.setHoraInicio(rs.getInt("HoraInicio"));
					horario.setHoraFin(rs.getInt("HoraFin"));
					horario.setDiaAtencion(rs.getString("DiaAtencion"));
					horario.setEstado(rs.getInt("Estado"));

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
			return horario;
	}

	@Override
	public boolean ModificarHorario(Horario horario) {
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE horariosxmedicos SET DiaAtencion='"+horario.getDiaAtencion()+"',HoraInicio='"+horario.getHoraInicio()+"', HoraFin='"+horario.getHoraFin()+"' where idHorario="+horario.getIdHorario();
		
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

	@Override
	public boolean EliminarHorario(int idHorario) {
		cn = new Conexion();
		cn.Open();
		
		boolean estado = false;
		
		try
		{
			String query = "UPDATE horariosxmedicos SET estado = 0 where idHorario = " + idHorario;
			estado = cn.execute(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return estado;
	}
	
	public Horario buscarHorario(int dniMedico, String dia)
	{
		cn = new Conexion();
		cn.Open();
			
		Horario horario = new Horario();
			
		try
			{
				ResultSet rs= cn.query("SELECT idHorario, HoraInicio, HoraFin, DiaAtencion, Estado FROM horariosxmedicos where Estado=1 and DNIMedico = "+dniMedico+" and DiaAtencion = '"+ dia+"'");
				rs.next();
				
					horario.setIdHorario(rs.getInt("idHorario"));
					horario.setDNIMedico(dniMedico);
					horario.setHoraInicio(rs.getInt("HoraInicio"));
					horario.setHoraFin(rs.getInt("HoraFin"));
					horario.setDiaAtencion(rs.getString("DiaAtencion"));
					horario.setEstado(rs.getInt("Estado"));

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				cn.close();
			}
			return horario;
	}
	
}
