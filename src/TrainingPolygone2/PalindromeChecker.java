package TrainingPolygone2;

public class PalindromeChecker {
    public static boolean main(String s) {
        
        String str = s.toLowerCase();
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