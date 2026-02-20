import java.util.List;

public class DataUtils {

    public float getMinValue(List<String> list) {
        float min = 0.f;
        try {
            min = Float.parseFloat(list.getFirst());
            for (String integer: list) {
                float value = Float.parseFloat(integer);
                if (value < min)
                    min = value;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return min;
    }

    public float getMaxValue(List<String> list) {
        float max = 0.f;
        try {
            max = Float.parseFloat(list.getFirst());
            for (String integer : list) {
                float value = Float.parseFloat(integer);
                if (value > max)
                    max = value;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return max;
    }

    public float getSum(List<String> list) {
        float sum = 0.f;
        try {
            for (String integer : list)
                sum += (Float.parseFloat(integer));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return sum;
    }

    public float getAverage(List<String> list) {
        float sum = 0;
        try {
            for (String integer : list)
                sum += (Float.parseFloat(integer));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return sum / list.size();
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
