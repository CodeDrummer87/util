import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    private FileRecordMode mode;

    public FileUtils(FileRecordMode mode) {
        this.mode = mode;
    }

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
        String path = mode.hasNewPath ? mode.getNewPath() : "";
        String prefix = mode.hasPrefix ? mode.getPrefix() : "";

        if (!integers.isEmpty()) writeToFile(path + prefix + "integers.txt", integers);
        if (!floats.isEmpty()) writeToFile(path + prefix + "floats.txt", floats);
        if (!strings.isEmpty()) writeToFile(path + prefix + "strings.txt", strings);
    }

    private void writeToFile(String fileName, List<String> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, mode.isAppended))) {
            for (String line : list) {
                writer.write(line + '\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
