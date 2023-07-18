<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Clinica Medica</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<br>
<div class="row">
        <div class="col-4"></div>
        	<div class="col">
        		<img src="https://centromedicomendoza.com/wordpress/wp-content/uploads/2016/04/012.jpg" class="img-fluid" alt="...">
            <div class="text-center">
            <br>
                <h2>Bienvenido a Clinica Medica SA</h2>
            </div>
            <div class="text-center text-muted">
                <p>Por favor, ingrese su DNI y contraseña para ingresar:</p>
            </div>
            <% if(request.getAttribute("errorCredenciales") != null) { %>
            <div class="col-12">
            	<div class="alert alert-danger" role="alert">	
  				Usuario o contraseña incorrectos. Por favor, reintente
				</div>
			</div>
			<% } %>
			<% if(request.getAttribute("errorDni") != null) { %>
            <div class="col-12">
            	<div class="alert alert-danger" role="alert">	
  				DNI invalido. Por favor, reintente
				</div>
			</div>
			<% } %>
			<% if(request.getAttribute("errorDadoDeBaja") != null) { %>
            <div class="col-12">
            	<div class="alert alert-danger" role="alert">	
  				 Usted ha sido dado de baja en el sistema. Comuniquese con el sindicato
				</div>
			</div>
			<% } %>
            <form action="ServletUsuario" method="post">
            
	            <div class="d-grid mx-auto">
	                <input type="text" name="txtDNI" placeholder="DNI" class="form-control" pattern="[0-9]+" autofocus title="Este campo solo admite numeros." required ></input>
	                <br>
	            </div>
	            <div class="d-grid mx-auto">
	                <input type="password" name="txtContraseña" placeholder="Contraseña" class="form-control" pattern="[a-z]+" title="Ingrese solo minúsculas" required ></input>
	                <br>
	            </div>   
	             <div class="d-grid mx-auto">
	                <input type=submit class="btn btn-primary" name="btnIngresar" value="Ingresar"></input>
	                <br>          
	            </div> 
            </form>
            <br />
            <br />
            <div class="col-4"></div>
            <div class="col-2"></div>
        </div>
        <div class="col-4"></div>
    </div>

	<%if(request.getAttribute("errorDni") != null)
	{
	%>
		<script type="text/javascript">
		function alertName(){
		alert("El dni es invalido. Por favor, reintente");
		} 
		</script> 
	<%
		}
	%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>