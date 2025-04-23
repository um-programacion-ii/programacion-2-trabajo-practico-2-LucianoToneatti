import alertas.AlertaDisponibilidad;
import alertas.AlertaVencimiento;
import consola.Consola;
import gestor.*;
import modelo.*;
import notificaciones.ServicioNotificaciones;
import notificaciones.ServicioNotificacionesEmail;
import gestor.GestorReportes;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Inicialización de los gestores
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        GestorPrestamos gestorPrestamos = new GestorPrestamos();

        // Crear servicio de notificaciones e inyectarlo en el gestor
        ServicioNotificaciones servicioNotificaciones = new ServicioNotificacionesEmail();
        GestorNotificaciones gestorNotificaciones = new GestorNotificaciones(servicioNotificaciones);

        // Crear el gestor de biblioteca
        GestorBiblioteca gestorBiblioteca = new GestorBiblioteca(gestorUsuarios, gestorRecursos);

        // Crear el gestor de reportes
        GestorReportes gestorReportes = new GestorReportes(
                gestorPrestamos.getListaPrestamos(), gestorUsuarios, gestorRecursos
        );

        // Crear la consola
        Consola consola = new Consola(gestorUsuarios, gestorRecursos, servicioNotificaciones, gestorBiblioteca, gestorReportes);

        // Crear alerta de vencimiento
        Map<String, RecursoDigital> recursosDigitales = new HashMap<>();
        for (Map.Entry<String, RecursoBase> entry : gestorRecursos.getRecursos().entrySet()) {
            if (entry.getValue() instanceof RecursoDigital) {
                recursosDigitales.put(entry.getKey(), (RecursoDigital) entry.getValue());
            }
        }

        AlertaVencimiento alertaVencimiento = new AlertaVencimiento(
                gestorPrestamos.getListaPrestamos(),
                gestorUsuarios.getUsuarios(),
                recursosDigitales
        );

        // Crear alerta de disponibilidad
        AlertaDisponibilidad alertaDisponibilidad = new AlertaDisponibilidad(
                gestorRecursos,
                gestorUsuarios,
                gestorRecursos.getRecursos()
        );

        boolean salir = false;

        while (!salir) {
            // Verificar alertas antes de mostrar el menú
            alertaVencimiento.verificarAlertas();

            // Verificar recursos disponibles y notificar a usuarios en espera
            for (RecursoBase recurso : gestorRecursos.getRecursos().values()) {
                alertaDisponibilidad.mostrarAlertaDisponibilidad(recurso);
            }

            consola.mostrarMenuPrincipal();
            String opcion = consola.leerEntrada("Seleccione una opción: ");

            switch (opcion) {
                case "1":
                    String id = consola.leerEntrada("ID del usuario: ");
                    String nombre = consola.leerEntrada("Nombre del usuario: ");
                    String email = consola.leerEntrada("Email del usuario: ");
                    Usuario nuevo = new Usuario(id, nombre, email);
                    gestorUsuarios.agregarUsuario(nuevo);
                    System.out.println("Usuario agregado.");
                    break;

                case "2":
                    System.out.println("=== Lista de Usuarios ===");
                    gestorUsuarios.listarUsuarios();
                    break;

                case "3":
                    consola.mostrarMenuAgregarRecurso();
                    String opcionRecurso = consola.leerEntrada("Seleccione tipo de recurso: ");
                    consola.procesarOpcionAgregarRecurso(opcionRecurso);
                    break;

                case "4":
                    consola.listarRecursos();
                    break;

                case "5":
                    consola.realizarOperacionEnRecurso();
                    break;

                case "6":
                    consola.buscarUsuarioPorNombre();
                    break;

                case "7":
                    consola.buscarRecursoPorTitulo();
                    break;

                case "8":
                    consola.buscarPorFiltros();
                    break;

                case "9":
                    // Registrar préstamo
                    String idUsuario = consola.leerEntrada("ID del usuario: ");
                    String idRecurso = consola.leerEntrada("ID del recurso: ");
                    gestorPrestamos.registrarPrestamo(idUsuario, idRecurso,
                            java.time.LocalDate.now(),
                            java.time.LocalDate.now().plusDays(14));
                    break;

                case "10":
                    consola.mostrarEstadisticasYReportes();
                    break;

                case "11":
                    consola.configurarNotificaciones();
                    break;

                case "0":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        gestorNotificaciones.cerrar();
        System.out.println("¡Programa finalizado!");
    }
}










