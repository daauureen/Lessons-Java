import java.util.Scanner;

public class Switch {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input age: ");
        String age = scanner.nextLine();
        switch (age) {
            case "0":
                System.out.println("You are born");
                break;
            case "7":
                System.out.println("You are gae");
                break;
            case "18":
                System.out.println("Thanks");
                break;
            case "99":
                System.out.println("Nooo");
                break;
            default:
                System.out.println("Error");
        }
    }
}
