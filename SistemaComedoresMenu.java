import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SistemaComedoresMenu extends JFrame {
    public SistemaComedoresMenu() {
        setTitle("Sistema de Gestión de Comedores Populares");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel fondo = new JPanel();
        fondo.setBackground(new Color(255, 255, 204)); // Amarillo claro
        fondo.setLayout(null); // Posicionamiento manual

        // Botones principales
        JButton btnBeneficiarios = new JButton(" Beneficiarios");
        JButton btnInsumos = new JButton(" Insumos");
        JButton btnMenus = new JButton(" Menús");

        Font fuente = new Font("Arial", Font.BOLD, 18);
        btnBeneficiarios.setFont(fuente);
        btnInsumos.setFont(fuente);
        btnMenus.setFont(fuente);

        btnBeneficiarios.setBounds(350, 160, 200, 40);
        btnInsumos.setBounds(350, 220, 200, 40);
        btnMenus.setBounds(350, 280, 200, 40);

        fondo.add(btnBeneficiarios);
        fondo.add(btnInsumos);
        fondo.add(btnMenus);

        // Barra de búsqueda por DNI
        JLabel lblBuscar = new JLabel(" Buscar por DNI:");
        JTextField txtDNI = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        lblBuscar.setFont(new Font("Arial", Font.BOLD, 16));
        txtDNI.setFont(new Font("Arial", Font.PLAIN, 16));
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 16));

        lblBuscar.setBounds(300, 360, 150, 30);
        txtDNI.setBounds(460, 360, 150, 30);
        btnBuscar.setBounds(620, 360, 100, 30);

        fondo.add(lblBuscar);
        fondo.add(txtDNI);
        fondo.add(btnBuscar);

        setContentPane(fondo);

        // Acciones de los botones
        btnBeneficiarios.addActionListener(e -> abrirModulo("Beneficiarios", "beneficiarios.csv",
                new String[]{"ID", "Nombre", "DNI", "Edad", "Comedor"}));

        btnInsumos.addActionListener(e -> abrirModulo("Insumos", "insumos.csv",
                new String[]{"ID", "Nombre", "Cantidad", "Unidad", "Proveedor"}));

        btnMenus.addActionListener(e -> abrirModulo("Menús", "menus.csv",
                new String[]{"Fecha", "Plato Principal", "Acompañamiento", "Postre", "Comedor"}));

        btnBuscar.addActionListener(e -> {
            String dniBuscado = txtDNI.getText().trim();
            if (dniBuscado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un DNI.");
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader("beneficiarios.csv"))) {
                String linea;
                br.readLine(); // Saltar encabezado
                boolean encontrado = false;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 3 && datos[2].trim().equals(dniBuscado)) {
                        encontrado = true;
                        mostrarBeneficiario(datos);
                        break;
                    }
                }
                if (!encontrado) {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún beneficiario con ese DNI.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer beneficiarios.csv: " + ex.getMessage());
            }
        });
    }

    private void abrirModulo(String titulo, String archivoCSV, String[] columnas) {
        JDialog ventana = new JDialog(this, titulo, true);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                modelo.addRow(datos);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer " + archivoCSV + ": " + e.getMessage());
        }

        ventana.add(scroll);
        ventana.setSize(700, 400);
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }

    private void mostrarBeneficiario(String[] datos) {
        String mensaje = String.format(
            " Beneficiario encontrado:\n\nID: %s\nNombre: %s\nDNI: %s\nEdad: %s\nComedor: %s",
            datos[0], datos[1], datos[2], datos[3], datos[4]
        );
        JOptionPane.showMessageDialog(this, mensaje, "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaComedoresMenu().setVisible(true));
    }
}
