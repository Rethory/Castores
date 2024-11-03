<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="alert alert-danger">
            <h4 class="alert-heading">Error</h4>
            <p>
                <c:choose>
                    <c:when test="${status == 403}">
                        No tienes permisos para acceder a esta página.
                    </c:when>
                    <c:when test="${status == 404}">
                        La página que buscas no existe.
                    </c:when>
                    <c:otherwise>
                        Ha ocurrido un error inesperado.
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
        <a href="/" class="btn btn-primary">Volver al inicio</a>
    </div>
</body>
</html>
