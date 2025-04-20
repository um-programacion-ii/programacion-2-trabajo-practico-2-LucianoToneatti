package modelo;

public class Revista extends RecursoBase {

    private String tema;

    public Revista(String identificador, String tema) {
        super(identificador);
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }
}
