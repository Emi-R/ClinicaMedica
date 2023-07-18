<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Entidades -->
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Especialidad"%>
<%@page import="entidad.Horario"%>
<%@ page import="auxiliares.ValidarUsuario" %>


<!-- Librerias -->
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta/Modificacion Medicos</title>
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
		ArrayList<Provincia> listaProv = new ArrayList<Provincia>();
		if (request.getAttribute("listaProv") != null) {
			listaProv = (ArrayList<Provincia>) request.getAttribute("listaProv");
		}
		
		ArrayList<Localidad> listaLoc = new ArrayList<Localidad>();
		if (request.getAttribute("listaLoc") != null) {
			listaLoc = (ArrayList<Localidad>) request.getAttribute("listaLoc");
		}
		
		ArrayList<Especialidad> listaEsp = new ArrayList<Especialidad>();
		if (request.getAttribute("listaEsp") != null) {
			listaEsp = (ArrayList<Especialidad>) request.getAttribute("listaEsp");
		}
		
		ArrayList<Horario> listaH = new ArrayList<Horario>();
		if (request.getAttribute("listaHorarios") != null) {
			listaH = (ArrayList<Horario>) request.getAttribute("listaHorarios");
		}
		
		Medico medico = new Medico();
		if (request.getAttribute("verMedico") != null) {
			medico = (Medico)request.getAttribute("verMedico");
		}
		
		if (request.getAttribute("ModificarMedico") != null) {
			medico = (Medico)request.getAttribute("ModificarMedico");
		}
		
		String[] diasDisponibles = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
		
		String esMasculino = medico.getSexo() == 'M' ? "checked" : "";
		String esFemenino = medico.getSexo() == 'F' ? "checked" : "";
		
	%>
	
<!-- Formulario y controles --> 
 <div class="container">
 	<h4>Datos del médico</h4> <hr>
 
 <!-- Si doy click en AgregarNuevo, cargo todos los campos vacíos --> 
 
 <%if ((request.getAttribute("verMedico") == null) && (request.getAttribute("ModificarMedico") == null) && (request.getParameter("dniMedico") == null) ) {%>	

 <form action="ServletMedicos" method="post" onsubmit="return validarFormulario()">
    <div class="row ">
        <div class="col-6">
            <div class="mb-2">
                <label for="DNI">DNI:</label>
				<input type="text" name="txtDNI" maxlength="8" placeholder="DNI" pattern="^[0-9]{8}$" autofocus title="Este campo solo admite un número de 8 dígitos." required>
            </div>
            <div class="mb-2">
                <label for="nombre">Nombre:</label>
				<input type="text" name="txtNombre" placeholder="Nombre" required id="Nombre">
            </div>
            <div class="mb-2">
               <label for="Apellido">Apellido:</label>
				<input type="text" name="txtApellido" placeholder="Apellido" required id="Apellido">
            </div>
            <div class="mb-2">
                <label for="Sexo">Sexo:</label>
				<input type="radio" name="Sexo" value="Femenino" required> Femenino
				<input type="radio" name="Sexo" value="Masculino" required> Masculino
            </div>
            <div class="mb-2">
                <label for="Nacionalidad">Nacionalidad:</label>
				<input type="text" name="txtNacionalidad" placeholder="Nacionalidad" id="Nacionalidad" required>
            </div>
            <div class="mb-2">
                <label for="FNac">Fecha de Nacimiento:</label>
				<input type="date" name="FNac" max="<%= LocalDate.now() %>" required>
            </div>
            <div class="mb-2">
                <label for="Mail">Mail:</label>
				<input type="email" name="txtMail" placeholder="Mail" required>
            </div>
            <div class="mb-2">
				<label for="Telefono">Teléfono:</label>
				<input type="tel" name="txtTelefono" placeholder="Telefono" pattern="[0-9]+" title="Ingrese solo números" required>
            </div>
            <div class="mb-2">
				<label for="Especialidad">Especialidad:</label>
				<select name="Especialidad" required>
					<option value="">Seleccionar opcion...</option>
					<%
						for (Especialidad e : listaEsp) {
					%>
						<option value="<%=e.getIdEspecialidad()%>"><%=e.getDescripcion()%></option>
					<%}%>
				</select>
            </div>
        </div>

        <div class="col-6">
        <h5>Dirección</h5><hr>
            <div class="mb-2">
                <label for="Calle">Calle:</label>
				<input type="text" name="txtCalle" placeholder="Calle" required id="Calle">
			</div>
			<div class="mb-2">
				<label for="Numero">Numero:</label>
				<input type="text" name="txtNumero" placeholder="Número" pattern="[0-9]+" title="Ingrese solo números" required>	
            </div>
            <div class="mb-2">
				<label for="Localidad">Localidad:</label>
				<select name="Localidades" required>
					<option value="">Seleccionar opcion...</option>
					<%
						for (Localidad l : listaLoc) {
					%>
					<option value="<%=l.getIDLocalidad()%>"><%=l.getDescripcion()%></option>
					<%}%>
				</select>
            </div>
            <br>
           <h5>Día y horario de atención</h5> <hr>
           <div class="mb-2">
				<label for="Dia">Dia:</label>
				<select name="Dia" required>
					<option value="">Seleccionar opcion...</option>
					<% for (String dia : diasDisponibles) {%>
  					    <option><%= dia %></option>
  					 <%}%>
				</select>
            </div>
            <div class="mb-2">
                <label for="Desde">De:</label>
				<input type="number" name="txtDesde" min="8" max="14" required>
 
                <label for="Hasta">A:</label>
				<input type="number" name="txtHasta" min="15" max="21" required>
            </div>  
          	
          </div>         
    </div>
    <div class="row">
        <div class="col-auto">
        <input type="reset" value="Restablecer" class="btn btn-secondary"> </input>
        <br><br>
        <div>
        	<input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary" onclick="return confirm('¿Está seguro que desea agregar este medico?')"> </input>
        	<a href="ServletMedicos?Param=list" class="btn btn-info">Regresar</a>
        </div>
        </div>
    </div>
    </form>
<%} %>

<!-- Si doy click a VerCompleto, solo veo los datos como lbl -->	
	
<% if (request.getAttribute("verMedico") != null) {%>
    <div class="row justify-content-center g-4">
        <div class="col-md-4">
            <div class="mb-2">
                <label for="DNI"><b>DNI:</b> <%=medico.getDNI() %> </label>
            </div>
            <div class="mb-2">
                <label for="nombre"><b>Nombre:</b> <%=medico.getNombre() %></label>
            </div>
            <div class="mb-2">
               <label for="Apellido"><b>Apellido:</b> <%=medico.getApellido() %></label>
            </div>
            <div class="mb-2">
                <label for="Sexo"><b>Sexo:</b></label>
				<input type="radio" name="Sexo" value="Femenino" disabled <%=esFemenino%>> Femenino
				<input type="radio" name="Sexo" value="Masculino" disabled <%=esMasculino%>> Masculino
            </div>
            <div class="mb-2">
                <label for="Nacionalidad"><b>Nacionalidad:</b> <%=medico.getNacionalidad() %></label>
            </div>
            <div class="mb-2">
                <label for="FNac"><b>Fecha de Nacimiento:</b> <%=medico.getFnac() %></label>
            </div>
            <div class="mb-2">
                <label for="Mail"><b>Mail:</b> <%=medico.getMail() %></label>
            </div>
            <div class="mb-2">
				<label for="Telefono"><b>Teléfono:</b> <%=medico.getTelefono() %></label>
            </div>
            <div class="mb-2">
				<label for="Especialidad"><b>Especialidad:</b> </label>
				<select name="Especialidad" disabled>
					<option value="<%=medico.getEspecialidad().getIdEspecialidad() %>" selected><%=medico.getEspecialidad().getDescripcion() %> </option>
				</select>
            </div>
        </div>

        <div class="col-md-4">
        <h5>Direccion</h5><hr>
            <div class="mb-2">
                <label for="Calle"><b>Calle:</b> <%=medico.getDireccion().getCalle() %></label>
			</div>
			<div class="mb-2">
				<label for="Numero"><b>Numero:</b> <%=medico.getDireccion().getNumero() %></label>
            </div>
            <div class="mb-2">
				<label for="Provincia"><b>Provincia:</b> </label>
				<select name="Provincias" disabled >
					<option value= "<%=medico.getDireccion().getProvincia().getIDProvincia()%>" selected> <%=medico.getDireccion().getProvincia().getDescripcion() %> </option>
				</select>
            </div>
            <div class="mb-2">
				<label for="Localidad"><b>Localidad:</b></label>
				<select name="Localidades" disabled >
					<option value= "<%=medico.getDireccion().getLocalidad().getIDLocalidad() %>" selected> <%=medico.getDireccion().getLocalidad().getDescripcion() %> </option>
				</select>
            </div>
            </div>
            <br>
       <div class="col-md-4">
           <h5>Dias y horarios de atencion</h5> <hr>  
          
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
				<td> <%=h.getDiaAtencion() %></td>
				<td> <%=h.getHoraInicio() %></td>
				<td> <%=h.getHoraFin() %></td>
          	</tr>
			<%}%>
			</tbody>
			</table>
			
			<form action="ServletHorarios" method="post">
					<input type="hidden" name="dniMedico" value= <%=medico.getDNI()%>>
					<input type="submit" name="btnModificarHorario" value="Modificar Horario" class="btn btn-warning">
			</form> 
       </div>     
    </div>
    <div class="row justify-content-center g-4">
        <div class="col-auto">
        <div>
        <br><br>
        <form action="ServletMedicos" method="post">
            	<input type="hidden" name="dniMedico" value= <%=medico.getDNI()%>>
				<input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> </input>
			<a href="ServletMedicos?Param=list" class="btn btn-info">Regresar</a>
			</form>	
        </div>
        </div>
    </div>
<%} %>

<!-- Si hago click en Modificar, se renderiza y muestra las cajas de txt para modificar -->

<% if (request.getAttribute("ModificarMedico") != null) { %>

<form id=mod action="ServletMedicos" method="post" onsubmit="return validarFormulario()">
	<div class="row justify-content-center g-4">
		<div class="col-md-4">
       		<div class="mb-2">
                <label for="DNI">DNI:</label>
                <input type="text"  name="txtDNI" maxlength="8" placeholder="DNI" required value=<%=medico.getDNI() %> readonly=true style="background-color: #f2f2f2">
            </div>
            <div class="mb-2">
                <label for="nombre">Nombre:</label>
                <input type="text" name="txtNombre" placeholder="Nombre" id="Nombre" value=<%=medico.getNombre() %>>
            </div>
            <div class="mb-2">
               <label for="Apellido">Apellido:</label>
               <input type="text" name="txtApellido" placeholder="Apellido" required id="Apellido" value=<%=medico.getApellido() %>>
            </div>
            <div class="mb-2">
                <label for="Sexo">Sexo:</label>
				<input type="radio" name="Sexo" value="Femenino" required <%=esFemenino%>> Femenino
				<input type="radio" name="Sexo" value="Masculino" required <%=esMasculino%>> Masculino
            </div>
            <div class="mb-2">
                <label for="Nacionalidad">Nacionalidad:</label>
                <input type="text" name="txtNacionalidad" placeholder="Nacionalidad" required id="Nacionalidad" value=<%=medico.getNacionalidad() %>>
            </div>
            <div class="mb-2">
                <label for="FNac">Fecha de Nacimiento:</label>
                <input type="date" name="FNac" max="<%= LocalDate.now() %>" required value=<%=medico.getFnac() %>>
            </div>
            <div class="mb-2">
                <label for="Mail">Mail:</label>
                <input type="email" name="txtMail" placeholder="Mail" required value=<%=medico.getMail() %>>
            </div>
            <div class="mb-2">
				<label for="Telefono">Teléfono:</label>
				<input type="tel" name="txtTelefono" placeholder="Telefono" required pattern="[0-9]+" title="Ingrese solo numeros" value=<%=medico.getTelefono() %>>
            </div>
            <div class="mb-2">
				<label for="Especialidad">Especialidad: </label>
				<select name="Especialidad">
					<% for (Especialidad e : listaEsp) {
      				if (request.getAttribute("ModificarMedico") != null && e.getIdEspecialidad() == medico.getEspecialidad().getIdEspecialidad()) {%>
        			<option value="<%=e.getIdEspecialidad() %>" selected><%=e.getDescripcion() %></option>
        		<%} else {%>
        			<option value="<%=e.getIdEspecialidad()%>"><%=e.getDescripcion() %></option>
        		<%}}%>
				</select>
            </div>
        </div>
   <div class="col-md-4">
        <h5>Direccion</h5><hr>
            <div class="mb-2">
                <label for="Calle">Calle:</label>
                <input type="text" name="txtCalle" placeholder="Calle" id="Calle" value=<%=medico.getDireccion().getCalle() %>>
			</div>
			<div class="mb-2">
				<label for="Numero">Numero:</label>
				<input type="text" name="txtNumero" placeholder="Numero" pattern="[0-9]+" title="Ingrese solo numeros" value=<%=medico.getDireccion().getNumero() %>>
            </div>
            <div class="mb-2">
				<label for="Localidad">Localidad:</label>
				<select name="Localidades" >
					<% for (Localidad l : listaLoc) {
      				if (request.getAttribute("ModificarMedico") != null && l.getIDLocalidad() == medico.getDireccion().getLocalidad().getIDLocalidad()) {%>
        			<option value="<%=l.getIDLocalidad()%>" selected><%=l.getDescripcion()%></option>
        		<%} else {%>
        			<option value="<%=l.getIDLocalidad()%>"><%=l.getDescripcion()%></option>
        		<%}}%>
        		</select>  
            </div>
        </div>
    </div>
    <div class="row justify-content-center g-4">
        <div class="col-auto">
        <div>
        <br><br>
            	<% if (request.getAttribute("verMedico") != null) {%>
				<input type="submit" name="btnModificar" value="Modificar Datos" class="btn btn-warning"> </input>
				<%} else {%>
					<input type="submit" name="btnConfirmar" value="Modificar Datos" class="btn btn-warning" onclick="return confirm('¿Está seguro que desea modificar este medico?')"> </input>
				<%}%>
			<a href="ServletMedicos?Param=list" class="btn btn-info">Regresar</a>
        </div>
        </div>
    </div>
</form>

<%} %>  

</div>

	<%if (request.getAttribute("errorDni") != null) {%>
	<script type="text/javascript">
		function alertName(){
		alert("El DNI ya se encuentra registrado");
		} 
		</script> 
	<%}%>
	

<!-- Alerta eliminacion horario ok  -->	
	<%
		if (request.getAttribute("eliminarHorario") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Horario eliminado con exito");
		} 
		</script> 
	<%
		}
	%>
	
<!-- Alerta modificacion horario ok -->
 	<%
		if (request.getAttribute("hmod") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Horario modificado con exito");
		} 
		</script> 
	<%
		}
	%>

<!-- Alerta agregar horario ok -->
 	<%
		if (request.getAttribute("agregadonh") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Horario agregado con exito");
		} 
		</script> 
	<%
		}
	%>

 <!-- Validar espacios vacios. -->
<script>
  function validarFormulario() {
    var Nombre = document.getElementById("Nombre").value.trim();
    var Apellido = document.getElementById("Apellido").value.trim();
    var Nacionalidad = document.getElementById("Nacionalidad").value.trim();
    var Calle = document.getElementById("Calle").value.trim();

    if (Nombre === "" || Apellido === "" || Nacionalidad === "" || Calle === "") {
      alert("No se pueden guardar espacios. Debe ingresar un valor en todos los campos.");
      return false;
    }
    return true;
  }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>