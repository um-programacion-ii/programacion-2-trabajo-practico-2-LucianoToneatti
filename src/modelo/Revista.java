package modelo;

public class Revista extends RecursoBase implements Prestable {

    private String titulo;
    private String autor;
    private boolean prestado = false; // Estado de si la revista está prestada

    public Revista(String identificador, String titulo, String autor) {
        super(identificador);
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
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
        System.out.println("Revista - ID: " + identificador + ", Título: " + titulo +
                ", Autor: " + autor + ", Estado: " + estado);
    }
}




