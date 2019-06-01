package niles.weather;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Console;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class TempTask extends AsyncTask<String, Void, Void> {

    // used to create the api request
    private String tempUrl = "https://api.openweathermap.org/data/2.5/weather";
    private String apiKey = "&apiKey=5967c0d83bb03717cbd30f4bf964eac6&units=imperial";

    private WeakReference<MainActivity> activityReference;
    MainActivity activity;

    public TempTask(MainActivity context){
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

        WeatherForecastItem place = gson.fromJson(results, WeatherForecastItem.class);

        final String tempMessage = "The Temperature is : " + place.getTemp();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Context context = activity.getApplicationContext();
                String message = tempMessage;
                int dur = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, message, dur);
                toast.show();

            }
        });

        return null;
    }
}
