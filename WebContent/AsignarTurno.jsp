<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@page import="entidad.Usuario"%>
    <%@page import="entidad.Medico"%>
    <%@page import="entidad.Turno"%>
    <%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@ page import="auxiliares.ValidarUsuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar Turnos</title>

<!-- Boostrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

<!-- DataTable -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>

<% 
String mensaje = "";
if(request.getAttribute("mensajeDeActualizacionDeTurno") != null)
{
	mensaje = (String)request.getAttribute("mensajeDeActualizacionDeTurno"); 
}

if (request.getAttribute("estadoPaciente") != null && request.getAttribute("estadoDP") != null){
	mensaje = "Paciente agregado con exito.";
}
%>
<script type="text/javascript">
$(document).ready(function() {
	var table = new DataTable('#miTabla', {
	    language: {
	        url: '//cdn.datatables.net/plug-ins/1.13.5/i18n/es-ES.json',
	    },
	});
});
	
	var mensaje = "<%=mensaje%>";
	if(mensaje != "")
	{
	    alert(mensaje);		
	}

</script>

</head>
<body>

	<%
		ArrayList<Turno> listaTurnosPorAsignar = new ArrayList<Turno>();
	
		if (request.getAttribute("listaTurnosPorAsignar") != null) {
			listaTurnosPorAsignar = (ArrayList<Turno>) request.getAttribute("listaTurnosPorAsignar");
		}
		
		Medico medicoSeleccionado = new Medico();
		if(request.getAttribute("medicoSeleccionado") != null){
			medicoSeleccionado = (Medico) (request.getAttribute("medicoSeleccionado"));
		}
		
		if (request.getAttribute("listaTurnosPorMedico") != null) {
			listaTurnosPorAsignar = (ArrayList<Turno>) request.getAttribute("listaTurnosPorMedico");
		}
		
		ArrayList<Medico> listaMedicos = new ArrayList<Medico>();
		if (request.getAttribute("listaMedicos") != null) {
			listaMedicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");
		}
		
		int dniPacienteACrear = 0;
		boolean crearPaciente = false;
		if(request.getAttribute("pacienteNoExiste") != null && request.getAttribute("dniACrear") != null)
		{
			crearPaciente = true;
			dniPacienteACrear = (int) request.getAttribute("dniACrear");
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
<form action="ServletTurno" method="post">
  <h4>Asignar turno</h4> <hr>
  <div class="mb-2">
				<select name="Medicos" required>

					<option value=0>Seleccione un medico...</option>
        			<% for (Medico m : listaMedicos) {
      				if (request.getAttribute("listaTurnosPorMedico") != null && m.getDNI() == medicoSeleccionado.getDNI()) {%>
        			<option value="<%=m.getDNI() %>" selected><%=m.getNombre()+" "+m.getApellido()%></option>
        		<%} else {%>
        			<option value="<%=m.getDNI()%>"><%=m.getNombre()+" "+m.getApellido()%></option>
        		<%}}%>
				</select>
				<input type="submit" value="Filtrar" name="btnFilter" class="btn btn-info">
				<input type="submit" value="Borrar Filtros" name="deleteFilters" class="btn btn-danger"/>

  </div>
  </form>
  <div class="row">
    <div class="col-4"></div>
  	<br>
  	<br>
    <div>
	<table class="table table-striped" style="margin: 0 auto;" id="miTabla">
		<thead>
			<tr>
				<th>ID de turno</th>
				<th>Medico</th>
				<th>Especialidad</th>

				<th>Horario de turno</th>
				<th>DNI de paciente</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%
			int horaActual = LocalTime.now().getHour();
			LocalDate diaActual = LocalDate.now();
			
			for (Turno t : listaTurnosPorAsignar) {

			%>
			<tr>
				<form action="ServletTurno" method="post">
				<td><%=t.getIdTurno()%> <input type="hidden" name = "idTurno" value = <%=t.getIdTurno()%>></td>
				<td><%=t.getMedico().getNombre()+" "+t.getMedico().getApellido()%></td>
				<td><%=t.getMedico().getEspecialidad().getDescripcion()%></td>
				<td><%=t.getFecha()+"\n"+t.getHora()+"hs"%> <input type="hidden" name = "fechaTurno" value = <%=t.getFecha()%>> <input type="hidden" name = "horaTurno" value = <%=t.getHora()%>> </td>
				<td>
				<div class="mb-3">
                        <input type="text" class="form-control" id="dni" name="dni" pattern="^[0-9]{8}$" autofocus title="Este campo solo admite un numero de 8 digitos.">

                </div>
                </td><%if(horaActual > t.getHora() && (diaActual.isEqual(t.getFecha()) || t.getFecha().isBefore(diaActual))){ %>
				<td><input type="submit" value="Asignar" name="btnAsignar" class="btn btn-info" disabled onclick="return confirm('Esta seguro que desea asignar este paciente?')"> <input type="hidden" name = "fechaTurno" value = <%=t.getFecha()%>></td>
				<% }else{ %>
				<td><input type="submit" value="Asignar" name="btnAsignar" class="btn btn-info" onclick="return confirm('Esta seguro que desea asignar este paciente?')"> <input type="hidden" name = "fechaTurno" value = <%=t.getFecha()%>></td>
				<% } %>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>

<script>
  var crearPaciente = <%=crearPaciente%>;
  var dniPaciente = <%=dniPacienteACrear%>;

  if (crearPaciente) {
    var confirmacion = confirm("El DNI ingresado no se encuentra en la base de datos."+"\n"+"¿Desea crear un paciente utilizando este DNI?");

    if (confirmacion)
    {
      sessionStorage.setItem('dniPacienteACrear', dniPaciente);
      var url = "ServletPacientes?Param=agregarNuevo&retornarAsignarTurnos=true";
      window.location.href = url;
    }
  }
</script>

</html>