import java.util.ArrayList;
import java.util.Scanner;

// Clase que representa un producto
class Producto {
    String nombre;
    double precio;
    int cantidad;

    // Constructor
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Método para mostrar información del producto
    @Override
    public String toString() {
        return "Producto: " + nombre + " | Precio: $" + precio + " | Cantidad: " + cantidad;
    }
}

// Clase Inventario con métodos sobrecargados
class Inventario {
    private ArrayList<Producto> productos;

    // Constructor
    public Inventario() {
        productos = new ArrayList<>();
    }

    // Método sobrecargado 1: solo nombre
    public void agregarProducto(String nombre) {
        productos.add(new Producto(nombre, 0.0, 0));
    }

    // Método sobrecargado 2: nombre y precio
    public void agregarProducto(String nombre, double precio) {
        if (precio < 0) {
            System.out.println("Error: El precio no puede ser negativo.");
            return;
        }
        productos.add(new Producto(nombre, precio, 0));
    }

    // Método sobrecargado 3: nombre, precio y cantidad
    public void agregarProducto(String nombre, double precio, int cantidad) {
        if (precio < 0 || cantidad < 0) {
            System.out.println("Error: El precio y la cantidad no pueden ser negativos.");
            return;
        }
        productos.add(new Producto(nombre, precio, cantidad));
    }

    // Mostrar productos del inventario
    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("Inventario vacío.");
        } else {
            System.out.println("Inventario de productos:");
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }
}

// Clase principal con menú interactivo
public class Integran {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventario inv = new Inventario();
        int opcion;

        do {
            System.out.println("\n=== INVENTARIO - INTEGRAN ===");
            System.out.println("1. Agregar producto (solo nombre)");
            System.out.println("2. Agregar producto (nombre y precio)");
            System.out.println("3. Agregar producto (nombre, precio y cantidad)");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del producto: ");
                    String nombre1 = sc.nextLine();
                    inv.agregarProducto(nombre1);
                    break;

                case 2:
                    System.out.print("Ingresar nombre del producto: ");
                    String nombre2 = sc.nextLine();
                    System.out.print("Ingresa el precio: ");
                    double precio2 = sc.nextDouble();
                    inv.agregarProducto(nombre2, precio2);
                    break;

                case 3:
                    System.out.print("Ingrese nombre del producto: ");
                    String nombre3 = sc.nextLine();
                    System.out.print("Ingresa el precio: ");
                    double precio3 = sc.nextDouble();
                    System.out.print("Ingresa la cantidad: ");
                    int cantidad3 = sc.nextInt();
                    inv.agregarProducto(nombre3, precio3, cantidad3);
                    break;

                case 4:
                    inv.mostrarProductos();
                    break;

                case 5:
                    System.out.println("EXIT...");
                    break;

                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
