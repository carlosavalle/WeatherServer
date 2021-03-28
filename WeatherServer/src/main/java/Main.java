import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.HibernateUtil;
import model.Persistence;
import model.Weather;
import model.WeatherCondition;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import javax.persistence.metamodel.EntityType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class Main {


   public static void main(final String[] args) throws Exception {
       Main http = new Main();

       String location = "Garzon";

       System.out.printf("Enter the location: ");
       ObjectMapper mapper = new ObjectMapper();
       mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


       try {
           // location = br.readLine();
           //  location ="Japon";
           String result = http.readHTTP("https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=imperial&apiKey=6ae2281a443225f45f30cc3a4a1d37b2");
           WeatherCondition wc = mapper.readValue(result, WeatherCondition.class);
           //  WeatherForecast wc = mapper.readValue(result, WeatherForecast.class);;

           System.out.println("It is the weather condition for " + location);
           System.out.println();
           System.out.println(wc);
           System.out.println();

           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();
           Date joiningDate = new Date();
           Weather weather = new Weather(joiningDate, wc.getId(), wc.getName(), wc.getTemperature(), wc.getHumidity(), wc.getWind().getSpeed(), wc.getPressure(), wc.getWeatherDescription(), wc.getWeatherIcon(), wc.getRain());
           System.out.printf(weather.toString());

           Persistence persistence = new Persistence();
           persistence.insertWeather(weather);

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