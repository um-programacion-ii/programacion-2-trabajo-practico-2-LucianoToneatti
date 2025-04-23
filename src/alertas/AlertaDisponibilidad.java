package alertas;

import modelo.*;
import gestor.GestorRecursos;
import gestor.GestorUsuarios;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class AlertaDisponibilidad {

    private GestorRecursos gestorRecursos;
    private GestorUsuarios gestorUsuarios;
    private Map<String, RecursoBase> recursos;

    public AlertaDisponibilidad(GestorRecursos gestorRecursos, GestorUsuarios gestorUsuarios, Map<String, RecursoBase> recursos) {
        this.gestorRecursos = gestorRecursos;
        this.gestorUsuarios = gestorUsuarios;
        this.recursos = recursos;
    }

    // Método para alertar disponibilidad de recursos
    public void mostrarAlertaDisponibilidad(RecursoBase recurso) {
        // Verificar si el recurso está disponible
        if (recurso.getEstado().equals(EstadoRecurso.DISPONIBLE)) {
            if (recurso.tieneReservas()) {
                Reserva siguienteReserva = recurso.obtenerProximaReserva();
                // Notificar al siguiente usuario en la lista de reservas
                System.out.println("¡El recurso '" + recurso.getTitulo() + "' ahora está disponible!");
                System.out.println("La próxima persona en la lista de espera es: " + siguienteReserva.getUsuario().getNombre());
                System.out.println("El recurso estará disponible para " + siguienteReserva.getUsuario().getNombre() + " para su préstamo.");
            } else {
                System.out.println("El recurso '" + recurso.getTitulo() + "' está disponible pero no hay usuarios en espera.");
            }
        }
    }


    // Mostrar alerta de disponibilidad
    private void mostrarAlertaDisponibilidad(RecursoDigital recurso) {
        System.out.println("=== Alerta de Disponibilidad ===");
        System.out.println("El recurso '" + recurso.getTitulo() + "' está ahora disponible.");
        System.out.println("ID Recurso: " + recurso.getIdentificador());
        System.out.println("Categoría: " + recurso.getCategoria());
        System.out.println("-----------------------------");

        // Preguntar al usuario si quiere hacer un préstamo
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Desea realizar un préstamo inmediato? (Sí/No): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("Sí")) {
            realizarPrestamo(recurso);
        }
    }

    // Realizar el préstamo
    private void realizarPrestamo(RecursoDigital recurso) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su ID de usuario: ");
        String usuarioId = scanner.nextLine();

        // Verificar si el usuario existe
        Usuario usuario = gestorUsuarios.getUsuarios().get(usuarioId);
        if (usuario != null) {
            // Obtener datos para el constructor de Prestamo
            String idUsuario = usuario.getId();
            String idRecurso = recurso.getIdentificador();
            LocalDate fechaInicio = LocalDate.now();
            LocalDate fechaFin = fechaInicio.plusDays(7); // Ejemplo: préstamo por 7 días

            Prestamo nuevoPrestamo = new Prestamo(idUsuario, idRecurso, fechaInicio, fechaFin);

            recurso.actualizarEstado(EstadoRecurso.PRESTADO); // Cambiar estado de recurso

            System.out.println("Préstamo realizado con éxito para el recurso '" + recurso.getTitulo() + "'");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}




