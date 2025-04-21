package modelo;

public abstract class RecursoBase implements RecursoDigital {
    protected String identificador;
    protected String titulo;
    protected EstadoRecurso estado;
    protected CategoriaRecurso categoria; // Usamos enum

    public RecursoBase(String identificador, String titulo, CategoriaRecurso categoria) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.categoria = categoria;
        this.estado = EstadoRecurso.DISPONIBLE;
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

    @Override
    public void actualizarEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("ID: " + identificador);
        System.out.println("Título: " + titulo);
        System.out.println("Categoría: " + categoria); // Enum.toString()
        System.out.println("Estado: " + estado);
    }
}
