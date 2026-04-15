// Example 6: CalculateTwoNumbers.java
import javax.swing.JOptionPane;

public class CalculateTwoNumbers {
    public static void main(String[] args) {
        String strNum1 = JOptionPane.showInputDialog(null,"Please input the first number:","Input the first number", JOptionPane.INFORMATION_MESSAGE);
        String strNum2 = JOptionPane.showInputDialog(null,"Please input the second number:","Input the second number", JOptionPane.INFORMATION_MESSAGE);

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        double tong = num1 + num2;
        double hieu = num1 - num2;
        double tich = num1 * num2;

        String message = "Sum: " + tong + "\n" + "Difference: " + hieu + "\n" + "Product: " + tich + "\n";
        if (num2 != 0) {
            double thuong = num1 / num2;
            message += "Quotient: " + thuong;
        } else {
            message += "Num 2 is zero!";
        }

        JOptionPane.showMessageDialog(null,message,"Calculation Result",JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}
