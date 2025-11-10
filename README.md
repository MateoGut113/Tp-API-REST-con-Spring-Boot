# Trabajo Práctico - APIs REST con Spring Boot

## 📝 Descripción del proyecto
Este proyecto es una aplicación web desarrollada con **Java y Spring Boot** que permite gestionar productos dentro de un sistema de e-commerce básico.
Incluye funcionalidades como crear, listar, actualizar y eliminar productos, además de filtrar por categoría y modificar el stock de forma parcial.\
Utiliza una base de datos en memoria **(H2)** para facilitar pruebas y visualizar los cambios dinámicamente sin necesidad de una base de datos externa.\
Está estructurado en capas (modelo, DTOs, repositorio, servicio, controlador) y aplica validaciones con **Bean Validation**, manejo global de excepciones y documentación interactiva con Swagger/OpenAPI.\
La API está diseñada para retornar respuestas claras y consistentes, utilizando códigos HTTP apropiados como **200, 201, 204, 400, 404 y 500**, según el resultado de cada operación.

## ⚙️ Tecnologías utilizadas
- **Java. Versión: 17**
- **Spring Boot. Versión: 3.5.7**
- **Gradle**
- **Lombok** (Utilizado para simplificar getters/setters)

Se hizo uso de **Spring Initializr** (start.spring.io) para crear el proyecto.

## 🚀 Instrucciones para clonar y ejecutar el proyecto
1. Clonar el repositorio:

   ```bash
   git clone https://github.com/MateoGut113/Tp-API-REST-con-Spring-Boot.git
   cd Tp-API-REST-con-Spring-Boot

2. Ejecutar el proyecto con Gradle:

    ```bash
    ./gradlew bootRun

O directamente desde el IDE ejecutando la clase `ProductosApiApplication`.

## 🌐 Tabla de endpoints

```
| Método | Ruta                                 | Descripción                                    |  Código HTTP          |
|--------|--------------------------------------|------------------------------------------------|-----------------------|
| GET    | /api/productos                       | Listar todos los productos                     | 200                   |
| GET    | /api/productos/{id}                  | Obtener producto por ID                        | 200 / 404             |
| GET    | /api/productos/categoria/{categoria} | Filtrar productos por categoría                | 200 / 500             |
| POST   | /api/productos                       | Crear nuevo producto (valida DTO)              | 201 / 400 / 500       |
| PUT    | /api/productos/{id}                  | Actualizar producto completo                   | 200 / 400 / 404 / 500 |
| PATCH  | /api/productos/{id}/stock            | Actualizar solo el stock                       | 200 / 400 / 404 / 500 |
| DELETE | /api/productos/{id}                  | Eliminar producto                              | 204 / 404             |
```

## 📸 Capturas de pantalla

**Documentación completa de endpoints**
1. Obtener producto por ID
![Captura de pantalla - GET productos 1°.png](capturas/Captura%20de%20pantalla%20-%20GET%20productos%201%C2%B0.png)
![Captura de pantalla - GET productos 2°.png](capturas/Captura%20de%20pantalla%20-%20GET%20productos%202%C2%B0.png)


2. Actualizar producto (completo)
![Captura de pantalla - Actualizar producto 1°.png](capturas/Captura%20de%20pantalla%20-%20Actualizar%20producto%201%C2%B0.png)
![Captura de pantalla - Actualizar producto 2°.png](capturas/Captura%20de%20pantalla%20-%20Actualizar%20producto%202%C2%B0.png)
![Captura de pantalla - Actualizar producto 3°.png](capturas/Captura%20de%20pantalla%20-%20Actualizar%20producto%203%C2%B0.png)


3. Eliminar producto
![Captura de pantalla - Eliminar producto 1°.png](capturas/Captura%20de%20pantalla%20-%20Eliminar%20producto%201%C2%B0.png)
![Captura de pantalla - Eliminar producto 2°.png](capturas/Captura%20de%20pantalla%20-%20Eliminar%20producto%202%C2%B0.png)


4. Obtener todos los productos
![Captura de pantalla - Obtener todos los productos.png](capturas/Captura%20de%20pantalla%20-%20Obtener%20todos%20los%20productos.png)


5. Crear un producto
![Captura de pantalla - Crear un producto 1°.png](capturas/Captura%20de%20pantalla%20-%20Crear%20un%20producto%201%C2%B0.png)
![Captura de pantalla - Crear un producto 2°.png](capturas/Captura%20de%20pantalla%20-%20Crear%20un%20producto%202%C2%B0.png)


6. Actualizar stock
![Captura de pantalla - Actualizar stock 1°.png](capturas/Captura%20de%20pantalla%20-%20Actualizar%20stock%201%C2%B0.png)
![Captura de pantalla - Actualizar stock 2°.png](capturas/Captura%20de%20pantalla%20-%20Actualizar%20stock%202%C2%B0.png)
![Captura de pantalla - Actualizar stock 3°.png](capturas/Captura%20de%20pantalla%20-%20Actualizar%20stock%203%C2%B0.png)


7. Buscar por categoria
![Captura de pantalla - Buscar por categoria 1°.png](capturas/Captura%20de%20pantalla%20-%20Buscar%20por%20categoria%201%C2%B0.png)
![Captura de pantalla - Buscar por categoria 2°.png](capturas/Captura%20de%20pantalla%20-%20Buscar%20por%20categoria%202%C2%B0.png)


**Prueba exitosa de POST (creando producto):**
![Captura de pantalla - CREATED 201.png](capturas/Captura%20de%20pantalla%20-%20CREATED%20201.png)


**Prueba de GET (listando productos)**
![Captura de pantalla - GET LIST.png](capturas/Captura%20de%20pantalla%20-%20GET%20LIST.png)


**Error 404 cuando producto no existe**
![Captura de pantalla - ERROR 404.png](capturas/Captura%20de%20pantalla%20-%20ERROR%20404.png)


**Error 400 de validación**
![Captura de pantalla - ERROR 400.png](capturas/Captura%20de%20pantalla%20-%20ERROR%20400.png)


**Consola H2 con datos persistidos**
![Captura de pantalla - Base de Datos.png](capturas/Captura%20de%20pantalla%20-%20Base%20de%20Datos.png)

## 🔍 Instrucciones para acceder a Swagger UI y consola H2
Asegurarse de que la aplicación esté corriendo (./gradlew bootRun).

### 📘 Swagger UI
Abrir el navegador y acceder a:

http://localhost:8080/swagger-ui/index.html

Desde allí puedes:

- Probar todos los endpoints de la API
- Ver los modelos de entrada y salida de datos (DTOs)
- Consultar los códigos de respuesta HTTP

------------------------------------------------------------------
### 🗄️ Consola H2 (Base de datos en memoria)
Acceder a:

http://localhost:8080/h2-console

Usar los siguientes datos de conexión:
- JDBC URL: jdbc:h2:mem:productosdb
- Usuario: sa
- Contraseña: (dejar vacío)

Luego:
- Probar la conexión con "Test Connection"
- Presionar "Connect" para visualizar la tabla producto y consultar los datos persistidos.

## 💭 Conclusiones personales sobre lo aprendido
En lo personal este trabajo práctico me permitió:

- Consolidar los fundamentos de Spring Boot aplicando **arquitectura en capas**, visto en las actividades.
- Implementar una **API REST**, integrando conceptos como **DTOs**, validaciones con **Bean Validation** y manejo global de excepciones.
- Trabajar con **Spring Data JPA** y una base de datos en memoria **(H2)**, lo que me ayudó a comprender mejor el vinculo entre persistencia y el ciclo de vida de los datos.
- Documentar la API con **Swagger/OpenAPI**, lo que da una visión más profesional sobre cómo presentar y probar servicios web.
- Familiarizarme con mas gestiones de dependencias usando **Gradle**.

Finalmente, considero que este trabajo fue una excelente oportunidad para integrar múltiples conceptos
y acercarme al desarrollo de aplicaciones reales con un enfoque mas profesional.

## 👤 Tu nombre y legajo
**Nombre:** Mateo Gutierrez\
**Comision:** 3k10\
**Legajo:** 48855
