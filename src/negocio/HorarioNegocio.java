package negocio;

import java.util.ArrayList;

import entidad.Horario;

public interface HorarioNegocio {
	
	public boolean InsertarHorario (Horario horario, int dni);
	public ArrayList<Horario> ListarTodos(int dni);
	public boolean ModificarHorario (Horario horario);
	public boolean EliminarHorario (int idHorario);
	public Horario ListarUno(int idHorario);
	public Horario buscarHorario(int dniMedico, String dia);
	public boolean buscarRepetido (int idHorario, String dia, int dni);
}
