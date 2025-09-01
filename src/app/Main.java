package app;

import dao.EstudianteDAO;
import dao.ReporteRepository;
import dao.impl.EstudianteDAOImpl;
import model.Estudiante;
import service.UniversidadService;
import service.UniversidadServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EstudianteDAO dao = new EstudianteDAOImpl();
        UniversidadService svc = new UniversidadServiceImpl();
        ReporteRepository repo = new ReporteRepository();

        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ UNIVERSIDAD ===");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Eliminar duplicados");
            System.out.println("6. Inscribir estudiante en materia");
            System.out.println("7. Ver inscripciones (JOIN)");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            int opcion = entrada.nextInt();
            entrada.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = entrada.nextLine();
                    System.out.print("Edad: ");
                    int edad = entrada.nextInt();
                    entrada.nextLine();
                    System.out.print("Carrera: ");
                    String carrera = entrada.nextLine();

                    int id = dao.crear(new Estudiante(nombre, edad, carrera));
                    System.out.println(" Estudiante creado con ID: " + id);
                }
                case 2 -> {
                    System.out.println("=== LISTA DE ESTUDIANTES ===");
                    dao.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID del estudiante a actualizar: ");
                    int id = entrada.nextInt();
                    entrada.nextLine();
                    dao.porId(id).ifPresentOrElse(e -> {
                        System.out.print("Nuevo nombre (" + e.getNombre() + "): ");
                        String nombre = entrada.nextLine();
                        System.out.print("Nueva edad (" + e.getEdad() + "): ");
                        int edad = entrada.nextInt();
                        entrada.nextLine();
                        System.out.print("Nueva carrera (" + e.getCarrera() + "): ");
                        String carrera = entrada.nextLine();

                        e.setNombre(nombre);
                        e.setEdad(edad);
                        e.setCarrera(carrera);
                        boolean ok = dao.actualizar(e);
                        System.out.println(ok ? " Actualizado" : " No se pudo actualizar");
                    }, () -> System.out.println(" No existe ese ID"));
                }
                case 4 -> {
                    System.out.print("ID del estudiante a eliminar: ");
                    int id = entrada.nextInt();
                    boolean ok = dao.eliminar(id);
                    System.out.println(ok ? " Eliminado" : " No se pudo eliminar");
                }
                case 5 -> {
                    int duplicados = eliminarDuplicados(dao);
                    System.out.println(" Eliminados " + duplicados + " duplicados");
                }
                case 6 -> {
                    System.out.print("ID del estudiante: ");
                    int idEst = entrada.nextInt();
                    System.out.print("ID de la materia: ");
                    int idMat = entrada.nextInt();
                    entrada.nextLine();
                    boolean ok = svc.inscribir(idEst, idMat, LocalDate.now());
                    System.out.println(ok ? " Inscripción realizada" : " No se pudo inscribir");
                }
                case 7 -> {
                    System.out.println("=== INSCRIPCIONES ===");
                    repo.listarInscripciones().forEach(iv ->
                            System.out.println(iv.estudiante() + " → " + iv.materia() + " (" + iv.fecha() + ")")
                    );
                }
                case 0 -> {
                    salir = true;
                    System.out.println(" Saliendo...");
                }
                default -> System.out.println(" Opción no válida");
            }
        }
        entrada.close();
    }

    // Metodo auxiliar para eliminar duplicados
    private static int eliminarDuplicados(EstudianteDAO dao) {
        var lista = dao.listar();
        int eliminados = 0;
        for (int i = 0; i < lista.size(); i++) {
            Estudiante e1 = lista.get(i);
            for (int j = i + 1; j < lista.size(); j++) {
                Estudiante e2 = lista.get(j);
                if (e1.getNombre().equals(e2.getNombre())
                        && e1.getEdad().equals(e2.getEdad())
                        && e1.getCarrera().equals(e2.getCarrera())) {
                    if (dao.eliminar(e2.getId())) {
                        eliminados++;
                    }
                }
            }
        }
        return eliminados;
    }
}
