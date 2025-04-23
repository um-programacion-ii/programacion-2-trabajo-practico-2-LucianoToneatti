package notificaciones;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {

    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        // Lógica para enviar un SMS
        System.out.println("Enviando SMS a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
    }
}
