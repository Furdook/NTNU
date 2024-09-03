package timonas.calc;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;

public class CalcController {

    private Calc calc;

    public CalcController() {
        calc = new Calc(0.0, 0.0, 0.0);
    }

    public Calc getCalc() {
        return calc;
    }

    public void setCalc(Calc calc) {
        this.calc = calc;
        updateOperandsView();
    }

    @FXML
    private ListView<Double> operandsView;

    @FXML
    private Label operandView;

    @FXML
    void initialize() {
        updateOperandsView();
    }

    private void updateOperandsView() {
        List<Double> operands = operandsView.getItems();
        operands.clear();
        int elementCount = Math.min(calc.getOperandCount(), 3);
        for (int i = 0; i < elementCount; i++) {
            operands.add(calc.peekOperand(elementCount - i - 1));
        }
    }

    private String getOperandString() {
        return operandView.getText();
    }

    private boolean hasOperand() {
        return !getOperandString().isBlank();
    }

    private double getOperand() {
        return Double.valueOf(operandView.getText());
    }
    
    private void setOperand(String operandString) {
        operandView.setText(operandString);
    }

    @FXML
    void handleEnter() {
        if (hasOperand()) {
            calc.pushOperand(getOperand());
        } else {
            calc.dup();
        }
        setOperand("");
        updateOperandsView();
    }

    private void appendToOperand(String s) {
        operandView.setText(operandView.getText() + s);
    }

    @FXML
    void handleDigit(ActionEvent ae) {
        if (ae.getSource() instanceof Labeled l) {
            appendToOperand(l.getText());
        }
    }

    @FXML
    void handlePoint() {
        var operandString = getOperandString();
        if (operandString.contains(".")) {
            operandString = operandString.substring(0, operandString.indexOf(".") + 1);
        } else {
            operandString += ".";
        }
        setOperand(operandString);
    }

    @FXML
    void handleClear() {
        operandView.setText("");
    }

    @FXML
    void handleSwap() {
        calc.swap();
    }

    private void performOperation(UnaryOperator<Double> op) {
        if (hasOperand()) {
            calc.pushOperand(getOperand());
            handleClear();
        }
        calc.performOperation(op);
        updateOperandsView();
    }

    private void performOperation(BinaryOperator<Double> op) {
        if (hasOperand()) {
            calc.pushOperand(getOperand());
            handleClear();
        }
        calc.performOperation(op);
        updateOperandsView();

    }

    @FXML
    void handleOpAdd() {
        performOperation((n1, n2) -> n1 + n2);
    }

    @FXML
    void handleOpSub() {
        performOperation((n1, n2) -> n1 - n2);
    }

    @FXML
    void handleOpMult() {
        performOperation((n1, n2) -> n1 * n2);
    }

    @FXML 
    void handleOpDiv() {
        performOperation((n1, n2) -> n1 / n2);
    }

    @FXML
    void handleOpSqrt() {
        performOperation((n1) -> Math.sqrt(n1));
    }

    @FXML
    void handleOpPi() {
        performOperation((n1) -> Math.PI);
    }
}
