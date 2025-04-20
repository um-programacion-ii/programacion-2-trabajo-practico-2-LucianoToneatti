package notificaciones;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {

    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        // Lógica para enviar un correo electrónico
        System.out.println("Enviando email a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
    }
}

