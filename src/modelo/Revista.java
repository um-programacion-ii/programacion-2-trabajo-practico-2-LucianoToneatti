package modelo;

public class Revista extends RecursoBase implements Prestable {

    private String autor;
    private boolean prestado = false; // Estado de si la revista está prestada
    private String categoria;

    public Revista(String identificador, String titulo, String autor, String categoria) {
        super(identificador, titulo, categoria);
        this.autor = autor;
        this.categoria = categoria;

    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;  // Implementamos el getter para la categoría
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
                ", Autor: " + autor + ", Categoría: " + categoria + ", Estado: " + estado);
    }
}