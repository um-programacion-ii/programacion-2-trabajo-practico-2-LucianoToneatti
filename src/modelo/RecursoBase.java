package modelo;

public abstract class RecursoBase implements RecursoDigital {
    protected String identificador;
    protected String titulo; // ➕ Nuevo atributo
    protected EstadoRecurso estado;

    public RecursoBase(String identificador, String titulo) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.estado = EstadoRecurso.DISPONIBLE; // Estado inicial por defecto
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    public String getTitulo() { // ➕ Nuevo getter
        return titulo;
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
        System.out.println("Estado: " + estado);
    }
}


