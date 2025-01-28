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
  - Los commits deben incluir el identificador de Jira en el mensaje. Ejemplo: `feat(JIRA-123): implementar endpoint de usuarios`.

---

## Flujo de Trabajo

1. **Crear una incidencia en Jira**:
   - Cada tarea, funcionalidad o corrección se gestiona como una incidencia en Jira.
2. **Desarrollo directo en `dev`**:
   - No se crean ramas adicionales. Todos los cambios se realizan directamente en `dev`.
3. **Pruebas y validación en `production`**:
   - Una vez completados los desarrollos en `dev`, se realiza un **merge** a `production` para pruebas en el entorno de preproducción.
4. **Despliegue en `master`**:
   - Tras validar los cambios en `production`, se realiza el **merge** final hacia `master`.

---

## Reglas de Commits

- Los mensajes de commit deben incluir el identificador de la incidencia de Jira, seguido de una descripción clara. Formato recomendado:
  - `feat(JIRA-123): implementar nueva funcionalidad`
  - `fix(JIRA-456): corregir error en autenticación`
  - `chore(JIRA-789): actualizar dependencias`

---

## Consideraciones Finales

- No se permite crear ramas adicionales fuera de `master`, `production` y `dev`.
- Los cambios en `dev` deben estar claramente relacionados con una incidencia de Jira.
- El flujo debe mantenerse sencillo para facilitar la trazabilidad y el control de versiones.

---
