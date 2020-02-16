package pl.sda.pk.YourWeather.external_api.arithmetics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Arithmetics {

    public static int getAverageInteger(List<Integer> list) {
        return (list.stream().mapToInt(Integer::intValue).sum()) / list.size();
    }

    public static float getAverageFloat(List<Float> list) {
        float input = (float) ((list.stream().mapToDouble(Float::floatValue).sum()) / list.size());
        return new BigDecimal(input).setScale(5, RoundingMode.HALF_EVEN).floatValue();
    }
}
