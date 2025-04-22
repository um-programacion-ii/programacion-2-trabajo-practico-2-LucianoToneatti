package gestor;

import modelo.Prestamo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();

    public void registrarPrestamo(String idUsuario, String idRecurso, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        Prestamo p = new Prestamo(idUsuario, idRecurso, fechaPrestamo, fechaDevolucion);
        prestamos.add(p);
        System.out.println("📚 Préstamo registrado para " + idUsuario + " del recurso " + idRecurso +
                ". Fecha de préstamo: " + fechaPrestamo + ", Fecha de devolución: " + fechaDevolucion);
    }

    public void mostrarPrestamos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos.");
        } else {
            System.out.println("📚 Lista de préstamos registrados:");
            for (Prestamo p : prestamos) {
                System.out.println(p);
            }
        }
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

}


