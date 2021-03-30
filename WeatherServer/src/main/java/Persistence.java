import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Persistence {


    private static SessionFactory factory;

    public Persistence() {

        // will start the hibernate service with the hibernate file configuration.
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }




// will insert weather data to the database and receive a weather object
    public  void insertWeather(Weather weather) throws Exception {

          // open the session
          Session session = factory.openSession();
          Transaction transaction = null;
          try {
              // begin the transaction
              transaction = session.beginTransaction();

              session.save(weather);
              transaction.commit();

          } catch (HibernateException e) {
              if (transaction != null) transaction.rollback();
              e.printStackTrace();
          } finally {
              session.close();
          }

      }

// will return a list of Weather history with date selected from the DB
    public List<Weather> listWeather(String sDate, String eDate) throws ParseException {


        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);

        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eDate);


        Session session = factory.openSession();
        Transaction transaction = null;
        List Weather = null;
        try {
            // will retrieve the weather  table data from the DB
            transaction = session.beginTransaction();
            Weather = session.createQuery("FROM Weather where date between :startDate AND :endDate")
                    .setParameter("startDate",startDate)
                    .setParameter("endDate",endDate)
                    .list();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Weather;
    }

    // will return a list of forecast by a specified location from the DB
    public List<WeatherForecastSummary> listForecast(String location) throws ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<WeatherForecastSummary> wfs_list = new ArrayList<>();
        // retrieve the infromation for the openweathermap api
        String result = readHTTP("https://api.openweathermap.org/data/2.5/forecast?q=" + location+ "&units=imperial&apiKey=6ae2281a443225f45f30cc3a4a1d37b2");
        WeatherForecast wf = mapper.readValue(result, WeatherForecast.class);

        Iterator var16 = wf.getList().iterator();
        // generate a summary
        while(var16.hasNext()) {
            WeatherForecastItem wf2 = (WeatherForecastItem)var16.next();
            WeatherForecastSummary auxWFS = new WeatherForecastSummary(wf2.getTime(),location,wf2.getMaxTemp(),wf2.getWind().getSpeed(),wf2.getWeatherCondition(),wf2.getRain());
            wfs_list.add(auxWFS);
        }

        return wfs_list;
    }

// it helps to read the json file from openweathermap
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



