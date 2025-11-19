import javax.print.DocFlavor.STRING;

public class Strings {
    public static void main(String[] args) {
        int number = 123;
        System.out.println(number);

        String message = "I am learning Java!";
        System.out.println(message);

        String name = "Dauren";
        String greeting = "Hello" + " " + name + "!";
        System.out.println(greeting);

        String fruits = "apples";
        int number1 = 10;
        String message1 = "I have " + number1 + " " + fruits;
        System.out.println(message1);



        String text1 = "Negus";
        int length = text1.length();
        System.out.println(length);


        String text2 = "abcdefg";
        String upperCase = text2.toUpperCase();
        System.out.println(upperCase);


        String text3 = "HIJKLMNOP";
        String lowerCase = text3.toLowerCase();
        System.out.println(lowerCase);


        String text4 = "I like coding";
        boolean isContains = text4.contains("like"); 
        System.out.println(isContains);
    }
}