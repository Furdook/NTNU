package tasks.O7;

public class CargoCar extends TrainCar {
    private int cargoWeight;

    public CargoCar(int deadWeight, int cargo) {
        super(deadWeight);
        this.setCargoWeight(cargo);
    }

    public int getCargoWeight() {
        return this.cargoWeight;
    }

    public void setCargoWeight(int n) {
        this.cargoWeight = n;
        this.setTotalWeight(n);
    }
    
}
