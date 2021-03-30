import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
// it is used to  receive actual weather from openweathermap and then saved into the DB
public class WeatherThread implements Runnable  {
    private ServletContext context;
    public WeatherThread(ServletContext servletContext) {
        this.context = context;
    }

    @Override
    public void run() {

        String location = "Garzon";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


        try {
            //get data from the openweathermap api
            Persistence persistence = new Persistence();
            String result = persistence.readHTTP("https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=imperial&apiKey=6ae2281a443225f45f30cc3a4a1d37b2");
            WeatherCondition wc = mapper.readValue(result, WeatherCondition.class);

            Date joiningDate = new Date();
            //creates a weather object with the data from the API
            Weather weather = new Weather(joiningDate,wc.getId(),wc.getName(),wc.getTemperature(),wc.getHumidity(),wc.getWind().getSpeed(),wc.getPressure(), wc.getWeatherDescription(),wc.getWeatherIcon(),wc.getRain());

            // save the data in the database

            persistence.insertWeather(weather);



        } catch (IOException var18) {
            var18.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
