package alertas;

import modelo.Prestamo;
import modelo.RecursoDigital;
import modelo.Usuario;
import gestor.GestorUsuarios;
import gestor.GestorRecursos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlertaVencimiento {

    private List<Prestamo> prestamos;
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private static final int RECORDATORIO_DIA_ANTERIOR = 1;
    private static final int RECORDATORIO_DIA_VENCIMIENTO = 0;
    private Map<String, Usuario> usuarios;
    private Map<String, RecursoDigital> recursos;

    public AlertaVencimiento(List<Prestamo> prestamos, Map<String, Usuario> usuarios, Map<String, RecursoDigital> recursos) {
        this.prestamos = prestamos;
        this.usuarios = usuarios;
        this.recursos = recursos;
    }

    public void verificarAlertas() {
        LocalDate hoy = LocalDate.now();
        Scanner scanner = new Scanner(System.in); // ← Scanner agregado

        for (Prestamo prestamo : prestamos) {
            LocalDate fechaDevolucion = prestamo.getFechaDevolucion();

            if (fechaDevolucion.equals(hoy.minusDays(RECORDATORIO_DIA_ANTERIOR))) {
                mostrarAlerta(prestamo, "📅 Recordatorio: el préstamo vence mañana.");
            } else if (fechaDevolucion.equals(hoy)) {
                mostrarAlerta(prestamo, "⏰ ¡ALERTA! El préstamo vence hoy.");

                // Solicitar renovación al usuario
                System.out.print("¿Desea renovar este préstamo? (s/n): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    renovarPrestamo(prestamo);
                }
            }
        }
    }

    private void mostrarAlerta(Prestamo prestamo, String mensaje) {
        Usuario usuario = usuarios.get(prestamo.getIdUsuario());
        RecursoDigital recurso = recursos.get(prestamo.getIdRecurso());

        System.out.println("=== Alerta de Vencimiento ===");
        System.out.println(mensaje);
        System.out.println("📄 ID Préstamo: " + prestamo.getIdPrestamo());
        System.out.println("👤 Usuario: " + (usuario != null ? usuario.getNombre() : "Desconocido"));
        System.out.println("📘 Recurso: " + (recurso != null ? recurso.getTitulo() : "Desconocido"));
        System.out.println("📅 Fecha Devolución: " + prestamo.getFechaDevolucion());
        System.out.println("-----------------------------");
    }

    private void renovarPrestamo(Prestamo prestamo) {
        // 📌 Lógica de renovación: agrega 14 días desde la fecha actual
        LocalDate nuevaFechaDevolucion = LocalDate.now().plusDays(14);
        prestamo.setFechaDevolucion(nuevaFechaDevolucion);
        System.out.println("✅ Préstamo renovado hasta: " + nuevaFechaDevolucion);
    }
}



