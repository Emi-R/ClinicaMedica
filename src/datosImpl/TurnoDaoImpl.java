package datosImpl;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import datos.TurnoDao;
import entidad.Persona;
import entidad.Provincia;
import entidad.Turno;
import entidad.Medico;
import entidad.Especialidad;
import entidad.Horario;
import entidad.Localidad;

public class TurnoDaoImpl implements TurnoDao{
	
	private Conexion cn;
	
	public boolean ActualizarTurno(Turno turno) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE turnos SET DNIPaciente='"+turno.getPaciente().getDNI()+"',IDEstado='"+turno.isEstado()+"' where IDTurno="+turno.getIdTurno();
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
	
	public ArrayList<Turno> ListarTodos() {
		cn = new Conexion();
		cn.Open();
		ArrayList<Turno> list = new ArrayList<Turno>();
		LocalDate fechaHoy = LocalDate.now();
		
			try
			{
				ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad =  especialidades.IDEspecialidad WHERE turnos.IDEstado = 0 AND turnos.Fecha >= '" + fechaHoy + "'");
				while(rs.next())
				{
					Especialidad especialidad = new Especialidad();
					especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
					especialidad.setDescripcion(rs.getString("especialidades.Nombre"));
					
					Medico medico = new Medico();
					medico.setDNI(rs.getInt("turnos.DNIMedico"));
					medico.setApellido(rs.getString("medicos.Apellido"));
					medico.setNombre(rs.getString("medicos.Nombres"));
					medico.setEspecialidad(especialidad);

					Turno turno = new Turno();
					turno.setMedico(medico);
					turno.setIdTurno(rs.getInt("turnos.IDTurno"));
					turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
					turno.setHora(rs.getInt("turnos.Hora"));
					
					list.add(turno);
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
	
	public ArrayList<Turno> ListarTurnosPorMedico(Medico medico) {
		
		cn = new Conexion();
		cn.Open();
		ArrayList<Turno> list = new ArrayList<Turno>();
		int dniMedico = medico.getDNI();
		LocalDate fechaHoy = LocalDate.now();
		
		try
		{
			
			ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, turnos.DNIPaciente, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad = especialidades.IDEspecialidad WHERE turnos.IDEstado = 0 AND medicos.DNI = " + dniMedico + " AND turnos.Fecha >= '" + fechaHoy + "'");
			
			while(rs.next())
			{
				Medico m = new Medico();
				Persona p = new Persona();
				Turno turno = new Turno();
				Especialidad especialidad = new Especialidad();
				
				especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
				especialidad.setDescripcion(rs.getString("especialidades.Nombre"));

				m.setDNI(dniMedico);
				m.setApellido(rs.getString("medicos.Apellido"));
				m.setNombre(rs.getString("medicos.Nombres"));
				m.setEspecialidad(especialidad);

				turno.setMedico(m);
				turno.setIdTurno(rs.getInt("turnos.IDTurno"));
				turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
				turno.setHora(rs.getInt("turnos.Hora"));	
				
				list.add(turno);	
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
	
	public ArrayList<Turno> ListarTurnosProximosPorMedico(Medico medico) {
		
		cn = new Conexion();
		cn.Open();
		ArrayList<Turno> list = new ArrayList<Turno>();
		int dniMedico = medico.getDNI();
		LocalDate fechaHoy = LocalDate.now();
		
		try
		{

			ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, turnos.DNIPaciente, pacientes.Nombres, pacientes.Apellido, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad = especialidades.IDEspecialidad INNER JOIN pacientes on turnos.DNIPaciente = pacientes.DNI WHERE turnos.IDEstado = 1 AND medicos.DNI = " + dniMedico + " AND turnos.Fecha >= '" + fechaHoy + "'");
			
			while(rs.next())
			{
				Medico m = new Medico();
				Persona p = new Persona();
				Turno turno = new Turno();
				Especialidad especialidad = new Especialidad();
				
				especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
				especialidad.setDescripcion(rs.getString("especialidades.Nombre"));

				m.setDNI(dniMedico);
				m.setApellido(rs.getString("medicos.Apellido"));
				m.setNombre(rs.getString("medicos.Nombres"));
				m.setEspecialidad(especialidad);
				p.setDNI(rs.getInt("turnos.DNIPaciente"));
				p.setApellido(rs.getString("pacientes.Apellido"));
				p.setNombre(rs.getString("pacientes.Nombres"));
				
				turno.setPaciente(p);
				turno.setMedico(m);
				turno.setIdTurno(rs.getInt("turnos.IDTurno"));
				turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
				turno.setHora(rs.getInt("turnos.Hora"));	
				
				list.add(turno);	
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
	
	public boolean ChequearFecha(LocalDate fecha, int dniMedico)
	{
		cn = new Conexion();
		cn.Open();	

		String query = "SELECT * FROM turnos where turnos.DNIMedico = "+ dniMedico +" and turnos.Fecha = '"+ fecha+"'";
		
		try
		 {
			ResultSet rs = cn.query(query);
			if(rs.next())
			{
				return true;
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
		return false;
	}
	
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i)
	{
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "insert into turnos (Fecha, Hora, DNIMedico, DNIPaciente, IDEstado, Observacion) values ('" + fecha + "'," + i + ","+ dniMedico + ", null, 0, null)";
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
	
	
	public boolean existeTurnoEnHorarioFecha(Turno turno) {
		cn = new Conexion();
		cn.Open();
		boolean existe = false;
		try
		{
      
			ResultSet rs= cn.query("SELECT * FROM turnos WHERE Fecha= '"+turno.getFecha()+"' AND Hora = "+turno.getHora()+" AND DNIPaciente = "+turno.getPaciente().getDNI()+" AND IDEstado = 1");
			if(rs.next())
			{
				existe = true;	
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

		return existe;
	}
	
	public ArrayList<Turno> ListarTurnosPorMedicoDiaActual(Medico medico) {
		cn = new Conexion();
		cn.Open();
		ArrayList<Turno> list = new ArrayList<Turno>();
		int dniMedico = medico.getDNI();
		LocalDate fechaHoy = LocalDate.now();
		try
		{
			
			ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, turnos.DNIPaciente, pacientes.Nombres, pacientes.Apellido, medicos.Nombres, medicos.Apellido FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN pacientes on turnos.DNIPaciente = pacientes.DNI WHERE turnos.IDEstado = 1 AND medicos.DNI = " + dniMedico + " AND turnos.Fecha = '" + fechaHoy + "'");

			while(rs.next())
			{
				Medico m = new Medico();
				Turno turno = new Turno();
				Persona p = new Persona();

				m.setDNI(dniMedico);
				m.setApellido(rs.getString("medicos.Apellido"));
				m.setNombre(rs.getString("medicos.Nombres"));
				p.setDNI(rs.getInt("turnos.DNIPaciente"));
				p.setApellido(rs.getString("pacientes.Apellido"));
				p.setNombre(rs.getString("pacientes.Nombres"));
				
				turno.setPaciente(p);
				turno.setMedico(m);
				turno.setIdTurno(rs.getInt("turnos.IDTurno"));
				turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
				turno.setHora(rs.getInt("turnos.Hora"));	
				
				turno.setHora(rs.getInt("turnos.Hora"));	
				
				list.add(turno);
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
	
	public boolean ActualizarEstadoTurnoAsistio(int idTurno, String observacion) 
	{
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE turnos SET IDEstado = 3, Observacion = '" + observacion + "' where IDTurno=" + idTurno;
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

	public boolean ActualizarEstadoTurnoAusente(int idTurno) 
	{
	
	boolean estado = true;

	cn = new Conexion();
	cn.Open();	

	String query = "UPDATE turnos SET IDEstado = 2 where IDTurno=" + idTurno;
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
	
	public ArrayList<Turno> ListarTurnosPorMedicoYFecha(Medico medico, LocalDate fechaDesde, LocalDate fechaHasta)
	{
		cn = new Conexion();
		cn.Open();
		
		ArrayList<Turno> list = new ArrayList<Turno>();
		int dniMedico = medico.getDNI();
		
		try
		{
			
			ResultSet rs= cn.query("SELECT turnos.IDTurno, turnos.DNIMedico, turnos.Fecha, turnos.Hora, turnos.DNIPaciente, pacientes.Nombres, pacientes.Apellido, medicos.Nombres, medicos.Apellido, turnos.IDEstado, turnos.Observacion FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI  INNER JOIN pacientes on turnos.DNIPaciente = pacientes.DNI AND medicos.DNI = "+ dniMedico +" AND turnos.Fecha >= '"+ fechaDesde +"' AND turnos.Fecha <= '"+ fechaHasta +"' and (turnos.IDEstado = 2 or turnos.IDEstado = 3)");

			while(rs.next())
			{
				Medico m = new Medico();
				Turno turno = new Turno();
				Persona p = new Persona();

				m.setDNI(dniMedico);
				m.setApellido(rs.getString("medicos.Apellido"));
				m.setNombre(rs.getString("medicos.Nombres"));
				p.setDNI(rs.getInt("turnos.DNIPaciente"));
				p.setApellido(rs.getString("pacientes.Apellido"));
				p.setNombre(rs.getString("pacientes.Nombres"));
				
				turno.setPaciente(p);
				turno.setMedico(m);
				turno.setIdTurno(rs.getInt("turnos.IDTurno"));
				String observacion = rs.getString("turnos.Observacion");
				
				if (observacion == null)
				{
					turno.setObservaciones("Sin observacion");
				} else {
					turno.setObservaciones(observacion);
				}
				
				turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
				turno.setHora(rs.getInt("turnos.Hora"));
				turno.setEstado(rs.getInt("turnos.IDEstado"));	
				turno.setHora(rs.getInt("turnos.Hora"));	
				
				list.add(turno);
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
	
	public boolean EliminarTurnosLibresPorMedico(int dniMedico)
	{
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "delete from turnos where turnos.DNIMedico = " + dniMedico + " and turnos.IDEstado = 0";
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
	
	public boolean EliminarTurnosLibresPorPaciente(int dniPaciente)
	{
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "delete from turnos where turnos.DNIPaciente = " + dniPaciente + " and turnos.IDEstado = 1";
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
	

	public ArrayList<Turno> ListarTurnosLibresPorMedico(Medico medico) {
		cn = new Conexion();
		cn.Open();
		ArrayList<Turno> list = new ArrayList<Turno>();
		int dniMedico = medico.getDNI();
		try
		{

			ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad = especialidades.IDEspecialidad WHERE turnos.IDEstado = 0 AND medicos.DNI = " + dniMedico);
			
			
			while(rs.next())
			{
				Medico m = new Medico();
				Turno turno = new Turno();
				Especialidad especialidad = new Especialidad();
				
				especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
				especialidad.setDescripcion(rs.getString("especialidades.Nombre"));

				m.setDNI(dniMedico);
				m.setApellido(rs.getString("medicos.Apellido"));
				m.setNombre(rs.getString("medicos.Nombres"));
				m.setEspecialidad(especialidad);
				
				turno.setMedico(m);
				turno.setIdTurno(rs.getInt("turnos.IDTurno"));
				turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
				turno.setHora(rs.getInt("turnos.Hora"));	
				
				list.add(turno);	
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
	public boolean eliminarTurnosxFecha(Medico medico, LocalDate fecha) {
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "delete from turnos where turnos.DNIMedico = " + medico.getDNI() + " and turnos.Fecha = '"+ fecha +"'  and turnos.IDEstado = 0";
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
	
	public int ContarTurnosLibres() {

		cn = new Conexion();
		cn.Open();
		int cantidad = 0;

		String query = "select count(IDTurno) as cantidad from turnos t where t.IDEstado = 0";

		try {
			
			ResultSet rs = cn.query(query);
			
			while (rs.next()) {
				
				cantidad = rs.getInt("cantidad");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			cn.close();
			
		}

		return cantidad;

	}
	
	
	public int ContarTurnosOcupados()
	{
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;

		String query = "select count(IDTurno) as cantidad from turnos t where t.IDEstado = 1";

		try {
			
			ResultSet rs = cn.query(query);
			
			while (rs.next()) {
				
				cantidad = rs.getInt("cantidad");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			cn.close();
			
		}

		return cantidad;
	}
	
	public int ContarTurnosPresentes()
	{
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;

		String query = "select count(IDTurno) as cantidad from turnos t where t.IDEstado = 3";

		try {
			
			ResultSet rs = cn.query(query);
			
			while (rs.next()) {
				
				cantidad = rs.getInt("cantidad");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			cn.close();
			
		}

		return cantidad;
	}
	
	public int ContarTurnosAusentes()
	{
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;

		String query = "select count(IDTurno) as cantidad from turnos t where t.IDEstado = 2";

		try {
			
			ResultSet rs = cn.query(query);
			
			while (rs.next()) {
				
				cantidad = rs.getInt("cantidad");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			cn.close();
			
		}

		return cantidad;

	}
	
	public int ContarTurnosAsignadosAMedico(int dni)
	{
		cn = new Conexion();
		cn.Open();
		LocalDate fechaHoy = LocalDate.now();
		int cantidad = 0;

		String query = "select count(IDTurno) as cantidad from turnos WHERE turnos.IDEstado = 1 AND turnos.DNIMedico = " + dni + " AND turnos.Fecha = '" + fechaHoy + "'";

		try {
			
			ResultSet rs = cn.query(query);
			
			while (rs.next()) {
				
				cantidad = rs.getInt("cantidad");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			cn.close();
			
		}

		return cantidad;
	}
}


