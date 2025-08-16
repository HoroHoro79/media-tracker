# Media Tracker

Media Tracker es un proyecto demo en Spring Boot para llevar un registro de series, películas, libros y videojuegos, con estados, puntuación y comentarios personales.  

---

## 1️⃣ Entorno de desarrollo

- **Java:** 17
- **IDE:** IntelliJ IDEA
- **Spring Boot:** 3.5.4
- **Gestión de dependencias:** Maven

Dependencias iniciales seleccionadas al crear el proyecto:

- Spring Web
- Spring Data JPA
- Spring Security
- Spring Boot DevTools
- Flyway
- H2 Database
- Validation
- Actuator

El proyecto se generó como **JAR**.

---

## 2️⃣ Control de versiones

- **Git** instalado en el equipo.
- Repositorio en GitHub: [https://github.com/HoroHoro79/media-tracker.git](https://github.com/HoroHoro79/media-tracker.git)
- Primer commit subido al repositorio.
- Uso de **SourceTree** para la gestión de commits, push y pull.

---

## 3️⃣ Configuración del IDE

- Abrir proyecto en IntelliJ IDEA.
- Configuración del **JDK 17**.
- Importación de dependencias con Maven (`mvn clean install`).
- Configuración de Spring Boot para usar **H2 Database** temporal:

```properties
spring.datasource.url=jdbc:h2:mem:mediatrackerdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
