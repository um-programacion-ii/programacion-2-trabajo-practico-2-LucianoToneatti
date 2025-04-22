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
        System.out.println("0. Salir");
    }

    public void mostrarMenuAgregarRecurso() {
        System.out.println("=== Agregar Recurso ===");
        System.out.println("1. Agregar Libro");
        System.out.println("2. Agregar Revista");
        System.out.println("3. Agregar Audiolibro");
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

    // Método para listar los recursos
    public void listarRecursos() {
        System.out.println("=== Listar Recursos ===");
        for (RecursoDigital recurso : gestorRecursos.getRecursos()) {
            recurso.mostrarDetalles(); // Llamamos al método de la subclase
        }
    }
}




