# Start Wars App ⭐

Aplicación Android desarrollada con **Kotlin y Jetpack Compose** para la gestión de un catálogo de películas de Star Wars.  
La aplicación permite **listar, añadir, editar y eliminar películas**, además de incluir una pantalla informativa **About Us**.  
Se ha trabajado con **Navigation Compose**, **Material 3**, gestión de estado y feedback visual al usuario mediante **SnackBar**.

---

## Funcionalidades de la aplicación

### Listar
La pantalla de listado muestra todas las películas almacenadas en la aplicación mediante un `LazyColumn`.  
Cada elemento presenta información relevante como el título, episodio, director, productor y fecha de estreno.

Interacciones disponibles:
- Pulsación corta sobre un elemento: navegación a la pantalla de **Editar**.
- Pulsación larga sobre un elemento: apertura de un diálogo de confirmación para **Eliminar**.

![Pantalla Listar](screenshots/listar.png)

---

### Añadir (Alta / Creación)
La pantalla de alta permite crear una nueva película mediante un formulario.  
Al guardar una película:
- Se generan automáticamente los campos de fecha de creación y edición.
- La película se añade al repositorio.
- Se retorna a la pantalla anterior o al listado según la navegación configurada.

![Pantalla Añadir](screenshots/añadir.png)

---

### Eliminar
La eliminación de una película se realiza desde el listado mediante una pulsación larga.

Flujo de eliminación:
1. El usuario realiza una pulsación larga sobre una película.
2. Se muestra un **AlertDialog** de confirmación.
3. Al confirmar, la película se elimina del repositorio.
4. Se muestra un **SnackBar sincronizado con el Scaffold**, informando al usuario del resultado de la acción.

Este comportamiento cumple los criterios de usabilidad y feedback visual solicitados.

![Diálogo eliminar](screenshots/eliminar.png)

---

### Editar
La pantalla de edición permite modificar una película existente.

Características:
- Se accede desde la pantalla de listado.
- Los campos se cargan automáticamente con los datos actuales de la película.
- Al guardar, se actualiza la información y la fecha de edición.
- Tras guardar, se retorna a la pantalla anterior.

![Pantalla Editar](screenshots/editar.png)

---

### About Us
La pantalla **About Us** muestra información general sobre la aplicación y/o el equipo desarrollador, incluyendo datos descriptivos del proyecto.

![Pantalla About Us](screenshots/about.png)

---

## Estructura de capturas

Las capturas de pantalla se encuentran en la carpeta:

