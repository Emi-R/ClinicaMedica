<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@page import="entidad.Usuario"%>
	<%@ page import="auxiliares.ValidarUsuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Principal Administrador</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
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
				    else if (user.getTipo().getIdTipoUsuario() == 1)
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
                        <div class="navbar-brand mb-0 h1" href="ServletUsuario?Param=1">
                            <img src="https://cdn-icons-png.flaticon.com/512/5394/5394174.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Menú Principal
                        </div>
                    </li>
    				<div class="container">
    					<div class="navbar-brand text-center" class="d-inline-block align-text-top" href="#"> Clinica Medica SA</div>
  					</div>
                </ul>

			<div class="row">
				<div class="col-6">
					<ul class="text-end navbar-brand mb-0 fs-6" style=""> <b> DNI:</b> <%= user.getDNI() %> </ul>
				</div>
			</div>
                <form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name="btnSalir" value="Salir"></input>
			</form>
            </div>
        </div>
    </nav>
	<br>
	<div class="row">
		<div class="col-4"></div>
		<div class="col">
			<div class="text-center">
				<br>
				<h2>Bienvenido Administrador</h2>
			</div>
			<div class="text-center text-muted">
				<p>Seleccione una opcion:</p>
			</div>
			<div class="col-4"></div>
			<div class="col-2"></div>
			<br />
			<div class="d-grid mx-auto">
				<a href="PrincipalTurnos.jsp" class="btn btn-primary">Administrar Turnos</a> <br>
			</div>
			<div class="d-grid mx-auto">
				<a href="ServletPacientes?Param=list" class="btn btn-primary">Administrar Pacientes</a> <br>
			</div>
			<div class="d-grid mx-auto">
				<a href="ServletMedicos?Param=list" class="btn btn-primary">Administrar Medicos</a> <br>
			</div>
			<div class="d-grid mx-auto">
				<a href="ServletReportes?Param=list" class="btn btn-primary">Reportes e Informes</a> <br>
			</div>
			<br /> <br />
			<div class="col-4"></div>
			<div class="col-2"></div>
		</div>
		<div class="col-4"></div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>