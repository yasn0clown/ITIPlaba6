import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MostUsedWords {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\mrend\\OneDrive\\Документы\\МТУСИ ВСЯКАЯ ХЕРНЯ\\ИТИП\\ITIPlaba6\\slova.txt";
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
            return;
        }

        Map<String, Integer> wordCount = new HashMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase().replaceAll("[^a-zа-я0-9]", "");
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        scanner.close();

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        System.out.println("Топ-10 самых частых слов:");
        for (int i = 0; i < Math.min(10, list.size()); i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
