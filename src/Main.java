import consola.Consola;
import gestor.GestorRecursos;
import gestor.GestorUsuarios;
import modelo.*;

public class Main {
    public static void main(String[] args) {
        Consola consola = new Consola();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();

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
                    String recursoId = consola.leerEntrada("ID del recurso: ");
                    RecursoDigital recurso = new RecursoBase(recursoId) {}; // clase anónima para testeo
                    gestorRecursos.agregarRecurso(recurso);
                    System.out.println("Recurso agregado.");
                    break;
                case "4":
                    System.out.println("=== Lista de Recursos ===");
                    gestorRecursos.listarRecursos();
                    break;
                case "0":
                    salir = true;
                    consola.cerrar();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        System.out.println("¡Programa finalizado!");
    }
}
