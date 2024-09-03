package tasks.O7;

public abstract class AbstractAccount {
    protected double balance;

        public void deposit(double amount) {
                if (amount < 0) throw new IllegalArgumentException();
                this.balance += amount;
        }

        public void withdraw(double amount) {
                if (amount < 0) throw new IllegalArgumentException();
                this.internalWithdraw(amount);
        }

        abstract void internalWithdraw(double amount);

        public double getBalance() {
                return this.balance;
        }
    
}
