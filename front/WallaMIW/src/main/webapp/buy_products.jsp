<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="buyBean" class="es.uniovi.miw.ws.wallamiw.client.BuyHandler" scope="request"></jsp:useBean>
<c:if test="${param.id == null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body class="container" style="height: 100%">
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
<div class="row justify-content-center align-items-center h-100">
    <div>
        <h1 class="text-center">Comprar producto</h1>
        <div class="rounded border border-secondary p-5 m-2">
            <c:choose>
                <c:when test="${param.amount.matches('[0-9]+') && 'POST'.equalsIgnoreCase(pageContext.request.getMethod())}">
                    <div class="d-grid gap-2 text-center">
                        <c:set var="response" scope="request" value="${buyBean.buy(param.id, param.amount)}"></c:set>
                        <c:choose>
                            <c:when test="${response == 204}">
                                <h3>Comprado con éxito</h3>
                            </c:when>
                            <c:when test="${response == 404}">
                                <h3>No existe el producto</h3>
                            </c:when>
                            <c:when test="${response == 409}">
                                <h3>No hay existencias</h3>
                            </c:when>
                        </c:choose>
                        <a href="/WallaMIW/index.jsp" class="btn btn-primary">Volver a la lista de productos</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <form action="buy_products.jsp?id=<%= request.getParameter("id") %>" method="post"
                          class="d-grid gap-2">
                        <label for="amount" class="form-label">Cantidad</label>
                        <input type="number" class="form-control" id="amount" value="0" name="amount">
                        <input type="submit" class="btn btn-primary" value="Enviar">
                    </form>
                </c:otherwise>

            </c:choose>
        </div>
    </div>
</div>

</body>
</html>