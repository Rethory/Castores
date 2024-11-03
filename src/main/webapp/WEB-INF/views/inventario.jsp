<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="includes/nav.jsp" %>
    
    <div class="container mt-4">
        <h2>Inventario</h2>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="mb-4">
                <h3>Agregar Nuevo Producto</h3>
                <form action="/inventario/agregar" method="POST" class="row g-3">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="col-md-4">
                        <input type="text" name="nombreProducto" class="form-control" placeholder="Nombre del producto" required/>
                    </div>
                    <div class="col-md-4">
                        <input type="number" name="cantidad" class="form-control" placeholder="Cantidad" required min="0"/>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-primary">Agregar</button>
                    </div>
                </form>
            </div>
        </sec:authorize>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Producto</th>
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
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <button class="btn btn-warning btn-sm" 
                                        onclick="mostrarModalEditar(${producto.idProducto}, '${producto.nombreProducto}', ${producto.cantidad})">
                                    Editar
                                </button>
                                
                                <form action="/inventario/eliminar/${producto.idProducto}" method="POST" style="display: inline;">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                </form>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <!-- Modal para editar producto -->
        <div class="modal fade" id="editarProductoModal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Editar Producto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <form id="formEditar" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label class="form-label">Nombre del Producto</label>
                                <input type="text" class="form-control" id="editNombreProducto" readonly/>
                            </div>
                            <div class="mb-3">
                                <label for="editCantidad" class="form-label">Nueva Cantidad</label>
                                <input type="number" class="form-control" id="editCantidad" name="cantidad" required min="0"/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary">Actualizar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function mostrarModalEditar(id, nombre, cantidad) {
            document.getElementById('editNombreProducto').value = nombre;
            document.getElementById('editCantidad').value = cantidad;
            document.getElementById('formEditar').action = '/inventario/editar/' + id;
            var myModal = new bootstrap.Modal(document.getElementById('editarProductoModal'));
            myModal.show();
        }
    </script>
</body>
</html>
