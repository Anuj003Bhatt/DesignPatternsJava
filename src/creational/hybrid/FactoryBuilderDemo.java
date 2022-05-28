package creational.hybrid;

abstract class BankAccount {
    private long accountNumber;
    private String owner;
    private double openingBalance;

    public BankAccount(long accountNumber, String owner, double openingBalance){
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.openingBalance = openingBalance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    protected void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    protected void setOwner(String owner) {
        this.owner = owner;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    protected void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    abstract double annualInterest();

    public interface Builder {
        BankAccount.Builder withOwner(String owner);
        BankAccount.Builder withOpeningBalance(double openingBalance);
        BankAccount build();
    }
}

class Savings extends BankAccount {
    private final double interestRate = 2;

    public Savings(long accountNumber, String owner, double openingBalance) {
        super(accountNumber, owner, openingBalance);
    }

    @Override
    public double annualInterest() {
        return (getOpeningBalance()*interestRate*12)/100;
    }

    /**
     * This is the builder pattern which is basically creating the complex object of Savings account
     */
    public static class Builder implements BankAccount.Builder{
        long accountNumber;
        String owner;
        double openingBalance;
        double interestRate;

        public Builder(long accountNumber) {
            this.accountNumber = accountNumber;
        }

        @Override
        public Savings.Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        @Override
        public Savings.Builder withOpeningBalance(double openingBalance) {
            this.openingBalance = openingBalance;
            return this;
        }

        @Override
        public Savings build() {
            return new Savings(accountNumber, owner, openingBalance);
        }

    }

    @Override
    public String toString() {
        return "Current{" +
                "\n\tAccount Number=" + getAccountNumber() +
                "\n\tAccount Owner=" + getOwner() +
                "\n\tInterest Rate=" + interestRate +
                "\n}";
    }
}

class Current extends BankAccount {
    private final double interestRate = 4;

    public Current(long accountNumber, String owner, double openingBalance) {
        super(accountNumber, owner, openingBalance);
    }

    @Override
    public double annualInterest() {
        return (getOpeningBalance()*interestRate*12)/100;
    }

    /**
     * This is the builder pattern which is basically creating the complex object of Current account
     */
    public static class Builder implements BankAccount.Builder{
        long accountNumber;
        String owner;
        double openingBalance;
        double interestRate;

        public Builder(long accountNumber) {
            this.accountNumber = accountNumber;
        }

        @Override
        public Current.Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        @Override
        public Current.Builder withOpeningBalance(double openingBalance) {
            this.openingBalance = openingBalance;
            return this;
        }

        @Override
        public Current build() {
            return new Current(accountNumber, owner, openingBalance);
        }

    }

    @Override
    public String toString() {
        return "Current{" +
                "\n\tAccount Number=" + getAccountNumber() +
                "\n\tAccount Owner=" + getOwner() +
                "\n\tInterest Rate=" + interestRate +
                "\n}";
    }
}

enum AccountTypes {
    SAVINGS,
    CURRENT
}

/**
 * The above in turn is extended with a factory pattern to provide another level of abstraction.
 */
abstract class AccountBuilderFactory {
    public static BankAccount.Builder getBuilder(String accountType, long accountNumber) {
        switch (AccountTypes.valueOf(accountType)) {
            case SAVINGS: return new Savings.Builder(accountNumber);
            case CURRENT: return new Current.Builder(accountNumber);
            default: return null;
        }
    }


}

public class FactoryBuilderDemo {

    public static void main(String[] args) {
        BankAccount.Builder currentAccountBuilder =
                AccountBuilderFactory.getBuilder(AccountTypes.CURRENT.toString(), 110101010101L);
        BankAccount currentAccount = currentAccountBuilder.withOpeningBalance(10000000000D).withOwner("Abc").build();
        System.out.println();
        System.out.println(currentAccount);
        System.out.println(currentAccount.annualInterest());

        BankAccount.Builder savingsAccountBuilder =
                AccountBuilderFactory.getBuilder(AccountTypes.SAVINGS.toString(), 110101010101L);
        BankAccount savingsAccount = savingsAccountBuilder.withOpeningBalance(10000000000D).withOwner("Abc").build();
        System.out.println();
        System.out.println(savingsAccount);
        System.out.println(savingsAccount.annualInterest());
    }
}
