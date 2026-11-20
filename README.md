# Kata 4 - Ingeniería del Software 2

## Proyecto

Continuación de la Kata 3. El programa lee personas desde `people.tsv`, descarta las filas no válidas y prepara un informe JSON. En esta kata, el informe también se ofrece mediante un servicio web.

`TsvPersonReader` carga los datos, `PersonReport` organiza las personas y el resumen, `PeopleService` contiene las consultas y `PeopleEndpoint` define las rutas HTTP. `PeopleWebServer` inicia Javalin en el puerto `8081` y Gson serializa las respuestas como JSON. La clase `Main` conserva el modo de fichero: por defecto lee `people.tsv` y genera `report.json`; con los argumentos `people.tsv --memory` genera `report-memory.json`.

La ruta `GET /people` devuelve el informe completo. `GET /people/{name}` busca por nombre sin distinguir mayúsculas: responde `200` si lo encuentra, `400` si el nombre está en blanco y `404` si no existe. Todas las respuestas se envían como `application/json`. Con el TSV incluido se obtienen 9 personas válidas y 1 fila descartada.

- Java / JDK: 27
- IntelliJ IDEA
- Maven
- Javalin 7.2.4
- Gson 2.14.0

Para compilar, ejecuta `mvn compile` desde la raíz del proyecto. En IntelliJ IDEA, ejecuta `PeopleWebServer` con el directorio de trabajo en esa raíz, donde se encuentra `people.tsv`. El servidor queda disponible en `http://localhost:8081`.

Con el servidor iniciado, estas peticiones permiten comprobar la lista, una persona existente, una búsqueda sin resultado y un nombre en blanco:

```bash
curl -i http://localhost:8081/people
curl -i http://localhost:8081/people/first
curl -i http://localhost:8081/people/missing
curl -i http://localhost:8081/people/%20
```

La primera petición devuelve `200` y un JSON con `summary.valid: 9` y `summary.discarded: 1`. La segunda devuelve `200` y la persona `first`; la tercera devuelve `404` con `{"error":"No existe la persona solicitada"}`; la última devuelve `400` con `{"error":"El nombre no es obligatorio"}`.

## Vídeo

[Ver vídeo de la Kata 4 en YouTube](https://youtu.be/2NKhKk7pUnI)

## Práctica y verificación

Se realizaron cinco intentos de la kata, conservados en las ramas `intento-1` a `intento-5`.

Al ejecutar `PeopleServiceCheck` desde IntelliJ IDEA, se comprueba que el servicio conserva los dos registros de ejemplo, encuentra `FIRST` sin distinguir mayúsculas y no encuentra `missing`. Al completarse correctamente, muestra `PeopleService: OK`.

Con el servidor iniciado, se comprobaron las respuestas de la API desde la terminal con `curl`: `200` para el informe y para una persona existente, y `404` para una persona inexistente. El README incluye también las peticiones para reproducir los casos `400` y `404`.

## Git

Los cinco intentos se conservan en las ramas `intento-1`, `intento-2`, `intento-3`, `intento-4` e `intento-5`. La versión final está en `main`; `intento-5` apunta a la misma entrega final. Las ramas están publicadas en GitHub.

```text
intento-5 → main → push
```
