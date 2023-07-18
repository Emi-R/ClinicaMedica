<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="entidad.Usuario"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Horario"%>

<!-- Librerias -->
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@ page import="auxiliares.ValidarUsuario" %>    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Turnos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
                        <a class="navbar-brand mb-0 h1" href="ServletUsuario?Param=1">
                            <img src="https://cdn-icons-png.flaticon.com/512/5394/5394174.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> 🢀  Volver a Menú Principal
                        </a>
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


<!-- Llamado a listados -->
	
	<%
		ArrayList<Medico> listaMedicos = new ArrayList<Medico>();
		if (request.getAttribute("listaMedicos") != null) 
		{
			listaMedicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");
		}
		
		ArrayList<Horario> listaH = new ArrayList<Horario>();
		if (request.getAttribute("listaHorarios") != null) {
			listaH = (ArrayList<Horario>) request.getAttribute("listaHorarios");
		}
		
		Medico medico = new Medico();
		if (request.getAttribute("verDatos") != null) {
			medico = (Medico)request.getAttribute("verDatos");
		}
		
	%>
	
	
<!-- Formulario y controles --> 
 <div class="container">
 <h4>Crear turno</h4> <hr>
 <form action="ServletTurno" method="post">
    <div class="row justify-content-center g-4">
        <div class="col-md-4">
            <div class="mb-2">
				<label for="Medicos">Medico: </label>
					<select name="Medicos" required>
						<% for (Medico m : listaMedicos) {
      				if (request.getAttribute("verDatos") != null && m.getDNI() == medico.getDNI()) {%>
        			<option value="<%=medico.getDNI() %>" selected><%=medico.getNombre()+" "+medico.getApellido()%></option>
        		<%} else {%>
        			<option value="<%=m.getDNI()%>"><%=m.getNombre()+" "+m.getApellido()%></option>
        		<%}}%>
        			</select>
				<input type="submit" name="btnBuscar" value="Buscar" class="btn btn-primary">
            </div>

    <!-- Si se hizo click en buscar, se traen datos necesarios para crear turnos. -->
    
    <%if ((request.getAttribute("verDatos") != null) && (request.getAttribute("listaHorarios") != null)) {%>
    
            <div class="mb-2">
				<label for="Especialidad"> <b>Especialidad:</b> <%=medico.getEspecialidad().getDescripcion()%></label>
            </div>
            <div class="mb-2">
                <label for="Horario"> <b>Dia y horario de atencion:</b></label>
                <table class="table">       		
				<tr>
					<th>Dia</th>
					<th>Desde</th>
					<th>Hasta</th>
				</tr>
				<tbody>
			<% 
				for (Horario h : listaH) { 
			%>
				<tr>
					<td> <%=h.getDiaAtencion()%></td>
					<td> <%=h.getHoraInicio()%> </td>
					<td> <%=h.getHoraFin()%> </td>
	          	</tr>
			<% } %>
				</tbody>
				</table>
            </div>
            <div class="mb-2">
				<label for="DiaAtencion"><b>Dia de turno:</b></label>
				<select name="DiaAtencion">
					<% 
						for (Horario h : listaH) { 
					%>
					<option> <%=h.getDiaAtencion()%> </option>
					<% } %>
				</select>
            </div>
            <div class="mb-2">
            	<label for="FechaTurno"><b>Fecha del turno:</b></label>
				<input type="date" name="FechaTurno" min="<%=LocalDate.now().plusDays(1) %>" value="<%=LocalDate.now().plusDays(1) %>">
			</div>
   <%} %>
            <div>
            	<br>
            </div>
        </div>

    <div class="row justify-content-center g-4">
        <div class="col-md-4">
        	<%if ((request.getAttribute("verDatos") != null) && (request.getAttribute("listaHorarios") != null)) {%>
        	<input type="reset" value="Restablecer" class="btn btn-secondary"> </input>
        	<input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary" style="margin-left: 50px"> </input>
<%} %>
        </div>
    </div>
    </form>   
</div>

	<%
		if (request.getAttribute("exito") != null) {
			
	%>
	<script type="text/javascript">
	
		var mensaje = 'Se crearon <%= request.getAttribute("cantTurnos") %> turnos para el medico <%= request.getAttribute("apellidoMedico") %> <%= request.getAttribute("nombreMedico") %> en la fecha <%= request.getAttribute("fecha") %>';
		function alertName(){
		alert(mensaje);
		} 
		</script> 
	<%
		}
	%>
	
	<%
		if (request.getAttribute("errorDia") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("La fecha elegida no corresponde al dia seleccionado.");
		} 
		</script> 
	<%
		}
	%>
	
	<%if (request.getAttribute("errorFechaOcupada") != null) {
	%>
		<script type="text/javascript">
		function alertName(){
		alert("Ya se han creado turnos para ese medico ese dia.");
		} 
		</script> 
	<%
		}
	%>
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>

</body>
</html>