package datosImpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Exceptions.UsuarioRegistrado;
import datos.MedicoDao;
import entidad.Direccion;
import entidad.Especialidad;
import entidad.Localidad;
import entidad.Medico;
import entidad.Persona;
import entidad.Provincia;

public class MedicoDaoImpl implements MedicoDao {
	
	private Conexion cn;
	
	public MedicoDaoImpl () {
		
	}

	public ArrayList<Medico> ListarTodos() {
		cn = new Conexion();
		cn.Open();
			
		ArrayList<Medico> list = new ArrayList<Medico>();
			
		try
			{
				ResultSet rs= cn.query("SELECT m.DNI, m.Apellido, m.Nombres, m.Sexo, m.Mail, e.IDEspecialidad, e.Nombre, m.Estado FROM medicos m INNER JOIN especialidades e ON m.IDEspecialidad = e.IDEspecialidad WHERE m.Estado = 1");
				while(rs.next())
				{
					Medico medico = new Medico();
					medico.setDNI(rs.getInt("DNI"));
					medico.setApellido(rs.getString("Apellido"));
					medico.setNombre(rs.getString("Nombres"));
					medico.setSexo(rs.getString("Sexo").charAt(0));
					medico.setMail(rs.getString("Mail"));
						Especialidad esp = new Especialidad();
						esp.setIdEspecialidad(rs.getInt("IDEspecialidad"));
						esp.setDescripcion(rs.getString("Nombre"));
					medico.setEspecialidad(esp);
					medico.setEstado(rs.getInt("Estado"));
					list.add(medico);
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
	public Medico ListarUno(int dni) {
		
		cn = new Conexion();
		cn.Open();
		
		Medico medico = new Medico();
		Direccion direccion = new Direccion();
		Especialidad esp = new Especialidad();
			
		try
			{
				ResultSet rs= cn.query("SELECT m.DNI,m.Apellido,m.Nombres,m.Sexo, m.FechaNacimiento,m.Nacionalidad,m.Mail, m.Telefono,e.Nombre, m.Estado,dm.Calle, dm.Numero, l.IDLocalidad, l.Nombre,p.IDProvincia, p.Nombre, e.IDEspecialidad, e.Nombre FROM medicos m INNER JOIN direccionesmedicos dm ON m.DNI = dm.DNI INNER JOIN localidades l ON dm.IDLocalidad = l.IDLocalidad INNER JOIN provincias p ON l.IDProvincia = p.IDProvincia INNER JOIN especialidades e ON e.IDEspecialidad = m.IDEspecialidad where m.Estado = 1 && m.DNI="+dni);
				rs.next();
				{
					medico.setDNI(rs.getInt("m.DNI"));
					medico.setApellido(rs.getString("m.Apellido"));
					medico.setNombre(rs.getString("m.Nombres"));
					medico.setSexo(rs.getString("m.Sexo").charAt(0));
					medico.setFnac(LocalDate.parse(rs.getString("m.FechaNacimiento")));
					medico.setNacionalidad(rs.getString("m.Nacionalidad"));
					medico.setMail(rs.getString("m.Mail"));
					medico.setTelefono(rs.getString("m.Telefono"));
					medico.setEstado(rs.getInt("m.Estado"));
					
					direccion.setCalle(rs.getString("dm.Calle"));
					direccion.setNumero(rs.getInt("dm.Numero"));
					direccion.setLocalidad(new Localidad(rs.getInt("l.IDLocalidad"),rs.getString("l.Nombre")));
					direccion.setProvincia(new Provincia(rs.getInt("p.IDProvincia"),rs.getString("p.Nombre")));
					
					esp.setIdEspecialidad(rs.getInt("e.IDEspecialidad"));
					esp.setDescripcion(rs.getString("e.Nombre"));
					
					medico.setDireccion(direccion);
					medico.setEspecialidad(esp);
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
			return medico;
	}

	@Override
	public boolean InsertarMedico(Medico medico) {
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	
		String query = "Insert into medicos (DNI, Apellido, Nombres, Sexo, FechaNacimiento, Nacionalidad, Mail, Telefono, IDEspecialidad, Estado) VALUES ('"+medico.getDNI()+"','"+medico.getApellido()+"','"+medico.getNombre()+"','"+medico.getSexo()+"','"+medico.getFnac()+"','"+medico.getNacionalidad()+"','"+medico.getMail()+"','"+medico.getTelefono()+"','"+medico.getEspecialidad().getIdEspecialidad()+"','"+medico.getEstado()+"')";	
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
	public boolean EditarMedico(Medico medico) {
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE medicos SET Apellido='"+medico.getApellido()+"',Nombres='"+medico.getNombre()+"', Sexo='"+medico.getSexo()+"', FechaNacimiento='"+medico.getFnac()+"', Nacionalidad='"+medico.getNacionalidad()+"', Mail='"+medico.getMail()+"', Telefono='"+medico.getTelefono()+"', IDEspecialidad= '"+medico.getEspecialidad().getIdEspecialidad()+"' where DNI="+medico.getDNI();
		
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
	public boolean EliminarMedico(int dni) {
		cn = new Conexion();
		cn.Open();
		
		boolean estado = false;
		
		try
		{
			String query = "UPDATE medicos SET estado = 0 where DNI = " + dni;
			estado = cn.execute(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return estado;
	}

	public boolean validarMedicoExistente(int dni){
		
		boolean existe = false;
		int cantDniBD;
		cn = new Conexion();
		cn.Open();
		
		try {
			ResultSet rs = cn.query("select count(DNI) as Cantidad from medicos where DNI = '" + dni + "'"); 
			rs.next();
			
			cantDniBD = rs.getInt("Cantidad");
			
			if (cantDniBD == 1)
				existe = true; 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			cn.close();
		}
		
		return existe;
	}

}
