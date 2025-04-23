package gestor;

import modelo.Prestamo;
import modelo.RecursoDigital;
import modelo.Usuario;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class GestorReportes {

    private List<Prestamo> prestamos;
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private ExecutorService executorService;

    public GestorReportes(List<Prestamo> prestamos, GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        this.prestamos = prestamos;
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    // 1. Reporte de todos los préstamos
    public void reporteGeneralPrestamos() {
        Callable<Void> tareaReporte = () -> {
            System.out.println("=== Reporte General de Préstamos ===");
            int totalPrestamos = prestamos.size();
            int prestamosGenerados = 0;

            for (Prestamo prestamo : prestamos) {
                Usuario usuario = gestorUsuarios.buscarUsuarioPorId(prestamo.getIdUsuario());
                RecursoDigital recurso = gestorRecursos.buscarRecursoPorID(prestamo.getIdRecurso());

                System.out.println("ID Préstamo: " + prestamo.getIdPrestamo());
                System.out.println("Usuario: " + (usuario != null ? usuario.getNombre() : "Desconocido"));
                System.out.println("Recurso: " + (recurso != null ? recurso.getTitulo() : "Desconocido"));
                System.out.println("Fecha Préstamo: " + prestamo.getFechaPrestamo());
                System.out.println("Fecha Devolución: " + prestamo.getFechaDevolucion());
                System.out.println("-------------------------------------");

                // Actualizar progreso
                prestamosGenerados++;
                int progreso = (prestamosGenerados * 100) / totalPrestamos;
                System.out.println("Progreso: " + progreso + "%");
                try {
                    Thread.sleep(100); // Simular tiempo de ejecución
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("Reporte de préstamos generado exitosamente.");
            return null;
        };

        // Ejecutar la tarea en segundo plano
        executorService.submit(tareaReporte);
    }

    // Método para cerrar el ExecutorService
    public void cerrar() {
        executorService.shutdown();
    }

    // 2. Préstamos por usuario
    public void prestamosPorUsuario(String idUsuario) {
        System.out.println("=== Préstamos del Usuario ===");
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        List<Prestamo> prestamosUsuario = prestamos.stream()
                .filter(p -> p.getIdUsuario().equals(idUsuario))
                .collect(Collectors.toList());

        if (prestamosUsuario.isEmpty()) {
            System.out.println("No hay préstamos para este usuario.");
        } else {
            for (Prestamo p : prestamosUsuario) {
                RecursoDigital recurso = gestorRecursos.buscarRecursoPorID(p.getIdRecurso());
                System.out.println("Recurso: " + (recurso != null ? recurso.getTitulo() : "Desconocido"));
                System.out.println("Fecha Préstamo: " + p.getFechaPrestamo());
                System.out.println("Fecha Devolución: " + p.getFechaDevolucion());
                System.out.println("---------------------------------");
            }
        }
    }

    // 3. Recursos más prestados
    public void recursosMasPrestados() {
        System.out.println("=== Recursos Más Prestados ===");

        Map<String, Long> conteo = prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getIdRecurso, Collectors.counting()));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5) // Top 5 recursos más prestados
                .forEach(entry -> {
                    RecursoDigital recurso = gestorRecursos.buscarRecursoPorID(entry.getKey());
                    System.out.println("Recurso: " + (recurso != null ? recurso.getTitulo() : "Desconocido"));
                    System.out.println("Cantidad de Préstamos: " + entry.getValue());
                    System.out.println("-----------------------------");
                });
    }

    // 4. Préstamos en una fecha específica
    public void prestamosPorFecha(LocalDate fecha) {
        System.out.println("=== Préstamos en la Fecha: " + fecha + " ===");

        List<Prestamo> prestamosEnFecha = prestamos.stream()
                .filter(p -> p.getFechaPrestamo().equals(fecha))
                .collect(Collectors.toList());

        if (prestamosEnFecha.isEmpty()) {
            System.out.println("No se registraron préstamos en esa fecha.");
        } else {
            for (Prestamo p : prestamosEnFecha) {
                Usuario usuario = gestorUsuarios.buscarUsuarioPorId(p.getIdUsuario());
                RecursoDigital recurso = gestorRecursos.buscarRecursoPorID(p.getIdRecurso());
                System.out.println("Usuario: " + (usuario != null ? usuario.getNombre() : "Desconocido"));
                System.out.println("Recurso: " + (recurso != null ? recurso.getTitulo() : "Desconocido"));
                System.out.println("Fecha Devolución: " + p.getFechaDevolucion());
                System.out.println("--------------------------------");
            }
        }
    }

    // 5. Reporte de préstamos activos (fechaDevolución > hoy)
    public void prestamosActivos() {
        System.out.println("=== Préstamos Activos ===");
        LocalDate hoy = LocalDate.now();

        List<Prestamo> activos = prestamos.stream()
                .filter(p -> p.getFechaDevolucion().isAfter(hoy))
                .collect(Collectors.toList());

        if (activos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
        } else {
            for (Prestamo p : activos) {
                Usuario usuario = gestorUsuarios.buscarUsuarioPorId(p.getIdUsuario());
                RecursoDigital recurso = gestorRecursos.buscarRecursoPorID(p.getIdRecurso());

                System.out.println("Usuario: " + (usuario != null ? usuario.getNombre() : "Desconocido"));
                System.out.println("Recurso: " + (recurso != null ? recurso.getTitulo() : "Desconocido"));
                System.out.println("Fecha Devolución: " + p.getFechaDevolucion());
                System.out.println("----------------------------------");
            }
        }
    }
}


