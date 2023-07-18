package negocio;

import java.util.ArrayList;

import entidad.Especialidad;

public interface EspecialidadNegocio {
	
	public ArrayList<Especialidad> obtenerTodos();
	public Especialidad obtenerUno(int id);

}
