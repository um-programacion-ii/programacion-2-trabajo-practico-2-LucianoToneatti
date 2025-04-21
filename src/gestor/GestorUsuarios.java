package gestor;

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

    // Método para buscar un usuario por nombre
    public Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) { // Comparación insensible a mayúsculas/minúsculas
                return usuario;
            }
        }
        return null; // Si no se encuentra el usuario, se devuelve null
    }

    public void listarUsuarios() {
        for (Usuario u : usuarios.values()) {
            System.out.println(u);
        }
    }
}

