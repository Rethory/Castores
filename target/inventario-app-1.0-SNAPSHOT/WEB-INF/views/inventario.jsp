<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Inventario</h2>
        
        <form action="/inventario/agregar" method="POST" class="mb-4">
            <div class="row">
                <div class="col-md-4">
                    <input type="text" class="form-control mb-2" name="nombreProducto" placeholder="Nombre del producto" required>
                </div>
                <div class="col-md-4">
                    <input type="number" class="form-control mb-2" name="cantidad" placeholder="Cantidad" required>
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary">Agregar Producto</button>
                </div>
            </div>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${productos}" var="producto">
                    <tr>
                        <td>${producto.idProducto}</td>
                        <td>${producto.nombreProducto}</td>
                        <td>${producto.cantidad}</td>
                        <td>
                            <form action="/inventario/editar/${producto.idProducto}" method="POST" style="display: inline;">
                                <input type="number" name="cantidad" class="form-control form-control-sm d-inline" style="width: 100px;">
                                <button type="submit" class="btn btn-warning btn-sm">Actualizar</button>
                            </form>
                            <form action="/inventario/eliminar/${producto.idProducto}" method="POST" style="display: inline;">
                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
