package modelo;

public class Libro extends RecursoBase {

    private String autor;

    public Libro(String identificador, String autor) {
        super(identificador);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }
}

