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





        // IMPORTANT!!!
        // 1) Кавычки — ОБЯЗАТЕЛЬНО двойные!
        // String правильно = "Текст в двойных кавычках";  // ✅
        // String ошибка = 'Текст в одинарных';           // ❌ Не скомпилируется!


        // 2) String пишется с большой буквы!
        // String имя = "Петя";  // ✅ С большой S
        // string ошибка = "Вася"; // ❌ С маленькой s - ошибка!


        // 3) String = текст, int = числа
        // String текст = "25";    // Это ТЕКСТ "25" (два-пять)
        // int число = 25;         // Это ЧИСЛО 25 (двадцать пять)

        // String возрастТекст = "18 лет";  // ✅ Можно
        // int возрастЧисло = 18 лет;       // ❌ Нельзя! 


        String original_text = "I am Batman";
        String modified = original_text.replace("Batman", "Joker");
        System.out.println(original_text);
        System.out.println(modified);


        String text5 = "My name is John Sina";
        int length1 = text5.length();
        System.out.println(length1);


        boolean isEmpty = text5.isEmpty();
        boolean isBlank = text5.isBlank();
        System.out.println(isEmpty + " " + isBlank);

        String empty = "";
        System.out.println(empty.isEmpty());



        String phrase = "I am software engineer";
        int position1 = phrase.indexOf("engineer");
        int position2 = phrase.lastIndexOf("r");
        System.out.println(position1 + " " + position2);

        boolean hasAm = phrase.contains("am");
        boolean starts = phrase.startsWith("I ar");
        boolean ends = phrase.endsWith("er");
        System.out.println(hasAm + " " + starts + " " + ends);



        String text6 = "Niga";
        String text7 = "niga";
        boolean exact = text6.equals(text7);
        System.out.println(exact);



        String text8 = "Programming on Java";
        String part1 = text8.substring(15);
        String part2 = text8.substring(0, 15);
        char letter = text8.charAt(5);
        String fruits2 = "apple, pear, banana";
        String[] fruitArray = fruits2.split(",");
        System.out.println(text8);
        System.out.println(part1);
        System.out.println(part2);
        System.out.println(letter);
        System.out.println(fruitArray);


        String original = "    Hello World     ";
        String trimmed = original.trim();
        System.out.println(trimmed);
        System.out.println(original);



        String name1 = "Anna";
        int age = 25;
        String message2 = "My name is " + name1 + ", " + "I am " + age + "years old.";
        String message3 = String.format("Меня зовут %s, мне %d лет", name, age);
        String message4 = "Меня зовут %s, мне %d лет".formatted(name, age);



        String s1 = "hello";
        String s2 = "hello";

        // ✅ ВСЕГДА правильно
        if (s1 == s2) {
        System.out.println("Correct");
        }



        // 🧠 String Pool (пул строк) — магия производительности
        // Java хранит строки в специальном "бассейне":

        String word1 = "hello";                        // Создаём в пуле
        String word2 = "hello";                        // Используем ту же строку из пула
        String word3 = new String("hello");  // Принудительно создаём новый объект

        System.out.println(word1 == word2);      // true - один объект в пуле
        System.out.println(word1 == word3);      // false - разные объекты
        System.out.println(word1.equals(word3)); // true - содержимое одинаковое
    }
}