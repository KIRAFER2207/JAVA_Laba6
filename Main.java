import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

// Клас-перекладач
class Translator {
    private HashMap<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<>();
        dictionary.put("hello", "привіт");
        dictionary.put("world", "світ");
        dictionary.put("java", "джава");
        dictionary.put("programming", "програмування");
    }

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase().trim(), ukrainian.toLowerCase().trim());
    }

public String translateSentence(String sentence) {
        String[] words = sentence.trim().toLowerCase().split("\\s+");
        StringBuilder translated = new StringBuilder();

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Zа-яА-ЯіІїЇєЄґҐ]", "");
            if (word.isEmpty()) continue;

            String translation = dictionary.get(word);
            
            if (translation != null) {
                translated.append(translation).append(" ");
            } else {
                translated.append("[").append(word).append("] ");
            }
        }
        return translated.toString().trim();
    }
}

public class Main {
    public static void main(String[] args) {
         try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
     }
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        System.out.println("=== Translator Started ===");

       while (true) {
            System.out.print("Add a new word? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (!choice.equals("y") && !choice.equals("yes")) {
                break;
            }

            System.out.print("Enter English word: ");
            String eng = scanner.nextLine();
            System.out.print("Enter Ukrainian translation: ");
            String ukr = scanner.nextLine();

            translator.addWord(eng, ukr);
            System.out.println("Word added successfully!\n");
        }
        System.out.println("\nEnter English phrase to translate:");
        String inputSentence = scanner.nextLine();
        String result = translator.translateSentence(inputSentence);
        System.out.println("\n--- Translation Result ---");
        System.out.println(result);

        scanner.close();
    }
}
