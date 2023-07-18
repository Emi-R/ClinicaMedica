package datos;

import java.util.ArrayList;
import java.util.List;

import Exceptions.UsuarioRegistrado;
import entidad.Medico;

public interface MedicoDao {
	
	public ArrayList<Medico> ListarTodos();
	public Medico ListarUno(int dni);
	public boolean InsertarMedico(Medico medico);
	public boolean EditarMedico(Medico medico);
	public boolean EliminarMedico(int dni); //Eliminar logico.
	public boolean validarMedicoExistente(int dni);
}
