import consola.Consola;
import gestor.GestorBiblioteca;
import gestor.GestorRecursos;
import gestor.GestorUsuarios;
import modelo.*;
import notificaciones.ServicioNotificaciones;
import notificaciones.ServicioNotificacionesEmail; // O ServicioNotificacionesSMS

public class Main {

    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        GestorBiblioteca gestorBiblioteca = new GestorBiblioteca(gestorUsuarios, gestorRecursos); // ✅ Instancia del gestor de biblioteca

        // Aquí elegimos el servicio de notificación que vamos a inyectar
        ServicioNotificaciones servicioNotificaciones = new ServicioNotificacionesEmail();

        // ✅ Creamos consola pasando todos los gestores y el servicio de notificaciones
        Consola consola = new Consola(gestorUsuarios, gestorRecursos, servicioNotificaciones, gestorBiblioteca);

        boolean salir = false;

        while (!salir) {
            consola.mostrarMenuPrincipal();
            String opcion = consola.leerEntrada("Seleccione una opción: ");

            switch (opcion) {
                case "1":
                    String id = consola.leerEntrada("ID del usuario: ");
                    String nombre = consola.leerEntrada("Nombre del usuario: ");
                    String email = consola.leerEntrada("Email del usuario: ");
                    Usuario nuevo = new Usuario(id, nombre, email);
                    gestorUsuarios.agregarUsuario(nuevo);
                    System.out.println("Usuario agregado.");
                    break;

                case "2":
                    System.out.println("=== Lista de Usuarios ===");
                    gestorUsuarios.listarUsuarios();
                    break;

                case "3":
                    consola.mostrarMenuAgregarRecurso();
                    String opcionRecurso = consola.leerEntrada("Seleccione tipo de recurso: ");
                    consola.procesarOpcionAgregarRecurso(opcionRecurso);
                    break;

                case "4":
                    consola.listarRecursos();
                    break;

                case "5":
                    consola.realizarOperacionEnRecurso();
                    break;

                case "6":
                    consola.buscarUsuarioPorNombre();
                    break;

                case "7":
                    consola.buscarRecursoPorTitulo();
                    break;

                case "8":
                    consola.buscarPorFiltros();
                    break;

                case "0":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        System.out.println("¡Programa finalizado!");
    }
}









