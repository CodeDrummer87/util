import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String...args) {

        List<String> files = new ArrayList<String>();
        for (String arg : args) {
            if (arg.contains(".txt"))
                files.add(arg);
        }

        if (!files.isEmpty()) {
            FileUtils utils = new FileUtils();
            for (String file : files) {
                utils.readFromFile(file);
            }
            utils.displayData();
        } else {
            System.out.println(".:: Среди аргументов нет файлов");
        }
    }
}
