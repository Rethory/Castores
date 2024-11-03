<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Histórico de Movimientos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="includes/nav.jsp" %>
    
    <div class="container mt-4">
        <h2>Histórico de Movimientos</h2>
        
        <form action="/movimientos" method="GET" class="mb-4">
            <div class="row g-3">
                <div class="col-md-3">
                    <select name="tipoMovimiento" class="form-select">
                        <option value="">Todos los movimientos</option>
                        <option value="Entrada" ${param.tipoMovimiento == 'Entrada' ? 'selected' : ''}>Entradas</option>
                        <option value="Salida" ${param.tipoMovimiento == 'Salida' ? 'selected' : ''}>Salidas</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <input type="datetime-local" name="fechaInicio" class="form-control" 
                           value="${param.fechaInicio}" step="1">
                </div>
                <div class="col-md-3">
                    <input type="datetime-local" name="fechaFin" class="form-control" 
                           value="${param.fechaFin}" step="1">
                </div>
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary">Filtrar</button>
                    <a href="/movimientos" class="btn btn-secondary">Limpiar</a>
                </div>
            </div>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tipo</th>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Usuario</th>
                    <th>Fecha y Hora</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${movimientos}" var="movimiento">
                    <tr>
                        <td>${movimiento.idMovimiento}</td>
                        <td>${movimiento.tipoMovimiento}</td>
                        <td>${movimiento.nombreProducto}</td>
                        <td>${movimiento.cantidad}</td>
                        <td>${movimiento.nombreUsuario}</td>
                        <td>
                            <fmt:parseDate value="${movimiento.fechaHora}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both"/>
                            <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy HH:mm:ss"/>
                        </td>
                        
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
