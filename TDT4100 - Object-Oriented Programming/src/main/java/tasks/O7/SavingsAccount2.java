package tasks.O7;

public class SavingsAccount2 extends AbstractAccount {
    private int withdrawals;
    private double fee;

    public SavingsAccount2(int withdrawals, double fee) {
        this.withdrawals = withdrawals;
        this.fee = fee;
    }

    @Override
    void internalWithdraw(double amount) {
        if ((this.balance - amount < 0 || (this.withdrawals == 0 && (this.balance - amount - fee < 0)))) throw new IllegalStateException();
        
        if (this.withdrawals <= 0) this.balance -= this.fee;
        else this.withdrawals--;

        this.balance -= amount;
    }
    
}
