package consola;

import modelo.*;
import gestor.*;

public class Consola {

    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;

    public Consola(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
    }

    public void mostrarMenuPrincipal() {
        System.out.println("=== Biblioteca Digital ===");
        System.out.println("1. Agregar usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Agregar recurso");
        System.out.println("4. Listar recursos");
        System.out.println("5. Realizar operación en recurso");
        System.out.println("0. Salir");
    }

    public void mostrarMenuAgregarRecurso() {
        System.out.println("=== Agregar Recurso ===");
        System.out.println("1. Agregar Libro (Prestable y Renovable)");
        System.out.println("2. Agregar Revista (Prestable)");
        System.out.println("3. Agregar Audiolibro (Prestable)");
    }

    public void mostrarMenuOperacionesRecurso() {
        System.out.println("=== Operaciones en Recurso ===");
        System.out.println("1. Prestar Recurso (Libro, Revista, Audiolibro)");
        System.out.println("2. Devolver Recurso (Libro, Revista, Audiolibro)");
        System.out.println("3. Renovar Recurso (solo Libro)");
    }

    public String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        return new java.util.Scanner(System.in).nextLine();
    }

    public void procesarOpcionAgregarRecurso(String opcion) {
        switch (opcion) {
            case "1":
                String idLibro = leerEntrada("ID del libro: ");
                String autor = leerEntrada("Autor del libro: ");
                Libro libro = new Libro(idLibro, autor);
                gestorRecursos.agregarRecurso(libro);
                System.out.println("Libro agregado.");
                break;
            case "2":
                String idRevista = leerEntrada("ID de la revista: ");
                String tema = leerEntrada("Tema de la revista: ");
                Revista revista = new Revista(idRevista, tema);
                gestorRecursos.agregarRecurso(revista);
                System.out.println("Revista agregada.");
                break;
            case "3":
                String idAudiolibro = leerEntrada("ID del audiolibro: ");
                String duracion = leerEntrada("Duración del audiolibro: ");
                Audiolibro audiolibro = new Audiolibro(idAudiolibro, duracion);
                gestorRecursos.agregarRecurso(audiolibro);
                System.out.println("Audiolibro agregado.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    public void listarRecursos() {
        System.out.println("=== Listar Recursos ===");
        for (RecursoDigital recurso : gestorRecursos.getRecursos()) {
            recurso.mostrarDetalles();
        }
    }

    public void realizarOperacionEnRecurso() {
        mostrarMenuOperacionesRecurso();
        String opcion = leerEntrada("Seleccione una opción: ");
        String idRecurso = leerEntrada("ID del recurso: ");

        RecursoDigital recurso = gestorRecursos.buscarRecursoPorID(idRecurso);
        if (recurso == null) {
            System.out.println("Recurso no encontrado.");
            return;
        }

        switch (opcion) {
            case "1":
                if (recurso instanceof Prestable) {
                    boolean exito = ((Prestable) recurso).prestar();
                    if (exito) {
                        System.out.println("Recurso prestado con éxito.");
                    } else {
                        System.out.println("No se pudo prestar el recurso (¿ya está prestado?).");
                    }
                } else {
                    System.out.println("Este recurso no se puede prestar.");
                }
                break;
            case "2":
                if (recurso instanceof Prestable) {
                    boolean exito = ((Prestable) recurso).devolver();
                    if (exito) {
                        System.out.println("Recurso devuelto con éxito.");
                    } else {
                        System.out.println("No se pudo devolver el recurso (¿ya estaba disponible?).");
                    }
                } else {
                    System.out.println("Este recurso no se puede devolver.");
                }
                break;
            case "3":
                if (recurso instanceof Renovable) {
                    boolean exito = ((Renovable) recurso).renovar();
                    if (exito) {
                        System.out.println("Recurso renovado con éxito.");
                    } else {
                        System.out.println("No se pudo renovar el recurso (¿está disponible?).");
                    }
                } else {
                    System.out.println("Este recurso no se puede renovar.");
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
}
