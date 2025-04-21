package comparadores;

import modelo.RecursoDigital;
import java.util.Comparator;

public class ComparadorTitulos implements Comparator<RecursoDigital> {

    @Override
    public int compare(RecursoDigital recurso1, RecursoDigital recurso2) {
        return recurso1.getTitulo().compareToIgnoreCase(recurso2.getTitulo());
    }
}

