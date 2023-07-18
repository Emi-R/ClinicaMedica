package datos;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadDao {
	
	public ArrayList<Localidad> obtenerTodos();
	public Localidad obtenerUno(int id);
}
