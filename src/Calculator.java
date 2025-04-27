// Calculator.java

public class Calculator {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new CalculatorUI();
        });
    }
}
