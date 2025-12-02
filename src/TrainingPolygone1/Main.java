package TrainingPolygone1;

import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input some text: ");
        String names = sc.nextLine();
        String[] splitNames = names.split(", ");
        System.out.println(Arrays.toString(splitNames));
    }
}