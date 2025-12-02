import java.util.Scanner;

public class ScannerClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input first number: ");
        float num1 = scanner.nextFloat();

        System.out.print("Input seconf number: ");
        float num2 = scanner.nextFloat();

        float res = num1 + num2;
        float res1 = num1 - num2;
        float res2 = num1 * num2;
        float res3 = num1 / num2;

        System.out.println(res + "\n" + res1 + "\n" + res2 + "\n" + res3);
        
        scanner.close();
    }
}