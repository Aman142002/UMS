package university.management.system;

import university.management.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField textFieldName;
    JPasswordField passwordField;
    JButton login, back;

    Login() {
        setLayout(null);

        // Username Label and TextField
        JLabel labelName = new JLabel("Username");
        labelName.setBounds(40, 20, 100, 20);
        add(labelName);

        textFieldName = new JTextField();
        textFieldName.setBounds(150, 20, 150, 20);
        add(textFieldName);

        // Password Label and PasswordField
        JLabel labelpass = new JLabel("Password");
        labelpass.setBounds(40, 70, 100, 20);
        add(labelpass);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 20);
        add(passwordField);

        // Login Button
        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setBackground(Color.GRAY);
        login.setForeground(Color.BLACK);
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.addActionListener(this);
        add(login);

        // Back Button
        back = new JButton("Back");
        back.setBounds(180, 140, 120, 30);
        back.setBackground(Color.LIGHT_GRAY);
        back.setForeground(Color.BLACK);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.addActionListener(this);
        add(back);

        // Image for the Login Window (Optional, you can remove it if not needed)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/second.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(350, 20, 200, 200);
        add(img);

        // Background Image (Optional, you can remove it if not needed)
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/loginback.png"));
        Image i22 = i11.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel image = new JLabel(i33);
        image.setBounds(0, 0, 600, 300);
        add(image);

        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = textFieldName.getText();
            String password = new String(passwordField.getPassword()); // Convert password to string

            // Fixing the query syntax
            String query = "SELECT * FROM login WHERE username='" + username + "' AND password='" + password + "'";

            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                if (resultSet.next()) {
                    // Login successful, navigate to the main class
                    setVisible(false);
                    new main_class(); // Opening the next window (MainClass)
                } else {
                    // Show error message if login fails
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            // Close the login screen if the user clicks "Back"
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login(); // Start the Login window
    }
}
