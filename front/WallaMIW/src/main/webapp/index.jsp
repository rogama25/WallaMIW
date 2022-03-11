<%@page import="es.uniovi.miw.ws.wallamiw.client.ListProductsWSClient"%>
<%@page import="es.uniovi.miw.ws.wallamiw.client.VoteProductsWSClient"%>
<%@ page import="es.uniovi.miw.ws.wallamiw.webservices.Product" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% if (request.getParameter("id")!=null){
  VoteProductsWSClient.addFavoriteProduct(request.getParameter("id"));
}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <title>Lista de productos</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a href="http://localhost:8083/wallaMIW/" class="navbar-brand">
      <img src="https://cdn.freebiesupply.com/logos/large/2x/w-logo-png-transparent.png" height="28" alt="WallaMIW">
    </a>
    <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <div class="navbar-nav">
        <a href="http://localhost:8083/wallaMIW/" class="nav-item nav-link active">Inicio</a>
        <a href="http://localhost:8080/WallaMIW/add_product.jsp" class="nav-item nav-link">Añadir producto</a>
      </div>
    </div>
  </div>
</nav>
<h1>Lista de productos</h1>
<div class="container">
  <div class="row">
    <div class="col-12">
      <table class="table table-image">
        <thead>
        <tr>
          <th scope="col">Título</th>
          <th scope="col">Imagen</th>
          <th scope="col">Stock</th>
          <th scope="col">Precio</th>
          <th scope="col">Categoría</th>
          <th scope="col">Descripción</th>
          <th scope="col">Favoritos</th>
          <th scope="col">Comprar</th>
        </tr>
        </thead>
        <tbody>
        <% List<Product> listado= ListProductsWSClient.getListaProductos();  %>

        <%
          for (int i = 0; i < listado.size(); ++i) {
        %>
        <div class="row">
          <tr>
            <th scope="row"><%= listado.get(i).getTitle()%></th>
            <td class="w-25">
              <img src="<%= listado.get(i).getImage()%>" class="img-fluid img-thumbnail" alt="Sheep">
            </td>
            <td><%= listado.get(i).getStock()%></td>
            <td><%= listado.get(i).getPrice()%></td>
            <td><%= listado.get(i).getCategory()%></td>
            <td><%= listado.get(i).getDescription()%></td>
            <td>
              <div>
                <%= listado.get(i).getFavorites()%>
                <a href="http://localhost:8080/wallaMIW?id=<%=listado.get(i).getId()%>" class="btn btn-default btnLike">
                  <img class="imgLike" src="https://www.kindpng.com/picc/m/697-6979063_instagram-like-icon-png-clipart-png-download-love.png" />
                </a>
              </div>

            </td>
            <td><a class="btn btn-primary" href="http://localhost:8080/WallaMIW/buy_products.jsp?id=<%=listado.get(i).getId()%>" role="button">Comprar</a></td>
          </tr>
        </div>
        <%
          }
        %>
        </tbody>
      </table>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
