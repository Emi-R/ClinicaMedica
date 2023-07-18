package datos;

import java.util.ArrayList;

import entidad.Especialidad;

public interface EspecialidadDao {
	
	public ArrayList<Especialidad> obtenerTodos();
	public Especialidad obtenerUno(int id);

}
