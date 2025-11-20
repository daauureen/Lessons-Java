import java.util.Scanner;
public class WhileLoops {
    public static void main(String[] args) {
        boolean t = 5<2;
        System.out.println(t);

        int value = 7;
        boolean b = value>5;
        System.out.println(b);


        int value2 = 0;
        while(value2 < 9){
            System.out.println("Hello " + value2);
            value2 = value2 + 1;
        }


        //🔁 Что такое цикл while?
        // Цикл while — это как "повторяй, пока условие истинно".


        // 📝 Базовая структура
        // while (условие) {
        // код, который повторяется
        // пока условие = true
        // }


        int i = 1;
        while (i <= 5) {
        System.out.println("Numba: " + i);
        i++; // Увеличиваем i на 1
        }


        int timer = 10;
        while(timer > 0) {
            
            System.out.println(timer + "...");

            timer--;
        }
        System.out.println("Start");




        

        Scanner scanner = new Scanner(System.in);
        String answer = "";

        while (!answer.equals("exit")) {
            System.out.println("Write the comand: ");
            answer = scanner.nextLine();
            System.out.println("U said " + answer);
        }
        System.out.println("Program ends");
    }
}