# Sistema de Administración de Inventario

Este es un sistema web para la administración de inventario desarrollado con Java Spring Boot.

## Las partes escritas del test y el diagrama entidad relación se encuentran en la siguiente ruta: 
- src\main\resources\Entidad Relacion db.png
- src\main\resources\test de conocimiento.txt
- link del video: https://drive.google.com/file/d/1a1h1ryHdTCKMlEA4xzkbKCCnUjOEMf3N/view?usp=sharing
  
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
   git clone https://github.com/Rethory/Castores.git

2. Crear la base de datos:

Abrir MySQL Workbench o cliente MySQL
Ejecutar los scripts en el siguiente orden:
/src/main/resources/scripts/schema.sql
/src/main/resources/scripts/data.sql

3. Configurar la conexión a la base de datos:

Abrir /src/main/resources/application.properties
Modificar las credenciales de MySQL según tu configuración

4. Importar el proyecto en el IDE preferido(Si decidiste descargar como zip el proyecto y no con clonación de github):

File -> Open -> Seleccionar la carpeta del proyecto
Click en Finish

5. Ejecutar la aplicación:
```bash
mvn clean package
```
y posteriormente
```bash
mvn spring-boot:run
```
6. Acceder a la aplicación:

Abrir navegador web
Ir a http://localhost:8080

## Credenciales por Defecto
- Administrador:
- Usuario: admin@empresa.com
- Contraseña: admin123
- Almacenista:
- Usuario: almacen@empresa.com
- Contraseña: almacen123


