import java.util.Scanner; // Importar la clase Scanner

public class usuariosimple {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del teclado
        Scanner scanner = new Scanner(System.in);

        // Solicitar y leer el nombre
        System.out.print("Por favor, ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        // Solicitar y leer la edad
        System.out.print("Ahora, ingresa tu edad: ");
        int edad = scanner.nextInt();

        // Solicitar y leer la altura
        System.out.print("Por último, ingresa tu altura en metros (ej: 1.75): ");
        double altura = scanner.nextDouble();

        // Mostrar los datos recolectados
        System.out.println("\n--- Datos Ingresados ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Altura: " + altura + " metros");

        // Cerrar el scanner (buena práctica)
        scanner.close();
    }
}