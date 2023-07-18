package datos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.UsuarioRegistrado;
import entidad.Persona;

public interface PacienteDao {
	
	public List<Persona> ListarTodos();
	public Persona ListarUno(int dni);
	public int ContarExtranjeros();
	public int ContarArg();
	public int ContarPacientes();
	public int ContarMayores();
	public int ContarMenores();
	public boolean InsertarPaciente(Persona paciente);
	public boolean EditarPaciente(Persona paciente);
	public boolean EliminarPaciente(int dni); //Eliminar logico.
	public boolean validarPacienteExistente(int dni);
}
