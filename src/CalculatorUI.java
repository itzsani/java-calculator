import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI implements ActionListener {

    private JFrame frame;
    private JTextField display;
    private JPanel buttonPanel;
    private CalculatorLogic logic;
    private String operator;
    private double firstNumber;
    private boolean isOperatorClicked;

    public CalculatorUI() {
        logic = new CalculatorLogic();
        operator = "";
        firstNumber = 0;
        isOperatorClicked = false;
        
        frame = new JFrame("Java Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center the window

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        frame.add(buttonPanel, BorderLayout.CENTER);

        String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "×",
            ".", "0", "=", "÷",
            "C"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();

        if (command.matches("[0-9\\.]")) {
            if (isOperatorClicked) {
                display.setText("");
                isOperatorClicked = false;
            }
            display.setText(display.getText() + command);
        } else if (command.matches("[+\\-×÷]")) {
            operator = command;
            firstNumber = Double.parseDouble(display.getText());
            isOperatorClicked = true;
        } else if (command.equals("=")) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = logic.calculate(firstNumber, secondNumber, operator);
            display.setText(formatResult(result));
            isOperatorClicked = true;
        } else if (command.equals("C")) {
            display.setText("");
            firstNumber = 0;
            operator = "";
            isOperatorClicked = false;
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }
}
