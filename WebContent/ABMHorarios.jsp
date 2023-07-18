<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="entidad.Usuario"%>
<%@page import="entidad.Horario"%>
<%@ page import="auxiliares.ValidarUsuario" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Horarios</title>
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
<!-- Llamado a listados  -->	
	
	
	
	<%	
		ArrayList<Horario> listaH = new ArrayList<Horario>();
		if (request.getAttribute("listaHorarios") != null) 
		{
			listaH = (ArrayList<Horario>) request.getAttribute("listaHorarios");
		}
		
		int dniMedico = 0;
		
		if (request.getAttribute("dniMedico") != null)
		{
			dniMedico = (int)request.getAttribute("dniMedico");
		}
		
		String[] diasDisponibles = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
		Set<String> diasSeleccionados = new HashSet<>();
	%>

<div class="container">
<h5>Dias y horarios de atencion</h5> <hr>  
	 <div class="row justify-content-center g-4">
        <div class="col-md-4">
           <table class="table">       		
				<tr>
					<th>Dia</th>
					<th>Desde</th>
					<th>Hasta</th>
					<th></th>
				</tr>
			<tbody>
			<%
				for (Horario h : listaH) {
			%>	
			<tr>
				 <form action=ServletHorarios method= post>
				<input type="hidden" name="idHorario" value=<%=h.getIdHorario() %>>
				<td>
					<select name="Dia" required>
						<% String diaSeleccionado = h.getDiaAtencion();
						   diasSeleccionados.add(h.getDiaAtencion());
						%>
						<option><%=diaSeleccionado %> </option>
  						<% for (String dia : diasDisponibles) {
  						   if (!diasSeleccionados.contains(dia)) { %>
  					    	<option><%= dia %></option>
  					  		<% }
  						} %>
					</select>
				</td>
				<td><input type="number" name="txtDesde" min="8" max="14" value=<%=h.getHoraInicio() %>></input></td>
				<td><input type="number" name="txtHasta" min="15" max="21" value=<%=h.getHoraFin() %>></input></td>
				<input type="hidden" name="dniMedico" value=<%=h.getDNIMedico() %>>
				<td><input type="submit" value="Eliminar" name="btnEliminarH" onclick="return confirm('¿Esta seguro que desea eliminar este horario?')" class="btn btn-danger"/></td>
				<td><input type="submit" value="Modificar" name="btnModificarH" onclick="return confirm('¿Esta seguro que desea modificar este horario?')" class="btn btn-warning"/></td>
          		</form>
          		<%
				 dniMedico = h.getDNIMedico(); // Asignar el valor de dniMedico a la variable
				%>
          	</tr>
			<%}%>
			
			</tbody>
			</table>
		<div>
		<br>
				<h6>Agregar nuevo</h6>
				<form action=ServletHorarios method= post>
				<div class="mb-2">
				<label for="DiaNuevo">Dia:</label>
				<select name="DiaNuevo">
					<option value="">Seleccionar opcion...</option>
  						<% for (String dia : diasDisponibles) {
  						   if (!diasSeleccionados.contains(dia)) { %>
  					    	<option><%= dia %></option>
  					  		<% }
  						} %>
				</select>
            </div>
            <div class="mb-2">
                <label for="Desde">De:</label>
				<input type="number" name="txtDesdeNuevo" min="8" max="14">
 
                <label for="Hasta">A:</label>
				<input type="number" name="txtHastaNuevo" min="15" max="21">
            </div>  
		</div>
		<br>
			<input type="submit" value="Aceptar" name="btnAceptarH" class="btn btn-primary"/>
			<input type="hidden" name="dniMedico" value=<%= dniMedico %>>
		<a href="ServletMedicos?Param=list" class="btn btn-info">Regresar</a>
		</form>
  </div>      		
</div>
</div>
<!-- Alerta -->

	<%
		if (request.getAttribute("error") != null) {
	%>
		<script type="text/javascript">
			function alertName()
			{
				alert("Primero debe completar los campos para agregar nuevo horario.");
			} 
		</script> 
	<%
		}
	%>
 
 <!-- Alerta dia repetido  -->
 
 <%
		if (request.getAttribute("repetido") != null) {
	%>
		<script type="text/javascript">
			function alertName()
			{
				alert("El medico ya trabaja en el dia seleccionado. Por favor, elija otro dia.");
			} 
		</script> 
	<%
		}
	%>	
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>