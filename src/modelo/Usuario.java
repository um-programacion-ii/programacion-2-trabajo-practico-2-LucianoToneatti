package modelo;

import alertas.NivelUrgencia;

import java.util.EnumSet;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private EnumSet<NivelUrgencia> preferenciasNotificacion = EnumSet.of(NivelUrgencia.INFO, NivelUrgencia.WARNING, NivelUrgencia.ERROR);
    private EnumSet<NivelUrgencia> notificacionesActivas;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        // Inicializa notificaciones activas con las preferencias actuales por defecto
        this.notificacionesActivas = EnumSet.copyOf(preferenciasNotificacion);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public EnumSet<NivelUrgencia> getPreferenciasNotificacion() {
        return preferenciasNotificacion;
    }

    public void setPreferenciasNotificacion(EnumSet<NivelUrgencia> preferencias) {
        this.preferenciasNotificacion = preferencias;
    }

    public EnumSet<NivelUrgencia> getNotificacionesActivas() {
        return notificacionesActivas;
    }

    public void setNotificacionesActivas(EnumSet<NivelUrgencia> notificacionesActivas) {
        this.notificacionesActivas = notificacionesActivas;
    }
}


