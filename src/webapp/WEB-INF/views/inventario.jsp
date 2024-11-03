<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Inventario</title>
  </head>
  <body>
    <h2>Inventario</h2>

    <sec:authorize access="hasRole('ADMIN')">
      <!-- Formulario para agregar producto (solo admin) -->
      <div>
        <h3>Agregar Nuevo Producto</h3>
        <form id="formAgregarProducto">
          <input
            type="text"
            name="nombreProducto"
            placeholder="Nombre del producto"
            required
          />
          <button type="submit">Agregar</button>
        </form>
      </div>
    </sec:authorize>

    <!-- Lista de productos -->
    <table>
      <thead>
        <tr>
          <th>Producto</th>
          <th>Cantidad</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${productos}" var="producto">
          <tr>
            <td>${producto.nombreProducto}</td>
            <td>${producto.cantidad}</td>
            <td>
              <sec:authorize access="hasRole('ADMIN')">
                <button onclick="aumentarInventario(${producto.id})">
                  Aumentar
                </button>
                <button onclick="cambiarEstatus(${producto.id})">
                  ${producto.estatus == 1 ? 'Desactivar' : 'Activar'}
                </button>
              </sec:authorize>
              <sec:authorize access="hasRole('ALMACENISTA')">
                <button onclick="registrarSalida(${producto.id})">
                  Salida
                </button>
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
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>
          <form id="formEditar" method="POST">
            <input
              type="hidden"
              name="${_csrf.parameterName}"
              value="${_csrf.token}"
            />
            <div class="modal-body">
              <div class="mb-3">
                <label class="form-label">Nombre del Producto</label>
                <input
                  type="text"
                  class="form-control"
                  id="editNombreProducto"
                  readonly
                />
              </div>
              <div class="mb-3">
                <label for="editCantidad" class="form-label"
                  >Nueva Cantidad</label
                >
                <input
                  type="number"
                  class="form-control"
                  id="editCantidad"
                  name="cantidad"
                  required
                  min="0"
                />
              </div>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Cancelar
              </button>
              <button type="submit" class="btn btn-primary">Actualizar</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <script>
      function mostrarModalEditar(id, nombre, cantidad) {
        document.getElementById("editNombreProducto").value = nombre;
        document.getElementById("editCantidad").value = cantidad;
        document.getElementById("formEditar").action =
          "/inventario/editar/" + id;
        new bootstrap.Modal(
          document.getElementById("editarProductoModal")
        ).show();
      }
    </script>
  </body>
</html>
