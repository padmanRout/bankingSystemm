import java.util.Scanner;

// Abstract base class (Abstraction + Encapsulation)
abstract class Account {
    private String accountNumber;
    private String accountHolder;
    protected double balance;

    public Account(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public abstract void withdraw(double amount); // Abstract method (Abstraction)

    public void showDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }

    public double getBalance() {
        return balance;
    }
}

// Child class #1 (Inheritance + Polymorphism)
class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500;

    public SavingsAccount(String accNo, String accHolder) {
        super(accNo, accHolder);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount);
        } else {
            System.out.println("❌ Minimum balance ₹500 must be maintained.");
        }
    }
}

// Child class #2 (Inheritance + Polymorphism)
class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = -10000;

    public CurrentAccount(String accNo, String accHolder) {
        super(accNo, accHolder);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= OVERDRAFT_LIMIT) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount);
        } else {
            System.out.println("❌ Overdraft limit exceeded (₹-10,000).");
        }
    }
}

// Main class
public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account = null;

        System.out.println("Welcome to Simple Banking System");
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Account Number: ");
        String number = sc.nextLine();
        System.out.print("Select Account Type (1. Savings, 2. Current): ");
        int type = sc.nextInt();

        // Runtime polymorphism
        if (type == 1) {
            account = new SavingsAccount(number, name);
        } else if (type == 2) {
            account = new CurrentAccount(number, name);
        } else {
            System.out.println("Invalid account type selected.");
            System.exit(0);
        }

        int choice;
        do {
            System.out.println("\n==== Menu ====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Details");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    break;
                case 3:
                    account.showDetails();
                    break;
                case 4:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}
