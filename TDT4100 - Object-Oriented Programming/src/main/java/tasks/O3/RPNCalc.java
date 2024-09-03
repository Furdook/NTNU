package tasks.O3;

import java.util.ArrayList;
import java.util.List;

public class RPNCalc {
    private List<Double> stack = new ArrayList<>();

    public void push(double d) {
        this.stack.add(d);
    }

    public double pop() {
        if (this.stack.size() == 0) return Double.NaN;

        double tmp = this.stack.get(getSize() - 1);
        this.stack.remove(getSize() - 1);

        return tmp;
    }

    public double peek(int i) {
        if (i > getSize() || i < 0) return Double.NaN;
        return this.stack.get(getSize() - 1 - i);
    }

    public int getSize() {
        return this.stack.size();
    }

    public void performOperation(char op) {
        switch (op) {
            case '+':
                push(pop() + pop());
                break;
            case '-':
                double a = pop();
                double b = pop();
                push(b - a);
                break;
            case '*':
                double c = pop();
                double d = pop();
                push(c * d);
                break;
            case '/':
                double e = pop();
                double f = pop();
                push(f / e);
            default:
                break;
        }
    }

}
