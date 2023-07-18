package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PacienteNegocio;
import negocio.TurnoNegocio;
import negocio.TurnosXEspNegocio;
import negocio.TurnosXMedNegocio;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.TurnoNegocioImpl;
import negocioImpl.TurnosXEspNegocioImpl;
import negocioImpl.TurnosXMedNegocioImpl;
import entidad.ReporteTurnosXEsp;
import entidad.ReporteTurnosXMed;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TurnosXEspNegocio rneg = new TurnosXEspNegocioImpl();
	TurnosXMedNegocio rneg2 = new TurnosXMedNegocioImpl();
	PacienteNegocio pNeg = new PacienteNegocioImpl();
	TurnoNegocio tNeg = new TurnoNegocioImpl();
	

    public ServletReportes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!=null)
		{
			ArrayList <ReporteTurnosXEsp> reporteEsp = rneg.obtenerReporte();
			request.setAttribute("reporteTurnosXEsp", reporteEsp);
			
			ArrayList <ReporteTurnosXMed> reporteMed = rneg2.obtenerReporte();
			request.setAttribute("reporteTurnosXMed", reporteMed);
			
			int cantidadPacientes = pNeg.ContarPacientes();
			request.setAttribute("cantTotal", cantidadPacientes);
			
			int cantidadArg = pNeg.ContarArg();
			request.setAttribute("cantArg", cantidadArg);
			
			int cantidadExtranjeros = pNeg.contarExtranjeros();
			request.setAttribute("cantExtranjeros", cantidadExtranjeros);
			
			int cantidadMayores = pNeg.ContarMayores();
			request.setAttribute("cantMayores", cantidadMayores);
			
			int cantidadMenores = pNeg.ContarMenores();
			request.setAttribute("cantMenores", cantidadMenores);
			
			int cantidadLibres = tNeg.ContarTurnosLibres();
			request.setAttribute("cantLibres", cantidadLibres);
			
			int cantidadOcupados = tNeg.ContarTurnosOcupados();
			request.setAttribute("cantOcupados", cantidadOcupados);
			
			int cantidadPresentes = tNeg.ContarTurnosPresentes();
			request.setAttribute("cantPresentes", cantidadPresentes);
			
			int cantidadAusentes = tNeg.ContarTurnosAusentes();
			request.setAttribute("cantAusentes", cantidadAusentes);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reportes.jsp");
			dispatcher.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
