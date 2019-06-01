package niles.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecast {


    @SerializedName("list")
    List<WeatherForecastItem> forecast;

    public void display(){
        for (WeatherForecastItem block : forecast) {
            block.display();
        }
    }
}
