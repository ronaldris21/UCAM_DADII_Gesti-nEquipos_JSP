<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="misTags" prefix="tags" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="css/main.css" rel="stylesheet"/>
</head>
<body>

  <div class="main-container">
        <div class="form-container">
            <img src="Logos/logo_yard_sale.svg" alt="logo" class="logo">
            <h1 class="title">Login</h1>
            <p class="subtitle">Inicia Sesi�n</p>

            <form action="Control?action=login" method="post" class="form">

                <label for="username" class="username" class="label">Usuario/correo</label>
                <input type="text" name="username" id="username" placeholder="RonaldRis21" class="input input-password" autofocus>
                
                <label for="password" class="password" class="label">Contrase�a</label>
                <input id="password"  type="password" name="password"  placeholder="*********" class="input input-password">
                
                <input type="submit" value="Iniciar Sesi�n" class="primary-btn login-btn">
                
                <tags:RequestErrorMessageTag/>
            </form>


        </div>
    </div>
    

</body>
</html>