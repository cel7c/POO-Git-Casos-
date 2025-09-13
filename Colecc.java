import java.util.ArrayList;
import java.util.Scanner;

public class Colecc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> estudiantes = new ArrayList<>();
        int opcion;

        do {
            // Menu de opciones
            System.out.println("\nSELECCIONE ALGUNA OPCION-------------");
            System.out.println("1. Ingresar 5 nombres de estudiantes");
            System.out.println("2. Mostrar lista completa");
            System.out.println("3. Eliminar el tercer nombre");
            System.out.println("4. Mostrar lista despues de eliminar");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    estudiantes.clear(); // Limpiamos
                    for (int i = 0; i < 5; i++) {
                        System.out.print("Ingresa el nombre del estudiante " + (i + 1) + ": ");
                        String nombre = sc.nextLine();
                        estudiantes.add(nombre);
                    }
                    System.out.println("5 nombres ingresados correctamente.");
                    break;

                case 2:
                    System.out.println("Lista de estudiantes:");
                    for (int i = 0; i < estudiantes.size(); i++) {
                        System.out.println((i + 1) + ". " + estudiantes.get(i));
                    }
                    break;

                case 3:
                    if (estudiantes.size() >= 3) {
                        String eliminado = estudiantes.remove(2); // indice 2 es el tercer elemento
                        System.out.println("Se elimino el tercer nombre: " + eliminado);
                    } else {
                        System.out.println("No hay suficientes estudiantes para eliminar el tercero.");
                    }
                    break;

                case 4:
                    System.out.println("Lista despues de eliminar:");
                    for (int i = 0; i < estudiantes.size(); i++) {
                        System.out.println((i + 1) + ". " + estudiantes.get(i));
                    }
                    break;

                case 5:
                    System.out.println("EXIT...");
                    break;

                default:
                    System.out.println("Opcion invalida, intenta de nuevo.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
