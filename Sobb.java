import java.util.Scanner;

public class Sobb {

    public int sumar(int a, int b) {
        return a + b;
    }

    public int sumar(int a, int b, int c) {
        return a + b + c;
    }

    public double sumar(double a, double b) {
        return a + b;
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in); 
        Sobb calc = new Sobb(); // Instancia de la misma clase

        System.out.println("WELCOME TO SOBB ");
        
        System.out.println("Ingresar 1er numero entero: ");
        int num1 = sc.nextInt();
        System.out.println("Ingresar 2do numero entero: ");
        int num2 = sc.nextInt();
        System.out.println("Resultado de la suma de 2 enteros: " + calc.sumar(num1, num2));

        System.out.println("Ingresar 1er numero entero: ");
        int num3 = sc.nextInt();
        System.out.println("Ingresar 2do numero entero: ");
        int num4 = sc.nextInt();
        System.out.println("Ingresar 3er numero entero: ");
        int num5 = sc.nextInt();
        System.out.println("Resultado de la suma de 3 enteros: " + calc.sumar(num3, num4, num5));

        System.out.println("Ingresar 1er numero decimal: ");
        double num6 = sc.nextDouble();
        System.out.println("Ingresar 2do numero decimal: ");
        double num7 = sc.nextDouble();
        System.out.println("Resultado de la suma de 2 decimales: " + calc.sumar(num6, num7));

        sc.close();
    }
}
