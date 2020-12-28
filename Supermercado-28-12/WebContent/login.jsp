<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Inicie Sesión</title>

  <!-- Bootstrap core CSS -->
  <link href="style/mainpage/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="style/mainpage/modern-business.css" rel="stylesheet">
  
    <link href="style/login/login.css" rel="stylesheet">
  
  
    
</head>
<body>
	 
<!-- Navigation -->
	  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	    <div class="container">
	      <a class="navbar-brand" href="index.jsp">Supermercado</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      <div class="collapse navbar-collapse" id="navbarResponsive">
	        <ul class="navbar-nav ml-auto">
	           <li class="nav-item active">
	            <a class="nav-link" href="#">Iniciar Sesión
	              <span class="sr-only">(current)</span>
	            </a>
          	  </li>
	          <li class="nav-item">
	            <a class="nav-link" href="registro.jsp">Registrarse</a>
	          </li>
	          
	        </ul>
	      </div>
	    </div>
	  </nav>
	  
<div class="container-fluid">
  <div class="row no-gutter">
    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
    <div class="col-md-8 col-lg-6">
      <div class="login d-flex align-items-center py-5">
        <div class="container">
          <div class="row">
            <div class="col-md-9 col-lg-8 mx-auto ">
              <h3 class="login-heading mb-4 text-center">Ingresá a tu cuenta</h3>
              
              <form action="Inicio" method="post">
                <div class="form-label-group">
                  <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Correo electrónico" required >
                  <label for="inputEmail">Correo electrónico</label>
                </div>

                <div class="form-label-group">
                  <input type="password" name="pass" id="inputPassword" class="form-control" placeholder="Contraseña" required>
                  <label for="inputPassword">Contraseña</label>
                </div>

             
                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Ingresar</button>
                <div class="text-center">
                  No tenés cuenta? <a  href="registro.jsp">Registrate aquí</a></div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>