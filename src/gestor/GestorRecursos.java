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

    // Método para listar los recursos
    public void listarRecursos() {
        for (RecursoDigital recurso : recursos) {
            System.out.println("Recurso ID: " + recurso.getIdentificador() + ", Estado: " + recurso.getEstado());
        }
    }

    // Método para buscar un recurso por su ID
    public RecursoDigital buscarRecursoPorID(String id) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getIdentificador().equals(id)) {
                return recurso;
            }
        }
        return null; // Si no se encuentra el recurso, se devuelve null
    }

    // Método para buscar recursos por título
    public List<RecursoDigital> buscarRecursosPorTitulo(String titulo) {
        List<RecursoDigital> resultados = new ArrayList<>();
        for (RecursoDigital recurso : recursos) {
            if (recurso.getTitulo().equalsIgnoreCase(titulo)) { // Comparación insensible a mayúsculas/minúsculas
                resultados.add(recurso);
            }
        }
        return resultados;
    }
}