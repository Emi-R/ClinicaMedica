<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@page import="entidad.Usuario"%>
	<%@page import="entidad.Medico"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagina Principal del Medico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <style>
        .btn-blue {
            background-color: #007bff;
            color: #fff;
        }

    </style>
</head>
<body>
<!-- Header -->

				<% 
				    Usuario user = (Usuario) session.getAttribute("usuario"); 
				    
				    if (user == null) 
				    {
				    	String mensajeUsuarioNull = "Usuario no registrado";
						request.getSession().setAttribute("errorMessage", mensajeUsuarioNull);
				        response.sendRedirect("Error.jsp"); 
				    } 
				    else if (user.getTipo().getIdTipoUsuario() == 0)
				    {
				    	String mensajeAccesoNoAutorizado = "Usuario sin permisos adecuados";
						request.getSession().setAttribute("errorMessage", mensajeAccesoNoAutorizado);
						
			            response.sendRedirect("Error.jsp");
				    }
				%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <div class="navbar-brand mb-0 h1">
                            <img src="https://cdn-icons-png.flaticon.com/512/5394/5394174.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Menu Principal
                        </div>
                    </li>
    					<div class="container">
    					<div class="navbar-brand text-center" class="d-inline-block align-text-top" href="#"> Clinica Medica SA</div>
  					</div>
                </ul>

			<% Usuario a = (Usuario) session.getAttribute("usuario");
			Medico medico = (Medico) session.getAttribute("medico");
			String mensajeTurnos;
			
			if (request.getAttribute("cantTurnosAsig") != null)
			{
				int cantTurnosAsig = (int) request.getAttribute("cantTurnosAsig");
				
				mensajeTurnos = "Tenes "+ cantTurnosAsig + " turnos asignados para el dia de hoy";
				
			} else if (request.getAttribute("cantTurnosAsig1") != null)
			{
				int cantTurnosAsig = (int) request.getAttribute("cantTurnosAsig1");
				mensajeTurnos = "Tenes "+ cantTurnosAsig + " turno asignado para el dia de hoy";
			}
			else
			{
				int cantTurnosAsig0 = (int) request.getAttribute("cantTurnosAsig0");
				mensajeTurnos = "No tenes turnos asignados para el dia de hoy";
			}
			%>
			<div class="row">
			<div class="col-6">
			<ul class="text-end navbar-brand mb-0 fs-6" style=""> <b>Medico:</b> <%= medico.getApellido()%>, <%= medico.getNombre()%> </ul>
			<ul class="text-end navbar-brand mb-0 fs-6" style=""> <b> DNI:</b> <%= a.getDNI() %> </ul>
			</div>
			</div>
                <form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name="btnSalir" value="Salir"></input>
			</form>
            </div>
        </div>
    </nav>

	<br>

    <div class="container">
        <br>
        <div class="row">
            <div class="col"></div>
            <div class="col text-center">
                <h2>Bienvenido <%= medico.getNombre()%> <%= medico.getApellido()%> </h2>
                <h5><%= mensajeTurnos %> </h2>
                <br>
                <div class="d-grid gap-2">
                    <a href="ServletTurno?Param=listarTurnos" class="btn btn-blue btn-lg">Ver Turnos</a>
                </div>
            </div>
            <div class="col"></div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    
</body>
</html>
