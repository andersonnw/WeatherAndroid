package niles.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class WeatherForecastItem {
    @SerializedName("dt_txt")
    String dateTime;

    @SerializedName("main")
    Map<String, Float> misc;

    Map<String, Float> wind;
    List<Map<String, String>> weather;

    public String getDateTime(){
        return dateTime;
    }

    public float getTempMin(){
        return misc.get("temp_min");
    }

    public float getTempMax(){
        return misc.get("temp_max");
    }

    public String getConditions() {
        return  weather.get(0).get("description");
    }

    public float getTemp(){ return misc.get("temp"); }

    public float getWind(){
        return wind.get("speed");
    }
    public void display() {
        System.out.format("%nTime: %s%n Temp: %s%n Description: %s%n Wind Speed: %s%n",
                getDateTime(), getTempMax(), getConditions(), getWind());
    }

    public String getString()
    {
        String output = String.format(("%nTime: %s%n Temp: %s%n Description: %s%n Wind Speed: %s%n"),
                getDateTime(), getTempMax(), getConditions(), getWind());

        return output;
    }
}
