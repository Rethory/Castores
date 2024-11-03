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
    <div class="container mt-4">
        <h2>Histórico de Movimientos</h2>
        
        <form action="/movimientos" method="GET" class="mb-4">
            <div class="row">
                <div class="col-md-3">
                    <select name="tipoMovimiento" class="form-select">
                        <option value="">Todos los movimientos</option>
                        <option value="Entrada">Entradas</option>
                        <option value="Salida">Salidas</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <input type="datetime-local" name="fechaInicio" class="form-control" placeholder="Fecha inicio">
                </div>
                <div class="col-md-3">
                    <input type="datetime-local" name="fechaFin" class="form-control" placeholder="Fecha fin">
                </div>
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary">Filtrar</button>
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
                        <td><fmt:formatDate value="${movimiento.fechaHora}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
