package modelo;

public class Audiolibro extends RecursoBase implements Prestable {

    private String autor;
    private String duracion;
    private boolean prestado = false; // Estado de si el audiolibro está prestado

    public Audiolibro(String identificador, String titulo, String autor, String duracion) {
        super(identificador, titulo);
        this.autor = autor;
        this.duracion = duracion;
    }

    public String getAutor() {
        return autor;
    }

    public String getDuracion() {
        return duracion;
    }

    @Override
    public boolean prestar() {
        if (estado == EstadoRecurso.DISPONIBLE && !prestado) {
            prestado = true;
            estado = EstadoRecurso.PRESTADO;
            return true;
        }
        return false;
    }

    @Override
    public boolean devolver() {
        if (prestado) {
            prestado = false;
            estado = EstadoRecurso.DISPONIBLE;
            return true;
        }
        return false;
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Audiolibro - ID: " + identificador + ", Título: " + titulo +
                ", Autor: " + autor + ", Duración: " + duracion + ", Estado: " + estado);
    }
}