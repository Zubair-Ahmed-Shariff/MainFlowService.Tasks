
package mypackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateBuyer extends JFrame {

    private JTextField txtContactSearch, txtName, txtEmail, txtAddress;
    private JComboBox<String> comboGender;
    private JButton btnUpdate, btnReset, btnCancel;

    public UpdateBuyer() {
        setTitle("Update Buyer Details");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        JLabel lblSearchContact = new JLabel("Search by Contact No:");
        txtContactSearch = new JTextField();

        JLabel lblName = new JLabel("Name:");
        txtName = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        JLabel lblAddress = new JLabel("Address:");
        txtAddress = new JTextField();

        JLabel lblGender = new JLabel("Gender:");
        comboGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        btnUpdate = new JButton("Update");
        btnReset = new JButton("Reset");
        btnCancel = new JButton("Cancel");

        add(lblSearchContact); add(txtContactSearch);
        add(lblName); add(txtName);
        add(lblEmail); add(txtEmail);
        add(lblAddress); add(txtAddress);
        add(lblGender); add(comboGender);
        add(btnUpdate); add(btnReset);
        add(new JLabel()); add(btnCancel);

        // Load existing data when focus is lost from contact field
        txtContactSearch.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                loadBuyerData(txtContactSearch.getText());
            }
        });

        // Update button logic
        btnUpdate.addActionListener(e -> updateBuyer());

        // Reset button logic
        btnReset.addActionListener(e -> resetFields());

        // Cancel button logic
        btnCancel.addActionListener(e -> dispose());
    }

    private void loadBuyerData(String contact) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "your_password")) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM buyer WHERE contact=?");
            pst.setString(1, contact);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txtName.setText(rs.getString("name"));
                txtEmail.setText(rs.getString("email"));
                txtAddress.setText(rs.getString("address"));
                comboGender.setSelectedItem(rs.getString("gender"));
            } else {
                JOptionPane.showMessageDialog(this, "No buyer found with this contact.");
                resetFields();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateBuyer() {
        String contact = txtContactSearch.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String gender = (String) comboGender.getSelectedItem();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "your_password")) {
            PreparedStatement pst = con.prepareStatement("UPDATE buyer SET name=?, email=?, address=?, gender=? WHERE contact=?");
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, address);
            pst.setString(4, gender);
            pst.setString(5, contact);

            int updated = pst.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Buyer details updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Buyer not found.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void resetFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        comboGender.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.");
        }

        SwingUtilities.invokeLater(() -> new UpdateBuyer().setVisible(true));
    }
}
