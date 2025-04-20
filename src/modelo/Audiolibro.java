package modelo;

public class Audiolibro extends RecursoBase {

    private String duracion;

    public Audiolibro(String identificador, String duracion) {
        super(identificador);
        this.duracion = duracion;
    }

    public String getDuracion() {
        return duracion;
    }
}

