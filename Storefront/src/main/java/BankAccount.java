
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankAccount {
    
    Scanner scan = new Scanner(System.in);

    private String password;
    private float balance;

    private boolean admin = false;

    public BankAccount(float initialDeposit) {
        balance = initialDeposit;
        setPassword();
    }
    
    // Ask the user to set password, requiring two successful entries to lock it in permanently
    private void setPassword() {
        try {
            System.out.println("Please enter a password for your account: ");
            System.out.println("If you want to log in as an admin, use the password \"admin\"");
            password = scan.nextLine();
            System.out.println("Confirm your password by entering it one more time!");
            String tempPassCheck = scan.nextLine();
            if(!password.equals(tempPassCheck))
            {
                System.out.println("Your passwords do not match ... ");
                setPassword();
            }
            else
            {
                System.out.println("Password set! Your account is now ready with a balance of " + balance);
            }

            if(password.equals(tempPassCheck) && password.equals("admin") && tempPassCheck.equals("admin")) {
                admin = true;

                Store.setAdmin(true);

            }
        }
        catch(InputMismatchException exception) {
            System.out.println("Invalid Input! Try again.");
        }
    }
    
    public boolean canAfford(double amount) {
        if(amount <= balance)
        {
            return true;
        }
        else
        {
            System.out.println("You don't have enough money in your account.");
            return false;
        }            
    }
    
    // Allow the user to attempt to withdraw money and report on success. If successful, adjust balance.
    public void makePurchase(double amount) {
        if(amount <= balance)
        {
            balance-=amount;
            System.out.println("" + amount + " spent from your account");
            System.out.println("You have " + balance + " remaining.");
        }
        else
        {
            System.out.println("Not enough remaining funds");
        }
    }
    
    public void depositMoney(double amount) {
        balance+=amount;
        System.out.println("You added " + amount + " to your account.");
        System.out.println("You now have $" + balance + " available.");
    }
    
    public void balanceReport()
    {
        System.out.println("You have " + balance + " left in your account.");
    }
    
    public boolean checkPassword() {
        System.out.println("Please enter your password to access account: ");
        String passEntry = scan.nextLine();
        if(passEntry.equals(password))
        {
            return true;
        }
        else
        {
            System.out.println("Incorrect password!");
            return false;
        }
    }

}
