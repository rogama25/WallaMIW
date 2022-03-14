<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="es.uniovi.miw.ws.wallamiw.client.AddProductWSClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% if (request.getCharacterEncoding() == null) {
    request.setCharacterEncoding("UTF-8");
}
if (request.getParameter("title")!=null && request.getParameter("description")!=null &&
    request.getParameter("category")!=null && request.getParameter("price")!=null &&
    request.getParameter("stock")!=null && request.getParameter("image")!=null){
AddProductWSClient.add(request.getParameter("title"),request.getParameter("description"),request.getParameter("price"),request.getParameter("category"),request.getParameter("image"),request.getParameter("stock"));
response.sendRedirect("/WallaMIW/index.jsp");
}%>

    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>

    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a href="/WallaMIW/" class="navbar-brand">
                <img src="https://cdn.freebiesupply.com/logos/large/2x/w-logo-png-transparent.png" height="28" alt="WallaMIW">
            </a>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <a href="/WallaMIW/" class="nav-item nav-link active">Inicio</a>
                    <a href="/WallaMIW/add_product.jsp" class="nav-item nav-link">Añadir producto</a>
                </div>
            </div>
        </div>
    </nav>
        <div class="container">
            <form action="add_product.jsp" method="post" role="form" data-toggle="validator" accept-charset="utf-8">
                <h2>Añadir producto</h2>
                <div class="form-group">
                    <label for="title" class="control-label col-xs-4">Título:</label>
                    <input type="text" name="title" id="title" class="form-control" required="true" />

                    <label for="description" class="control-label col-xs-4">Descripción:</label>
                    <input type="text" name="description" id="description" class="form-control" required="true" />

                    <label for="price" class="control-label col-xs-4">Precio:</label>
                    <input type="number" name="price" id="price" class="form-control" required="true" step="any" />

                    <label for="category" class="control-label col-xs-4">Categoría:</label>
                    <input type="text" name="category" id="category" class="form-control" required="true" />

                    <label for="image" class="control-label col-xs-4">Imagen (Usar sólo .jpg):</label>
                    <input type="text" name="image" id="image" class="form-control" required/>


                    <label for="stock" class="control-label col-xs-4">Stock:</label>
                    <input type="number" name="stock" id="stock" class="form-control" required="true" />


                    <br></br>
                    <button type="submit" class="btn btn-primary" >Guardar</button>
                </div>
            </form>
        </div>
    </body>

    </html>