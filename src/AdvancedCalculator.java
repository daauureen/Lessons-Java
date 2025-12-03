import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AdvancedCalculator — GUI calculator demonstrating OOP principles.
 *
 * Features:
 * - Object-oriented design: Operation interfaces, implementations and an Engine
 * - Basic binary operations: + - * / % ^
 * - Unary operations: sqrt, +/- (sign)
 * - Chaining operations (compute as you press = or operator)
 * - Clear, backspace, decimal input
 * - Simple history panel
 * - Keyboard support for digits and basic operators
 *
 * Implementation notes:
 * - Uses Swing (no external libs)
 * - Single-file example: all classes are static inner classes to keep code compact
 *
 * To run:
 * javac AdvancedCalculator.java
 * java AdvancedCalculator
 */
public class AdvancedCalculator {


    // Entry point

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame().setVisible(true));
    }

    
    // CalculatorFrame: the GUI

    public static class CalculatorFrame extends JFrame {
        private final JTextField display = new JTextField();
        private final JTextArea historyArea = new JTextArea(8, 20);
        private final CalculatorEngine engine = new CalculatorEngine();

        private boolean startNewNumber = true; // if true, next digit replaces display

        public CalculatorFrame() {
            setTitle("Advanced Calculator");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(420, 520);
            setLocationRelativeTo(null);
            initUI();
        }

        private void initUI() {
            setLayout(new BorderLayout(6, 6));

            // Top panel: display
            display.setFont(new Font("Monospaced", Font.PLAIN, 26));
            display.setHorizontalAlignment(JTextField.RIGHT);
            display.setEditable(false);
            display.setText("0");
            add(display, BorderLayout.NORTH);

            // Center: keypad + ops
            JPanel center = new JPanel(new BorderLayout());

            JPanel buttons = createButtonsPanel();
            center.add(buttons, BorderLayout.CENTER);

            add(center, BorderLayout.CENTER);

            // Right: history
            historyArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(historyArea);
            scroll.setBorder(BorderFactory.createTitledBorder("History"));
            add(scroll, BorderLayout.EAST);

            // Keyboard support
            setupKeyBindings();
        }

        private JPanel createButtonsPanel() {
            JPanel panel = new JPanel(new BorderLayout(4,4));

            JPanel nums = new JPanel(new GridLayout(4, 3, 4, 4));
            String[] numButtons = {"7","8","9","4","5","6","1","2","3","0",".","+/-"};
            for (String label : numButtons) {
                JButton b = makeButton(label);
                b.addActionListener(e -> onInput(label));
                nums.add(b);
            }

            JPanel ops = new JPanel(new GridLayout(6, 1, 4, 4));
            String[] opButtons = {"+","-","*","/","%","^"};
            for (String op : opButtons) {
                JButton b = makeButton(op);
                b.addActionListener(e -> onOperator(op));
                ops.add(b);
            }

            JPanel rightTop = new JPanel(new BorderLayout(4,4));
            rightTop.add(nums, BorderLayout.CENTER);
            rightTop.add(ops, BorderLayout.EAST);

            panel.add(rightTop, BorderLayout.CENTER);

            // bottom row: sqrt, clear, backspace, equals
            JPanel bottom = new JPanel(new GridLayout(1,4,4,4));
            JButton sqrt = makeButton("sqrt");
            sqrt.addActionListener(e -> onUnaryOp("sqrt"));
            bottom.add(sqrt);

            JButton clear = makeButton("C");
            clear.addActionListener(e -> onClear());
            bottom.add(clear);

            JButton back = makeButton("<-");
            back.addActionListener(e -> onBackspace());
            bottom.add(back);

            JButton eq = makeButton("=");
            eq.addActionListener(e -> onEquals());
            bottom.add(eq);

            panel.add(bottom, BorderLayout.SOUTH);

            return panel;
        }

        private JButton makeButton(String text) {
            JButton b = new JButton(text);
            b.setFont(new Font("SansSerif", Font.PLAIN, 18));
            return b;
        }

        // Input handling
        private void onInput(String label) {
            if (label.equals("+/-")) {
                toggleSign();
                return;
            }

            if (startNewNumber) {
                if (label.equals(".")) {
                    display.setText("0.");
                    startNewNumber = false;
                    return;
                } else if (label.equals("0")) {
                    // keep 0
                    display.setText("0");
                    startNewNumber = false;
                    return;
                }
                display.setText(label.equals(".") ? "0." : label);
                startNewNumber = false;
            } else {
                if (label.equals(".") && display.getText().contains(".")) return;
                display.setText(display.getText() + label);
            }
        }

        private void toggleSign() {
            try {
                double v = Double.parseDouble(display.getText());
                v = -v;
                display.setText(formatDouble(v));
            } catch (NumberFormatException ex) {
                // ignore
            }
        }

        private void onOperator(String opSymbol) {
            try {
                double value = Double.parseDouble(display.getText());
                engine.enterOperand(value);
                engine.setPendingOperator(opSymbol);
                startNewNumber = true;
                appendHistory(String.format("%s %s", formatDouble(value), opSymbol));
            } catch (NumberFormatException ex) {
                // ignore
            }
        }

        private void onUnaryOp(String op) {
            try {
                double value = Double.parseDouble(display.getText());
                double res = engine.performUnary(op, value);
                display.setText(formatDouble(res));
                appendHistory(op + "(" + formatDouble(value) + ") = " + formatDouble(res));
                startNewNumber = true;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void onEquals() {
            try {
                double value = Double.parseDouble(display.getText());
                double res = engine.calculate(value);
                display.setText(formatDouble(res));
                appendHistory("= " + formatDouble(res));
                startNewNumber = true;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void onClear() {
            engine.reset();
            display.setText("0");
            startNewNumber = true;
            appendHistory("-- cleared --\n");
        }

        private void onBackspace() {
            if (startNewNumber) return;
            String s = display.getText();
            if (s.length() <= 1) {
                display.setText("0");
                startNewNumber = true;
            } else {
                display.setText(s.substring(0, s.length() - 1));
            }
        }

        private void appendHistory(String line) {
            historyArea.append(line + "\n");
            historyArea.setCaretPosition(historyArea.getDocument().getLength());
        }

        private void setupKeyBindings() {
            // Use key listener for simplicity
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (Character.isDigit(c) || c == '.') {
                        onInput(String.valueOf(c));
                    } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '%') {
                        onOperator(String.valueOf(c));
                    } else if (c == '\n' || c == '=') {
                        onEquals();
                    } else if (c == '\b') {
                        onBackspace();
                    }
                }
            });
            // Ensure frame is focusable
            setFocusable(true);
            requestFocusInWindow();
        }

        private String formatDouble(double v) {
            if (v == (long) v) return String.format("%d", (long) v);
            return String.format("%s", v);
        }
    }

    // CalculatorEngine: OOP core

    public static class CalculatorEngine {
        private Double leftOperand = null;
        private String pendingOp = null; // symbol

        private final Map<String, BinaryOperation> binaryOps = new LinkedHashMap<>();
        private final Map<String, UnaryOperation> unaryOps = new LinkedHashMap<>();

        public CalculatorEngine() {
            // register binary ops
            registerBinary("+", new Add());
            registerBinary("-", new Subtract());
            registerBinary("*", new Multiply());
            registerBinary("/", new Divide());
            registerBinary("%", new Mod());
            registerBinary("^", new Power());

            // unary
            registerUnary("sqrt", new Sqrt());
            registerUnary("neg", new Negate());
        }

        public void registerBinary(String sym, BinaryOperation op) {
            binaryOps.put(sym, op);
        }

        public void registerUnary(String sym, UnaryOperation op) {
            unaryOps.put(sym, op);
        }

        public void enterOperand(double value) {
            if (leftOperand == null) leftOperand = value;
            else if (pendingOp != null) {
                // compute immediately when chaining
                BinaryOperation op = binaryOps.get(pendingOp);
                if (op == null) throw new IllegalStateException("Unknown operator: " + pendingOp);
                leftOperand = op.apply(leftOperand, value);
                pendingOp = null; // keep result as left for next operator
            } else {
                leftOperand = value; // overwrite
            }
        }

        public void setPendingOperator(String opSymbol) {
            if (!binaryOps.containsKey(opSymbol)) throw new IllegalArgumentException("Operator not supported: " + opSymbol);
            pendingOp = opSymbol;
        }

        public double performUnary(String opSymbol, double value) {
            UnaryOperation u = unaryOps.get(opSymbol);
            if (u == null) throw new IllegalArgumentException("Unary op not found: " + opSymbol);
            return u.apply(value);
        }

        public double calculate(double rightOperand) {
            if (leftOperand == null && pendingOp == null) {
                return rightOperand; // nothing to do
            }
            if (pendingOp == null) return rightOperand;
            BinaryOperation op = binaryOps.get(pendingOp);
            if (op == null) throw new IllegalStateException("Operator not found: " + pendingOp);
            double result = op.apply(leftOperand, rightOperand);
            // reset engine to allow further calculations starting from result
            leftOperand = result;
            pendingOp = null;
            return result;
        }

        public void reset() {
            leftOperand = null;
            pendingOp = null;
        }
    }


    // Operations (OOP)

    public interface BinaryOperation {
        double apply(double a, double b);
    }

    public interface UnaryOperation {
        double apply(double a);
    }

    public static class Add implements BinaryOperation {
        public double apply(double a, double b) { return a + b; }
    }

    public static class Subtract implements BinaryOperation {
        public double apply(double a, double b) { return a - b; }
    }

    public static class Multiply implements BinaryOperation {
        public double apply(double a, double b) { return a * b; }
    }

    public static class Divide implements BinaryOperation {
        public double apply(double a, double b) {
            if (b == 0) throw new IllegalArgumentException("Division by zero");
            return a / b;
        }
    }

    public static class Mod implements BinaryOperation {
        public double apply(double a, double b) { return a % b; }
    }

    public static class Power implements BinaryOperation {
        public double apply(double a, double b) { return Math.pow(a, b); }
    }

    public static class Sqrt implements UnaryOperation {
        public double apply(double a) {
            if (a < 0) throw new IllegalArgumentException("Sqrt of negative number");
            return Math.sqrt(a);
        }
    }

    public static class Negate implements UnaryOperation {
        public double apply(double a) { return -a; }
    }
}
