package gestor;

import modelo.RecursoDigital;
import comparadores.ComparadorTitulos;
import comparadores.ComparadorCategorias;
import comparadores.ComparadorEstados;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Importar tu enum
import modelo.CategoriaRecurso;

public class GestorRecursos {

    private List<RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public List<RecursoDigital> getRecursos() {
        return recursos;
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
}



