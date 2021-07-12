/**
 *
 *  @author Korba Mateusz S21347
 *
 */

package web1;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Service {
    private String countryName;
    private String key;
    private String iso;
    private String currency;
    private String city;

    public Service(String kraj) {
        city = new String();
        countryName = kraj;
        key = "ea5562978a1799f522b1bc08354d48a4";
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("en", iso);
            countries.put(l.getDisplayCountry(new Locale("en-us","uk")), iso);
        }
        iso = countries.get(countryName);

        try {
            currency = Currency.getInstance(new Locale("en-us", iso)).getCurrencyCode();
        } catch (Exception e) {
            System.out.println("Country Not Found");
            System.exit(0);
        }
    }

    public double getRateFor(String string) {
        if(currency.equals(string)) {
            return 1;
        }
        JSONObject json = new JSONObject(connectToSite("https://api.exchangerate.host/latest?base="+currency+"&symbols="+string));
        return json.getJSONObject("rates").getDouble(string);
    }

    public String getWeather(String string) {
        city = string;
        JSONObject json = new JSONObject(connectToSite("http://api.openweathermap.org/data/2.5/weather?q=" + string + "," + iso + "&APPID=" + key + "&units=metric"));
        return json.toString();
    }

    public double getNBPRate() {
        if(!(currency.equals("PLN"))) {
            String tmp = connectToSite("http://www.nbp.pl/kursy/kursya.html");
            if(!tmp.contains(currency)) {
                tmp = connectToSite("http://www.nbp.pl/kursy/kursyb.html");
                tmp = tmp.substring(tmp.indexOf(currency) + 51);
            }else {
                tmp = tmp.substring(tmp.indexOf(currency) + 48);
            }
            tmp = tmp.substring(tmp.indexOf('>')+1, tmp.indexOf('<'));
            tmp = tmp.replaceAll(",", ".");
            return Double.parseDouble(tmp);
        }else {
            return 1;
        }

    }

    public String connectToSite(String string) {
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(string)).openConnection();
            con.setRequestMethod("GET");
            con.connect();
            StringBuffer buffer = new StringBuffer();
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();

            return buffer.toString();
        } catch(Exception e) {}

        return "";
    }

    public String getCountry() {
        return countryName;
    }

    public String getCity() {
        return city;
    }

    public String getCurrency() {
        return currency;
    }
}
