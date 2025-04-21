package modelo;

public class Libro extends RecursoBase implements Prestable, Renovable {

    private String autor;
    private boolean prestado = false; // Estado de si el libro está prestado
    private int vecesRenovado = 0;    // Contador de veces renovado

    public Libro(String identificador, String titulo, String autor) {
        super(identificador, titulo); // Nuevo constructor con título
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
                ", Autor: " + autor + ", Estado: " + estado);
    }
}









