import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            if (line.contains(".") && (Character.isDigit(val.charAt(0)) || val.charAt(0) == '-')) {
                floats.add(val);
                continue;
            }
            if ((Character.isDigit(line.charAt(0)) || val.charAt(0) == '-') && !line.contains(".")) {
                integers.add(val);
                continue;
            }
            string.append(val).append(" ");
        }
        if (!string.isEmpty()) {
            strings.add(string.toString().trim());
        }
    }

    public void displayData() {
        System.out.println("\n.:: Целые числа:");
        for (String i: integers)
            System.out.println(i);

        System.out.println("\n.:: Дробные числа:");
        for (String f: floats)
            System.out.println(f);

        System.out.println("\n.:: Строки:");
        for (String s: strings)
            System.out.println(s);
    }
}
