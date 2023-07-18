package negocioImpl;

import java.util.ArrayList;

import datos.ReporteTurnosXEspDao;
import datos.ReporteTurnosXMedDao;
import datosImpl.ReporteTurnosXEspDaoImpl;
import datosImpl.ReporteTurnosXMedDaoImpl;
import entidad.ReporteTurnosXMed;
import negocio.TurnosXMedNegocio;

public class TurnosXMedNegocioImpl implements TurnosXMedNegocio {

	public ReporteTurnosXMedDao rdao = new ReporteTurnosXMedDaoImpl();
	
	public ArrayList<ReporteTurnosXMed> obtenerReporte() {

		return rdao.obtenerReporte();
	}

}
