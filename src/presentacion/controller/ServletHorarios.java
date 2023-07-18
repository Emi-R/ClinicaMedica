package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Horario;
import entidad.Localidad;
import entidad.Medico;
import entidad.Provincia;
import negocio.DireccionNegocio;
import negocio.EspecialidadNegocio;
import negocio.HorarioNegocio;
import negocio.LocalidadNegocio;
import negocio.MedicoNegocio;
import negocio.ProvinciaNegocio;
import negocio.TurnoNegocio;
import negocioImpl.DireccionNegocioImpl;
import negocioImpl.EspecialidadNegocioImpl;
import negocioImpl.HorarioNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.TurnoNegocioImpl;

@WebServlet("/ServletHorarios")
public class ServletHorarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HorarioNegocio hNeg = new HorarioNegocioImpl();
	ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
	LocalidadNegocio locNeg = new LocalidadNegocioImpl();
	EspecialidadNegocio espNeg = new EspecialidadNegocioImpl();
	MedicoNegocio mNeg = new MedicoNegocioImpl();
	DireccionNegocio dmNeg = new DireccionNegocioImpl();
	TurnoNegocio tNeg = new TurnoNegocioImpl();
	
    public ServletHorarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificarHorario") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniMedico"));
			
			if(hNeg.ListarTodos(dni) !=null) 
			{
				ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
				request.setAttribute("listaHorarios", listaHorario);
				
				request.setAttribute("dniMedico", dni);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMHorarios.jsp");
				dispatcher.forward(request, response);
			}	
		}
		
		if(request.getParameter("btnEliminarH") != null)
		{
			int dni =Integer.parseInt(request.getParameter("dniMedico"));
			
			int idHorario = Integer.parseInt(request.getParameter("idHorario"));
			String dia = request.getParameter("Dia");

			boolean eliminarhorario = hNeg.EliminarHorario(idHorario); 

			ArrayList<Provincia> listaP = provNeg.obtenerTodos();
			request.setAttribute("listaProv", listaP);
			
			ArrayList<Localidad> listaL = locNeg.obtenerTodos();
			request.setAttribute("listaLoc", listaL);
			
			if(mNeg.ListarUno(dni) != null)
			{				
				Medico medico = new Medico();
				medico = mNeg.ListarUno(dni);
				tNeg.eliminarTurnosxDia(medico, dia); //elimino turnos libres del dia eliminado.
				request.setAttribute("verMedico", medico);
				request.setAttribute("dniMedico",dni);
				
				ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
				request.setAttribute("listaHorarios", listaHorario);
				
				request.setAttribute("eliminarHorario", eliminarhorario);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMMedicos.jsp");
				dispatcher.forward(request, response);
			}	
		}
		
		if(request.getParameter("btnAceptarH") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniMedico"));
			
			if ((request.getParameter("DiaNuevo") != "") && ((request.getParameter("txtDesdeNuevo") != "")) && (request.getParameter("txtHastaNuevo") != "") ) 
			{
				Horario hn = new Horario();
				hn.setDNIMedico(dni);
				hn.setDiaAtencion(request.getParameter("DiaNuevo"));
				hn.setHoraInicio(Integer.parseInt(request.getParameter("txtDesdeNuevo")));
				hn.setHoraFin(Integer.parseInt(request.getParameter("txtHastaNuevo")));
				hn.setEstado(1);
				
				boolean agregadonh = hNeg.InsertarHorario(hn, dni);
					

				ArrayList<Provincia> listaP = provNeg.obtenerTodos();
				request.setAttribute("listaProv", listaP);
				
				ArrayList<Localidad> listaL = locNeg.obtenerTodos();
				request.setAttribute("listaLoc", listaL);
			
				if(mNeg.ListarUno(dni) != null)
				{				
					Medico medico = new Medico();
					medico = mNeg.ListarUno(dni);
					request.setAttribute("verMedico", medico);
					request.setAttribute("dniMedico",dni);
					request.setAttribute("agregadonh",agregadonh);
					
					ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
					request.setAttribute("listaHorarios", listaHorario);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMMedicos.jsp");
					dispatcher.forward(request, response);
				}
			}
			else 
			{
				boolean error = true;
				
				if(hNeg.ListarTodos(dni) !=null) 
				{
					ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
					request.setAttribute("listaHorarios", listaHorario);
					
					request.setAttribute("dniMedico", dni);
					request.setAttribute("error", error);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMHorarios.jsp");
					dispatcher.forward(request, response);
				}	
			}
		}
		
		if(request.getParameter("btnModificarH") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniMedico"));
			int idHorario = Integer.parseInt(request.getParameter("idHorario"));
			
			Horario h = new Horario();
				h.setIdHorario(idHorario);
				h.setDiaAtencion(request.getParameter("Dia"));
				h.setHoraInicio(Integer.parseInt(request.getParameter("txtDesde")));
				h.setHoraFin(Integer.parseInt(request.getParameter("txtHasta")));
			
			String dia = (request.getParameter("Dia"));
			
			if (hNeg.buscarRepetido(idHorario, dia, dni)) 
			{
				Horario horarioViejo = hNeg.ListarUno(idHorario);
				String diaViejo = horarioViejo.getDiaAtencion();
				
				boolean hmod = hNeg.ModificarHorario(h);
				
				ArrayList<Provincia> listaP = provNeg.obtenerTodos();
				request.setAttribute("listaProv", listaP);
				
				ArrayList<Localidad> listaL = locNeg.obtenerTodos();
				request.setAttribute("listaLoc", listaL);
				
				if(mNeg.ListarUno(dni) != null)
				{				
					Medico medico = new Medico();
					medico = mNeg.ListarUno(dni);
					request.setAttribute("verMedico", medico);
					request.setAttribute("dniMedico",dni);
					tNeg.eliminarTurnosxDia(medico, diaViejo);
					
					ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
					request.setAttribute("listaHorarios", listaHorario);
					request.setAttribute("hmod", hmod);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMMedicos.jsp");
					dispatcher.forward(request, response);
					
				} 
			}	
			else 
			{
				boolean repetido = true;
				
				if(hNeg.ListarTodos(dni) !=null) 
				{
					ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
					request.setAttribute("listaHorarios", listaHorario);
					
					request.setAttribute("dniMedico", dni);
					request.setAttribute("repetido", repetido);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMHorarios.jsp");
					dispatcher.forward(request, response);
				}	
			}
		}
	}	
}

