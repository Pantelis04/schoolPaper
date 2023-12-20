package auth.cableTv.gui;

import auth.cableTv.domain.Admin;
import auth.cableTv.domain.Subscriber;
import auth.cableTv.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.List;

public class Gui extends JFrame{

    private JPanel categoryPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private Admin loggedInAdmin;
    private Subscriber loggedInSubscriber;

    private Repository repository =new Repository();

    public Gui() {
        initializeUI();
        setupLoginPanel();
    }

    public void initializeUI() {
        setTitle("CableTv");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setFocusable(true);
        setLayout(new BorderLayout());
    }

    private void setupLoginPanel() {
        categoryPanel = new JPanel(new GridLayout(3, 2));

        categoryPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        categoryPanel.add(usernameField);

        categoryPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        categoryPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        categoryPanel.add(loginButton);

        add(categoryPanel, BorderLayout.CENTER);
    }


    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Use getLines to get admins and subscribers
        // Check if the user is an admin or subscriber
        // If admin, show admin panel; if subscriber, show subscriber panel
        // You need to implement the logic for authentication and switching panels

        // For example:
        if (isAdmin(username, password)) {
            showAdminPanel();
        } else if (isSubscriber(username, password)) {
            showSubscriberPanel();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }

    private boolean isAdmin(String username, String password) {
        repository.getLines("admin.txt", List.of(username,password));
        return false;
    }

    private boolean isSubscriber(String username, String password) {
        repository.getLines("subscriber.txt", List.of(username,password));
        return false;
    }

    private void showAdminPanel() {
        // Implement the admin panel
        // Include search functionality, display movie/tv series details, reviews, and update fields
    }

    private void showSubscriberPanel() {
        // Implement the subscriber panel
        // Include search functionality, display movie/tv series details, and add to favorites
        // Also, display subscriber profile with reviews and favorite movies/tv series
    }


}
