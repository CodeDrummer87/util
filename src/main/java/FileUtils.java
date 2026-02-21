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
            boolean isDigitCondition = Character.isDigit(val.charAt(0)) || val.charAt(0) == '-';
            if (val.contains(".") && isDigitCondition) {
                floats.add(val);
                continue;
            }
            if (!val.contains(".") && isDigitCondition) {
                integers.add(val);
                continue;
            }
            string.append(val).append(" ");
        }
        if (!string.isEmpty()) {
            strings.add(string.toString().trim());
        }
    }

    public void getOutputData() {
        String path = mode.hasNewPath ? mode.getNewPath() : "";
        String prefix = mode.hasPrefix ? mode.getPrefix() : "";
        if (mode.hasNewPath) {
            checkDirectory(path);
            path += File.separator;
        }

        if (!integers.isEmpty()) {
            writeToFile(path + prefix + "integers.txt", integers);
            if (mode.areStatistics) {
                displayStatistics(mode.isBrief, integers, "целым числам");
            }
        }
        if (!floats.isEmpty()) {
            writeToFile(path + prefix + "floats.txt", floats);
            if (mode.areStatistics) {
                displayStatistics(mode.isBrief, floats, "дробным числам");
            }
        }
        if (!strings.isEmpty()) {
            writeToFile(path + prefix + "strings.txt", strings);
            if (mode.areStatistics) {
                displayStatistics(mode.isBrief, strings, "строкам");
            }
        }
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

    private void displayStatistics(boolean isBrief, List<String> list, String target) {
        if (isBrief) {
            displayBriefStatistics(list, target);
        } else {
            displayFullStatistics(list, target);
        }
    }

    private void displayBriefStatistics(List<String> list, String target) {
        System.out.println("\n.:: Краткая статистика по " + target + " :::");
        getLine('-', 45);
        System.out.println("записано элементов: " + list.size());
    }

    private void displayFullStatistics(List<String> list, String target) {
        DataUtils dataUtils = new DataUtils();
        System.out.println("\n.:: Полная статистика по " + target + " :::");
        getLine('-', 45);
        System.out.println("записано элементов: " + list.size());
        if (target.contains("числ")) {
            System.out.println("минимальное значение: " + dataUtils.getMinValue(list));
            System.out.println("максимальное значение: " + dataUtils.getMaxValue(list));
            System.out.println("сумма: " + dataUtils.getSum(list));
            System.out.println("среднее значение: " + dataUtils.getAverage(list));
        } else {
            System.out.println("размер самой короткой строки: " + dataUtils.getMinLength(list));
            System.out.println("размер самой длинной строки: " + dataUtils.getMaxLength(list));
        }
    }

    private void getLine(char ch, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }

    private void checkDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
