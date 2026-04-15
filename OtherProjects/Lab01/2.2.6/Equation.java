//Example 7: Equation.java
import javax.swing.JOptionPane;

public class Equation {
    public static void main(String[] args) {
        String strChoice = JOptionPane.showInputDialog(null,"Choose equation type:\n1.ax + b = 0 \n2. 2 variables \n3. ax^2 + bx + c = 0","Equation Solver",JOptionPane.INFORMATION_MESSAGE);

        int choice = Integer.parseInt(strChoice);

        if (choice == 1) {
            solveLinearEquation();
        } else if (choice == 2) {
            solveLinearSystem();
        } else if (choice == 3) {
            solveQuadraticEquation();
        } 

        System.exit(0);
    }

    public static void solveLinearEquation() {
        String strA = JOptionPane.showInputDialog(null, "Input a:", "Linear Equation", JOptionPane.INFORMATION_MESSAGE);
        String strB = JOptionPane.showInputDialog(null, "Input b:", "Linear Equation", JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);

        String result;
        if (a == 0) {
            if (b == 0) {
                result = "Inf";
            } else {
                result = "None";
            }
        } else {
            double x = -b / a;
            result = "x = " + x;
        }

        JOptionPane.showMessageDialog(null, result, "Linear Equation", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void solveLinearSystem() {
        String strA11 = JOptionPane.showInputDialog(null, "Input a11:", "Linear System", JOptionPane.INFORMATION_MESSAGE);
        String strA12 = JOptionPane.showInputDialog(null, "Input a12:", "Linear System", JOptionPane.INFORMATION_MESSAGE);
        String strB1 = JOptionPane.showInputDialog(null, "Input b1:", "Linear System", JOptionPane.INFORMATION_MESSAGE);
        String strA21 = JOptionPane.showInputDialog(null, "Input a21:", "Linear System", JOptionPane.INFORMATION_MESSAGE);
        String strA22 = JOptionPane.showInputDialog(null, "Input a22:", "Linear System", JOptionPane.INFORMATION_MESSAGE);
        String strB2 = JOptionPane.showInputDialog(null, "Input b2:", "Linear System", JOptionPane.INFORMATION_MESSAGE);

        double a11 = Double.parseDouble(strA11);
        double a12 = Double.parseDouble(strA12);
        double b1 = Double.parseDouble(strB1);
        double a21 = Double.parseDouble(strA21);
        double a22 = Double.parseDouble(strA22);
        double b2 = Double.parseDouble(strB2);

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        String result;
        if (D != 0) {
            double x1 = D1 / D;
            double x2 = D2 / D;
            result = "x1 = " + x1 + "\n" + "x2 = " + x2;
        } else {
            if (D1 == 0 && D2 == 0) {
                result = "Inf";
            } else {
                result = "None";
            }
        }

        JOptionPane.showMessageDialog(null, result, "Linear System", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void solveQuadraticEquation() {
        String strA = JOptionPane.showInputDialog(null, "Input a:", "Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
        String strB = JOptionPane.showInputDialog(null, "Input b:", "Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
        String strC = JOptionPane.showInputDialog(null, "Input c:", "Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);

        String result;
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    result = "Inf";
                } else {
                    result = "None";
                }
            } else {
                double x = -c / b;
                result = "x = " + x;
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                result = "x1 = " + x1 + "\n" + "x2 = " + x2;
            } else if (delta == 0) {
                double x = -b / (2 * a);
                result = "Double: x = " + x;
            } else {
                result = "None.";
            }
        }

        JOptionPane.showMessageDialog(null, result, "Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
    }
}
