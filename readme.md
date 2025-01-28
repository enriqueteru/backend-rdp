# Relatos de Papel - Backend

Este repositorio contiene el código del backend para la plataforma **Relatos de Papel**. A continuación, se describe la estructura de ramas y las reglas asociadas a su uso, siguiendo una metodología simplificada basada en incidencias de Jira.

---

## Estructura de Ramas

### Ramas Principales

#### `master`
- **Propósito**: Contiene el código de producción.
- **Reglas**:
  - Solo se permite hacer **merge** desde la rama `dev`.
  - Refleja siempre el estado actual en producción.
  - Despliegues en el entorno de producción se realizan directamente desde esta rama.

### Ramas de Desarrollo

#### `dev`
- **Propósito**: Entorno activo de desarrollo donde se integran los cambios provenientes de las incidencias de Jira.
- **Reglas**:
  - Las funcionalidades o correcciones se desarrollan directamente en esta rama.
  - Cada cambio debe estar vinculado a una incidencia de Jira.
  - Los commits deben incluir el identificador de Jira en el mensaje. Ejemplo: `feat(BRDP-123): implementar endpoint de usuarios`.

---
### Ramas de Incidencias

#### Formato de las Ramas
- Cada incidencia de Jira debe tener su propia rama temporal con el siguiente formato:
  - **`<proyecto-idincidencia-descripcion>`**
  - Ejemplo: `BRDP-123-crear-endpoint-usuarios`

#### Reglas de las Ramas
- Las ramas de incidencias deben crearse desde `dev`.
- Una vez completada la incidencia:
  - Realizar un **merge** hacia `dev`.
  - Cerrar la incidencia en Jira como "Hecha".
  - Eliminar la rama de la incidencia tras la fusión.

---

## Flujo de Trabajo

1. **Crear una incidencia en Jira**:
   - Cada funcionalidad o corrección se gestiona como una incidencia en Jira.

2. **Crear una rama de la incidencia**:
   - Partiendo de `dev`, crear una rama con el formato `<proyecto-incidenciaID-descripcion>`.
   - Ejemplo: `git checkout -b BBRDP-123-crear-endpoint-usuarios`

3. **Desarrollar y realizar commits**:
   - Los mensajes de commit deben incluir el identificador de Jira. Ejemplo:
     - `feat(BBRDP-123): implementar endpoint de usuarios`
     - `fix(BBRDP-456): corregir validación de datos`

4. **Revisión en `dev`**:
   - Cerrar la incidencia en Jira como "Hecha".
   - Eliminar la rama de la incidencia:
     - `git branch -d BRDP-123-crear-endpoint-usuarios`

5. **Despliegue final en `master`**:
   - Una vez validados los cambios en `dev`, mergear en `master`.

---

## Reglas de Commits

- Los mensajes de commit deben incluir el identificador de Jira para facilitar la trazabilidad. Formato recomendado:
  - `feat(<proyecto-idincidencia>): descripción clara del cambio`
  - Ejemplo: `feat(BRDP-123): agregar autenticación con JWT`

---

## Consideraciones Finales

- **Control de ramas**:
  - Solo deben existir las ramas principales (`master`, `dev`) y ramas temporales por incidencia.
  - Las ramas de incidencias deben eliminarse tras ser fusionadas en `dev`.

- **Gestión de Jira**:
  - Toda tarea debe estar asociada a una incidencia en Jira.
---