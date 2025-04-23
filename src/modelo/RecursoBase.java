package modelo;

import modelo.Usuario;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.Queue;
import modelo.Reserva;

public abstract class RecursoBase implements RecursoDigital {
    protected String identificador;
    protected String titulo;
    protected EstadoRecurso estado;
    protected CategoriaRecurso categoria; // Usamos enum
    protected Usuario usuarioActual;

    protected BlockingQueue<Reserva> reservas = new PriorityBlockingQueue<>();

    public RecursoBase(String identificador, String titulo, CategoriaRecurso categoria) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.categoria = categoria;
        this.estado = EstadoRecurso.DISPONIBLE;
    }

    // Métodos sincronizados para evitar condiciones de carrera
    public synchronized Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public synchronized void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public CategoriaRecurso getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRecurso categoria) {
        this.categoria = categoria;
    }

    @Override
    public EstadoRecurso getEstado() {
        return estado;
    }

    public Queue<Reserva> getReservas() {
        return reservas;
    }

    public synchronized void actualizarEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("ID: " + identificador);
        System.out.println("Título: " + titulo);
        System.out.println("Categoría: " + categoria); // Enum.toString()
        System.out.println("Estado: " + estado);
    }

    /// ///////////////////////RESERVAS////////////////////////////////
    public synchronized void agregarReserva(Reserva reserva) {
        reservas.offer(reserva);
        System.out.println("Reserva agregada: " + reserva.getUsuario().getNombre() +
                " para el recurso: " + this.titulo + " con prioridad: " + reserva.getPrioridad());
    }

    public synchronized void procesarProximaReserva() {
        if (!reservas.isEmpty()) {
            Reserva proximaReserva = reservas.poll();
            Usuario siguienteUsuario = proximaReserva.getUsuario();
            this.usuarioActual = siguienteUsuario;
            this.estado = EstadoRecurso.PRESTADO;

            System.out.println("Recurso asignado a: " + siguienteUsuario.getNombre() +
                    " debido a una reserva. Fecha de reserva: " + proximaReserva.getFechaReserva() +
                    " con prioridad " + proximaReserva.getPrioridad());
        } else {
            this.estado = EstadoRecurso.DISPONIBLE;
            this.usuarioActual = null;
            System.out.println("No hay más reservas. Recurso ahora disponible.");
        }
    }

    // Método adicional para obtener la próxima reserva
    public synchronized Reserva obtenerProximaReserva() {
        return reservas.peek(); // Retorna la próxima reserva sin eliminarla
    }

    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas para este recurso.");
        } else {
            System.out.println("Reservas para " + this.titulo + ":");
            for (Reserva r : reservas) {
                System.out.println("- " + r.getUsuario().getNombre() + " | Fecha: " + r.getFechaReserva() + " | Prioridad: " + r.getPrioridad());
            }
        }
    }

    public boolean tieneReservas() {
        return !reservas.isEmpty();
    }
    ///////////////////////////////////////////////////////////////////////
    // Añadir el método devolverRecurso en RecursoBase
    public synchronized void devolverRecurso() {
        this.estado = EstadoRecurso.DISPONIBLE;
        this.usuarioActual = null;
        System.out.println("Recurso devuelto y ahora disponible para nuevas reservas.");
    }
}


