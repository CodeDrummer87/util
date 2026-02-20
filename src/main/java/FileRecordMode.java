import java.util.List;

public class FileRecordMode {

    public boolean hasNewPath = false;
    private String newPath;

    public boolean hasPrefix = false;
    private String prefix;

    public boolean isAppended = false;
    public boolean areStatistics = false;
    public boolean areFullStatistics = false;

    public FileRecordMode(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            switch (options.get(i)) {
                case "-o":
                    hasNewPath = true;
                    newPath = options.get(++i);
                    break;
                case "-p":
                    hasPrefix = true;
                    prefix = options.get(++i);
                    break;
                case "-a": isAppended = true; break;
                case "-s": areStatistics = true; break;
                case "-f": areFullStatistics = true; break;
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
}
