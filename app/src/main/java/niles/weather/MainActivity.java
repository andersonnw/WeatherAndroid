package niles.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTemp(View view){
        //get the input
        EditText input = (EditText) findViewById(R.id.cityTxt);
        String city = input.getText().toString();

        // create and run the task
        TempTask task = new TempTask(this);
        task.execute(city);
    }

    public void showFore(View view) {
        //get input
        EditText input = (EditText) findViewById(R.id.cityTxt);
        String city = input.getText().toString();

        ForecastTask task = new ForecastTask(this);
        task.execute(city);
    }


}
