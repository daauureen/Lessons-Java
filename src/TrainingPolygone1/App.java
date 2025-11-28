package TrainingPolygone1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Input first number: ");
        double a = sc.nextDouble(); 

        System.out.print("Input operator ( + , - , * , / , % )");
        char operator = sc.next().charAt(0);

        System.out.print("Input second number: ");
        double b = sc.nextDouble();

        double result;
        sc.close();

        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    System.out.println("U can't divide to zero");
                    return;
                }
                result = a / b;
                break;
            default:
                System.out.println("Error");
                return;
        }
        System.out.println("Result " + result);
    }
}