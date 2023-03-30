import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Label;

public class PolynomialCalculatorGUI extends JFrame {
    private Polynomial polynomial1, polynomial2;
    private JTextField polynomial1Field, polynomial2Field, resultField;
    Label resultLabel = new Label("Result: ");

    public PolynomialCalculatorGUI() {
        super("Polynomial Calculator");

        polynomial1 = new Polynomial();
        polynomial2 = new Polynomial();

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel polynomial1Label = new JLabel("Polynomial 1:");
        polynomial1Field = new JTextField();
        JLabel polynomial2Label = new JLabel("Polynomial 2:");
        polynomial2Field = new JTextField();
        inputPanel.add(polynomial1Label);
        inputPanel.add(polynomial1Field);
        inputPanel.add(polynomial2Label);
        inputPanel.add(polynomial2Field);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton addButton = new JButton("Add");
        addButton.setFont(addButton.getFont().deriveFont(18f));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readPolynomials();
                Operations operations = new Operations();
                Polynomial sum = operations.add(readPolynomials().get(0), readPolynomials().get(1));
                resultField.setText("Sum: " + sum.toString());
            }
        });
        JButton subtractButton = new JButton("Subtract");
        subtractButton.setFont(subtractButton.getFont().deriveFont(18f));
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readPolynomials();
                Operations operations = new Operations();
                Polynomial subtraction = operations.subtract(readPolynomials().get(0), readPolynomials().get(1));
                resultField.setText("Difference: " + subtraction.toString());
            }
        });
        JButton multiplyButton = new JButton("Multiply");
        multiplyButton.setFont(multiplyButton.getFont().deriveFont(18f));
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readPolynomials();
                Operations operations = new Operations();
                Polynomial product = operations.multiply(readPolynomials().get(0), readPolynomials().get(1));
                resultField.setText("Product: " + product.toString());
            }
        });
        JButton derivativeButton = new JButton("Derivative (Polynomial1 only)");
        derivativeButton.setFont(derivativeButton.getFont().deriveFont(18f));
        derivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readPolynomials();
                Operations operations = new Operations();
                Polynomial derivative = operations.derivative(readPolynomials().get(0));
                resultField.setText("Derivative: " + derivative.toString());
            }
        });
        JButton integrateButton = new JButton("Integrate (Polynomial1 only)");
        integrateButton.setFont(integrateButton.getFont().deriveFont(18f));
        integrateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readPolynomials();
                Operations operations = new Operations();
                Polynomial integrate = Operations.integrate(readPolynomials().get(0));
                resultField.setText("Integrate: " + integrate.toString());
            }
        });
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(derivativeButton);
        buttonPanel.add(integrateButton);
        JPanel resultPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        resultPanel.setBackground(Color.LIGHT_GRAY);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.CENTER);
        resultField.setFont(resultField.getFont().deriveFont(18f));
        resultPanel.add(resultField);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setSize(1750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private ArrayList<Polynomial> readPolynomials() {
        ArrayList<Polynomial> polynomials = new ArrayList<>();
        String[] terms1 = polynomial1Field.getText().split("\\+");
        String[] terms2 = polynomial2Field.getText().split("\\+");
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();
        try {
            for (String term : terms1) {
                String[] parts = term.trim().split("\\^");
                int power = 0;
                double coefficient = 0.0;
                if (parts.length == 2) {
                    power = Integer.parseInt(parts[1]);
                } else if (parts.length == 1) {
                    if(parts[0].contains("x"))
                    {
                        power = 1;
                    }
                    else
                        power = 0;
                }
                String coeffStr = parts[0].replace("x", "").trim();
                if (coeffStr.equals("")) {
                    coefficient = 1.0;
                } else if (coeffStr.equals("-")) {
                    coefficient = -1.0;
                } else {
                    coefficient = Double.parseDouble(coeffStr);
                }
                polynomial1.addTerm(power, coefficient);
            }
            for (String term : terms2) {
                String[] parts = term.trim().split("\\^");
                int power = 0;
                double coefficient = 0.0;
                if (parts.length == 2) {
                    power = Integer.parseInt(parts[1]);
                } else if (parts.length == 1) {
                    if(parts[0].contains("x"))
                    {
                        power = 1;
                    }
                    else
                        power = 0 ;
                }
                String coeffStr = parts[0].replace("x", "").trim();
                if (coeffStr.equals("")) {
                    coefficient = 1.0;
                } else if (coeffStr.equals("-")) {
                    coefficient = -1.0;
                } else {
                    coefficient = Double.parseDouble(coeffStr);
                }
                polynomial2.addTerm(power, coefficient);
            }
            polynomials.add(polynomial1);
            polynomials.add(polynomial2);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
        return polynomials;
    }

    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        Label resultArea = resultLabel;
        if (action.equals("Add")) {
            ArrayList<Polynomial> polynomials = readPolynomials();
            if (polynomials.size() == 2) {
                Operations operations = new Operations();
                Polynomial result = operations.add(polynomials.get(0), polynomials.get(1));
                resultArea.setText(result.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both polynomials");
            }

        } else if (action.equals("Subtract")) {
            ArrayList<Polynomial> polynomials = readPolynomials();
            if (polynomials.size() == 2) {
                Operations operations = new Operations();
                Polynomial result = operations.subtract(polynomials.get(0), polynomials.get(1));
                resultArea.setText(result.toString());
            }
        } else if (action.equals("Multiply")) {
            ArrayList<Polynomial> polynomials = readPolynomials();
            if (polynomials.size() == 2) {
                Operations operations = new Operations();
                Polynomial result = operations.multiply(polynomials.get(0), polynomials.get(1));
                resultArea.setText(result.toString());
            }
        } else if(action.equals("Derivative (Polynomial1 only)")){
            ArrayList<Polynomial> polynomials = readPolynomials();
            if(polynomials.size()==1){
                Operations operations = new Operations();
                Polynomial result = operations.derivative(polynomials.get(0));
                resultArea.setText(result.toString());
            }
        } else if(action.equals("Integrate (Polynomial1 only)")){
            ArrayList<Polynomial> polynomials = readPolynomials();
            if(polynomials.size()==1){
                Operations operations = new Operations();
                Polynomial p1 = polynomials.get(0);
                if(p1.toString().equals("0")){
                    JOptionPane.showMessageDialog(this, "Constant value");
                }
                else{
                    Polynomial result = Operations.integrate(polynomials.get(0));
                 resultArea.setText(result.toString());
                }
            }
        }
    }
    public static void main(String[] args) {
        PolynomialCalculatorGUI calculator = new PolynomialCalculatorGUI();
        calculator.setVisible(true);
    }
}
