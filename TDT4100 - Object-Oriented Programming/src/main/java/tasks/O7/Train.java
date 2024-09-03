package tasks.O7;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private List<TrainCar> trainCars = new ArrayList<>(); 

    public void addTrainCar(TrainCar trainCar) {
        this.trainCars.add(trainCar);
    }

    public boolean contains(TrainCar trainCar) {
        return this.trainCars.contains(trainCar);
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (TrainCar trainCar : this.trainCars) {
            totalWeight += trainCar.getTotalWeight();
        }
        return totalWeight;
    }

    public int getPassengerCount() {
        int passengerCount = 0;
        for (TrainCar trainCar : this.trainCars) {
            if (trainCar instanceof PassengerCar) {
                passengerCount += ((PassengerCar) trainCar).getPassengerCount();
            }
        }
        return passengerCount;
    }

    public int getCargoWeight() {
        int cargoWeight = 0;
        for (TrainCar trainCar : this.trainCars) {
            if (trainCar instanceof CargoCar) {
                cargoWeight += ((CargoCar) trainCar).getCargoWeight();
            }
        }
        return cargoWeight;
    }

    @Override
    public String toString() {
        String trainString = "";
        for (TrainCar trainCar : this.trainCars) {
            trainString += trainCar.toString() + "";
        }
        return trainString;
    }

}
