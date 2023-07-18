<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Turno"%>
<%@page import="entidad.Medico"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Turnos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		var table = new DataTable('#miTabla', {
		    language: {
		        url: '//cdn.datatables.net/plug-ins/1.13.5/i18n/es-ES.json',
		    },
		});
	});
	
	$(document).ready(function() {
		var table2 = new DataTable('#miTabla2', {
		    language: {
		        url: '//cdn.datatables.net/plug-ins/1.13.5/i18n/es-ES.json',
		    },
		});
	});
</script>

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
                        <a class="navbar-brand mb-0 h1" href="ServletUsuario?Param=1">
                            <img src="https://cdn-icons-png.flaticon.com/512/5394/5394174.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> 🢀  Volver a Menú Principal
                        </a>
                    </li>
    				<div class="container">
    					<div class="navbar-brand text-center" class="d-inline-block align-text-top" href="#"> Clinica Medica SA</div>
  					</div>
                </ul>
			<% Usuario a = (Usuario) session.getAttribute("usuario"); 
			Medico medico = (Medico) session.getAttribute("medico");%>
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

	<% List<Turno> listaT = new ArrayList<Turno>();
		if (request.getAttribute("listaTurnos") != null) {
		listaT = (List<Turno>) request.getAttribute("listaTurnos");
	}
		
		List<Turno> listaFiltrada = new ArrayList<Turno>();
		if (request.getAttribute("listaTurnosFiltrada") != null) {
		listaFiltrada = (List<Turno>) request.getAttribute("listaTurnosFiltrada");
	}
		%>
    <!-- Tabla de Turnos -->
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Listado de Turnos</h1>
                <hr>
                <% if(request.getAttribute("turnosProximos") != null) { %>
                	<a href="ServletTurno?Param=listarTurnosDiaActual" class="btn btn-info">Mostrar solo turnos del dia de hoy</a>
                <% } else { %>
                	<a href="ServletTurno?Param=listarTurnos" class="btn btn-info">Mostrar turnos de los dias proximos</a>
                <% }%>
                <br>
                <br>
                <table class="table table-striped dataTable" style="margin: 0 auto;" id="miTabla">
                    <thead>
                        <tr>
                        	<th>ID Turno</th>
                            <th>Apellido</th>
                            <th>Nombre</th>
                            <th>DNI</th>
                            <th></th>
                            <th>Fecha y hora del Turno</th>
                            <th>Observaciones</th>
                            <th>Confirmar Asistencia</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%for (Turno t : listaT) { %>
                    
                        <tr>
                        	<form method="post" action="ServletTurno">
                        	<td><%= t.getIdTurno() %> <input type="hidden" name = "idTurno" value = <%=t.getIdTurno()%>></td>
                            <td><%= t.getPaciente().getApellido() %> </td>
                            <td><%= t.getPaciente().getNombre() %> </td>
                            <td><%= t.getPaciente().getDNI() %> <input type="hidden" name = "dniPaciente" value = <%=t.getPaciente().getDNI()%>></td>
                            <td><input type="submit" name="btnVerInfoPacienteMedico" value="Ver paciente" class="btn btn-outline-success">
                            <td><%= t.getFecha() %>  -  <%= t.getHora() %> Hs.</td>
                             <% if(request.getAttribute("turnosProximos") != null) { %>
                            <td><input type="text" class="form-control" id="observacion" name="txtObservacion" disabled autofocus></td>
                            <td><input type="submit" name="btnAsistio" value="Asistió" class="btn btn-primary" disabled onclick="return confirm('¿Está seguro que desea actualizar este turno como paciente presente?')">
                            <input type="submit" name="btnAusente" value="Ausente" class="btn btn-danger" disabled onclick="return confirm('¿Está seguro que desea actualizar este turno como paciente ausente?')"/></td>
                        	 <% } else { %>
                        	 <td><input type="text" class="form-control" id="observacion" name="txtObservacion" autofocus></td>
                        	  <td><input type="submit" name="btnAsistio" value="Asistió" class="btn btn-primary" onclick="return confirm('¿Está seguro que desea actualizar este turno como paciente presente?')">
                            <input type="submit" name="btnAusente" value="Ausente" class="btn btn-danger" onclick="return confirm('¿Está seguro que desea actualizar este turno como paciente ausente?')"/></td>
                        	 <% }%>
                        	</form>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
     <div class="container">
     <hr>
        <div class="row">
        <h4>Filtrar turnos por fecha</h4>
        	<div class="col-2">
        		<div class="mb-2">
					<form method="post" action="ServletTurno">
                	<label for="FInicio">Desde:</label>
					<input type="date" name="fechaDesde" value="<%=LocalDate.now()%>" > <br>
                	<br>
                	<label for="FFin">Hasta:</label>
					<input type="date" name="fechaHasta" value="<%=LocalDate.now().plusDays(1)%>"> 
					<br>
					<br>
					<input type="submit" name="btnBuscarxFecha" value="Buscar" class="btn btn-primary text-center">
					<hr>
					</form>
            	</div>
        	</div>
        </div>
        <table class="table table-striped dataTable" style="margin: 0 auto;" id="miTabla2">
                    <thead>
                        <tr>
                        	<th>ID Turno</th>
                            <th>Apellido</th>
                            <th>Nombre</th>
                            <th>DNI</th>
                            <th>Fecha del Turno</th>
                            <th>Asistio?</th>
                            <th>Observaciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% for (Turno t : listaFiltrada) { 
                    	int estado = t.isEstado();		
                    	String stringEstado = "";
                    	
                    	switch(estado)	{
                    	case 2:
                    	{
                    		stringEstado = "Ausente";
                    		break;
                    	}
                    	case 3:
                    	{
                    		stringEstado = "Asistio";
                    		break;
                    	}
                    	
                    	} %>
                        <tr>
                        	<td><%= t.getIdTurno() %></td>
                            <td><%= t.getPaciente().getApellido() %></td>
                            <td><%= t.getPaciente().getNombre() %></td>
                            <td><%= t.getPaciente().getDNI() %></td>
                            <td><%= t.getFecha() %>  -  <%= t.getHora() %> Hs.</td>
                            <td><%= stringEstado %></td>
                            <td><%= t.getObservaciones() %>  </td>
                        </tr>
                         <% } %>
                    </tbody>
                </table>
                <input type="hidden" id="inputOculto">
			</div>

<!-- Alerta asistio ok -->
 	<%
		if (request.getAttribute("actualizado") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Turno actualizado con exito");
		} 
		</script> 
	<%
		}
	%>
	
	<%
		if (request.getAttribute("errorFiltroFecha") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Error en filtro. El campo 'Desde' no puede ser mayor al campo 'Hasta'");
		} 
		</script> 
	<%
		}
	%>

<%
		if (request.getAttribute("error") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("El campo Observacion no puede estar vacio. Agregue una por favor");
		} 
		</script> 
	<%
		}
	%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>
