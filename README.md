# API Documentation: TicketSystem

## Especificaciones Técnicas del Proyecto

**Java**: 17  
**Spring Boot**: 3.3.0  
**Maven**: 3.0  
**Base de Datos**: MySQL

## Docker

La aplicación se encuentra alojada en Docker, lo que permite un despliegue simplificado del servicio junto con la base de datos MySQL. Asegúrate de tener Docker y Docker Compose instalados correctamente en tu máquina.

### Requisitos previos

- **Docker**: Asegúra tener Docker instalado y funcionando en tu sistema.  

### Pasos para ejecutar la aplicación con Docker

1. **Construir las imágenes de los contenedores**:
   
   docker-compose build

2. **Levantar el contenedor**:
   
   docker-compose up
   
3. **Acceso a la aplicación**:
   
Una vez que los contenedores estén corriendo, la API estará disponible en el puerto configurado, por defecto:
  http://localhost:8080/api/v1/tickets



## Clonación del Repositorio

Clona el repositorio ejecutando el siguiente comando:


git clone https://github.com/sebassuaza98/ticketsystem.git
```

Una vez clonado el repositorio, abre tu IDE de preferencia, carga el proyecto y en la raíz del proyecto ejecuta el siguiente comando para construirlo:


mvn clean install
```

---

## Arquitectura del Sistema

La arquitectura está diseñada bajo el enfoque **Hexagonal**, lo que permite una separación clara de responsabilidades y facilita la adaptabilidad a cambios futuros.

---

## Manual de Instalación y Configuración

### Requisitos previos

- **Java**: 17
- **Maven**: Para la construcción del proyecto.
- **Docker**: Para el despliegue del servicio y la base de datos.

### Configuración del Entorno

1. Clona el repositorio del proyecto:

    git clone https://github.com/sebassuaza98/ticketsystem.git
    ```

2. Instala las dependencias con Maven:

    mvn clean install
    ```

3. Configura la base de datos en el archivo `application.properties`:

    ```properties
    spring.application.name=ticketsystem
    spring.datasource.url=jdbc:mysql://localhost:3307/bdticketsystem
    spring.datasource.username=luc
    spring.datasource.password=herlubon14
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.sql.init.mode=always
    ```

### Ejecución de la Aplicación

Para iniciar la aplicación, ejecuta el siguiente comando:

mvn spring-boot:run
```
---
## Descripción

El API `TicketSystem` gestiona la creación, actualización, eliminación y consulta de tickets en un sistema. Cada ticket tiene un estado (`ABIERTO` o `CERRADO`) y está asociado a un usuario.

---
## Base URL

```plaintext
/api/v1/tickets
```
---

## Endpoints

### 1. Crear un Ticket

**Endpoint**:  
`POST /api/v1/tickets`

**Descripción**:  
Crea un nuevo ticket en el sistema.

**Request Body**:
```json
{
  "usuario": "nombre_usuario",
  "status": "ABIERTO"
}
```

**Responses**:
- **201 Created**:
```json
{
  "status": 201,
  "data": {
    "id": 1,
    "usuario": "nombre_usuario",
    "fechaCreacion": "2024-12-12T12:00:00",
    "status": "ABIERTO"
  },
  "details": "Ticket creado con éxito"
}
```

---

### 2. Actualizar un Ticket

**Endpoint**:  
`PUT /api/v1/tickets/{id}`

**Descripción**:  
Actualiza un ticket existente.

**Request Body**:
```json
{
  "usuario": "nuevo_usuario",
  "status": "CERRADO"
}
```

**Responses**:
- **200 OK**:
```json
{
  "status": 200,
  "data": {
    "id": 1,
    "usuario": "nuevo_usuario",
    "fechaCreacion": "2024-12-12T12:00:00",
    "fechaActualizacion": "2024-12-12T14:00:00",
    "status": "CERRADO"
  },
  "details": "Ticket actualizado con éxito"
}
```

---

### 3. Eliminar un Ticket

**Endpoint**:  
`DELETE /api/v1/tickets/{id}`

**Descripción**:  
Elimina un ticket por su ID.

**Responses**:
- **200 OK**:
```json
{
  "status": 200,
  "data": "Ticket eliminado con éxito"
}
```

---

### 4. Obtener un Ticket

**Endpoint**:  
`GET /api/v1/tickets/{id}`

**Descripción**:  
Obtiene un ticket por su ID.

**Responses**:
- **200 OK**:
```json
{
  "status": 200,
  "data": {
    "id": 1,
    "usuario": "nombre_usuario",
    "fechaCreacion": "2024-12-12T12:00:00",
    "status": "ABIERTO"
  },
  "details": ""
}
```
- **404 Not Found**:
```json
{
  "status": 404,
  "data": null,
  "details": "Ticket no encontrado"
}
```

---

### 5. Obtener todos los Tickets

**Endpoint**:  
`GET /api/v1/tickets`

**Descripción**:  
Obtiene una lista paginada de todos los tickets.

**Query Parameters**:
- `page` (opcional, defecto: 0): Número de página.
- `size` (opcional, defecto: 10): Tamaño de la página.

**Responses**:
- **200 OK**:
```json
{
  "totalPages": 5,
  "currentPage": 0,
  "status": 200,
  "data": [
    {
      "id": 1,
      "usuario": "nombre_usuario",
      "fechaCreacion": "2024-12-12T12:00:00",
      "status": "ABIERTO"
    },
    {
      "id": 2,
      "usuario": "otro_usuario",
      "fechaCreacion": "2024-12-12T12:30:00",
      "status": "CERRADO"
    }
  ]
}
``
---

## Modelos

### Ticket

```json
{
  "id": 1,
  "usuario": "nombre_usuario",
  "fechaCreacion": "2024-12-12T12:00:00",
  "fechaActualizacion": "2024-12-12T14:00:00",
  "status": "ABIERTO"
}
```

### Status

Valores permitidos:
- `ABIERTO`
- `CERRADO`
