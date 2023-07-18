package negocio;

import java.util.ArrayList;

import entidad.Provincia;

public interface ProvinciaNegocio {
	
	public ArrayList<Provincia> obtenerTodos();
	public Provincia obtenerUno(int id);

}
