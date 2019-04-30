package com.example.myapplication.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {


    TextView selectCity, cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    ProgressBar loader;
    Typeface weatherFont;
    String city = "New York, US";
    String OPEN_WEATHER_MAP_API = "834a7c991764da3c80b314d0f7dc132a";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_weather);

        Button etPhoneHomeBtn = findViewById(R.id.btnHomefromWeather);
        etPhoneHomeBtn.setOnClickListener(v -> startActivity(new Intent(WeatherActivity.this, HomeActivity.class)));

        loader = findViewById(R.id.loader);
        selectCity = findViewById(R.id.selectCity);
        cityField = findViewById(R.id.city_field);
        updatedField = findViewById(R.id.updated_field);
        detailsField =  findViewById(R.id.details_field);
        currentTemperatureField =  findViewById(R.id.current_temperature_field);
        humidity_field =  findViewById(R.id.humidity_field);
        pressure_field = findViewById(R.id.pressure_field);
        weatherIcon =  findViewById(R.id.weather_icon);
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons_regular_webfont.ttf");
        weatherIcon.setTypeface(weatherFont);

        taskLoadUp(city);

        selectCity.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(WeatherActivity.this);
            alertDialog.setTitle("Change City");
            final EditText input = new EditText(WeatherActivity.this);
            input.setText(city);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);

            alertDialog.setPositiveButton("Change",
                    (dialog, which) -> {
                        city = input.getText().toString();
                        taskLoadUp(city);
                    });
            alertDialog.setNegativeButton("Cancel",
                    (dialog, which) -> dialog.cancel());
            alertDialog.show();
        });

    }

    public void taskLoadUp(String query) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    class DownloadWeather extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(String... args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();

                    cityField.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    detailsField.setText(details.getString("description").toUpperCase(Locale.US));
                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    humidity_field.setText("Humidity: " + main.getString("humidity") + "%");
                    pressure_field.setText("Pressure: " + main.getString("pressure") + " hPa");
                    updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));

                    loader.setVisibility(View.GONE);

                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
    


