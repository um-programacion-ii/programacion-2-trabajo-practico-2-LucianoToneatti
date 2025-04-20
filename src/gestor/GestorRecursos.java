package gestor;

import modelo.RecursoDigital;

import java.util.HashMap;
import java.util.Map;

public class GestorRecursos {
    private Map<String, RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new HashMap<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.put(recurso.getIdentificador(), recurso);
    }

    public RecursoDigital buscarRecursoPorId(String id) {
        return recursos.get(id);
    }

    public void listarRecursos() {
        for (RecursoDigital r : recursos.values()) {
            System.out.println(r);
        }
    }
}

