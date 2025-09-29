import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BeneficiariosGUI extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;

    public BeneficiariosGUI() {
        setTitle("Registro de Beneficiarios - Comedores Populares");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"ID", "Nombre", "DNI", "Edad", "Comedor"});
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        cargarDatosCSV("beneficiarios.csv");

        add(scroll, BorderLayout.CENTER);
    }

    private void cargarDatosCSV(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                modelo.addRow(datos);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BeneficiariosGUI().setVisible(true));
    }
}
