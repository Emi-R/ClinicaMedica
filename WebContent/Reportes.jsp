<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <%@page import="entidad.Usuario"%>
   <%@page import="java.util.ArrayList"%>
   <%@page import="java.util.List"%>
   <%@page import="entidad.ReporteTurnosXEsp"%>
   <%@page import="entidad.ReporteTurnosXMed"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reportes y Estadisticas</title>
    
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    
    <!-- DataTable -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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
    <script type="text/javascript">
    $(document).ready(function() {
    	var table = new DataTable('#miTabla2', {
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
	<%
		List<ReporteTurnosXEsp> reporte1 = new ArrayList<ReporteTurnosXEsp>();
		if (request.getAttribute("reporteTurnosXEsp") != null) {
			reporte1 = (List<ReporteTurnosXEsp>) request.getAttribute("reporteTurnosXEsp");
		}
		
		List<ReporteTurnosXMed> reporte2 = new ArrayList<ReporteTurnosXMed>();
		if (request.getAttribute("reporteTurnosXMed") != null) {
			reporte2 = (List<ReporteTurnosXMed>) request.getAttribute("reporteTurnosXMed");
		}
	%>
    <!-- Tabla y botones -->
    <div class="container">
    <div class="text-center">
   		<h1>Reportes y Estadisticas</h1>
   		<br>
    </div>
    <div >
    	<br>
   		<h3>Cantidad de turnos asignados por Especialidad</h3>
    </div>
        <div class="row">
            <div class="col-4"></div>
            <br>
            <div>
                <table class="table table-striped" style="margin: 0 auto;" id="miTabla">
                    <thead>
                        <tr>
                            <th>Especialidad</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
			for (ReporteTurnosXEsp r : reporte1) {
					%>	
                        <tr>
                            <td><%=r.getEspecialidad().getDescripcion()%></td>
                            <td><%=r.getCantidad()%></td>
                        </tr>

                        <% } %>
                    </tbody>
             </table>
   <br>
   <hr>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-4"></div>
             	<div>
             	 	<br>
   					<h3>Cantidad de turnos asignados por Medicos</h3>
					<br>
    			</div>
            <br>
            <div>
                <table class="table table-striped" style="margin: 0 auto;" id="miTabla2">
                    <thead>
                        <tr>
                            <th>Apellido</th>
                            <th>Nombre</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
			for (ReporteTurnosXMed r : reporte2) {
					%>	
                        <tr>
                            <td><%=r.getApellido()%></td>
                            <td><%=r.getNombre()%></td>
                            <td><%=r.getCantidad()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                    </table>
                    </div>
		 </div>
		 
		 <% int cantArg = (int) request.getAttribute("cantArg");
		 	int cantExt = (int) request.getAttribute("cantExtranjeros");
		 	int cantTotal = (int) request.getAttribute("cantTotal");%>
		 <hr>

		 <table class="table table-striped" style="margin: 0 auto;" id="miTabla">
                    <thead>
                        <tr>
                            <th>Cantidad pacientes argentinos</th>
                            <th>Cantidad pacientes extranjeros</th>
                            <th>Cantidad pacientes totales</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%=cantArg%></td>
                            <td><%=cantExt%></td>
                            <td><%=cantTotal%></td>
                        </tr>

                    </tbody>
             </table>
		<br>
   		<hr>
		<% int cantMayores = (int) request.getAttribute("cantMayores");
		   int cantMenores = (int) request.getAttribute("cantMenores");%>
		 	
		 	<table class="table table-striped" style="margin: 0 auto;" id="miTabla">
                    <thead>
                        <tr>
                            <th>Cantidad pacientes mayores de edad</th>
                            <th>Cantidad pacientes menores de edad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%=cantMayores%></td>
                            <td><%=cantMenores%></td>
                        </tr>

                    </tbody>
             </table>
             <br>
   		<hr>
   		
	 <% int cantidadLibres = (int) request.getAttribute("cantLibres");
		int cantidadOcupados = (int) request.getAttribute("cantOcupados");
		int cantidadPresentes = (int) request.getAttribute("cantPresentes");
		int cantidadAusentes = (int) request.getAttribute("cantAusentes");%>
		 	
		 	<table class="table table-striped" style="margin: 0 auto;" id="miTabla">
                    <thead>
                        <tr>
                            <th>Cantidad de turnos libres</th>
                            <th>Cantidad de turnos ocupados</th>
                            <th>Cantidad de turnos presentes</th>
                            <th>Cantidad de turnos ausentes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%=cantidadLibres%></td>
                            <td><%=cantidadOcupados%></td>
                            <td><%=cantidadPresentes%></td>
                            <td><%=cantidadAusentes%></td>
                        </tr>

                    </tbody>
             </table>
 	</div>
        