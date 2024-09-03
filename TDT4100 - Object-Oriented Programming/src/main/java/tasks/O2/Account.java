package tasks.O2;

public class Account {
    private double interestRate;
    private double balance; 

    public Account(double n, double rate) {
        if (n < 0 || rate < 0) throw new IllegalArgumentException("Values cannot beless than zero");

        this.interestRate = rate;
        this.balance = n;
    } 

    public double getBalance() {
        return this.balance;
    }

    public double getInterestRate() {
        return this.interestRate;
    } 

    public void setInterestRate(double rate) {
        if (rate < 0) throw new IllegalArgumentException("Interest rate cannot be less than zero");

        this.interestRate = rate;
    } 

    public void deposit(double n) {
        if (n < 0) throw new IllegalArgumentException("Cannot deposit less than zero");
        
        this.balance += n;
    }

    public void withdraw(double n) {
        if (n < 0) throw new IllegalArgumentException("Cannot withdraw less than zero");
        if (this.balance - n < 0) throw new IllegalArgumentException("Cannot overdraw your balance");

        this.balance -= n;
    } 

    public void addInterest() {
        this.balance += (this.balance * this.interestRate) / 100;   
    }
}
