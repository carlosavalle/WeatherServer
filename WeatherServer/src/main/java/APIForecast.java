import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


@WebServlet("/forecast")
public class APIForecast extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // will convert forecast information into JSON and return the user.
        Persistence persistence = new Persistence();
        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            // will call listforecast to receive data from the specified location
            s = mapper.writeValueAsString(persistence.listForecast("Garzon"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(s);
        out.flush();
    }



}




