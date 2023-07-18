<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="entidad.Usuario"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error!!!!</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <div class="navbar-brand mb-0 h1" href="ServletUsuario?Param=1">
                            <img src="https://cdn-icons-png.flaticon.com/512/5394/5394174.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top">
                        </div>
                    </li>
    				<div class="container">
    					<div class="navbar-brand text-center" class="d-inline-block align-text-top" href="#"> Clinica Medica SA</div>
  					</div>
                </ul>

            </div>
        </div>
    </nav>
    
    <div class="row">
		<div class="col-4"></div>
		<div class="col">

			<div class="col-4"></div>
			<div class="col-2"></div>
			<br />
			<div class="d-grid mx-auto">
				<% String mensaje = (String) request.getSession().getAttribute("errorMessage"); %>
        	<h2 class="text-center"><%= mensaje %></h2>
				<br>
				
				<a href="ServletUsuario?Param=1" class="btn btn-info">Regresar</a>
			</div>
			
			<div class="col-4"></div>
			<div class="col-2"></div>
		</div>
		<div class="col-4"></div>
	</div>


</div>
</div>
	

	
	
</body>
</html>