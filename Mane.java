import java.util.Scanner;

public class Mane {
    public static void main(String[] args) {
        // objeto Scanner para leer la entrada del usuario
        Scanner sc = new Scanner(System.in);

        try {
            // ingrese dos números enteros
            System.out.print("Ingrese 1er numero entero: ");
            int num1 = sc.nextInt();

            System.out.print("Ingrese 2do numero entero: ");
            int num2 = sc.nextInt();

            // división
            int resultado = num1 / num2;
            System.out.println("Resultado de la division: " + resultado);

        } catch (ArithmeticException e) {
            // Capturamos el error si el usuario intenta dividir entre cero
            System.out.println("Error: division entre cero no permitida");
        } finally {
            // Cerramos el Scanner siempre, ocurra o no ocurra error
            sc.close();
            System.out.println("Programa finalizado.");
        }
    }
}
