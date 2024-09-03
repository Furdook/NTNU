package tasks.O1;

public class Account { //
    private float balance;
    private float interestRate;

    public Account() {
        this.balance = (float) 0.0;
        this.interestRate = (float) 0.0;
    }

    public void deposit(float i) {
        if (i < 0) return;
        this.balance += i;
    }

    public void addInterest() {
        this.balance += this.balance * (this.interestRate / 100);
    }

    public float getBalance() {
        return this.balance;
    }

    public float getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(float i) {
        this.interestRate = i;
    }

    @Override
    public String toString() {
        return "Account balance: " + this.balance;
    }
    public static void main(String[] args) {
        Account test = new Account();
        test.deposit(10);
        System.out.println(test.toString());
    }
}
