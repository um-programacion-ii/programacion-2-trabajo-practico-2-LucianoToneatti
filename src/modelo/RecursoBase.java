package modelo;

public abstract class RecursoBase implements RecursoDigital {
    protected String identificador;
    protected EstadoRecurso estado;

    public RecursoBase(String identificador) {
        this.identificador = identificador;
        this.estado = EstadoRecurso.DISPONIBLE;
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
    public String toString() {
        return "RecursoBase{" +
                "identificador='" + identificador + '\'' +
                ", estado=" + estado +
                '}';
    }
}
