
# Kruger Fullstack API – Backend

Este es el backend del sistema de gestión de usuarios, proyectos y tareas.  
Construido con **Spring Boot 3**, expuesto como API REST y protegido con JWT.

---

## 🚀 Requisitos

- Docker
- Docker Compose

---

## ▶️ Ejecución del proyecto

```bash
docker-compose up --build
````

Esto compilará y levantará dos servicios:

* **app**: aplicación Spring Boot (puerto `8080`)
* **db**: PostgreSQL (puerto `5435`, usuario: `postgres`, password: `k13st2`)

---

## 🌐 Acceso

### Swagger UI

```
http://localhost:8080/kdevfull/swagger
```

### Endpoints principales (versión v1)

* `POST   /auth/login` → Obtener token JWT
* `POST   /kdevfull/v1/users` → Crear usuario (`ADMIN` requerido)
* `GET    /kdevfull/v1/users`
* `POST   /kdevfull/v1/projects`
* `GET    /kdevfull/v1/tasks`

> Usa el botón "Authorize" en Swagger para autenticarte con un token JWT:
> `Bearer eyJhbGci...`

---

## 🧪 Credenciales de prueba

Si se inicializó la base con el script `init.sql`:

```
usuario: admin
contraseña: 12345
rol: ADMIN

usuario: user
contraseña: 12345
rol: USER
```

*(El password está previamente encriptado en el script de inicialización)*

*(Se insertaron proyectos y tareas en el script de inicialización)*

---

## ⚙️ Variables configurables

Estas variables están definidas en `application.properties` y expuestas en `docker-compose.yml`:

| Variable                | Descripción                        | Valor por defecto                      |
| ----------------------- | ---------------------------------- | -------------------------------------- |
| `DB_URL`                | URL de conexión a PostgreSQL       | `jdbc:postgresql://db:5432/postgres`   |
| `DB_USER`               | Usuario de la base                 | `postgres`                             |
| `DB_PASSWORD`           | Contraseña de la base              | `k13st2`                               |
| `TOKEN_SECRET`          | Clave secreta para JWT             | `27ecde8e-ee57-4764-b02f-aa2f89de1ef1` |
| `TOKEN_EXPIRATION_TIME` | Expiración del JWT en milisegundos | `600000`                                 |

---

## 📦 Construcción manual (opcional)

Si quieres compilar sin Docker:

```bash
mvn clean package -DskipTests
java -jar target/*.jar
```

---

## 📚 Notas

* CORS está habilitado globalmente para todos los orígenes.
* Swagger muestra los endpoints protegidos con JWT.
* Requiere Java 17 (Docker lo usa automáticamente).


# Kruger Fullstack – Frontend

Este es el frontend de la prueba técnica para Kruger Corporation.  
Construido con **Next.js**, **React**, **TailwindCSS**, y manejo de sesión con JWT usando cookies.

---

## 🚀 Requisitos

- Node.js 20+
- npm 9+ o pnpm/yarn equivalente
- `npm install` para instalar las dependencias del proyecto
- Se debe iniciar el contenedor con el proyecto backend previamente
---

## ▶️ Scripts disponibles

```bash
npm run dev      # Ejecuta la app en modo desarrollo (http://localhost:3000)
npm run build    # Compila la app para producción
npm start        # Inicia la app en modo producción
npm run lint     # Ejecuta el linter (ESLint)
