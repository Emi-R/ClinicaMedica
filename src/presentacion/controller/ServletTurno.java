package presentacion.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


import entidad.Direccion;
import entidad.Horario;
import entidad.Localidad;
import entidad.Medico;
import entidad.Persona;
import entidad.Provincia;
import entidad.Turno;
import entidad.Usuario;
import negocio.DireccionNegocio;
import negocio.HorarioNegocio;
import negocio.LocalidadNegocio;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;
import negocio.ProvinciaNegocio;
import negocio.TurnoNegocio;
import negocioImpl.DireccionNegocioImpl;
import negocioImpl.HorarioNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.TurnoNegocioImpl;

@WebServlet("/ServletTurno")
public class ServletTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	TurnoNegocio tneg = new TurnoNegocioImpl();
	MedicoNegocio mneg = new MedicoNegocioImpl();
	HorarioNegocio hNeg = new HorarioNegocioImpl();
	PacienteNegocio pneg = new PacienteNegocioImpl();
	ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
	LocalidadNegocio locNeg = new LocalidadNegocioImpl();
	DireccionNegocio dpNeg = new DireccionNegocioImpl();

       
    public ServletTurno() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Param")!=null)
		{
			String param = request.getParameter("Param").toString();
			
			switch(param){
			
			case "list":
			{
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
				dispatcher.forward(request, response);
				break;
			}
			
			case "listarM":
			{
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
				dispatcher.forward(request, response);
				break;
			}
			case "listarTurnos":
			{
				Usuario u = (Usuario) request.getSession().getAttribute("usuario");
				Medico m = mneg.ListarUno(u.getDNI());
				
				ArrayList<Turno> listaturnos = tneg.ListarTurnosProximosPorMedico(m);
				request.setAttribute("listaTurnos", listaturnos);
				
				boolean turnosProximos = true;
				request.setAttribute("turnosProximos", turnosProximos);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
				dispatcher.forward(request, response);
				break;
			}
			case "listarTurnosDiaActual":
			{
				Usuario u = (Usuario) request.getSession().getAttribute("usuario");
				Medico m = mneg.ListarUno(u.getDNI());
				
				ArrayList<Turno> listaturnos = tneg.ListarTurnosPorMedicoDiaActual(m);
				request.setAttribute("listaTurnos", listaturnos);
				
				boolean turnosDiaActual = true;
				request.setAttribute("turnosDiaActual", turnosDiaActual);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
				dispatcher.forward(request, response);
				break;
			}
			
			default:
				break;
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnAsignar")!=null) {
			
			Persona paciente = new Persona();
			String mensajeDeActualizacion = "";
			
			if(request.getParameter("dni") == null || request.getParameter("dni") == "") 
			{
				mensajeDeActualizacion = "Por favor, ingrese un DNI.";
				
				//Carga de listas predeterminadas
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				
				request.setAttribute("mensajeDeActualizacionDeTurno", mensajeDeActualizacion);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
		    	dispatcher.forward(request, response);
			}
			else 
			{
				paciente = pneg.ListarUno((Integer.parseInt(request.getParameter("dni"))));
				if(paciente.getDNI() == 0) 
				{
					System.out.println("Paciente no existe");
					//Carga de listas predeterminadas
					ArrayList<Medico> listaMedicos = mneg.ListarTodos();
					request.setAttribute("listaMedicos", listaMedicos);
					ArrayList<Turno> lista = tneg.ListarTodos();
					request.setAttribute("listaTurnosPorAsignar", lista);
					
					//Paciente no existe
					request.setAttribute("pacienteNoExiste", true);
					request.setAttribute("dniACrear", Integer.parseInt(request.getParameter("dni")));
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
					dispatcher.forward(request, response);
				}
				else 
				{
					paciente.setDNI((Integer.parseInt(request.getParameter("dni"))));
					
					Turno t = new Turno();
					t.setIdTurno((Integer.parseInt(request.getParameter("idTurno"))));
					t.setFecha(LocalDate.parse(request.getParameter("fechaTurno")));
					t.setHora(Integer.parseInt(request.getParameter("horaTurno")));			
					t.setPaciente(paciente);
					
					if(tneg.existeTurnoEnHorarioFecha(t) == true) 
					{
						//Carga de listas predeterminadas
						ArrayList<Medico> listaMedicos = mneg.ListarTodos();
						request.setAttribute("listaMedicos", listaMedicos);
						ArrayList<Turno> lista = tneg.ListarTodos();
						request.setAttribute("listaTurnosPorAsignar", lista);
						
						//Mensaje de error
						mensajeDeActualizacion = "El paciente ya tiene un turno asginado para esa fecha y hora.";
						request.setAttribute("mensajeDeActualizacionDeTurno", mensajeDeActualizacion);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
						dispatcher.forward(request, response);
						
					}
					else 
					{
						t.setEstado(1);
						
						boolean estado = tneg.ActualizarTurno(t);
						
						
						if(estado == true) {
							mensajeDeActualizacion = "Se asigno el paciente al turno exitosamente.";
						}
						else {
							mensajeDeActualizacion = "No se pudo asignar el turno."+"\n"+"Verifique que el DNI ingresado sea valido.";
						}
						
						//Carga de listas predeterminadas
						ArrayList<Medico> listaMedicos = mneg.ListarTodos();
						request.setAttribute("listaMedicos", listaMedicos);
						ArrayList<Turno> lista = tneg.ListarTodos();
						request.setAttribute("listaTurnosPorAsignar", lista);
						
						request.setAttribute("mensajeDeActualizacionDeTurno", mensajeDeActualizacion);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
						dispatcher.forward(request, response);							
					}
					
				}
				
			}
			
		}
		
		if(request.getParameter("btnFilter")!=null) 
		{
			Medico m = new Medico();
			m.setDNI(Integer.parseInt(request.getParameter("Medicos")));
			
			request.setAttribute("medicoSeleccionado", m);
			
			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			
			if(Integer.parseInt(request.getParameter("Medicos")) == 0)
			{
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
				dispatcher.forward(request, response);
			}
			
			ArrayList<Turno> listaPorMedico = tneg.ListaTurnosPorMedico(m);
			request.setAttribute("listaTurnosPorMedico", listaPorMedico);				
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("deleteFilters")!=null) 
		{
			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			
			ArrayList<Turno> lista = tneg.ListarTodos();
			request.setAttribute("listaTurnosPorAsignar", lista);
			
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
	    	dispatcher.forward(request, response);
		}
		
		// LOGICA PARA CREAR TURNOS
		
		//Buscar datos del m�dico seleccionado
		if(request.getParameter("btnBuscar")!= null) 
		{
			int dni = Integer.parseInt(request.getParameter("Medicos"));
			request.getSession().setAttribute("dniMedico", dni);

			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			
			if(mneg.ListarUno(dni) != null)
			{				
				Medico medico = new Medico();
				medico = mneg.ListarUno(dni);
				request.setAttribute("verDatos", medico);
				
				ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
				request.setAttribute("listaHorarios", listaHorario);
				
				boolean buscar = true;
				request.setAttribute("buscar", buscar);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
				dispatcher.forward(request, response);
			}
		}

		if(request.getParameter("btnAceptar") != null) 
		{
			int dniMedico = (int) request.getSession().getAttribute("dniMedico");
			
			Medico m = mneg.ListarUno(dniMedico);
			int cont = 0;
			String dia = request.getParameter("DiaAtencion");
			LocalDate fecha = LocalDate.parse(request.getParameter("FechaTurno"));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("es"));
	        String dayOfWeek = fecha.format(formatter);

	        if (dayOfWeek.equalsIgnoreCase(dia)) 
	        {
	        	if(!tneg.chequearFecha(fecha, dniMedico))
	        	{
	        		Horario h = hNeg.buscarHorario(dniMedico, dia);
	    			
	    			for(int i = h.getHoraInicio(); i < h.getHoraFin();i++)
	    			{
	    				if(!tneg.insertarTurno(dniMedico, fecha, i))
	    				{
	    					boolean error = true;
	    					request.setAttribute("error", error);

	    				}
	    				cont++;
	    			}

	    			boolean exito = true;
					  request.setAttribute("exito", exito);
	    			
					  request.setAttribute("cantTurnos", cont);
					  request.setAttribute("apellidoMedico", m.getApellido());
					  request.setAttribute("nombreMedico", m.getNombre());
					  request.setAttribute("fecha", fecha);
					
					  ArrayList<Medico> listaMedicos = mneg.ListarTodos();
					  request.setAttribute("listaMedicos", listaMedicos);
					
					  RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
					  dispatcher.forward(request, response);
	        	}
	        	else
	        	{
	        		ArrayList<Medico> listaMedicos = mneg.ListarTodos();
					request.setAttribute("listaMedicos", listaMedicos);
					
	        		boolean errorFechaOcupada = true;
					request.setAttribute("errorFechaOcupada", errorFechaOcupada);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
					dispatcher.forward(request, response);
	        	}
	        
	        }
	        else
        	{
	        	ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				    request.setAttribute("listaMedicos", listaMedicos);
				
        		boolean errorDia = true;
				    request.setAttribute("errorDia", errorDia);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
				    dispatcher.forward(request, response);
        	}
		}
		
		if(request.getParameter("btnAsistio") != null) {
			
			int idTurno = Integer.parseInt(request.getParameter("idTurno"));
			String observacion = request.getParameter("txtObservacion");
			
			if (observacion.trim().isEmpty()) 
			{
				
				Usuario u = (Usuario) request.getSession().getAttribute("usuario");
				Medico m = mneg.ListarUno(u.getDNI());

				ArrayList<Turno> listaturnos = tneg.ListarTurnosProximosPorMedico(m);
				request.setAttribute("listaTurnos", listaturnos);
				
				boolean turnosProximos = true;
				request.setAttribute("turnosProximos", turnosProximos);

				boolean error = true;
				request.setAttribute("error", error);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
				dispatcher.forward(request, response);
				
			} else {

				tneg.ActualizarEstadoTurnoAsistio(idTurno, observacion);

				Usuario u = (Usuario) request.getSession().getAttribute("usuario");
				Medico m = mneg.ListarUno(u.getDNI());

				ArrayList<Turno> listaturnos = tneg.ListarTurnosProximosPorMedico(m);
				request.setAttribute("listaTurnos", listaturnos);
				
				boolean turnosProximos = true;
				request.setAttribute("turnosProximos", turnosProximos);

				boolean exito = true;
				request.setAttribute("actualizado", exito);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
		if(request.getParameter("btnAusente") != null) {
			
			int idTurno = Integer.parseInt(request.getParameter("idTurno"));

			tneg.ActualizarEstadoTurnoAusente(idTurno);
			
			Usuario u = (Usuario) request.getSession().getAttribute("usuario");
			Medico m = mneg.ListarUno(u.getDNI());
			
			ArrayList<Turno> listaturnos = tneg.ListarTurnosProximosPorMedico(m);
			request.setAttribute("listaTurnos", listaturnos);
			
			boolean turnosProximos = true;
			request.setAttribute("turnosProximos", turnosProximos);
			
			boolean exito = true;
			request.setAttribute("actualizado", exito);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("btnVerInfoPacienteMedico") != null) {
			
			int dni =Integer.parseInt(request.getParameter("dniPaciente"));
			
			ArrayList<Provincia> listaP = provNeg.obtenerTodos();
			request.setAttribute("listaProv", listaP);
			
			ArrayList<Localidad> listaL = locNeg.obtenerTodos();
			request.setAttribute("listaLoc", listaL);
			
			if(pneg.ListarUno(dni) != null)
			{				
				Persona paciente = new Persona();
				paciente = pneg.ListarUno(dni);	
				request.setAttribute("verPaciente", paciente);
				request.setAttribute("dniPaciente",dni);
				boolean verPacienteComoMedico = true;
				request.setAttribute("verPacienteComoMedico", verPacienteComoMedico);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMPacientes.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		if(request.getParameter("btnBuscarxFecha") != null) {
			
			Usuario u = (Usuario) request.getSession().getAttribute("usuario");
			Medico m = mneg.ListarUno(u.getDNI());

			LocalDate fechaDesde = LocalDate.parse(request.getParameter("fechaDesde"));
			LocalDate fechaHasta = LocalDate.parse(request.getParameter("fechaHasta"));
			
			if(fechaDesde.isAfter(fechaHasta)) {
				Boolean errorFiltroFecha = true;
				request.setAttribute("errorFiltroFecha", errorFiltroFecha);
				
				ArrayList<Turno> listaturnos = tneg.ListarTurnosProximosPorMedico(m);
				request.setAttribute("listaTurnos", listaturnos);
				
				boolean turnosProximos = true;
				request.setAttribute("turnosProximos", turnosProximos);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
				dispatcher.forward(request, response);
			} else {
				
				ArrayList<Turno> listaturnos = tneg.ListarTurnosProximosPorMedico(m);
				request.setAttribute("listaTurnos", listaturnos);
				
				boolean turnosProximos = true;
				request.setAttribute("turnosProximos", turnosProximos);
				
				ArrayList<Turno> listaTurnosFiltrada = tneg.ListarTurnosPorMedicoYFecha(m, fechaDesde, fechaHasta);
				request.setAttribute("listaTurnosFiltrada", listaTurnosFiltrada);
				
				Boolean filtrando = true;
				request.setAttribute("filtrando", filtrando);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
				dispatcher.forward(request, response);
			}

		}


	}

}