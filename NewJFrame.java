
import javax.swing.*;

public class NewJFrame extends javax.swing.JFrame {

    // Constructor
    public NewJFrame() {
        initComponents();
    }

    // NetBeans-generated init method (replace with GUI builder code if you use drag-and-drop)
    private void initComponents() {
        jLabel1 = new JLabel("Login Page");
        jLabel2 = new JLabel("username");
        jLabel3 = new JLabel("password");
        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();
        jButton1 = new JButton("login");
        jButton2 = new JButton("clear");
        jButton3 = new JButton("close");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);

        jLabel1.setBounds(150, 20, 100, 30);
        jLabel2.setBounds(50, 70, 100, 25);
        jLabel3.setBounds(50, 110, 100, 25);
        jTextField1.setBounds(150, 70, 150, 25);
        jPasswordField1.setBounds(150, 110, 150, 25);
        jButton1.setBounds(30, 180, 80, 30);
        jButton2.setBounds(150, 180, 80, 30);
        jButton3.setBounds(270, 180, 80, 30);

        add(jLabel1);
        add(jLabel2);
        add(jLabel3);
        add(jTextField1);
        add(jPasswordField1);
        add(jButton1);
        add(jButton2);
        add(jButton3);

        // Event Listeners
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton3.addActionListener(this::jButton3ActionPerformed);
    }

    // Login Button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTextField1.getText().equals("admin") &&
            new String(jPasswordField1.getPassword()).equals("1234")) {
            setVisible(false);
            new NewJFrame().setVisible(true); // or go to next frame
        } else {
            jTextField1.setText(null);
            jPasswordField1.setText(null);
            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
        }
    }

    // Clear Button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1.setText(null);
        jPasswordField1.setText(null);
    }

    // Close Button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
    }

    // Main method
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new NewJFrame().setVisible(true));
    }

    // Variable Declarations
    private JTextField jTextField1;
    private JPasswordField jPasswordField1;
    private JButton jButton1, jButton2, jButton3;
    private JLabel jLabel1, jLabel2, jLabel3;
}
