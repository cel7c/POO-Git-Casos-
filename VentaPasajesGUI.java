import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VentaPasajesGUI extends JFrame {
    private JTextField txtNombre, txtAsiento, txtPrecio;
    private JComboBox<String> cbDestino;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;
    private List<String[]> ventas = new ArrayList<>();
    private static final String ARCHIVO = "ventas.dat";

    public VentaPasajesGUI() {
        setTitle("Sistema de Ventas - Pasajes de Bus [DATOS GUARDADOS]");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cargar ventas existentes
        cargarVentas();

        crearComponentes();
        actualizarTabla();
    }

    private void crearComponentes() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // PANEL DE FORMULARIO
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Ingresar Datos"));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Destino:"));
        cbDestino = new JComboBox<>(new String[]{"Lima", "Arequipa", "Cusco", "Puno", "Abancay"});
        panelFormulario.add(cbDestino);

        panelFormulario.add(new JLabel("Asiento:"));
        txtAsiento = new JTextField();
        panelFormulario.add(txtAsiento);

        panelFormulario.add(new JLabel("Precio S/.:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        JButton btnRegistrar = new JButton("REGISTRAR VENTA");
        btnRegistrar.setBackground(new Color(0, 150, 0));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 12));
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(btnRegistrar);

        // PANEL DE TABLA
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Ventas Registradas - " + ventas.size() + " ventas"));

        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Destino", "Asiento", "Precio"}, 0);
        tablaVentas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaVentas);
        scrollTabla.setPreferredSize(new Dimension(500, 200));
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // EVENTOS
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        // AGREGAR COMPONENTES
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void registrarVenta() {
        String nombre = txtNombre.getText().trim();
        String destino = cbDestino.getSelectedItem().toString();
        String asiento = txtAsiento.getText().trim();
        String precioText = txtPrecio.getText().trim();

        // Validaciones
        if (nombre.isEmpty() || asiento.isEmpty() || precioText.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Complete todos los campos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioText);
            
            // Guardar en memoria y archivo
            String[] nuevaVenta = {nombre, destino, asiento, String.valueOf(precio)};
            ventas.add(nuevaVenta);
            guardarVentas();
            
            // Actualizar tabla
            actualizarTabla();
            limpiarCampos();
            
            JOptionPane.showMessageDialog(this, 
                " VENTA REGISTRADA EXITOSAMENTE\n\n" +
                "Nombre: " + nombre + "\n" +
                "Destino: " + destino + "\n" + 
                "Asiento: " + asiento + "\n" +
                "Precio: S/. " + precio + "\n\n" +
                "Total de ventas: " + ventas.size() + "\n" +
                "Los datos están GUARDADOS permanentemente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Ingrese un precio válido\nEjemplo: 50.00", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarVentas() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split("\\|");
                    if (datos.length == 4) {
                        ventas.add(datos);
                    }
                }
                System.out.println("_" + ventas.size() + " ventas cargadas desde archivo");
            } catch (IOException e) {
                System.err.println("Error cargando ventas: " + e.getMessage());
            }
        }
    }

    private void guardarVentas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (String[] venta : ventas) {
                writer.println(venta[0] + "|" + venta[1] + "|" + venta[2] + "|" + venta[3]);
            }
            System.out.println("Ventas guardadas: " + ventas.size());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error guardando archivo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (String[] venta : ventas) {
            modeloTabla.addRow(new Object[]{venta[0], venta[1], venta[2], "S/. " + venta[3]});
        }
        
        // Actualizar título
        Container content = getContentPane();
        Component[] components = content.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                Component[] subComps = panel.getComponents();
                for (Component subComp : subComps) {
                    if (subComp instanceof JPanel) {
                        JPanel subPanel = (JPanel) subComp;
                        if (subPanel.getBorder() instanceof javax.swing.border.TitledBorder) {
                            javax.swing.border.TitledBorder border = (javax.swing.border.TitledBorder) subPanel.getBorder();
                            if (border.getTitle().contains("Ventas Registradas")) {
                                subPanel.setBorder(BorderFactory.createTitledBorder(
                                    "Ventas Registradas - " + ventas.size() + " ventas"));
                            }
                        }
                    }
                }
            }
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtAsiento.setText("");
        txtPrecio.setText("");
        cbDestino.setSelectedIndex(0);
        txtNombre.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentaPasajesGUI().setVisible(true);
            }
        });
    }
}