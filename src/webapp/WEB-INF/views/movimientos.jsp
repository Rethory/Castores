<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Historial de Movimientos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Historial de Movimientos</h2>
        
        <div class="mb-3">
            <label for="filterMovimiento">Filtrar por tipo:</label>
            <select id="filterMovimiento" class="form-control" onchange="filtrarMovimientos()">
                <option value="">Todos</option>
                <option value="Entrada">Entradas</option>
                <option value="Salida">Salidas</option>
            </select>
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Fecha/Hora</th>
                    <th>Tipo</th>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Usuario</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${movimientos}" var="movimiento">
                    <tr class="movimiento-row" data-tipo="${movimiento.tipoMovimiento}">
                        <td><fmt:formatDate value="${movimiento.fechaHora}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <td>${movimiento.tipoMovimiento}</td>
                        <td>${movimiento.producto.nombreProducto}</td>
                        <td>${movimiento.cantidad}</td>
                        <td>${movimiento.usuario.nombre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
    function filtrarMovimientos() {
        const filtro = document.getElementById('filterMovimiento').value;
        const rows = document.getElementsByClassName('movimiento-row');
        
        for (let row of rows) {
            if (!filtro || row.getAttribute('data-tipo') === filtro) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    }
    </script>
</body>
</html>
