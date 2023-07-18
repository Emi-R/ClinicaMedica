package presentacion.controller;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import Exceptions.DniInvalido;
import entidad.Usuario;
import negocio.MedicoNegocio;
import negocio.TurnoNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.TurnoNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UsuarioNegocio userNeg = new UsuarioNegocioImpl();
	MedicoNegocio mNeg = new MedicoNegocioImpl();
	TurnoNegocio tNeg = new TurnoNegocioImpl();
	
    public ServletUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param") != null) {
			
			Usuario user = (Usuario) request.getSession().getAttribute("usuario");

			if(user.getTipo().getIdTipoUsuario() == 0) {

		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalAdmin.jsp");
				dispatcher.forward(request, response);
			}
			else {
				Medico m = (Medico) request.getSession().getAttribute("medico");
				
				int cantTurnosAsig = tNeg.ContarTurnosAsignadosAMedico(m.getDNI());
				
				switch (cantTurnosAsig) {
				case 0:
					request.setAttribute("cantTurnosAsig0", cantTurnosAsig);
					break;
				case 1:
					request.setAttribute("cantTurnosAsig1", cantTurnosAsig);
					break;
				default:
					request.setAttribute("cantTurnosAsig", cantTurnosAsig);
					break;

				}
				
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalMedic.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnIngresar")!=null) {
			
			String pass = request.getParameter("txtContraseña");
			String dnistring = request.getParameter("txtDNI");
			
			if(dnistring.length() > 9) 
			{
				request.setAttribute("errorDni", true);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			int dni = Integer.parseInt(request.getParameter("txtDNI")); 
			
			try 
			{
				userNeg.validarDNI(dni); 
			} 
			catch (DniInvalido dniInv) 
			{
				
				System.out.println(dniInv.getMessage());

				request.setAttribute("errorDni", true);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
				dispatcher.forward(request, response);
				return;
				
			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "Ocurrio un error");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
				return;
			} 

			Usuario user;
			
			user = (Usuario) userNeg.obtenerUsuario(pass, dni);
			
			if(user != null) 
			{	
				if (user.getEstado() == 1) 
				{
					if(user.getTipo().getIdTipoUsuario() == 0) 
					{
						request.getSession().setAttribute("usuario", user);
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalAdmin.jsp");
						dispatcher.forward(request, response);
					}
					else 
					{
						
						Medico m = mNeg.ListarUno(dni);
						
						request.getSession().setAttribute("usuario", user);
						request.getSession().setAttribute("medico", m);
						
						int cantTurnosAsig = tNeg.ContarTurnosAsignadosAMedico(m.getDNI());
						
						switch (cantTurnosAsig) {
						case 0:
							request.setAttribute("cantTurnosAsig0", cantTurnosAsig);
							break;
						case 1:
							request.setAttribute("cantTurnosAsig1", cantTurnosAsig);
							break;
						default:
							request.setAttribute("cantTurnosAsig", cantTurnosAsig);
							break;
						}
						
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalMedic.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if (user.getEstado() == 0) {
					
						request.setAttribute("errorDadoDeBaja", true);

				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
						dispatcher.forward(request, response);	
						return;
				}			
			} 
			else
			{
				request.setAttribute("errorCredenciales", true);
				
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		
		if(request.getParameter("btnSalir") != null)
		{
			request.getSession().removeAttribute("usuario");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
			dispatcher.forward(request, response);
		}	
	}
}
