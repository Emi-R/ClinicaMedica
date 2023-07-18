package negocioImpl;

import datos.DireccionDao;
import datosImpl.DireccionDaoImpl;
import entidad.Direccion;
import negocio.DireccionNegocio;

public class DireccionNegocioImpl implements DireccionNegocio {
	
	private DireccionDao dpdao = new DireccionDaoImpl();

	@Override
	public Direccion ListarUno(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertarDP(int dni, Direccion direccion) {
		return dpdao.InsertarDP(dni, direccion);
	}
	
	public boolean InsertarDM(int dni, Direccion direccion) {
		return dpdao.InsertarDM(dni, direccion);
	}

	@Override
	public boolean EditarDP(int DNI, Direccion direccion) {
		return dpdao.EditarDP(DNI, direccion);
	}

	@Override
	public boolean EditarDM(int dni, Direccion direccion) {
		return dpdao.EditarDM(dni, direccion);
	}

}
