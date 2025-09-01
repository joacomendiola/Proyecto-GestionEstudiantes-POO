package model;

public class Estudiante {
    private Integer id;
    private String nombre;
    private Integer edad;
    private String carrera;

    public Estudiante(){}
    public Estudiante(Integer id,String nombre, Integer edad,String carrera){
        this.id=id;
        this.nombre=nombre;
        this.edad=edad;
        this.carrera=carrera;
    }
    public Estudiante(String nombre, Integer edad,String carrera){
        this(null,nombre,edad,carrera);
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString(){
        return "Estudiante{id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", carrera='" + carrera + '\'' +
                '}';

    }

}
