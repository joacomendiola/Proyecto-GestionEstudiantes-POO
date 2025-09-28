<div align="center">

# 🎓 Sistema de Gestión Académica

![Java](https://img.shields.io/badge/Java-17-red)
![POO](https://img.shields.io/badge/Paradigma-POO-blue)
![SQL](https://img.shields.io/badge/Persistencia-SQL-4479A1)
![DAO](https://img.shields.io/badge/Patrón-DAO-green)
![Estado](https://img.shields.io/badge/Estado-Terminado-success)

**Sistema académico con persistencia en base de datos SQL y patrón DAO**

</div>

---

## 📖 Descripción
Sistema completo de gestión académica desarrollado en Java que administra **estudiantes, materias y calificaciones** con persistencia en base de datos SQL. Implementa el **patrón DAO** para separar la lógica de negocio del acceso a datos y aplica principios de **Programación Orientada a Objetos**.

---

## ✨ Características

- **👨‍🎓 Gestión de Estudiantes**: Registro completo con datos personales y académicos
- **📚 Administración de Materias**: Catálogo de materias con códigos y descripciones
- **📊 Sistema de Calificaciones**: Registro y cálculo de notas con validaciones
- **💾 Persistencia en SQL**: Almacenamiento en base de datos relacional
- **🏗️ Patrón DAO**: Separación clara entre lógica de negocio y acceso a datos
- **📈 Reportes Académicos**: Promedios, materias aprobadas y estadísticas

---

## 🏗️ Arquitectura del Sistema

### Capas implementadas:
- **🎯 Capa de Presentación**: Interfaz de consola y menús
- **⚙️ Capa de Negocio**: Lógica académica y reglas del dominio
- **💾 Capa de Persistencia**: DAOs para acceso a base de datos
- **🗄️ Base de Datos**: MySQL/PostgreSQL/SQLite

### Principios POO aplicados:
- **🔷 Encapsulamiento**: Atributos privados con acceso controlado
- **🔄 Polimorfismo**: Comportamientos específicos por entidad
- **🔗 Composiciones**: Relaciones entre estudiantes y materias
- **🎯 Interfaces**: Contratos para DAOs y servicios

---

## 🗄️ Estructura de Base de Datos

### Tablas principales:
```sql
-- Tabla de estudiantes
CREATE TABLE estudiantes (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    legajo VARCHAR(20) UNIQUE,
    email VARCHAR(150)
);

-- Tabla de materias  
CREATE TABLE materias (
    id INT PRIMARY KEY,
    codigo VARCHAR(20) UNIQUE,
    nombre VARCHAR(100),
    descripcion TEXT
);

-- Tabla de calificaciones
CREATE TABLE calificaciones (
    id INT PRIMARY KEY,
    estudiante_id INT,
    materia_id INT,
    calificacion DECIMAL(4,2),
    fecha DATE,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id),
    FOREIGN KEY (materia_id) REFERENCES materias(id)
);
