package auth.cableTv.gui;

import auth.cableTv.domain.Admin;
import auth.cableTv.domain.Subscriber;
import auth.cableTv.repository.ObjectParser;
import auth.cableTv.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Gui extends JFrame {

    private JPanel categoryPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;


    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton loginButton;

    private JButton registerButton;

    private AdminUi adminUi = new AdminUi();

    private SubscriberUi subscriberUi = new SubscriberUi();

    private RegistrationUi registrationUi = new RegistrationUi();

    private Repository repository = new Repository();
    private ObjectParser objectParser = new ObjectParser();


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

    public void setupLoginPanel() {
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

        registerButton = new JButton("Regiister");
        categoryPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationUi.showRegistrationPanel();
            }
        });


        add(categoryPanel, BorderLayout.CENTER);
    }


    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // For example:
        if (isAdmin(username, password)) {
            adminUi.showAdminPanel(username);
        } else if (isSubscriber(username, password)) {
            subscriberUi.showSubscriberPanel(username);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }

    private boolean isAdmin(String username, String password) {
        List<String> lines = repository.getLines("admin.txt", List.of(username, password));
        Admin admin = null;
        if (lines != null && !lines.isEmpty()) {
            admin = objectParser.parseAdminString(lines.get(0));
        }
        if (admin != null && admin.getUsername() != null && !admin.getUsername().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isSubscriber(String username, String password) {
        List<String> lines = repository.getLines("subscriber.txt", List.of(username, password));
        Subscriber subscriber = null;
        if (lines != null && !lines.isEmpty()) {
            subscriber = objectParser.parseSubscriberString(lines.get(0));
        }
        if (subscriber != null && subscriber.getUsername() != null && !subscriber.getUsername().isEmpty()) {
            return true;
        }
        return false;
    }



}
