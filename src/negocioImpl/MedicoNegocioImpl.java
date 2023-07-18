package negocioImpl;

import java.util.ArrayList;

import Exceptions.UsuarioRegistrado;
import datos.MedicoDao;
import datos.PacienteDao;
import datosImpl.MedicoDaoImpl;
import entidad.Medico;
import entidad.Persona;
import negocio.MedicoNegocio;

public class MedicoNegocioImpl implements MedicoNegocio {
	
	private MedicoDao mdao = new MedicoDaoImpl();
	
	public MedicoNegocioImpl () {
	
	}

	@Override
	public ArrayList<Medico> ListarTodos() {
		return (ArrayList<Medico>) mdao.ListarTodos();
	}

	@Override
	public Medico ListarUno(int dni) {
		return mdao.ListarUno(dni);
	}

	@Override
	public boolean InsertarMedico(Medico medico) {
		return mdao.InsertarMedico(medico);
	}

	@Override
	public boolean EditarMedico(Medico medico) {
		return mdao.EditarMedico(medico);
	}

	@Override
	public boolean EliminarMedico(int dni) {
		return mdao.EliminarMedico(dni);
	}

	@Override
	public boolean validarMedicoExistente(int dni) throws UsuarioRegistrado {
		Boolean existe = false;

		existe = mdao.validarMedicoExistente(dni);
		
		if(existe)
		{
			throw new UsuarioRegistrado();
		}
			
		return existe;
	}


}
