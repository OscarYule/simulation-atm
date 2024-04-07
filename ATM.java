import static java.lang.Math.*;

public class ATM {
    private double balance;
    private String storedPIN = "1234"; // Simulated stored PIN for verification

    public ATM(double initialBalance) {
        balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0 & amount <= 10000 & amount-round(amount) < 0.01) {
            balance += amount;
            System.out.println("Deposit Successful. New Balance: $" + balance);
            return true;
        } else if (amount-round(amount) >= 0.01) {
            System.out.println("Less than a penny? Poor guy. ");
            return false;
        } else if (amount <= 0) {
            System.out.println("No Negatives! ");
            return false;
        } else if (amount > 10000){
            System.out.println("If you want to withdraw more than $10000, please go to the counter");
            return false;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount){
        if (amount > 0 & balance >= amount & amount <= 10000 & amount-round(amount) < 0.01) {
            balance -= amount;
            System.out.println("Withdraw Successful. New Balance: $" + balance);
            return true;
        } else if (amount-round(amount) >= 0.01) {
            System.out.println("Less than a penny? Poor guy. ");
            return false;
        } else if (amount > 10000) {
            System.out.println("Insufficient Funds. \nIf you want to withdraw more than $10000, please go to the counter");
            return false;
        } else if (amount <= 0){
            System.out.println("No Negatives! ");
            return false;
        } else {
            System.out.println("Insufficient Funds. ");
            return false;
        }
    }

    public double checkBalance() {
        System.out.println("Current Balance: $" + balance);
        return balance;
    }

    public boolean verifyPIN(String enteredPIN) {
        if (enteredPIN.equals(storedPIN)) {
            System.out.println("PIN verified successfully.");
            return true;
        } else {
            System.out.println("Invalid PIN. Access denied.");
            return false;
        }
    }

}
