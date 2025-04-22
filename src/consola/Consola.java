package consola;

import excepciones.UsuarioNoEncontradoException;
import gestor.GestorBiblioteca;
import gestor.GestorNotificaciones;
import gestor.GestorUsuarios;
import gestor.GestorRecursos;
import modelo.*;
import notificaciones.ServicioNotificaciones;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Consola {

    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private ServicioNotificaciones servicioNotificaciones;
    private final Scanner scanner = new Scanner(System.in);
    private final GestorNotificaciones gestorNotificaciones;


    public Consola(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, ServicioNotificaciones servicioNotificaciones, GestorBiblioteca gestorBiblioteca) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.servicioNotificaciones = servicioNotificaciones;
        this.gestorNotificaciones = new GestorNotificaciones(servicioNotificaciones);

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
        System.out.println("4. Reservar recurso");
        System.out.println("5. Ver todas las Reservas de un Recurso");
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
                mostrarCategoriasDisponibles();
                String categoriaLibroStr = leerEntrada("Categoria del libro: ");
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
                mostrarCategoriasDisponibles();
                String categoriaRevistaStr = leerEntrada("Categoria de la revista: ");
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
                mostrarCategoriasDisponibles();
                String categoriaAudioLibroStr = leerEntrada("Categoria del Audiolibro: ");
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

        // Manejar caso 5 por separado antes de pedir usuario
        if (opcion.equals("5")) {
            String titulo = leerEntrada("Ingrese el título del recurso: ");
            RecursoBase recursoBase = gestorRecursos.buscarRecursoPorTitulo(titulo);

            if (recursoBase == null) {
                System.out.println("Recurso no encontrado.");
                return;
            }

            Queue<Reserva> reservas = recursoBase.getReservas();
            if (reservas.isEmpty()) {
                System.out.println("No hay reservas para este recurso.");
            } else {
                System.out.println("Reservas para " + recursoBase.getTitulo() + ":");
                reservas.forEach(reserva -> {
                    System.out.println("- " + reserva.getUsuario().getNombre() + " | Fecha: " + reserva.getFechaReserva());
                });
            }
            return;  // Importante: salir para no continuar con el resto del método
        }

        // Pedir recurso
        String titulo = leerEntrada("Ingrese el título del recurso: ");
        RecursoBase recurso = gestorRecursos.buscarRecursoPorTitulo(titulo);

        if (recurso == null) {
            System.out.println("Recurso no encontrado.");
            return;
        }

        // Pedir usuario
        String nombreUsuario = leerEntrada("Ingrese el nombre del usuario: ");
        Usuario usuario = gestorUsuarios.buscarUsuarioPorNombre(nombreUsuario);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }


        switch (opcion) {
            case "1":
                if (recurso instanceof Prestable prestable) {
                    if (prestable.prestar()) {
                        System.out.println("Préstamo exitoso.");
                        gestorNotificaciones.enviarNotificacionAsync(usuario.getEmail(), "Se te ha prestado el recurso: " + recurso.getTitulo());
                        try {
                            Thread.sleep(100); // 100 milisegundos
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // buena práctica
                        }
                    } else {
                        System.out.println("No se pudo prestar el recurso.");
                    }
                } else {
                    System.out.println("Este recurso no se puede prestar.");
                }
                break;

            case "2":
                if (recurso instanceof Prestable prestable) {
                    if (prestable.devolver()) {
                        System.out.println("Devolución exitosa.");
                        gestorNotificaciones.enviarNotificacionAsync(usuario.getEmail(), "Has devuelto el recurso: " + recurso.getTitulo());
                        try {
                            Thread.sleep(100); // 100 milisegundos
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // buena práctica
                        }

                        // Si hay reservas, asignar recurso automáticamente
                        if (recurso instanceof RecursoBase recursoBase && recursoBase.tieneReservas()) {
                            Reserva siguienteReserva = recursoBase.obtenerProximaReserva();
                            recursoBase.setUsuarioActual(siguienteReserva.getUsuario());
                            recurso.actualizarEstado(EstadoRecurso.PRESTADO);

                            System.out.println("El recurso ha sido asignado automáticamente a: " + siguienteReserva.getUsuario().getNombre());
                            gestorNotificaciones.enviarNotificacionAsync(
                                    siguienteReserva.getUsuario().getEmail(),
                                    "El recurso '" + recurso.getTitulo() + "' ahora está disponible y ha sido asignado a vos."
                            );
                            try {
                                Thread.sleep(100); // 100 milisegundos
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt(); // buena práctica
                            }
                        }

                    } else {
                        System.out.println("No se pudo devolver el recurso.");
                    }
                } else {
                    System.out.println("Este recurso no se puede devolver.");
                }
                break;


            case "3":
                if (recurso instanceof Libro libro) {
                    if (libro.renovar()) {
                        System.out.println("Renovación exitosa. Veces renovado: " + libro.getVecesRenovado());
                        gestorNotificaciones.enviarNotificacionAsync(usuario.getEmail(), "Has renovado el libro: " + recurso.getTitulo());
                        try {
                            Thread.sleep(100); // 100 milisegundos
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // buena práctica
                        }
                    } else {
                        System.out.println("No se pudo renovar el recurso.");
                    }
                } else {
                    System.out.println("Solo los libros pueden renovarse.");
                }
                break;

            default:
                System.out.println("Opción inválida.");

            case "4":
                if (recurso instanceof RecursoBase recursoBase) {
                    int prioridad;
                    try {
                        prioridad = Integer.parseInt(leerEntrada("Ingrese la prioridad de la reserva (0 = mayor prioridad): "));
                    } catch (NumberFormatException e) {
                        System.out.println("Prioridad inválida. Se asignará prioridad por defecto (5).");
                        prioridad = 5;
                    }

                    Reserva reserva = new Reserva(usuario, recursoBase, prioridad);
                    recursoBase.agregarReserva(reserva);

                    System.out.println("Reserva realizada con éxito.");
                    gestorNotificaciones.enviarNotificacionAsync(usuario.getEmail(), "Has reservado el recurso: " + recurso.getTitulo());
                    try {
                        Thread.sleep(100); // 100 milisegundos
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // buena práctica
                    }
                } else {
                    System.out.println("Este recurso no puede ser reservado.");
                }
                break;

            case "5":
                String tituloRecurso = leerEntrada("Ingrese el título del recurso: ");
                RecursoDigital recursoEncontrado = gestorRecursos.buscarRecursoPorTitulo(tituloRecurso);

                if (recursoEncontrado == null) {
                    System.out.println("Recurso no encontrado.");
                    break;
                }

                if (recursoEncontrado instanceof RecursoBase recursoBase) {
                    recursoBase.mostrarReservas();
                } else {
                    System.out.println("Este recurso no tiene reservas asociadas.");
                }
                break;


        }
    }

    public void buscarUsuarioPorNombre() {
        String nombre = leerEntrada("Ingrese el nombre del usuario: ");
        try {
            Usuario usuario = gestorUsuarios.buscarUsuarioPorNombreConExcepcion(nombre);
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

    public void mostrarMenuBusquedaPorFiltros() {
        System.out.println("=== Búsqueda por Filtros ===");
        System.out.println("1. Ordenar por categoría");
        System.out.println("2. Buscar por título");
        System.out.println("0. Volver al menú principal");
    }

    public void buscarPorFiltros() {
        mostrarMenuBusquedaPorFiltros();
        String opcion = leerEntrada("Seleccione un filtro de búsqueda: ");

        switch (opcion) {
            case "1":
                mostrarCategoriasDisponibles();
                String categoria = leerEntrada("Ingrese la categoría: ");
                List<RecursoDigital> recursosPorCategoria = gestorRecursos.filtrarPorCategoria(CategoriaRecurso.valueOf(categoria.toUpperCase()));
                mostrarResultadosBusqueda(recursosPorCategoria);
                break;

            case "2":
                String titulo = leerEntrada("Ingrese el título: ");
                List<RecursoDigital> recursosPorTitulo = gestorRecursos.buscarRecursosPorTitulo(titulo);
                mostrarResultadosBusqueda(recursosPorTitulo);
                break;

            case "0":
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    private void mostrarResultadosBusqueda(List<RecursoDigital> recursos) {
        if (recursos.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            for (RecursoDigital recurso : recursos) {
                recurso.mostrarDetalles();
            }
        }
    }

    private CategoriaRecurso convertirACategoriaRecurso(String categoriaStr) {
        try {
            return CategoriaRecurso.valueOf(categoriaStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void mostrarCategoriasDisponibles() {
        System.out.println("=== Categorías Disponibles ===");
        for (CategoriaRecurso categoria : CategoriaRecurso.values()) {
            System.out.println(categoria.name());
        }
    }
}

