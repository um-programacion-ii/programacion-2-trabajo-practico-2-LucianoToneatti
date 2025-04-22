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

    public void listarUsuarios() {
        for (Usuario u : usuarios.values()) {
            System.out.println(u);
        }
    }
}
