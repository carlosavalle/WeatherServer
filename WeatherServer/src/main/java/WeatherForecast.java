import java.util.List;
// used to retrieve data from openweathermap
public class WeatherForecast {

    private List<WeatherForecastItem> list;

    public WeatherForecast() {
    }

    public WeatherForecast(List<WeatherForecastItem> list) {
        this.list = list;
    }

    public List<WeatherForecastItem> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "list=" + list +
                '}';
    }
}
