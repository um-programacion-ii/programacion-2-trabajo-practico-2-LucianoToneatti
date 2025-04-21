package consola;

import excepciones.UsuarioNoEncontradoException;
import gestor.GestorBiblioteca;
import gestor.GestorUsuarios;
import gestor.GestorRecursos;
import modelo.*;
import notificaciones.ServicioNotificaciones;

import java.util.List;
import java.util.Scanner;

public class Consola {

    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private ServicioNotificaciones servicioNotificaciones;
    private final Scanner scanner = new Scanner(System.in);  // Scanner único y reutilizable

    public Consola(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, ServicioNotificaciones servicioNotificaciones, GestorBiblioteca gestorBiblioteca) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void mostrarMenuPrincipal() {
        System.out.println("=== Biblioteca Digital ===");
        System.out.println("1. Agregar usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Agregar recurso");
        System.out.println("4. Listar recursos");
        System.out.println("5. Realizar operación en recurso");
        System.out.println("6. Buscar usuario por nombre");
        System.out.println("7. Buscar recurso por título");
        System.out.println("8. Buscar por filtros");
        System.out.println("9. Registrar prestamo");
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
        return scanner.nextLine();
    }

    public void procesarOpcionAgregarRecurso(String opcion) {
        switch (opcion) {
            case "1":
                String idLibro = leerEntrada("ID del libro: ");
                String tituloLibro = leerEntrada("Título del libro: ");
                String autorLibro = leerEntrada("Autor del libro: ");
                // Mostrar categorías disponibles antes de que el usuario ingrese la categoría
                mostrarCategoriasDisponibles();
                String categoriaLibroStr = leerEntrada("Categoria del libro: ");

                // Convertir String a CategoriaRecurso
                CategoriaRecurso categoriaLibro = convertirACategoriaRecurso(categoriaLibroStr);

                if (categoriaLibro == null) {
                    System.out.println("Categoría no válida.");
                    return;
                }

                Libro libro = new Libro(idLibro, tituloLibro, autorLibro, categoriaLibro);
                gestorRecursos.agregarRecurso(libro);
                System.out.println("Libro agregado.");
                break;

            case "2":
                String idRevista = leerEntrada("ID de la revista: ");
                String tituloRevista = leerEntrada("Título de la revista: ");
                String autorRevista = leerEntrada("Autor de la revista: ");
                // Mostrar categorías disponibles antes de que el usuario ingrese la categoría
                mostrarCategoriasDisponibles();
                String categoriaRevistaStr = leerEntrada("Categoria de la revista: ");

                // Convertir String a CategoriaRecurso
                CategoriaRecurso categoriaRevista = convertirACategoriaRecurso(categoriaRevistaStr);

                if (categoriaRevista == null) {
                    System.out.println("Categoría no válida.");
                    return;
                }

                Revista revista = new Revista(idRevista, tituloRevista, autorRevista, categoriaRevista);
                gestorRecursos.agregarRecurso(revista);
                System.out.println("Revista agregada.");
                break;

            case "3":
                String idAudiolibro = leerEntrada("ID del audiolibro: ");
                String tituloAudiolibro = leerEntrada("Título del audiolibro: ");
                String autorAudiolibro = leerEntrada("Autor del audiolibro: ");
                String duracion = leerEntrada("Duración del audiolibro: ");
                // Mostrar categorías disponibles antes de que el usuario ingrese la categoría
                mostrarCategoriasDisponibles();
                String categoriaAudioLibroStr = leerEntrada("Categoria del Audiolibro: ");

                // Convertir String a CategoriaRecurso
                CategoriaRecurso categoriaAudioLibro = convertirACategoriaRecurso(categoriaAudioLibroStr);

                if (categoriaAudioLibro == null) {
                    System.out.println("Categoría no válida.");
                    return;
                }

                Audiolibro audiolibro = new Audiolibro(idAudiolibro, tituloAudiolibro, autorAudiolibro, categoriaAudioLibro, duracion);
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
                    ((Prestable) recurso).prestar();
                    servicioNotificaciones.enviarNotificacion("usuario@example.com", "Tu recurso ha sido prestado.");
                } else {
                    System.out.println("Este recurso no se puede prestar.");
                }
                break;
            case "2":
                if (recurso instanceof Prestable) {
                    ((Prestable) recurso).devolver();
                    servicioNotificaciones.enviarNotificacion("usuario@example.com", "Tu recurso ha sido devuelto.");
                } else {
                    System.out.println("Este recurso no se puede devolver.");
                }
                break;
            case "3":
                if (recurso instanceof Renovable) {
                    ((Renovable) recurso).renovar();
                    servicioNotificaciones.enviarNotificacion("usuario@example.com", "Tu recurso ha sido renovado.");
                } else {
                    System.out.println("Este recurso no se puede renovar.");
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    public void buscarUsuarioPorNombre() {
        String nombre = leerEntrada("Ingrese el nombre del usuario: ");
        try {
            Usuario usuario = gestorUsuarios.buscarUsuarioPorNombre(nombre);
            System.out.println("Usuario encontrado: " + usuario);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }


    public void buscarRecursoPorTitulo() {
        String titulo = leerEntrada("Ingrese el título del recurso: ");
        List<RecursoDigital> resultados = gestorRecursos.buscarRecursosPorTitulo(titulo);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron recursos.");
        } else {
            for (RecursoDigital recurso : resultados) {
                recurso.mostrarDetalles();
            }
        }
    }

    ///////////////////////////FILTROS////////////////////////////////////////////////

    public void mostrarMenuBusquedaPorFiltros() {
        System.out.println("=== Búsqueda por Filtros ===");
        System.out.println("1. Ordenar por categoría");
        System.out.println("2. Ordenar por título");
        System.out.println("0. Volver al menú principal");
    }

    public void buscarPorFiltros() {
        mostrarMenuBusquedaPorFiltros();
        String opcion = leerEntrada("Seleccione un filtro de búsqueda: ");

        switch (opcion) {
            case "1":
                // Mostrar categorías disponibles antes de que el usuario ingrese la categoría
                mostrarCategoriasDisponibles();
                String categoria = leerEntrada("Ingrese la categoría: ");
                List<RecursoDigital> recursosPorCategoria = gestorRecursos.filtrarPorCategoria(CategoriaRecurso.valueOf(categoria.toUpperCase()));
                mostrarResultadosBusqueda(recursosPorCategoria);
                break;

            case "2":
                String titulo = leerEntrada("Ingrese el título: ");
                List<RecursoDigital> recursosPorTitulo = gestorRecursos.ordenarPorTitulo(titulo);
                mostrarResultadosBusqueda(recursosPorTitulo);
                break;

            case "0":
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    // Método auxiliar para mostrar los resultados de la búsqueda
    private void mostrarResultadosBusqueda(List<RecursoDigital> recursos) {
        if (recursos.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            for (RecursoDigital recurso : recursos) {
                recurso.mostrarDetalles();
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////7

    // Método auxiliar para convertir el String a CategoriaRecurso
    private CategoriaRecurso convertirACategoriaRecurso(String categoriaStr) {
        try {
            return CategoriaRecurso.valueOf(categoriaStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // Si la categoría no es válida, devolvemos null
        }
    }


    /// /////////////////////////////////////////////////////////////////////////////////77

    public void mostrarCategoriasDisponibles() {
        System.out.println("=== Categorías Disponibles ===");
        for (CategoriaRecurso categoria : CategoriaRecurso.values()) {
            System.out.println(categoria.name());
        }
    }

}

