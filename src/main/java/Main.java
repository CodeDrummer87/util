import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String...args) {

        List<String> files = Arrays.stream(args).filter(a -> a.endsWith(".txt")).toList();

        if (!files.isEmpty()) {
            List<String> options = Arrays.stream(args).filter(o -> !o.contains(".txt")).toList();
            FileRecordMode mode = new FileRecordMode(options);

            FileUtils utils = new FileUtils(mode);
            for (String file : files) {
                utils.readFromFile(file);
            }

            utils.createFiles();
        } else {
            System.out.println(".:: Среди аргументов нет файлов");
        }
    }
}
