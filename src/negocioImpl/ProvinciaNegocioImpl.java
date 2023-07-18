package negocioImpl;

import java.util.ArrayList;

import datos.ProvinciaDao;
import datosImpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {
	
	public ProvinciaDao pdao = new ProvinciaDaoImpl();

	@Override
	public ArrayList<Provincia> obtenerTodos() {
		return pdao.obtenerTodos();
	}

	@Override
	public Provincia obtenerUno(int id) {
		return pdao.obtenerUno(id);
	}
	
	

}
