package alertas;

import modelo.Prestamo;
import modelo.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Recordatorio {

    private List<String> historialAlertas = new ArrayList<>();

    public void verificarRecordatorios(List<Prestamo> prestamos, List<Usuario> usuarios) {
        LocalDate hoy = LocalDate.now();

        for (Prestamo p : prestamos) {
            LocalDate fechaFin = p.getFechaDevolucion();
            long diasRestantes = fechaFin.toEpochDay() - hoy.toEpochDay();

            NivelUrgencia urgencia;
            if (diasRestantes > 3) {
                urgencia = NivelUrgencia.INFO;
            } else if (diasRestantes >= 1) {
                urgencia = NivelUrgencia.WARNING;
            } else {
                urgencia = NivelUrgencia.ERROR;
            }

            // Buscar usuario correspondiente
            Usuario u = usuarios.stream()
                    .filter(user -> user.getId().equals(p.getIdUsuario()))
                    .findFirst().orElse(null);

            if (u != null && u.getPreferenciasNotificacion().contains(urgencia)) {
                String mensaje = "[%s] El préstamo del recurso '%s' vence el %s (Usuario: %s)"
                        .formatted(urgencia, p.getIdRecurso(), fechaFin, u.getNombre());

                historialAlertas.add(mensaje);
                System.out.println(mensaje);
            }
        }
    }

    public List<String> getHistorialAlertas() {
        return historialAlertas;
    }
}

