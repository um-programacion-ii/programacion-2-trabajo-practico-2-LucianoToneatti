package gestor;

import excepciones.UsuarioNoEncontradoException;
import modelo.Usuario;
import modelo.RecursoDigital;

import java.util.List;

public class GestorBiblioteca {

    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;

    // Constructor modificado para aceptar GestorUsuarios y GestorRecursos
    public GestorBiblioteca(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
    }

    // Métodos de la clase GestorBiblioteca
    public Usuario buscarUsuarioPorNombre(String nombre) throws UsuarioNoEncontradoException {
        return gestorUsuarios.buscarUsuarioPorNombre(nombre);
    }

    public List<RecursoDigital> buscarRecursosPorTitulo(String titulo) {
        return gestorRecursos.buscarRecursosPorTitulo(titulo);
    }

    /// ///////////////7
    public void generarReportePrestamosPorUsuario() {
        // tu implementación
    }

    public void generarReporteRecursosMasPrestados() {
        // tu implementación
    }

    public void mostrarEstadisticasPorCategoria() {
        // tu implementación
    }
}