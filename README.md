# Sistema de Administración de Inventario

Este es un sistema web para la administración de inventario desarrollado con Java Spring Boot.

## Tecnologías Utilizadas

- **IDE:** IntelliJ IDEA (4.29.0)
- **Lenguaje:** Java 11
- **Framework:** Spring Boot 2.7.0
- **Base de Datos:** MySQL 8.0
- **Servidor Web:** Apache Tomcat (embebido)
- **Gestor de Dependencias:** Maven 3.8.1

## Requisitos Previos

1. JDK 11 instalado
2. MySQL 8.0 instalado
3. Maven instalado
4. IDE

## Pasos para Ejecutar la Aplicación

1. Clonar el repositorio:
   ```bash
   

2. Crear la base de datos:

Abrir MySQL Workbench o cliente MySQL
Ejecutar los scripts en el siguiente orden:
/src/main/resources/scripts/schema.sql
/src/main/resources/scripts/data.sql

3. Configurar la conexión a la base de datos:

Abrir /src/main/resources/application.properties
Modificar las credenciales de MySQL según tu configuración

4. Importar el proyecto en el IDE preferido:

File -> Open -> Seleccionar la carpeta del proyecto
Click en Finish

5. Ejecutar la aplicación:

mvn clean package
y posteriormente
mvn spring-boot:run

6. Acceder a la aplicación:

Abrir navegador web
Ir a http://localhost:8080

Credenciales por Defecto
Administrador:
Usuario: admin@empresa.com
Contraseña: admin123
Almacenista:
Usuario: almacen@empresa.com
Contraseña: almacen123

/inventario-app
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/empresa/
│   │   │       ├── config/
│   │   │       ├── controllers/
│   │   │       ├── models/
│   │   │       ├── repositories/
│   │   │       └── services/
│   │   ├── resources/
│   │   │   └── scripts/
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── views/
└── pom.xml

Funcionalidades
Administrador
Ver inventario
Agregar productos
Aumentar inventario
Dar de baja/reactivar productos
Ver histórico de movimientos
Almacenista
Ver inventario
Registrar salidas de productos
