package tasks.O6.delegation.office;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Clerk implements Employee {

    private List<Clerk> resources = new ArrayList<>();
    private final List<String> emptyList = new ArrayList<>();
    private int taskCount = 0;
    private Printer printer;

    public Clerk(Printer printer) {
        resources.add(this);
        this.printer = printer;
        printer.history.put(this, emptyList);
    }

    public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
        this.taskCount++;
        return operation.apply(value1, value2);
    }

    public void printDocument(String document) {
        this.taskCount++;
        this.printer.printDocument(document, this);
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public int getResourceCount() {
        return resources.size();
    }

    @Override
    public List<Employee> getResources() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResources'");
    }
}


