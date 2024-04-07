import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.round;

public class Main extends JFrame {
    private ATM atm;
    private JTextField pinField;
    private JButton verifyPinButton, balanceButton, depositButton, withdrawButton;
    private JLabel messageLabel;

    public Main() {
        atm = new ATM(1000); // Initialize ATM with a balance of 1000 for demonstration

        setTitle("ATM System");
        setSize(450, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        pinField = new JTextField(10);
        verifyPinButton = new JButton("Verify PIN");
        messageLabel = new JLabel("Welcome!");
        balanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        balanceButton.setEnabled(false);
        depositButton.setEnabled(false);
        withdrawButton.setEnabled(false);

        add(new JLabel("PIN:"));
        add(pinField);
        add(verifyPinButton);
        add(messageLabel);
        add(balanceButton);
        add(depositButton);
        add(withdrawButton);

        setupButtonListeners();

        setLocationRelativeTo(null); // Center the window
    }

    private void setupButtonListeners() {
        verifyPinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (atm.verifyPIN(pinField.getText())) {
                    messageLabel.setText("PIN Verified Successfully.");
                    JOptionPane.showMessageDialog(null, "PIN Verified Successfully.");
                    enableTransactionButtons(true);
                    verifyPinButton.setEnabled(false);
                    pinField.setEnabled(false);
                } else {
                    messageLabel.setText("Invalid PIN. Try again.");
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Current Balance: $" + atm.checkBalance());
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to deposit:");
                try {
                    double amount = Double.parseDouble(input);
                    if (atm.deposit(amount)) {
                        JOptionPane.showMessageDialog(null, "Deposited: $" + amount + "\nCurrent Balance: $" + atm.checkBalance());
                    } else if (amount-round(amount) >= 0.01) {
                        JOptionPane.showMessageDialog(null, "Less than a penny? ");
                    } else if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "You cannot deposit less than $0. ");
                    } else if (amount >10000){
                        JOptionPane.showMessageDialog(null, "If you want to withdraw more than $10,000, please go to the counter. ");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. \nPlease enter a numerical amount. ");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
                try {
                    double amount = Double.parseDouble(input);
                    if (atm.withdraw(amount)) {
                        JOptionPane.showMessageDialog(null, "Withdrew: $" + amount + "\nCurrent Balance: $" + atm.checkBalance());
                    } else if (amount-round(amount) >= 0.01) {
                        JOptionPane.showMessageDialog(null, "Less than a penny? ");
                    } else if (amount > 10000){
                        JOptionPane.showMessageDialog(null, "If you want to withdraw more than $10,000, please go to the counter. ");
                    } else if (amount <= 0){
                        JOptionPane.showMessageDialog(null, "You cannot withdraw less than $0. ");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Funds.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.\nPlease enter a numerical amount. ");
                }
            }
        });
    }

    private void enableTransactionButtons(boolean enabled) {
        balanceButton.setEnabled(enabled);
        depositButton.setEnabled(enabled);
        withdrawButton.setEnabled(enabled);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}