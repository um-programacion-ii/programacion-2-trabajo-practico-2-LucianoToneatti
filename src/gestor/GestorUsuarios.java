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

    // Versión que lanza una excepción si no encuentra el usuario
    public Usuario buscarUsuarioPorNombreConExcepcion(String nombre) throws UsuarioNoEncontradoException {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        throw new UsuarioNoEncontradoException("No se encontró un usuario con el nombre " + nombre);
    }

    // Versión alternativa que devuelve null si no encuentra el usuario
    public Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }
    public Map<String, Usuario> getUsuarios() {
        return this.usuarios;
    }

}



