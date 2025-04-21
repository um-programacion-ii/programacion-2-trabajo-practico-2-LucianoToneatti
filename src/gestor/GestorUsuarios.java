package gestor;

import excepciones.RecursoNoDisponibleException;
import excepciones.UsuarioNoEncontradoException;
import modelo.RecursoDigital;
import modelo.Usuario;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.get(id);
    }

    public void listarUsuarios() {
        for (Usuario u : usuarios.values()) {
            System.out.println(u);
        }
    }

    // Única versión del método que busca por nombre y lanza excepción si no se encuentra
    public Usuario buscarUsuarioPorNombre(String nombre) throws UsuarioNoEncontradoException {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        throw new UsuarioNoEncontradoException("No se encontró un usuario con el nombre " + nombre);
    }

}


