<%@ page import="static es.uniovi.miw.ws.wallamiw.api.VoteAPI.addFav" %><%--
  Created by IntelliJ IDEA.
  User: rodri
  Date: 06/05/2022
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/json;charset=UTF-8" language="java" %>
    <%= addFav(request.getParameter("id")) %>
