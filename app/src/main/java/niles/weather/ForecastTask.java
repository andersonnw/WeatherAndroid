package niles.weather;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ForecastTask extends AsyncTask<String, Void, Void>  {
    // used to create the api request
    private String tempUrl = "https://api.openweathermap.org/data/2.5/forecast";
    private String apiKey = "&apiKey=5967c0d83bb03717cbd30f4bf964eac6&units=imperial";

    private WeakReference<MainActivity> activityReference;
    MainActivity activity;

    public ForecastTask(MainActivity context){
        activityReference = new WeakReference<>(context);
        activity = activityReference.get();
    }

    // used to display


    @Override
    protected Void doInBackground(String... strings) {
        // create the url for the api request
        String city = new String();
        city = "?q=" + strings[0];
        String url = tempUrl + city + apiKey;

        System.out.println(url);
        String results;
        try {
            results = Http.httpGet(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        //create the Gson to get ready for the conversion
        Gson gson = new Gson();
        WeatherForecast forecast = gson.fromJson(results, WeatherForecast.class);

        // take the results and save it to the list view on the main activity
        final List<String> forecastList = new ArrayList<>();
        for ( WeatherForecastItem item : forecast.forecast) {
            String temp = item.getString();
            forecastList.add(temp);
        }


        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, forecastList);
                ListView listView = (ListView) activity.findViewById(R.id.foreList);

                listView.setAdapter(adapter);
            }
        });

        return null;
    }
}
