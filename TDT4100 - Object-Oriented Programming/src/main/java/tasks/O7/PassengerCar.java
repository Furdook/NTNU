package tasks.O7;

public class PassengerCar extends TrainCar {
    private int passengerCount;
    static final int avargePassengerWeight = 80;

    public PassengerCar(int deadWeight, int passengerCount) {
        super(deadWeight);
        this.setPassengerCount(passengerCount);
    }

    public int getPassengerCount() {
        return this.passengerCount;
    }

    public void setPassengerCount(int n) {
        this.passengerCount = n;
        this.setTotalWeight(n*avargePassengerWeight);
    }
    
}
    
