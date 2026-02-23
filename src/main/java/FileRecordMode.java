import java.util.List;

public class FileRecordMode {

    public boolean hasNewPath = false;
    private String newPath;

    public boolean hasPrefix = false;
    private String prefix;

    public boolean isAppended = false;
    public boolean areStatistics = false;
    public boolean isBrief = false;

    public FileRecordMode(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            switch (options.get(i)) {
                case "-o":
                    if (isCorrectIndex(i + 1, options)) {
                        hasNewPath = true;
                        newPath = options.get(++i);
                    } else
                        System.out.println(".:: Некорректный путь для выходных файлов");
                    break;
                case "-p":
                    if (isCorrectIndex(i + 1, options)) {
                        hasPrefix = true;
                        prefix = options.get(++i);
                    } else
                        System.out.println(".:: Некорректный префикс");
                    break;
                case "-a": isAppended = true; break;
                case "-s": areStatistics = true; isBrief = true; break;
                case "-f": areStatistics = true; isBrief = false; break;
                default:
                    System.out.println(".:: Указана некорректная опция: " + options.get(i));
            }
        }
    }

    public String getNewPath() {
        return newPath;
    }

    public String getPrefix() {
        return prefix;
    }

    private boolean isCorrectIndex(int index, List<String> list) {
        return index < list.size() && !list.get(index).startsWith("-");
    }
}
