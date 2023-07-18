package datosImpl;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Exceptions.UsuarioRegistrado;
import datos.PacienteDao;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Persona;
import entidad.Provincia;

public class PacienteDaoImpl implements PacienteDao {
	
	private Conexion cn;
	
	public PacienteDaoImpl () {
		
	}

	//GR Obtener una lista con todos los pacientes
	@Override
	public List<Persona> ListarTodos() {
		cn = new Conexion();
		cn.Open();
			List<Persona> list = new ArrayList<Persona>();
			try
			{
				ResultSet rs= cn.query("SELECT DNI, Apellido, Nombres, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, Estado FROM pacientes WHERE Estado = 1");
				while(rs.next())
				{
					Persona paciente = new Persona();
					paciente.setDNI(rs.getInt("DNI"));
					paciente.setApellido(rs.getString("Apellido"));
					paciente.setNombre(rs.getString("Nombres"));
					paciente.setSexo(rs.getString("Sexo").charAt(0));
					paciente.setNacionalidad(rs.getString("Nacionalidad"));
					paciente.setFnac(LocalDate.parse(rs.getString("FechaNacimiento")));
					paciente.setMail(rs.getString("Mail"));
					paciente.setTelefono(rs.getString("Telefono"));
					paciente.setEstado(rs.getInt("Estado"));
					list.add(paciente);
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

	//GR Obtener un paciente a partir del DNI
	@Override
	public Persona ListarUno(int dni) {
		cn = new Conexion();
		cn.Open();
		Persona paciente = new Persona();
		Direccion direccion = new Direccion();
			try
			{
				ResultSet rs= cn.query("SELECT pacientes.DNI,pacientes.Apellido,pacientes.Nombres,pacientes.Sexo, pacientes.Nacionalidad, pacientes.FechaNacimiento, pacientes.Mail, pacientes.Telefono,pacientes.Estado,direccionespacientes.Calle, direccionespacientes.Numero, localidades.IDLocalidad, localidades.Nombre,provincias.IDProvincia, provincias.Nombre FROM pacientes INNER JOIN direccionespacientes ON pacientes.DNI = direccionespacientes.DNI INNER JOIN localidades ON direccionespacientes.IDLocalidad = localidades.IDLocalidad INNER JOIN provincias ON localidades.IDProvincia = provincias.IDProvincia where pacientes.Estado = 1 && pacientes.DNI="+dni);
				rs.next();
				{
					paciente.setDNI(rs.getInt("pacientes.DNI"));
					paciente.setApellido(rs.getString("pacientes.Apellido"));
					paciente.setNombre(rs.getString("pacientes.Nombres"));
					paciente.setSexo(rs.getString("pacientes.Sexo").charAt(0));
					paciente.setNacionalidad(rs.getString("pacientes.Nacionalidad"));
					paciente.setFnac(LocalDate.parse(rs.getString("pacientes.FechaNacimiento")));
					paciente.setMail(rs.getString("pacientes.Mail"));
					paciente.setTelefono(rs.getString("pacientes.Telefono"));
					paciente.setEstado(rs.getInt("pacientes.Estado"));
					
					direccion.setCalle(rs.getString("direccionespacientes.Calle"));
					direccion.setNumero(rs.getInt("direccionespacientes.Numero"));
					direccion.setLocalidad(new Localidad(rs.getInt("localidades.IDLocalidad"),rs.getString("localidades.Nombre")));
					direccion.setProvincia(new Provincia(rs.getInt("provincias.IDProvincia"),rs.getString("provincias.Nombre")));
					
					paciente.setDireccion(direccion);
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
			return paciente;
	}
	
	// ANDRE
	@Override
	public boolean InsertarPaciente(Persona paciente) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO pacientes (DNI,Apellido,Nombres,Sexo,FechaNacimiento,Nacionalidad,Mail,Telefono,Estado) VALUES ('"+paciente.getDNI()+"','"+paciente.getApellido()+"','"+paciente.getNombre()+"','"+paciente.getSexo()+"','"+paciente.getFnac()+"','"+paciente.getNacionalidad()+"','"+paciente.getMail()+"','"+paciente.getTelefono()+"','"+paciente.getEstado()+"')";			
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

	// ANDRE
	public boolean EditarPaciente(Persona paciente) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE pacientes SET Apellido='"+paciente.getApellido()+"',Nombres='"+paciente.getNombre()+"', Sexo='"+paciente.getSexo()+"', Nacionalidad='"+paciente.getNacionalidad()+"', FechaNacimiento='"+paciente.getFnac()+"', Mail='"+paciente.getMail()+"', Telefono='"+paciente.getTelefono()+"' where DNI="+paciente.getDNI();
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
	public boolean EliminarPaciente(int dni) {
		
		cn = new Conexion();
		cn.Open();
		
		boolean estado = false;
		
		try
		{
			String query = "UPDATE pacientes SET estado = 0 where DNI = " + dni;
			estado = cn.execute(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return estado;
	}

	@Override
	public int ContarExtranjeros() {
		
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;
		
			try
			{
				ResultSet rs= cn.query("select count(p.DNI) as cantidad from pacientes p where p.Nacionalidad not like 'Argentina' and p.Estado = 1");
				
				while(rs.next())
				{
				cantidad = rs.getInt("cantidad");
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

		return cantidad;
	}
	
	public int ContarPacientes() {
		
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;
		
			try
			{
				ResultSet rs= cn.query("select count(p.DNI) as cantidad from pacientes p where p.Estado = 1");
				
				while(rs.next())
				{
				cantidad = rs.getInt("cantidad");
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

		return cantidad;
	}
	
	public int ContarArg()
	{
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;
		
			try
			{
				ResultSet rs= cn.query("select count(p.DNI) as cantidad from pacientes p where p.Nacionalidad like 'Argentina' and p.Estado = 1");
				
				while(rs.next())
				{
				cantidad = rs.getInt("cantidad");
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

		return cantidad;
	}
	
	public int ContarMayores()
	{
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;
		
			try
			{
				ResultSet rs= cn.query("select count(p.DNI) as cantidad from pacientes p where (timestampdiff (year, p.FechaNacimiento, curdate())) > 18 and p.Estado = 1");
				
				while(rs.next())
				{
				cantidad = rs.getInt("cantidad");
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

		return cantidad;
	}
	
	public int ContarMenores()
	{
		cn = new Conexion();
		cn.Open();
		int cantidad = 0;
		
			try
			{
				ResultSet rs= cn.query("select count(p.DNI) as cantidad from pacientes p where (timestampdiff (year, p.FechaNacimiento, curdate())) < 18 and p.Estado = 1");
				
				while(rs.next())
				{
				cantidad = rs.getInt("cantidad");
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

		return cantidad;
	}
	
	public boolean validarPacienteExistente(int dni)
	{
		boolean existe = false;
		int cantDniBD;
		cn = new Conexion();
		cn.Open();
		
		try {
			
			ResultSet rs = cn.query("select count(DNI) as Cantidad from pacientes where DNI = " + dni); 
			rs.next();
			
			cantDniBD = rs.getInt("Cantidad");
			
			if (cantDniBD == 1) {
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
		
		return existe;

	}
}
