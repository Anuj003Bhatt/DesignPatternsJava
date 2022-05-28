package creational.builder;

class BankAccount {
    private long accountNumber;
    private String owner;
    private double openingBalance;

    public BankAccount(long accountNumber, String owner, double openingBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.openingBalance = openingBalance;
    }

    public static class Builder {
        private long accountNumber;
        private String owner;
        private double openingBalance;

        public Builder(long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder withOpeningBalance(double openingBalance) {
            this.openingBalance = openingBalance;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(accountNumber, owner, openingBalance);
        }
    }

}


public class BuilderPatternDemo {
    public static void main(String[] args) {

        BankAccount.Builder builder = new BankAccount.Builder(110101010101L);
        BankAccount currentAccount = builder
                .withOpeningBalance(10000000000D)
                .withOwner("Abc")
                .build();
        System.out.println();
        System.out.println(currentAccount);
    }
}
