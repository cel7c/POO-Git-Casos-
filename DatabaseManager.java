import java.io.File;
import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:data.db";
    
    // Cargar el driver manualmente
    static {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println(" Driver SQLite cargado MANUALMENTE");
        } catch (ClassNotFoundException e) {
            System.err.println(" ERROR CRÍTICO: Driver SQLite no encontrado");
            System.err.println("   El JAR sqlite-jdbc-3.50.3.0.jar no está en el classpath");
            System.err.println("   Ejecuta desde CMD: java -cp \".;sqlite-jdbc-3.50.3.0.jar\" VentaPasajesGUI");
        }
    }
    
    public DatabaseManager() {
        System.out.println(" Iniciando DatabaseManager...");
        crearTabla();
    }
    
    private Connection connect() {
        try {
            System.out.println(" Conectando a BD...");
            Connection conn = DriverManager.getConnection(URL);
            System.out.println(" Conexión BD exitosa");
            return conn;
        } catch (SQLException e) {
            System.err.println(" Falla conexión BD: " + e.getMessage());
            return null;
        }
    }
    
    private void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS ventas_pasajes (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nombre_pasajero TEXT NOT NULL," +
                     "destino TEXT NOT NULL," +
                     "asiento TEXT NOT NULL," +
                     "precio REAL NOT NULL," +
                     "fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP)";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println(" Tabla creada exitosamente");
            
            // Verificar archivo
            File dbFile = new File("data.db");
            if (dbFile.exists()) {
                System.out.println(" ARCHIVO data.db CREADO: " + dbFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println(" Error tabla: " + e.getMessage());
        }
    }
    
    public boolean insertarVenta(String nombre, String destino, String asiento, double precio) {
        String sql = "INSERT INTO ventas_pasajes(nombre_pasajero, destino, asiento, precio) VALUES(?,?,?,?)";
        
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, destino);
            pstmt.setString(3, asiento);
            pstmt.setDouble(4, precio);
            pstmt.executeUpdate();
            System.out.println(" Venta GUARDADA EN BD: " + nombre);
            return true;
        } catch (Exception e) {
            System.err.println(" Error guardando venta: " + e.getMessage());
            return false;
        }
    }
}