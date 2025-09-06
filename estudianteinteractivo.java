import java.util.Scanner;

// Clase Estudiante con atributos privados
class Estudiante {
    // Atributos privados
    private String nombre;
    private int edad;
    private double promedio;
    private String codigo;

    // Constructor
    public Estudiante(String nombre, int edad, double promedio, String codigo) {
        this.nombre = nombre;
        this.edad = edad;
        this.promedio = promedio;
        this.codigo = codigo;
    }

    // Métodos GET
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPromedio() {
        return promedio;
    }

    public String getCodigo() {
        return codigo;
    }

    // Métodos SET
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}

// Clase principal que mantiene el nombre solicitado
public class estudianteinteractivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== REGISTRO DE ESTUDIANTE ===");
        
        // Solicitar datos por teclado
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese la edad: ");
        int edad = scanner.nextInt();
        
        System.out.print("Ingrese el promedio: ");
        double promedio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        
        System.out.print("Ingrese el código de estudiante: ");
        String codigo = scanner.nextLine();

        // Crear objeto Estudiante usando el constructor
        Estudiante estudiante = new Estudiante(nombre, edad, promedio, codigo);
        
        // Mostrar datos usando métodos get
        System.out.println("\n=== INFORMACIÓN REGISTRADA ===");
        System.out.println("Nombre: " + estudiante.getNombre());
        System.out.println("Edad: " + estudiante.getEdad() + " años");
        System.out.println("Promedio: " + estudiante.getPromedio());
        System.out.println("Código: " + estudiante.getCodigo());
        
        // Ejemplo de uso de métodos set
        System.out.print("\n¿Desea modificar el promedio? (s/n): ");
        char opcion = scanner.nextLine().charAt(0);
        
        if (opcion == 's' || opcion == 'S') {
            System.out.print("Nuevo promedio: ");
            double nuevoPromedio = scanner.nextDouble();
            estudiante.setPromedio(nuevoPromedio);
            
            System.out.println("Promedio actualizado: " + estudiante.getPromedio());
            
            // Mostrar todos los datos actualizados
            System.out.println("\n=== DATOS ACTUALIZADOS ===");
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Edad: " + estudiante.getEdad() + " años");
            System.out.println("Promedio: " + estudiante.getPromedio());
            System.out.println("Código: " + estudiante.getCodigo());
        }
        
        scanner.close();
        System.out.println("\n¡Registro completado!");
    }
}