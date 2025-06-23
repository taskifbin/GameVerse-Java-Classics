import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class login extends JFrame implements ActionListener{
        JLabel userLabel, passLabel;
        JTextField userField;
        JPasswordField passField;
        JButton loginBtn, registerBtn;

        Connection conn;

        public login() {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_db", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            setTitle("Login");
            setSize(400, 300);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            userLabel = new JLabel("Username:");
            userLabel.setBounds(30, 50, 100, 30);
            add(userLabel);

            userField = new JTextField();
            userField.setBounds(150, 50, 150, 30);
            add(userField);

            passLabel = new JLabel("Password:");
            passLabel.setBounds(30, 100, 100, 30);
            add(passLabel);

            passField = new JPasswordField();
            passField.setBounds(150, 100, 150, 30);
            add(passField);

            loginBtn = new JButton("Login");
            loginBtn.setBounds(100, 150, 100, 30);
            loginBtn.addActionListener(this);
            add(loginBtn);

            registerBtn = new JButton("Register");
            registerBtn.setBounds(210, 150, 100, 30);
            registerBtn.addActionListener(this);
            add(registerBtn);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginBtn) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                try {
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Login Successful!");
                        dispose();
                        new gameMenu();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Credentials!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == registerBtn) {
                dispose();
                new registration();

        }
    }
}
