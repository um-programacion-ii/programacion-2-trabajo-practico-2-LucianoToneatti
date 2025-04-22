package modelo;

import java.time.LocalDateTime;

public class Reserva implements Comparable<Reserva> {
    private Usuario usuario;
    private RecursoBase recurso;
    private LocalDateTime fechaReserva;
    private int prioridad;  // Nueva variable de prioridad

    public Reserva(Usuario usuario, RecursoBase recurso, int prioridad) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();
        this.prioridad = prioridad; // Asignación de prioridad
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoBase getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "usuario=" + usuario.getNombre() +
                ", recurso=" + recurso.getTitulo() +
                ", fecha=" + fechaReserva +
                ", prioridad=" + prioridad +  // Mostrar prioridad
                '}';
    }

    // Implementación del método compareTo para ordenar por prioridad
    @Override
    public int compareTo(Reserva o) {
        return Integer.compare(o.prioridad, this.prioridad);  // Prioridad más alta primero
    }
}



