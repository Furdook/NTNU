package tasks.O6.delegation.office;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Printer {
    protected HashMap<Employee, List<String>> history = new HashMap<>();

    public void printDocument(String document, Employee employee) {
        List<String> tmpList = this.history.get(employee);
        tmpList.add(document);
        this.history.put(employee, tmpList);
    }

    public List<String> getPrintHistory(Employee employee) {
        return new ArrayList<>(this.history.get(employee));
    }
}
