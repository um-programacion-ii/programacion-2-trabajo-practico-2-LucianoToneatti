package gestor;

import notificaciones.ServicioNotificaciones;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestorNotificaciones {

    private final ExecutorService executor;
    private final ServicioNotificaciones servicio;

    // Constructor acepta cualquier tipo de servicio: Email o SMS
    public GestorNotificaciones(ServicioNotificaciones servicio) {
        this.executor = Executors.newFixedThreadPool(2); // podés ajustar el pool
        this.servicio = servicio;
    }

    // Método para enviar notificación de forma asíncrona
    public void enviarNotificacionAsync(String destinatario, String mensaje) {
        executor.submit(() -> {
            servicio.enviarNotificacion(destinatario, mensaje);
        });
    }

    // Importante: apagar el executor al cerrar la app
    public void cerrar() {
        executor.shutdown();
    }
}

