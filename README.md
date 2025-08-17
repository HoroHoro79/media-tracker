# Media Tracker API

Aplicación para el seguimiento de medios con gestión de usuarios y autenticación segura mediante JWT.

---

## Tecnologías

| Tecnología                | Uso principal |
|----------------------------|---------------|
| **Java 17**               | Lenguaje de programación |
| **Spring Boot 3**         | Framework principal |
| **Spring Security + JWT** | Autenticación y autorización |
| **PostgreSQL**            | Base de datos |
| **Lombok**                | Reducción de boilerplate |
| **Swagger / OpenAPI**     | Documentación y pruebas de la API |

---

## Funcionalidad actual de la aplicación

Actualmente la aplicación permite:

- Registrar y gestionar usuarios.  
- Acceder a recursos protegidos mediante autenticación JWT.  
- Cambiar la contraseña de un usuario.  
- Documentar y probar la API con Swagger.  

> Nota: la idea futura es añadir seguimiento de **series, películas, libros, videojuegos**, etc.

---

## Flujo de seguridad (JWT)

```text
          +-------------------+
          |      Login        |
          |-------------------|
          | Usuario + Pass    |
          +-------------------+
                    |
                    v
          +-------------------+
          | Validación usuario|
          | y contraseña      |
          +-------------------+
                    |
          Genera token JWT
                    v
          +-------------------+
          | Devuelve token al |
          | cliente           |
          +-------------------+
                    |
                    v
          +-------------------+
          | Peticiones a API  |
          | con Authorization |
          | Bearer <token>    |
          +-------------------+
                    |
                    v
          +-------------------+
          | JwtFilter valida  |
          | token y carga     |
          | Authentication   |
          +-------------------+
                    |
                    v
          +-------------------+
          | Acceso concedido  |
          | o denegado        |
          +-------------------+
