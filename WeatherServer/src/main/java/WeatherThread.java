import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

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

            String result = readHTTP("https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=imperial&apiKey=6ae2281a443225f45f30cc3a4a1d37b2");
            WeatherCondition wc = mapper.readValue(result, WeatherCondition.class);

            System.out.println("It is the weather condition for " + location);
            System.out.println();
            System.out.println(wc);
            System.out.println();

            Date joiningDate = new Date();
            Weather weather = new Weather(joiningDate,wc.getId(),wc.getName(),wc.getTemperature(),wc.getHumidity(),wc.getWind().getSpeed(),wc.getPressure(), wc.getWeatherDescription(),wc.getWeatherIcon(),wc.getRain());
            System.out.printf(weather.toString());

            Persistence persistence = new Persistence();
            persistence.insertWeather(weather);



        } catch (IOException var18) {
            var18.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String readHTTP(String url) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder data = new StringBuilder();

            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    data.append(line);
                }
            } while(line != null);

            return data.toString();
        } catch (IOException var7) {
            System.out.println("Error reading HTTP Response: " + var7);
            return null;
        }


    }
}
