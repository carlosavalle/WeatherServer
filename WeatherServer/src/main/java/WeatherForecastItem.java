import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

// used to retrieve data from openweathermap
public class WeatherForecastItem {
    @JsonProperty("dt_txt")
    private String dt_txt;
    @JsonProperty("main")
    private Map<String, Float> main;
    @JsonProperty("wind")
    private WeatherWind wind;
    @JsonProperty("weather")
    private List<WeatherDescription> weather;
    @JsonProperty("rain")
    private Map<String, Float> rain;

    public WeatherForecastItem() {
    }

    public WeatherForecastItem(String dt_txt, Map<String, Float> main, WeatherWind wind, List<WeatherDescription> weather) {
        this.dt_txt = dt_txt;
        this.main = main;
        this.wind = wind;
        this.weather = weather;
    }
    public String getTime (){
        return dt_txt;
    }

    public float getTemperature(){
     return main.get("temp");
    }

    public float getMaxTemp(){
        return main.get("temp_max");
    }


    public String getWeatherCondition(){
        return weather.get(0).getDescription();
    }

    public WeatherWind getWind() {
        return wind;
    }

    public float getRain(){
        if (rain == null){
            return 0.0F;
        }
        return rain.get("3h");
    }

    @Override
    public String toString() {
        return "WeatherForecastItem{" +
                "dt_txt='" + dt_txt + '\'' +
                ", main=" + main +
                ", wind=" + wind +
                ", weather=" + weather +
                '}';
    }
}
