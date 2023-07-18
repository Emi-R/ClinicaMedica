package datos;

import java.util.ArrayList;
import entidad.Provincia;

public interface ProvinciaDao {
	
	public ArrayList<Provincia> obtenerTodos();
	public Provincia obtenerUno(int id);

}
