import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WordCounter {

    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        Map<String, Integer> wordCounts = countWordsInFile(inputFilePath);

        writeWordCountsToFile(wordCounts, outputFilePath);
    }

    public static Map<String, Integer> countWordsInFile(String fileName) {
        Map<String, Integer> wordCounts = new HashMap<>();
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] words = line.split("\\s+");

                for (String word : words) {
  
                    word = word.toLowerCase();
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordCounts;
    }

    public static void writeWordCountsToFile(Map<String, Integer> wordCounts, String outputFileName) {
        Path outputPath = Paths.get(outputFileName);

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Output file created: " + outputPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
