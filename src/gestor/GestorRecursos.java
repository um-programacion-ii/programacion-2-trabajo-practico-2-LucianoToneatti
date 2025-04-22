package gestor;

import modelo.RecursoDigital;
import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {

    private List<RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    // Método para obtener la lista de recursos
    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

    public void listarRecursos() {
        for (RecursoDigital recurso : recursos) {
            System.out.println("Recurso ID: " + recurso.getIdentificador() + ", Estado: " + recurso.getEstado());
        }
    }
}



