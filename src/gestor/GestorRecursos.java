package gestor;

import modelo.RecursoDigital;
import comparadores.ComparadorTitulos;
import comparadores.ComparadorCategorias;
import comparadores.ComparadorEstados;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            System.out.println("Recurso ID: " + recurso.getIdentificador() + ", Título: " + recurso.getTitulo() +
                    ", Estado: " + recurso.getEstado() + ", Categoría: " + recurso.getCategoria());
        }
    }

    // Método para buscar un recurso por su ID
    public RecursoDigital buscarRecursoPorID(String id) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getIdentificador().equals(id)) {
                return recurso;
            }
        }
        return null;
    }

    // Método para buscar recursos por título usando Streams
    public List<RecursoDigital> buscarRecursosPorTitulo(String titulo) {
        return recursos.stream()
                .filter(recurso -> recurso.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    // Método para filtrar recursos por categoría
    public List<RecursoDigital> filtrarPorCategoria(String categoria) {
        return recursos.stream()
                .filter(recurso -> recurso.getCategoria().equalsIgnoreCase(categoria)) // Filtrado por categoría
                .collect(Collectors.toList());
    }

    // Método para ordenar recursos por Título
    public List<RecursoDigital> ordenarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(recurso -> recurso.getTitulo().equalsIgnoreCase(titulo)) // Filtrado por título
                .sorted(new ComparadorTitulos()) // Ordena por título
                .collect(Collectors.toList());
    }

    // Método para ordenar recursos por Categoría
    public List<RecursoDigital> ordenarPorCategoria(String categoria) {
        return recursos.stream()
                .filter(recurso -> recurso.getCategoria().equalsIgnoreCase(categoria)) // Filtrado por categoría
                .sorted(new ComparadorCategorias()) // Ordena por categoría
                .collect(Collectors.toList());
    }

}


