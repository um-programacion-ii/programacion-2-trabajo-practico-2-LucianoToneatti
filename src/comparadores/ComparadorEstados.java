package comparadores;

import modelo.RecursoDigital;
import java.util.Comparator;

public class ComparadorEstados implements Comparator<RecursoDigital> {

    @Override
    public int compare(RecursoDigital recurso1, RecursoDigital recurso2) {
        return recurso1.getEstado().compareTo(recurso2.getEstado());
    }
}

