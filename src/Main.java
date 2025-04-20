import consola.Consola;
import gestor.GestorRecursos;
import gestor.GestorUsuarios;
import modelo.*;

public class Main {

    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        Consola consola = new Consola(gestorUsuarios, gestorRecursos);

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
                    System.out.println("=== Lista de Recursos ===");
                    gestorRecursos.listarRecursos();
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





