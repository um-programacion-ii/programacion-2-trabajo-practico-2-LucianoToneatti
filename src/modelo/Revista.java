package modelo;

public class Revista extends RecursoBase implements Prestable {

    private String autor;
    private boolean prestado = false;

    public Revista(String identificador, String titulo, String autor, CategoriaRecurso categoria) {
        super(identificador, titulo, categoria); // Usa enum en lugar de String
        this.autor = autor;
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
                ", Autor: " + autor + ", Categoría: " + categoria + ", Estado: " + estado);
    }
}