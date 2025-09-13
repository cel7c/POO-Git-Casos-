import java.util.Scanner;

// Excepción personalizada para números negativos
class NumeroNegativoException extends Exception {
    public NumeroNegativoException(String mensaje) {
        super(mensaje);
    }
}

public class Mult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Solicitar número al usuario
            System.out.print("Ingresa un numero entero: ");
            String input = sc.nextLine();

            // Intentar convertir la entrada a numero entero
            int numero = Integer.parseInt(input);

            // Verificar si el numero es negativo
            if (numero < 0) {
                throw new NumeroNegativoException("Error: el numero no puede ser negativo.");
            }

            // Si todo va bien, mostrar el numero ingresado
            System.out.println("Numero ingresado correctamente: " + numero);

        } catch (NumberFormatException e) {
            // Captura cuando el usuario escribe algo que no es un numero entero
            System.out.println("Error: ingresar un numero entero valido.");
        } catch (NumeroNegativoException e) {
            // Captura la excepcion personalizada para numeros negativos
            System.out.println(e.getMessage());
        } finally {
            // Se ejecuta siempre
            System.out.println("Programa finalizado...");
        }

        sc.close();
    }
}
