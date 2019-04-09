/**
 * @author Bartczuk Damian S17763
 */

package zad1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.lang.*;
import java.util.*;

public class Service {
    static String country;
    private String currencyCode;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Service(String country) {
        this.country = country;
    }

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }

    public String getWeather(String tower) {
        String API_KEY = "b81a436451b518ab690030de79f0c14f";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + tower + "&appid=" + API_KEY + "&units=metric";
        String result = "";
        try {
            result = getJSONValue(urlString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public Double getRateFor(String currency) {
        String localeSpecifiedToCountry = getLocaleForSpecifiedCountry();
        String JSONString = "";
        Currency currencyInstance = Currency.getInstance(Locale.forLanguageTag(localeSpecifiedToCountry));
        String currencyCode = currencyInstance.getCurrencyCode();
        String ur = "https://api.exchangeratesapi.io/latest?symbols=" + currencyCode + "&base=" + currency;
        try {
            JSONString = getJSONValue(ur);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> respMap = jsonToMap(JSONString);
        Map<String, Object> mainMap = jsonToMap(respMap.get("rates").toString());
        Double exchangeRate = Double.parseDouble(String.valueOf(mainMap.get(currencyCode)));
        return exchangeRate;
    }

    private String getLocaleForSpecifiedCountry() {
        Map<Locale, String> m = new HashMap<>();
        for (Locale l : Locale.getAvailableLocales()) {
            m.put(l, l.getCountry());
        }
        Optional<Map.Entry<Locale, String>> o = m.entrySet()
                .stream()
                .filter(localeStringEntry -> localeStringEntry.getKey()
                        .getDisplayCountry().equals(country)).findFirst();

        String localeSpecifiedToCountry = o.get().getKey().toString().replace("_", "-");
        return localeSpecifiedToCountry;
    }

    public Double getNBPRate() {
        String localeSpecifiedToCountry = getLocaleForSpecifiedCountry();
        Currency currency = Currency.getInstance(Locale.forLanguageTag(localeSpecifiedToCountry));
        currencyCode = currency.getCurrencyCode();
        String ur = "https://api.exchangeratesapi.io/latest?symbols=" + currencyCode + "&base=PLN";
        String str = "";
        try {
            str = getJSONValue(ur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = jsonToMap(str);
        Map<String, Object> mainMap = jsonToMap(map.get("rates").toString());
        double rate = Double.parseDouble(mainMap.get(currencyCode).toString());
        return rate;
    }

    public String getJSONValue(String urlString) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }
}
