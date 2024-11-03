-- Insertar roles básicos
INSERT INTO roles (nombreRol) VALUES
('Administrador'),
('Almacenista');

-- Insertar usuario administrador
INSERT INTO usuarios (nombre, correo, contraseña, idRol, estatus) VALUES
('Administrador', 'admin@empresa.com', 'admin123', 1, 1);

-- Insertar usuario almacenista
INSERT INTO usuarios (nombre, correo, contraseña, idRol, estatus) VALUES
('Almacenista', 'almacen@empresa.com', 'almacen123', 2, 1);
