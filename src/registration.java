import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class registration extends JFrame implements ActionListener{

        JLabel nameLabel, userLabel, passLabel, emailLabel, ageLabel, genderLabel;
        JTextField nameField, userField, emailField, ageField;
        JPasswordField passField;
        JComboBox<String> genderBox;
        JButton registerBtn, backBtn;

        Connection conn;

        public registration() {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_db", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            setTitle("Registeration");
            setSize(400, 400);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            nameLabel = new JLabel("Name:");
            nameLabel.setBounds(30, 20, 100, 30);
            add(nameLabel);

            nameField = new JTextField();
            nameField.setBounds(150, 20, 150, 30);
            add(nameField);

            userLabel = new JLabel("Username:");
            userLabel.setBounds(30, 60, 100, 30);
            add(userLabel);

            userField = new JTextField();
            userField.setBounds(150, 60, 150, 30);
            add(userField);

            passLabel = new JLabel("Password:");
            passLabel.setBounds(30, 100, 100, 30);
            add(passLabel);

            passField = new JPasswordField();
            passField.setBounds(150, 100, 150, 30);
            add(passField);

            emailLabel = new JLabel("Email:");
            emailLabel.setBounds(30, 140, 100, 30);
            add(emailLabel);

            emailField = new JTextField();
            emailField.setBounds(150, 140, 150, 30);
            add(emailField);

            ageLabel = new JLabel("Age:");
            ageLabel.setBounds(30, 180, 100, 30);
            add(ageLabel);

            ageField = new JTextField();
            ageField.setBounds(150, 180, 150, 30);
            add(ageField);

            genderLabel = new JLabel("Gender:");
            genderLabel.setBounds(30, 220, 100, 30);
            add(genderLabel);

            String[] genders = {"Male", "Female", "Other"};
            genderBox = new JComboBox<>(genders);
            genderBox.setBounds(150, 220, 150, 30);
            add(genderBox);

            registerBtn = new JButton("Register");
            registerBtn.setBounds(100, 270, 100, 30);
            registerBtn.addActionListener(this);
            add(registerBtn);

            backBtn = new JButton("Back");
            backBtn.setBounds(210, 270, 100, 30);
            backBtn.addActionListener(this);
            add(backBtn);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerBtn) {
                String name = nameField.getText();
                String username = userField.getText();
                String password = new String(passField.getPassword());
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderBox.getSelectedItem();

                try {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, username, password, email, age, gender) VALUES (?, ?, ?, ?, ?, ?)");
                    ps.setString(1, name);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setString(4, email);
                    ps.setInt(5, age);
                    ps.setString(6, gender);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Registration Successful!");
                    dispose();
                    new login();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: Username already exists.");
                }
            } else if (e.getSource() == backBtn) {
                dispose();
                new login();

        }
    }
}
