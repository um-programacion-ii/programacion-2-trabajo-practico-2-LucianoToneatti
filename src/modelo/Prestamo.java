package modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Prestamo {
    private String idPrestamo;
    private String idUsuario;
    private String idRecurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(String idUsuario, String idRecurso, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.idPrestamo = UUID.randomUUID().toString();
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getIdPrestamo() {
        return idPrestamo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo='" + idPrestamo + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", idRecurso='" + idRecurso + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}




