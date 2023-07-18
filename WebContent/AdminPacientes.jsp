<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="entidad.Persona"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@ page import="auxiliares.ValidarUsuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado pacientes</title>

<!-- Boostrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

<!-- DataTable -->
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
</script>

</head>
<body>

	<%
		List<Persona> listaP = new ArrayList<Persona>();
		if (request.getAttribute("listaPacientes") != null) {
			listaP = (List<Persona>) request.getAttribute("listaPacientes");
		}
	%>

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
	
<!-- Tabla y botones -->	

<div class="container">
<h4>Lista de pacientes</h4> <hr> 	
  <div class="row">
    <div class="col-4"></div>
    <div class="text-center">
         <a href="ServletPacientes?Param=agregarNuevo" class="btn btn-primary">Agregar Nuevo</a>
  	</div>
  	<br>
  	<br>
    <div>
	<table class="table table-striped" style="margin: 0 auto;" id="miTabla">
		<thead>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Sexo</th>
				<th>Fecha de Nacimiento</th>
				<th>Mail</th>
				<th>Telefono</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<%
			for (Persona p : listaP) {

		%>	
		<tr>
			<form method="post" action="ServletPacientes">
			<td><%=p.getDNI()%> <input type="hidden" name = "dniPaciente" value = <%=p.getDNI()%>></td>
			<td><%=p.getNombre()%></td>
			<td><%=p.getApellido()%></td>
			<td><%=p.getSexo()%></td>
			<td><%=p.getFnac()%></td>
			<td><%=p.getMail()%></td>
			<td><%=p.getTelefono()%></td>
			<td> <input type="submit" value="Ver Completo" name="btnVer" class="btn btn-info"> </td>
			<td> <input type="submit" value="Eliminar" name="btnEliminar" class="btn btn-danger" onclick="return confirm('¿Está seguro que desea eliminar este paciente?')"/> </td>	
			</form>
		</tr>
		<%
			}
		%>
	</tbody>
	</table>
</div>
<div class="col-4"></div>
</div>
</div>

<!-- Mensajes de confirmacion -->

<!-- Eliminar -->
	<%if (request.getAttribute("eliminado") != null) {%>
	<script type="text/javascript">
		function alertName(){
		alert("Paciente eliminado con exito");
		} 
		</script> 
	<%}%>
	
<!-- Modificar -->		
	<%if (request.getAttribute("modificado") != null && request.getAttribute("modificadoDP")!= null) {%>
	<script type="text/javascript">
		function alertName(){
		alert("Paciente modificado con exito");
		} 
		</script> 
	<%}%>
	
<!-- Agregar -->
	<%if (request.getAttribute("estadoPaciente") != null && request.getAttribute("estadoDP")!= null) {%>
	<script type="text/javascript">
		function alertName(){
		alert("Paciente agregado con exito");
		} 
		</script> 
	<%}%>	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>