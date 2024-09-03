package tasks.O2;

public class Vehicle {
    private char type;
    private char fuel;
    private String reg;
    private String fuelTypes = "GDEH";

    public Vehicle(char type, char fuel, String reg) {
        if (type != 'C' && type != 'M') throw new IllegalArgumentException("Not a valid vehicle type");
        if (type == 'M' && fuel == 'H') throw new IllegalArgumentException("Motorcycles cannot run on hydrogen");
        if (!fuelTypes.contains(String.valueOf(fuel))) throw new IllegalArgumentException("Not a valid fuel source");

        this.type = type;
        this.fuel = fuel;
        this.setRegistrationNumber(reg);    
    } 
   
    public char getFuelType() {
        return this.fuel;
    } 

    public String getRegistrationNumber() {
        return this.reg;
    } 

    public void setRegistrationNumber(String reg) {
        if (type == 'C' && reg.length() != 7 ||                 // check length if car
            type == 'M' && reg.length() != 6 ||                 // check length if motorcycle
            fuel != 'E' && reg.charAt(0) == 'E' ||              // if fuel type not E, reg should not start at E
            fuel != 'H' && reg.charAt(0) == 'H' ||              // if fuel type not H, reg should not start at H
            fuel == 'E' && reg.charAt(0) != 'E' ||              // if fuel type E, reg should start at E
            fuel == 'H' && reg.charAt(0) != 'H' ||              // if fuel type H, reg should start at H
            !reg.substring(0, 2).matches("[A-Z]+") ||           // checks that first two chars of reg are capital letters from A-Z
            !reg.substring(2, reg.length()).matches("[0-9]+"))  // checks that the remaining vhars are numbers

            throw new IllegalArgumentException("Not a valid registration");
        
        this.reg = reg;
    } 
    
    public char getVehicleType() {
        return this.type;
    } 
}
