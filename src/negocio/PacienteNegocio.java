package negocio;

import java.util.ArrayList;
import java.util.List;

import Exceptions.UsuarioRegistrado;
import entidad.Persona;

public interface PacienteNegocio {
	
	public ArrayList<Persona> ListarTodos();
	public Persona ListarUno(int dni);
	public int ContarPacientes();
	public int contarExtranjeros();
	public int ContarArg();
	public int ContarMayores();
	public int ContarMenores();
	public boolean InsertarPaciente(Persona paciente);
	public boolean EditarPaciente(Persona paciente);
	public boolean EliminarPaciente(int dni); //Eliminar logico.
	public boolean validarPacienteExistente(int dni) throws UsuarioRegistrado;
}
