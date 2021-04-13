# WeatherServer
List of Coding Topics included: 

•	[Topic 1] Servlet.
•	[Topic 2] Collections.
•	[Topic 3] Hibernate.
•	[Topic 4] JSON and HTTP/URL
•	[Topic 5] Threads, Runnables, Executors




I worked for an agroindustry company that needs to know historical weather conditions, daily and forecast. Sometimes the historical condition is difficult to find in the country that I am from, so I created an application for it. I used the API from openweathermap.org to retrieve the weather condition and the forecast for the city of Garzon. To create the historical weather data, I am retrieving the current weather every 15 minutes, and I am storing it on a database. Also, I created an android app where the user can check this data


![image](https://user-images.githubusercontent.com/4127427/114586235-d2a5c100-9c5a-11eb-81cb-46c2c593dfa0.png)
Server Side

1- Server Running
![image](https://user-images.githubusercontent.com/4127427/114586336-ecdf9f00-9c5a-11eb-873a-fd3fe94284a8.png)


The server will save the weather condition every 15 minutes.
![image](https://user-images.githubusercontent.com/4127427/114586353-f23ce980-9c5a-11eb-8a11-20b7fde5d9e3.png)







2- Weather history.
Example http://localhost:8080/WeatherServer_war/weather?startDate=2021-03-28 8:34:55&endDate=2021-03-28 23:34:55
 
![image](https://user-images.githubusercontent.com/4127427/114586375-f79a3400-9c5a-11eb-9097-6b620ce7c6b2.png)


3- Forecast
Example http://localhost:8080/WeatherServer_war/forecast
![image](https://user-images.githubusercontent.com/4127427/114586397-fcf77e80-9c5a-11eb-8ccc-13b2713281ac.png)


 

 
