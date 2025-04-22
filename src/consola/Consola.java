package consola;

import modelo.*;
import gestor.*;
import notificaciones.ServicioNotificaciones;

public class Consola {

    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private ServicioNotificaciones servicioNotificaciones;  // Agregar la dependencia

    public Consola(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, ServicioNotificaciones servicioNotificaciones) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.servicioNotificaciones = servicioNotificaciones;  // Asignar la dependencia
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
        System.out.println("1. Agregar Libro");
        System.out.println("2. Agregar Revista");
        System.out.println("3. Agregar Audiolibro");
    }

    public void mostrarMenuOperacionesRecurso() {
        System.out.println("=== Operaciones en Recurso ===");
        System.out.println("1. Prestar Recurso");
        System.out.println("2. Devolver Recurso");
        System.out.println("3. Renovar Recurso (solo libros)");
    }

    public String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        return new java.util.Scanner(System.in).nextLine();
    }

    public void procesarOpcionAgregarRecurso(String opcion) {
        switch (opcion) {
            case "1":
                String idLibro = leerEntrada("ID del libro: ");
                String tituloLibro = leerEntrada("Título del libro: ");
                String autorLibro = leerEntrada("Autor del libro: ");
                Libro libro = new Libro(idLibro, tituloLibro, autorLibro);
                gestorRecursos.agregarRecurso(libro);
                System.out.println("Libro agregado.");
                break;
            case "2":
                String idRevista = leerEntrada("ID de la revista: ");
                String tituloRevista = leerEntrada("Título de la revista: ");
                String autorRevista = leerEntrada("Autor de la revista: ");
                Revista revista = new Revista(idRevista, tituloRevista, autorRevista);
                gestorRecursos.agregarRecurso(revista);
                System.out.println("Revista agregada.");
                break;
            case "3":
                String idAudiolibro = leerEntrada("ID del audiolibro: ");
                String tituloAudiolibro = leerEntrada("Título del audiolibro: ");
                String autorAudiolibro = leerEntrada("Autor del audiolibro: ");
                String duracion = leerEntrada("Duración del audiolibro: ");
                Audiolibro audiolibro = new Audiolibro(idAudiolibro, tituloAudiolibro, autorAudiolibro, duracion);
                gestorRecursos.agregarRecurso(audiolibro);
                System.out.println("Audiolibro agregado.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    // Método para listar los recursos
    public void listarRecursos() {
        System.out.println("=== Listar Recursos ===");
        for (RecursoDigital recurso : gestorRecursos.getRecursos()) {
            recurso.mostrarDetalles(); // Llamamos al método de la subclase
        }
    }

    // Método para realizar operaciones en recursos
    public void realizarOperacionEnRecurso() {
        mostrarMenuOperacionesRecurso();
        String opcion = leerEntrada("Seleccione una opción: ");
        String idRecurso = leerEntrada("ID del recurso: ");

        // Buscar el recurso por ID
        RecursoDigital recurso = gestorRecursos.buscarRecursoPorID(idRecurso);
        if (recurso == null) {
            System.out.println("Recurso no encontrado.");
            return;
        }

        switch (opcion) {
            case "1":
                if (recurso instanceof Prestable) {
                    ((Prestable) recurso).prestar();
                    // Enviar notificación de préstamo
                    servicioNotificaciones.enviarNotificacion("usuario@example.com", "Tu recurso ha sido prestado.");
                } else {
                    System.out.println("Este recurso no se puede prestar.");
                }
                break;
            case "2":
                if (recurso instanceof Prestable) {
                    ((Prestable) recurso).devolver();
                    // Enviar notificación de devolución
                    servicioNotificaciones.enviarNotificacion("usuario@example.com", "Tu recurso ha sido devuelto.");
                } else {
                    System.out.println("Este recurso no se puede devolver.");
                }
                break;
            case "3":
                if (recurso instanceof Renovable) {
                    ((Renovable) recurso).renovar();
                    // Enviar notificación de renovación
                    servicioNotificaciones.enviarNotificacion("usuario@example.com", "Tu recurso ha sido renovado.");
                } else {
                    System.out.println("Este recurso no se puede renovar.");
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
}
