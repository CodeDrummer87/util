import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void readFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null)
                sortData(line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortData(String line) {
        var values = line.split(" ");
        StringBuilder string = new StringBuilder();
        for (String val: values) {
            if (val.contains(".") && (Character.isDigit(val.charAt(0)) || val.charAt(0) == '-')) {
                floats.add(val);
                continue;
            }
            if ((Character.isDigit(val.charAt(0)) || val.charAt(0) == '-') && !val.contains(".")) {
                integers.add(val);
                continue;
            }
            string.append(val).append(" ");
        }
        if (!string.isEmpty()) {
            strings.add(string.toString().trim());
        }
    }

    public void createFiles() {
        if (!integers.isEmpty()) writeToFile("integers.txt", integers);
        if (!floats.isEmpty()) writeToFile("floats.txt", floats);
        if (!strings.isEmpty()) writeToFile("strings.txt", strings);
    }

    private void writeToFile(String fileName, List<String> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : list) {
                writer.write(line + '\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
