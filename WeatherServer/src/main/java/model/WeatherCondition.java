package model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class WeatherCondition {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

   // @SerializedName("main")

   //@JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("main")
    private Map<String, Float> measurements;

    @JsonProperty("wind")
    private WeatherWind wind;

    @JsonProperty("weather")
    private List<WeatherDescription> weather;

    @JsonProperty("rain")
    private Map<String, Float> rain;

    public WeatherCondition() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Float> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Map<String, Float> measurements) {
        this.measurements = measurements;
    }

    public WeatherWind getWind() {
        return wind;
    }

    public void setWind(WeatherWind wind) {
        this.wind = wind;
    }

    public String getWeatherDescription() {
        return weather.get(0).getDescription();
    }

    public String getWeatherIcon() {
        return weather.get(0).getIcon();
    }

    public void setWeather(List<WeatherDescription> weather) {
        this.weather = weather;
    }
    public float getTemperature(){
        return measurements.get("temp");
    }
    public float getHumidity(){
        return measurements.get("humidity");
    }
    public float getPressure(){
        return measurements.get("pressure");
    }
    public float getRain(){
        if (rain == null){
            return 0.0F;
        }
        return rain.get("1h");
    }

    @Override
    public String toString() {
        return "WeatherCondition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurements=" + measurements +
                ", wind=" + wind +
                ", weather=" + weather +
                ", rain=" + getRain() +
                '}';
    }
}
