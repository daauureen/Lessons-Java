package TrainingPolygone2;

public class PalindromeChecker {
    public static boolean main(String s) {
        // Приводим строку к нижнему регистру
        String str = s.toLowerCase();
        
        // Сравниваем символы с начала и с конца
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}