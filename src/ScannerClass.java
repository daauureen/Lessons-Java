import java.util.Scanner;

public class ScannerClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input something");
        String string = sc.nextLine();
        System.out.println("You inputted: " + string);
        sc.close();
    }
}