package tasks.O6.delegation.office;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;

public class Manager implements Employee {
    protected List<Employee> resources = new ArrayList<>();
    private int taskCount = 0;

    public Manager(Collection<Employee> employees) {
        if (employees.isEmpty()) throw new IllegalArgumentException();

        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                for (Employee n : employee.getResources()) {
                    this.resources.add(n);
                }
            }
            else this.resources.add(employee);
        }
        this.resources.add(this);
    }

    public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
        this.taskCount++;
        return this.resources.get(0).doCalculations(operation, value1, value2);
    }

    public void printDocument(String document) {
        this.taskCount++;
        this.resources.get(0).printDocument(document);
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public int getResourceCount() {
        return this.resources.size();
    }

    public List<Employee> getResources() {
        return this.resources;
    }
    
}
