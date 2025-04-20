package modelo;

public abstract class RecursoBase implements RecursoDigital {
    protected String identificador;
    protected EstadoRecurso estado;

    public RecursoBase(String identificador) {
        this.identificador = identificador;
        this.estado = EstadoRecurso.DISPONIBLE; // Estado inicial por defecto
    }

    @Override
    public String getIdentificador() {
        return identificador;
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
        System.out.println("Estado: " + estado);
    }
}


