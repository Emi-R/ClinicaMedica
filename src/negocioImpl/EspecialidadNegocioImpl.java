package negocioImpl;

import java.util.ArrayList;

import datos.EspecialidadDao;
import datosImpl.EspecialidadDaoImpl;
import entidad.Especialidad;
import negocio.EspecialidadNegocio;

public class EspecialidadNegocioImpl implements EspecialidadNegocio {
	
	public EspecialidadDao edao = new EspecialidadDaoImpl();
	
	@Override
	public ArrayList<Especialidad> obtenerTodos() {
		return edao.obtenerTodos();
	}

	@Override
	public Especialidad obtenerUno(int id) {
		return edao.obtenerUno(id);
	}

}
