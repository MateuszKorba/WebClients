package web1;

import org.json.JSONObject;

public class Weather {

    String weatherDescription;
    double temperature;
    double humidity;
    double pressure;
    double wind;
    String city;
    String country;

    public Weather(String s1, String s2, String s3) {

        city = s2;
        country = s3;
        JSONObject json = new JSONObject(s1);

        String tmp = json.get("weather").toString();
        weatherDescription = tmp.toString().substring(tmp.indexOf("\"description\":\"") + 15);
        weatherDescription = weatherDescription.substring(0, weatherDescription.indexOf("\""));

        tmp = json.get("main").toString();
        tmp = tmp.substring(8, tmp.indexOf(','));
        temperature = Double.parseDouble(tmp);

        tmp = json.get("main").toString();
        tmp = tmp.substring(tmp.indexOf("\"humidity\":") + 11);
        tmp = tmp.substring(0, tmp.indexOf(","));
        humidity = Double.parseDouble(tmp);

        tmp = json.get("main").toString();
        tmp = tmp.substring(tmp.indexOf("\"pressure\":") + 11);
        tmp = tmp.substring(0, tmp.indexOf(","));
        pressure = Double.parseDouble(tmp);

        tmp = json.get("wind").toString();
        tmp = tmp.substring(tmp.indexOf("\"speed\":") + 8);
        tmp = tmp.replaceAll("[}]", ",");
        tmp = tmp.substring(0, tmp.indexOf(","));
        wind = Double.parseDouble(tmp);
    }

    public String toString() {
        return "Weather Description: " + weatherDescription +
               "\nTemprature: " + temperature + " °C\n" +
               "Humidity: " + humidity + "%" +
               "\nPressure: " + pressure + " hPa" +
               "\nWind speed: " + wind + " m/s";
    }
}