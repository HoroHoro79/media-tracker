# 🎬 Media Tracker API

Aplicación backend para gestionar usuarios y, en el futuro, registrar y hacer seguimiento de **series, películas, libros y videojuegos**, con autenticación segura y documentación completa.

---

## 🛠 Tecnologías principales

| Tecnología                 | Propósito |
|-----------------------------|-----------|
| **Java 17**                | Lenguaje principal |
| **Spring Boot 3**          | Framework backend |
| **Spring Security + JWT**  | Autenticación y autorización |
| **PostgreSQL**             | Persistencia de datos |
| **Lombok**                 | Reducción de boilerplate |
| **Swagger / OpenAPI**      | Documentación y testing de la API |

---

## ⚡ Funcionalidad actual de la aplicación

Actualmente la aplicación permite:

- 🔐 **Gestión de usuarios**: registro, login y cambio de contraseña.  
- 🛡 **Autenticación JWT**: acceso seguro a recursos protegidos.  
- 📜 **Documentación con Swagger**: prueba y visualización de endpoints.  

> Nota: la idea futura es añadir **tracking de medios**: series, películas, libros, videojuegos, etc.

---

## 🗂 Arquitectura y flujo de seguridad (JWT)

```text
       ┌───────────────┐
       │   Cliente /   │
       │ Swagger / App │
       └───────┬───────┘
               │
               │ POST /login (user + pass)
               ▼
       ┌───────────────┐
       │  LoginController
       │ Valida usuario│
       │ y contraseña │
       └───────┬───────┘
               │
               │ Genera JWT
               ▼
       ┌───────────────┐
       │  JWT devuelto │
       │ al cliente    │
       └───────┬───────┘
               │
               │ Enviar Authorization: Bearer <token>
               ▼
       ┌───────────────┐
       │ JwtFilter     │
       │ Valida token  │
       │ y carga auth  │
       └───────┬───────┘
               │
       ┌───────▼────────┐
       │ Acceso a API   │
       │ autorizado o  │
       │ denegado       │
       └────────────────┘
