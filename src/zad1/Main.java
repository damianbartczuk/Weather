package zad1;
import java.util.Map;

public class Main {
    private static String temperature;
    private static String humidity;
    private static String windSpeed;

    public static void main(String[] args) {
        Service s = new Service("Włochy");
        String weatherJson = s.getWeather("Warsaw");
        String rateFor = "USD";
        Double rate1 = s.getRateFor(rateFor);
        Double rate2 = s.getNBPRate();
        GUIDesign gui = new GUIDesign();
        s.getWeather("Warsaw");
        gui.show();
        Map<String, Object> mapWeater = s.jsonToMap(weatherJson);
        Map<String, Object> mainMap = s.jsonToMap(mapWeater.get("main").toString());
        Map<String, Object> windMap = s.jsonToMap(mapWeater.get("wind").toString());
        temperature = String.valueOf(mainMap.get("temp"));
        humidity = String.valueOf(mainMap.get("humidity"));
        windSpeed = String.valueOf(windMap.get("speed"));
        String tempLabTxt = gui.getTemperatureLabel().getText();
        gui.setTemperatureLabel((tempLabTxt + temperature));
        String humidityLabelTxt = gui.getHumidityLabel().getText();
        gui.setHumidityLabel(humidityLabelTxt + " " + humidity + "%");
        String windSpeedLabelTxt = gui.getWideSpeedLabel().getText();
        gui.getWideSpeedLabel().setText(windSpeedLabelTxt + windSpeed);
        gui.getCurrencyToPLNLabel().setText(s.getCurrencyCode()+" w przeliczeniu na  PLN = " + rate2);
        gui.getCurrencyFromTo().setText(s.getCurrencyCode() + " w przeliczeniu na  " + rateFor + " = " + rate1);
    }
}
