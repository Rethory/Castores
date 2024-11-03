<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Sistema de Inventario</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <!-- Menú común para todos los usuarios autenticados -->
                <li class="nav-item">
                    <a class="nav-link" href="/inventario">Inventario Activo</a>
                </li>

                <!-- Menú solo para ADMIN -->
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="/movimientos">Histórico</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/inventario/inactivos">Productos Inactivos</a>
                    </li>
                </sec:authorize>

                <!-- Menú solo para ALMACENISTA -->
                <sec:authorize access="hasRole('ROLE_ALMACENISTA')">
                    <li class="nav-item">
                        <a class="nav-link" href="/salidas">Registrar Salida</a>
                    </li>
                </sec:authorize>
            </ul>

            <!-- Información del usuario y cerrar sesión -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <span class="nav-link">
                        <sec:authentication property="principal.username" />
                    </span>
                </li>
                <li class="nav-item">
                    <form action="/logout" method="post" class="d-inline">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-link nav-link">Cerrar Sesión</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
