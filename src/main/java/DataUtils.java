import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DataUtils {

    public BigDecimal getMinValue(List<String> list) {
        BigDecimal min = BigDecimal.ZERO;
        try {
            min = new BigDecimal(list.getFirst());
            for (String integer : list) {
                BigDecimal value = new BigDecimal(integer);
                if (value.compareTo(min) < 0) {
                    min = value;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return min;
    }

    public BigDecimal getMaxValue(List<String> list) {
        BigDecimal max = BigDecimal.ZERO;
        try {
            for (String integer : list) {
                BigDecimal value = new BigDecimal(integer);
                if (value.compareTo(max) > 0) {
                    max = value;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return max;
    }

    public BigDecimal getSum(List<String> list) {
        BigDecimal sum = BigDecimal.ZERO;
        try {
            for (String integer : list) {
                sum = sum.add(new BigDecimal(integer));
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return sum;
    }

    public BigDecimal getAverage(List<String> list) {
        BigDecimal sum = getSum(list);
        BigDecimal count = new BigDecimal(list.size());
        return sum.divide(count, RoundingMode.DOWN);
    }

    public int getMinLength(List<String> list) {
        int min = list.getFirst().length();
        for (String line: list) {
            if (line.length() < min)
                min = line.length();
        }
        return min;
    }

    public int getMaxLength(List<String> list) {
        int max = list.getFirst().length();
        for (String line: list) {
            if (line.length() > max)
                max = line.length();
        }
        return max;
    }
}
