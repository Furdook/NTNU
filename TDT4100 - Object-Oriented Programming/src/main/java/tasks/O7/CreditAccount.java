package tasks.O7;

public class CreditAccount extends AbstractAccount{
    private double creditLine;

    public CreditAccount(double creditLine) {
        this.setCreditLine(creditLine);
    }   

    public double getCreditLine() {
        return this.creditLine;
    }

    public void setCreditLine(double creditLine) {
        if (creditLine < 0) throw new IllegalArgumentException();
        if (this.balance < 0 && creditLine < (0 - this.balance)) throw new IllegalStateException();
        this.creditLine = creditLine;
    }

    @Override
    void internalWithdraw(double amount) {
        if ((this.balance - amount) < (0 - this.creditLine)) throw new IllegalStateException();
        this.balance -= amount;
    }
}
