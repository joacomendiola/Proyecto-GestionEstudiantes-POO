<div align="center">

# 🎓 Sistema de Gestión de Estudiantes

![Java](https://img.shields.io/badge/Java-17-red)
![POO](https://img.shields.io/badge/Paradigma-POO-blue)
![Persistencia](https://img.shields.io/badge/Persistencia-MySQL-green)
![Patrón](https://img.shields.io/badge/Patrón-DAO-yellow)
![Estado](https://img.shields.io/badge/Estado-Terminado-success)

**Sistema académico con persistencia en MySQL y patrón DAO desarrollado en Java**

</div>

---

## 📖 Descripción
Sistema de gestión académica desarrollado en Java que administra **estudiantes** y sus **inscripciones a materias**.  
Aplica principios de **POO** y utiliza el **patrón DAO** para separar la lógica de negocio del acceso a datos.  
La persistencia se implementa con **MySQL** mediante JDBC y un archivo `config.properties`.

---

## ✨ Características

- **👩‍🎓 Gestión de Estudiantes**: Altas, bajas, modificaciones y consultas (CRUD completo).  
- **📘 Inscripciones**: Asociación de estudiantes con materias ya cargadas en la base de datos.  
- **📊 Reportes**: Consultas con JOIN usando vista SQL (`v_inscripciones`).  
- **🗑️ Eliminación de duplicados**: Detección y eliminación de registros repetidos por datos.  
- **💾 Persistencia en MySQL**: Conexión configurada vía `config.properties`.  
- **⚡ Patrón DAO**: Acceso a datos desacoplado de la lógica de negocio.  

---

## 🏗️ Arquitectura del Sistema

### Capas implementadas:
- **Capa de Presentación**: `Main.java` (menú de consola).  
- **Capa de Negocio**: `UniversidadService` y `UniversidadServiceImpl`.  
- **Capa de Persistencia**: `EstudianteDAO` y `EstudianteDAOImpl`.  
- **Reportes**: `ReporteRepository` e `InscripcionVista` para consultas con JOIN.  
- **Base de Datos**: Scripts SQL incluidos (`creacion_tablas.sql` y `modificacion_relaciones.sql`).  

### Principios POO aplicados:
- **📦 Encapsulamiento**: Atributos privados con acceso controlado en `Estudiante`.  
- **🔄 Polimorfismo**: Implementaciones concretas en el DAO y el servicio.  
- **🎯 Interfaces**: Contratos para `EstudianteDAO` y `UniversidadService`.  

---

## 🗄️ Base de Datos (MySQL)

El proyecto incluye los scripts necesarios en la carpeta `sql/`:

- `creacion_tablas.sql` → crea las tablas `estudiantes`, `materias` e `inscripciones`, además de la vista `v_inscripciones`.  
- `modificacion_relaciones.sql` → actualiza claves foráneas con `CASCADE`.  

👉 **Importante:** las **materias se crean directamente desde el script SQL** (no existe clase `Materia` en Java).

Ejemplo de tabla `materias`:

```sql
CREATE TABLE materias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    creditos INT NOT NULL
) ENGINE=InnoDB;
```

---

## 📂 Estructura del Código

```text
Proyecto/
├── sql/
│   ├── creacion_tablas.sql
│   └── modificacion_relaciones.sql
│
├── src/
│   ├── app/
│   │   └── Main.java
│   │
│   ├── dao/
│   │   ├── impl/
│   │   │   └── EstudianteDAOImpl.java
│   │   ├── EstudianteDAO.java
│   │   ├── InscripcionVista.java
│   │   └── ReporteRepository.java
│   │
│   ├── main/
│   │   └── resources/
│   │       └── config.properties
│   │
│   ├── model/
│   │   └── Estudiante.java
│   │
│   ├── service/
│   │   ├── UniversidadService.java
│   │   └── UniversidadServiceImpl.java
│   │
│   ├── test/
│   │   └── Test.java
│   │
│   └── util/
│       └── ConexionBD.java
│
├── .gitignore
└── Proyecto.iml
```

---

## ⚙️ Configuración

En `src/main/resources/config.properties` se definen los parámetros de conexión a MySQL:

```properties
db.url=jdbc:mysql://localhost:3306/universidad?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.user=root
db.pass=root1234
```

---

## 💻 Ejemplo de Código

### 🔹 CRUD de Estudiantes
```java
EstudianteDAO dao = new EstudianteDAOImpl();

// Crear estudiante
int id = dao.crear(new Estudiante("Juan Pérez", 21, "Ingeniería en Sistemas"));

// Listar estudiantes
dao.listar().forEach(System.out::println);

// Buscar por ID
dao.porId(id).ifPresent(System.out::println);

// Actualizar
Estudiante e = new Estudiante(id, "Juan Pérez", 22, "Informática");
dao.actualizar(e);

// Eliminar
dao.eliminar(id);
```

### 🔹 Inscripción en Materias
```java
UniversidadService svc = new UniversidadServiceImpl();
boolean ok = svc.inscribir(1, 2, LocalDate.now());
System.out.println(ok ? "Inscripción realizada" : "No se pudo inscribir");
```

### 🔹 Reporte de Inscripciones
```java
ReporteRepository repo = new ReporteRepository();
repo.listarInscripciones().forEach(iv ->
    System.out.println(iv.estudiante() + " → " + iv.materia() + " (" + iv.fecha() + ")")
);
```

---

## 👨‍💻 Autor
Desarrollado por **Joaquín Mendiola**  
🌐 GitHub: [joacomendiola](https://github.com/joacomendiola)
