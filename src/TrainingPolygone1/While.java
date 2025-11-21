// ✅ Лёгкие задачи на while
// 1. Подсчёт суммы от 1 до N

// Ввод: N
// Вывод: сумма чисел от 1 до N включительно.
// Использовать только while.
// -------------------------------------------------------------------//
// package TrainingPolygone1;

// import java.util.Scanner;

// public class While {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.print("Input num: ");
//         int n = sc.nextInt();

//         int sum = 0;
//         int i = 1;

//         while (i <= n) {
//             sum = sum + i;
//             i++;
//         }
//         System.out.println(sum);
//     }
// }
// -------------------------------------------------------------------//
package TrainingPolygone1;

import java.util.Scanner;

public class While {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number: ");
        int n = sc.nextInt();

        while (n >= 0) {
            System.out.println(n + "");
            n--;
        }
    }
}