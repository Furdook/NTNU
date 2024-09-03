package tasks.O7;

public class TrainCar {
    private int deadWeight;
    private int totalWeight;

    public TrainCar(int n) {
        this.setDeadWeight(n);
    }

    public int getTotalWeight() {
        return this.totalWeight + this.getDeadWeight();
    }

    public void setTotalWeight(int n) {
        this.totalWeight = n;
    }

    public void setDeadWeight(int n) {
        this.deadWeight = n;
    }

    public int getDeadWeight() {
        return this.deadWeight;
    }
}


