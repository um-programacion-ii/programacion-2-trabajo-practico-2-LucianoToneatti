package simulacion;

import modelo.*;
import java.util.concurrent.*;

public class SimulacionConcurrencia {

    public static void main(String[] args) throws InterruptedException {
        // Crear usuarios
        Usuario usuario1 = new Usuario("1", "Juan", "juan@example.com");
        Usuario usuario2 = new Usuario("2", "Maria", "maria@example.com");
        Usuario usuario3 = new Usuario("3", "Pedro", "pedro@example.com");

        // Crear recurso concreto
        RecursoConcreto recurso1 = new RecursoConcreto("R001", "Libro de Ciencia", CategoriaRecurso.CIENCIA);

        // Crear un ExecutorService para manejar los hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Crear y ejecutar las reservas en diferentes hilos
        executor.submit(() -> hacerReserva(usuario1, recurso1, 1));  // Prioridad baja
        executor.submit(() -> hacerReserva(usuario2, recurso1, 3));  // Prioridad alta
        executor.submit(() -> hacerReserva(usuario3, recurso1, 2));  // Prioridad media

        // Esperar a que todos los hilos terminen
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Mostrar estado final de las reservas y procesar la primera reserva
        recurso1.mostrarReservas();
        recurso1.procesarProximaReserva();  // Procesar la siguiente reserva según prioridad

        // Devolver el recurso y procesar la siguiente reserva
        recurso1.devolverRecurso();
        recurso1.procesarProximaReserva();
    }

    // Método para simular la reserva de un recurso
    public static void hacerReserva(Usuario usuario, RecursoConcreto recurso, int prioridad) {
        Reserva reserva = new Reserva(usuario, recurso, prioridad);
        synchronized (recurso) {  // Sincronización para evitar condiciones de carrera
            recurso.agregarReserva(reserva);
            System.out.println("Reserva realizada por " + usuario.getNombre() + " con prioridad " + prioridad);
        }
    }
}

// Clase RecursoConcreto
class RecursoConcreto extends RecursoBase {

    public RecursoConcreto(String identificador, String titulo, CategoriaRecurso categoria) {
        super(identificador, titulo, categoria);
    }

    // Método para procesar la próxima reserva de manera sincronizada
    @Override
    public synchronized void procesarProximaReserva() {
        super.procesarProximaReserva();
    }
}

