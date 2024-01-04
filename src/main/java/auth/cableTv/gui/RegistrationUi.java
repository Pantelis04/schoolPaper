package auth.cableTv.gui;

import auth.cableTv.domain.Subscriber;
import auth.cableTv.repository.RepositorySubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationUi {
    private RepositorySubscriber repositorySubscriber = new RepositorySubscriber();

    void showRegistrationPanel() {

        JFrame adminFrame = new JFrame("Registration Panel - ");
        adminFrame.setSize(1600, 600);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLayout(new FlowLayout());
        adminFrame.add(registerPanel());
        adminFrame.setVisible(true);
        adminFrame.setLocationRelativeTo(null);
    }

    JPanel registerPanel() {


        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(Color.LIGHT_GRAY);
        registerPanel.setSize(1500, 200);

        JTextField firstNameField = new JTextField(6);

        JTextField lastNameField = new JTextField(6);

        JTextField passwordField = new JTextField(6);

        JTextField usernameField = new JTextField(6);

        JButton registerButton = new JButton("register");

        JTextArea searchResultArea = new JTextArea(10, 40);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    searchResultArea.setText("Cant create subscriber there are empty fields.");
                    searchResultArea.repaint();
                } else {
                    Subscriber subscriber = repositorySubscriber.findSubscriber(username);
                    if (subscriber == null) {
                        repositorySubscriber.addASubscriber(new Subscriber(firstName, lastName, username, password));
                        searchResultArea.setText("Subscriber " + username + " was created");
                        searchResultArea.repaint();
                    } else {
                        searchResultArea.setText("Invalid username");
                        searchResultArea.repaint();
                    }
                }
            }
        });

        registerPanel.add(new JLabel("FirstName:"));
        registerPanel.add(firstNameField);
        registerPanel.add(new JLabel("LastName:"));
        registerPanel.add(lastNameField);
        registerPanel.add(new JLabel("UserName:"));
        registerPanel.add(usernameField);
        registerPanel.add(new JLabel("Password:"));
        registerPanel.add(passwordField);
        registerPanel.add(registerButton);
        registerPanel.add(searchResultArea);
        return registerPanel;
    }
}
