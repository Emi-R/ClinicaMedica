package datos;

import java.util.ArrayList;

import entidad.Horario;

public interface HorarioDao {

	public boolean InsertarHorario (Horario horario, int dni);
	public ArrayList<Horario> ListarTodos(int dni);
	public Horario ListarUno(int idHorario);
	public boolean ModificarHorario (Horario horario);
	public boolean EliminarHorario (int idHorario);
	public Horario buscarHorario(int dniMedico, String dia);
	
}
