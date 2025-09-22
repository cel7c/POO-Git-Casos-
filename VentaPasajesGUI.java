import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentaPasajesGUI extends JFrame {
    private JTextField txtNombre, txtAsiento, txtPrecio;
    private JComboBox<String> cbDestino;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;

    public VentaPasajesGUI() {
        setTitle("Registro de Venta de Pasajes TBus");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de formulario con márgenes
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // margen interno

        panelFormulario.add(new JLabel("Nombre del pasajero:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Destino:"));
        cbDestino = new JComboBox<>(new String[]{"Lima", "Arequipa", "Cusco", "Puno", "Abancay"});
        panelFormulario.add(cbDestino);

        panelFormulario.add(new JLabel("Número de asiento:"));
        txtAsiento = new JTextField();
        panelFormulario.add(txtAsiento);

        panelFormulario.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        JButton btnRegistrar = new JButton("Registrar Venta");
        panelFormulario.add(btnRegistrar);

        // Modelo de tabla
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Destino", "Asiento", "Precio"}, 0);
        tablaVentas = new JTable(modeloTabla);

        // Evento botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        // Layout principal (márgenes generales)
        JPanel contenedorPrincipal = new JPanel(new BorderLayout(10, 10));
        contenedorPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // margen de la ventana

        contenedorPrincipal.add(panelFormulario, BorderLayout.NORTH);
        contenedorPrincipal.add(new JScrollPane(tablaVentas), BorderLayout.CENTER);

        add(contenedorPrincipal);
    }

    private void registrarVenta() {
        String nombre = txtNombre.getText();
        String destino = cbDestino.getSelectedItem().toString();
        String asiento = txtAsiento.getText();
        String precio = txtPrecio.getText();

        if (nombre.isEmpty() || asiento.isEmpty() || precio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        modeloTabla.addRow(new Object[]{nombre, destino, asiento, precio});

        // Limpiar espacios
        txtNombre.setText("");
        txtAsiento.setText("");
        txtPrecio.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentaPasajesGUI().setVisible(true);
        });
    }
}
