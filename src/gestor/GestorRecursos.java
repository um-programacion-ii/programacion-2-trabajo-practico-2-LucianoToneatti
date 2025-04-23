package gestor;

import excepciones.RecursoNoDisponibleException;
import modelo.RecursoBase;
import modelo.RecursoDigital;
import comparadores.ComparadorTitulos;
import comparadores.ComparadorCategorias;
import comparadores.ComparadorEstados;

import java.util.*;
import java.util.stream.Collectors;

// Importar tu enum
import modelo.CategoriaRecurso;

public class GestorRecursos {

    private List<RecursoBase> recursos;



    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoBase recurso) {
        recursos.add(recurso);
    }

    public void listarRecursos() {
        for (RecursoDigital recurso : recursos) {
            System.out.println("Recurso ID: " + recurso.getIdentificador() + ", Título: " + recurso.getTitulo() +
                    ", Estado: " + recurso.getEstado() + ", Categoría: " + recurso.getCategoria());
        }
    }

    public RecursoDigital buscarRecursoPorID(String id) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getIdentificador().equals(id)) {
                return recurso;
            }
        }
        return null;
    }

    public List<RecursoDigital> buscarRecursosPorTitulo(String titulo) {
        return recursos.stream()
                .filter(recurso -> recurso.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    // ✅ Método corregido para filtrar por enum CategoriaRecurso
    public List<RecursoDigital> filtrarPorCategoria(CategoriaRecurso categoria) {
        return recursos.stream()
                .filter(recurso -> recurso.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    // ✅ Método corregido para ordenar solo si pertenece a una categoría dada
    public List<RecursoDigital> ordenarPorCategoria(CategoriaRecurso categoria) {
        return recursos.stream()
                .filter(recurso -> recurso.getCategoria() == categoria)
                .sorted(new ComparadorCategorias())
                .collect(Collectors.toList());
    }

    // Este no hace falta modificarlo porque trabaja con Strings
    public List<RecursoDigital> ordenarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(recurso -> recurso.getTitulo().equalsIgnoreCase(titulo))
                .sorted(new ComparadorTitulos())
                .collect(Collectors.toList());
    }
    /// ///////////////////////////////////////////////////////////////////////////////////////////
    public void realizarOperacionRecurso(String idRecurso) throws RecursoNoDisponibleException {
        RecursoDigital recurso = buscarRecursoPorID(idRecurso);
        if (recurso == null) {
            throw new RecursoNoDisponibleException("El recurso con ID " + idRecurso + " no está disponible.");
        }
        // Operaciones con el recurso
    }


    /// ///////////////////////////////////////////////////////////////////////////////////////////

    public RecursoBase buscarRecursoPorTitulo(String titulo) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getTitulo().equalsIgnoreCase(titulo)) {
                return (RecursoBase) recurso;
            }
        }
        return null;
    }

    public Map<String, RecursoBase> getRecursos() {
        Map<String, RecursoBase> recursosMap = new HashMap<>();
        for (RecursoBase recurso : recursos) {
            recursosMap.put(recurso.getIdentificador(), recurso);
        }
        return recursosMap;
    }
}



