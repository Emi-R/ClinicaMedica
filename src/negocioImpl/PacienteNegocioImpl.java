package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import Exceptions.UsuarioRegistrado;
import datos.PacienteDao;
import datosImpl.PacienteDaoImpl;
import entidad.Persona;
import negocio.PacienteNegocio;


public class PacienteNegocioImpl implements PacienteNegocio {
	
	private PacienteDao pacienteDao = new PacienteDaoImpl();
	
	public PacienteNegocioImpl () {
		
	}

	public PacienteNegocioImpl(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	@Override
	public ArrayList<Persona> ListarTodos() {
		return (ArrayList<Persona>) pacienteDao.ListarTodos();
	}

	//GR Lista 1 paciente a partir de su DNI
	@Override
	public Persona ListarUno(int dni) {
		return pacienteDao.ListarUno(dni);
	}

	@Override
	public boolean InsertarPaciente(Persona paciente) {
		return pacienteDao.InsertarPaciente(paciente);
	}

	@Override
	public boolean EditarPaciente(Persona paciente) {
		return pacienteDao.EditarPaciente(paciente);
	}

	@Override
	public boolean EliminarPaciente(int dni) {
		return pacienteDao.EliminarPaciente(dni);
	}

	@Override
	public int contarExtranjeros() {
		return pacienteDao.ContarExtranjeros();
	}
	
	public int ContarPacientes()
	{
		return pacienteDao.ContarPacientes();
	}
	
	public int ContarArg()
	{
		return pacienteDao.ContarArg();
	}
	
	public int ContarMayores()
	{
		return pacienteDao.ContarMayores();
	}
	
	public int ContarMenores()
	{
		return pacienteDao.ContarMenores();
	}
	
	@Override
	public boolean validarPacienteExistente(int dni) throws UsuarioRegistrado {
		
		Boolean existe = false;

		existe = pacienteDao.validarPacienteExistente(dni);
		
		if(existe)
		{
			throw new UsuarioRegistrado();
		}
			
		return existe;
	}
}
