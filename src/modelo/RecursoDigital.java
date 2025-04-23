package modelo;

public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
    void mostrarDetalles();
    String getTitulo();
    CategoriaRecurso getCategoria();

}



