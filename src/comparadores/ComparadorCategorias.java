package comparadores;

import modelo.RecursoDigital;
import java.util.Comparator;

public class ComparadorCategorias implements Comparator<RecursoDigital> {

    @Override
    public int compare(RecursoDigital recurso1, RecursoDigital recurso2) {
        return recurso1.getCategoria().compareToIgnoreCase(recurso2.getCategoria());
    }
}

