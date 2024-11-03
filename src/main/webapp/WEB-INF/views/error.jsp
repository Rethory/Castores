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
        <div class="card">
            <div class="card-header bg-danger text-white">
                <h4>Error</h4>
            </div>
            <div class="card-body">
                <h5 class="card-title">Ha ocurrido un error inesperado</h5>
                <p class="card-text">
                    <c:choose>
                        <c:when test="${status == 403}">
                            No tienes permisos para acceder a esta página.
                        </c:when>
                        <c:when test="${status == 404}">
                            La página que buscas no existe.
                        </c:when>
                        <c:when test="${status == 500}">
                            Error interno del servidor. Por favor, contacta al administrador.
                        </c:when>
                        <c:otherwise>
                            Ha ocurrido un error inesperado. Por favor, intenta nuevamente.
                        </c:otherwise>
                    </c:choose>
                </p>
                <a href="/inventario" class="btn btn-primary">Volver al Inventario</a>
                <a href="/login" class="btn btn-secondary">Ir al Login</a>
            </div>
        </div>
    </div>
</body>
</html>
