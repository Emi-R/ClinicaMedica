package negocioImpl;

import java.util.ArrayList;

import datos.LocalidadDao;
import datosImpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {
	
	public LocalidadDao ldao = new LocalidadDaoImpl();
	
	@Override
	public ArrayList<Localidad> obtenerTodos() {
		return ldao.obtenerTodos();
	}

	@Override
	public Localidad obtenerUno(int id) {
		return ldao.obtenerUno(id);
	}

}
