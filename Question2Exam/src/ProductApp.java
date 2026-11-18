import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//  GUI, input controls, processing, file output and menu.
public class ProductApp extends JFrame {
    private final JTextField txtCustomerName = new JTextField(18);
    private final JComboBox<String> cmbProduct = new JComboBox<>(new String[] {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
    });
    private final JTextField txtPrice = new JTextField(18);

    public ProductApp() {
        setTitle("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(460, 260);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomerName);
        panel.add(new JLabel("Select Product:"));
        panel.add(cmbProduct);
        panel.add(new JLabel("Enter Price:"));
        panel.add(txtPrice);

        JButton btnProcess = new JButton("PROCESS");
        btnProcess.addActionListener(e -> processSale());
        panel.add(new JLabel(""));
        panel.add(btnProcess);
        add(panel);

        createMenu();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> processSale());

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }

    // Called by both the button and Tools -> Process.
    private void processSale() {
        String customerName = txtCustomerName.getText().trim();
        String productName = (String) cmbProduct.getSelectedItem();
        String priceText = txtPrice.getText().trim();

        if (customerName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the customer name and product price.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            if (price < 0) {
                JOptionPane.showMessageDialog(this, "Price cannot be negative.");
                return;
            }

            ProductCalculations calculations = new ProductCalculations(price);

            String message = "Customer Name: " + customerName
                    + "\nProduct: " + productName
                    + "\nVAT: R " + calculations.GetVat()
                    + "\nCOMMISSION: R " + calculations.GetCommission()
                    + "\nTOTAL: R " + calculations.GetTotal();

            JOptionPane.showMessageDialog(this, message);
            saveSale(customerName, productName, calculations);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not write to data.txt: " + ex.getMessage());
        }
    }

    // Append all relevant sale information to data.txt.
    private void saveSale(String customerName, String productName,
                          ProductCalculations calculations) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
            writer.println("DATA LOG");
            writer.println("********************************");
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + productName);
            writer.println("VAT: R " + calculations.GetVat());
            writer.println("COMMISSION: R " + calculations.GetCommission());
            writer.println("TOTAL: R " + calculations.GetTotal());
            writer.println("********************************");
            writer.println();
        }
    }

    // Clear text fields and reset combo box to its first item.
    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
        txtCustomerName.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductApp app = new ProductApp();
            app.setVisible(true);
        });
    }
}
