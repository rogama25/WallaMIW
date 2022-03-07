<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="buyBean" class="es.uniovi.miw.buyjsp.BuyHandler" scope="request"></jsp:useBean>
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
<body class="container">
<div class="row justify-content-center">
    <h1 class="text-center">Comprar producto</h1>
    <c:choose>
        <c:when test="${param.id.matches('[0-9]+') && param.amount.matches('[0-9]+') && 'POST'.equalsIgnoreCase(pageContext.request.getMethod())}">
            <c:set var="response" scope="request" value="${buyBean.buy(param.id, param.amount)}"></c:set>
            <c:out value="${response}"></c:out>
            <c:choose>
                <c:when test="${response == 204}">
                    <p>Comprado con éxito</p>
                </c:when>
                <c:when test="${response == 404}">
                    <p>No existe el producto</p>
                </c:when>
                <c:when test="${response == 409}">
                    <p>No hay existencias</p>
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <form action="buy.jsp?id=<%= request.getParameter("id") %>" method="post" class="justify-content-center">
                <label for="amount" class="form-label">Cantidad</label>
                <input type="number" class="form-control" id="amount" value="0" name="amount">
                <input type="submit" class="btn btn-primary width-100" value="Enviar">
            </form>
        </c:otherwise>

    </c:choose>
</div>

</body>
</html>