package negocio;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadNegocio {
	
	public ArrayList<Localidad> obtenerTodos();
	public Localidad obtenerUno(int id);
}
