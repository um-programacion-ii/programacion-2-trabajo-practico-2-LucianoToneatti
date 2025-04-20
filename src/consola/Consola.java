package consola;

import java.util.Scanner;

public class Consola {
    private Scanner scanner;

    public Consola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("=== Biblioteca Digital ===");
        System.out.println("1. Agregar usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Agregar recurso");
        System.out.println("4. Listar recursos");
        System.out.println("0. Salir");
    }

    public String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void cerrar() {
        scanner.close();
    }
}
