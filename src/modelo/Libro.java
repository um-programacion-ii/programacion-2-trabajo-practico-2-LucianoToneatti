package modelo;

public class Libro extends RecursoBase implements Prestable, Renovable {

    private String autor;
    private boolean prestado = false;
    private int vecesRenovado = 0;

    public Libro(String identificador, String titulo, String autor, CategoriaRecurso categoria) {
        super(identificador, titulo, categoria); // Usa enum directamente
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
    public boolean renovar() {
        if (estado == EstadoRecurso.PRESTADO && vecesRenovado < 3) {
            vecesRenovado++;
            return true;
        }
        return false;
    }

    @Override
    public int getVecesRenovado() {
        return vecesRenovado;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Libro - ID: " + identificador + ", Título: " + titulo +
                ", Autor: " + autor + ", Categoría: " + categoria + ", Estado: " + estado);
    }
}











