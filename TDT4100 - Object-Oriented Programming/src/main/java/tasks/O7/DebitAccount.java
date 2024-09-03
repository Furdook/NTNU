package tasks.O7;

public class DebitAccount extends AbstractAccount {

    public DebitAccount() {
        this.balance = 0;
    }

    @Override
    public void internalWithdraw(double amount) {
        if (this.balance - amount < 0) throw new IllegalStateException();
        this.balance -= amount;
    }
}


