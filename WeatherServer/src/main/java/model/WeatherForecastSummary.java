package model;

import java.util.Comparator;

public class WeatherForecastSummary {
    private String city;
    private float maxTemp;
    private float maxWind;

    public WeatherForecastSummary() {
    }

    public WeatherForecastSummary(String city, float maxTemp, float maxWind) {
        this.city = city;
        this.maxTemp = maxTemp;
        this.maxWind = maxWind;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMaxWind() {
        return maxWind;
    }

    public void setMaxWind(float maxWind) {
        this.maxWind = maxWind;
    }
// compared to sort the array to Max Temp
    public static Comparator<WeatherForecastSummary> sortMaxTemp = new Comparator<WeatherForecastSummary>() {
        @Override
        public int compare(WeatherForecastSummary o1, WeatherForecastSummary o2) {
            return Float.compare(o2.getMaxTemp(),o1.getMaxTemp());
        }
    };
    // compared to sort the array to Max Wind
    public static Comparator<WeatherForecastSummary> sortMaxWind = new Comparator<WeatherForecastSummary>() {
        @Override
        public int compare(WeatherForecastSummary o1, WeatherForecastSummary o2) {
            return Float.compare(o2.getMaxWind(),o1.getMaxWind());
        }
    };


    @Override
    public String toString() {
        return  "city='" + city + '\'' +
                ", maxTemp=" + maxTemp +
                ", maxWind=" + maxWind ;
    }
}
