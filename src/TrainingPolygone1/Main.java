// ✅ Лёгкие задачи на while
// 1. Подсчёт суммы от 1 до N

// Ввод: N
// Вывод: сумма чисел от 1 до N включительно.
// Использовать только while.

package TrainingPolygone1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input num: ");
        int n = scanner.nextInt();

        int sum = 0;
        int i = 1;

        while (i <= n) {
            sum = sum + i;
            i++;
        }

        System.out.println(sum);
    }
}