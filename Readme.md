Para poder hacer comprobaciones (con proyecto arrancado):

Ruta: http://localhost:8080/tareas

En postman:
- Leer: Get: http://localhost:8080/tareas

- Insertar: Post: http://localhost:8080/tareas
  {
      "id": 2,
      "name": "bbb",
      "descripcion": "bbb",
      "fecha": "2025-01-03",
      "estado": false
  }

- Actualizar: Put: http://localhost:8080/tareas/id
  {
      "name": "aaa",
      "descripcion": "aaa",
      "fecha": "2025-02-05",
      "estado": true
  }

- Borrar: Delete: http://localhost:8080/tareas/id