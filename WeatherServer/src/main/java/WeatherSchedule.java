import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// it is used to call the WeatherThread class to receive actual weather from openweathermap and then saved into the DB. it execute every 15 minutes in the background.
public class WeatherSchedule  implements ServletContextListener{
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable command = new WeatherThread(event.getServletContext());
        // Delay 1 Minute to first execution
        long initialDelay = 1;
        TimeUnit unit = TimeUnit.MINUTES;
        // period the period between successive executions
        long period = 15;// 15 Minute!

        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
}
