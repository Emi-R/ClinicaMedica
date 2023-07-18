package negocioImpl;

import java.util.ArrayList;

import datos.ReporteTurnosXEspDao;
import datosImpl.ReporteTurnosXEspDaoImpl;
import entidad.ReporteTurnosXEsp;
import negocio.TurnosXEspNegocio;
import negocio.TurnosXMedNegocio;

public class TurnosXEspNegocioImpl implements TurnosXEspNegocio{

	public ReporteTurnosXEspDao rdao = new ReporteTurnosXEspDaoImpl();
	
	@Override
	public ArrayList<ReporteTurnosXEsp> obtenerReporte() {
		return rdao.obtenerReporte();
	}

}
