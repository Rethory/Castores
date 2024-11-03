<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Salida de Productos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="index.jsp"/>
    
    <div class="container mt-4">
        <h2>Salida de Productos</h2>
        
        <div class="alert alert-info">
            Solo se muestran productos activos
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Producto</th>
                    <th>Cantidad Disponible</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${productos}" var="producto">
                    <tr>
                        <td>${producto.nombreProducto}</td>
                        <td>${producto.cantidad}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" 
                                    onclick="mostrarModalSalida(${producto.idProducto}, '${producto.nombreProducto}', ${producto.cantidad})"
                                    ${producto.cantidad == 0 ? 'disabled' : ''}>
                                Registrar Salida
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal Salida -->
    <div class="modal fade" id="modalSalida" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Registrar Salida</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formSalida">
                        <input type="hidden" id="productoId">
                        <div class="form-group">
                            <label>Producto:</label>
                            <input type="text" id="productoNombre" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            <label>Cantidad Disponible:</label>
                            <input type="text" id="cantidadDisponible" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            <label>Cantidad a Retirar:</label>
                            <input type="number" id="cantidadSalida" class="form-control" required min="1">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary" onclick="registrarSalida()">Registrar</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        function mostrarModalSalida(id, nombre, cantidad) {
            $('#productoId').val(id);
            $('#productoNombre').val(nombre);
            $('#cantidadDisponible').val(cantidad);
            $('#cantidadSalida').attr('max', cantidad);
            $('#modalSalida').modal('show');
        }

        function registrarSalida() {
            const cantidad = parseInt($('#cantidadSalida').val());
            const disponible = parseInt($('#cantidadDisponible').val());
            
            if (cantidad > disponible) {
                alert('No hay suficiente inventario disponible');
                return;
            }

            $.ajax({
                url: '/api/productos/' + $('#productoId').val() + '/cantidad',
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({
                    cantidad: -cantidad // Negativo para indicar salida
                }),
                success: function() {
                alert('Salida registrada correctamente');
                $('#modalSalida').modal('hide');
                location.reload();
        },
            error: function(xhr) {
            let mensaje = 'Error al procesar la solicitud';
            if (xhr.responseJSON && xhr.responseJSON.error) {
                mensaje = xhr.responseJSON.error;
            }
            alert(mensaje);
        }
    });
}
    </script>
</body>
</html>
