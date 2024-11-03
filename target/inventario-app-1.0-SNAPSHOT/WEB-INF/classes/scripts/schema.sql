-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS inventario_db;
USE inventario_db;

-- Crear tabla de roles
CREATE TABLE IF NOT EXISTS roles (
    idRol INT(2) PRIMARY KEY AUTO_INCREMENT,
    nombreRol VARCHAR(50) NOT NULL
);

-- Crear tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    idUsuario INT(6) PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    idRol INT(2),
    estatus INT(1) DEFAULT 1,
    FOREIGN KEY (idRol) REFERENCES roles(idRol)
);

-- Crear tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    idProducto INT(6) PRIMARY KEY AUTO_INCREMENT,
    nombreProducto VARCHAR(100) NOT NULL,
    cantidad INT DEFAULT 0,
    estatus INT(1) DEFAULT 1,
    fechaCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    idUsuario INT(6),
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);

-- Crear tabla de movimientos
CREATE TABLE IF NOT EXISTS movimientos (
    idMovimiento INT(6) PRIMARY KEY AUTO_INCREMENT,
    tipoMovimiento ENUM('Entrada', 'Salida') NOT NULL,
    cantidad INT NOT NULL,
    fechaHora DATETIME DEFAULT CURRENT_TIMESTAMP,
    idProducto INT(6),
    idUsuario INT(6),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto),
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);
