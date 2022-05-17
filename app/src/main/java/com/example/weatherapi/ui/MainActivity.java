package com.example.weatherapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapi.R;
import com.example.weatherapi.data.RetrofitBuilder;
import com.example.weatherapi.data.Weather;
import com.example.weatherapi.data.WeatherHolderApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout slideUp;
    private LinearLayout slide;
    private TextView cityNameTextView;
    private TextView localtime;
    private TextView condition;
    private TextView temp_c;
    private TextView max_min_temp;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView cloud;
    private ImageView noon;
    private ImageView night;
    private EditText city;
    private Button search;
    private TextView swipeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideUp = findViewById(R.id.slideUp);
        slide = findViewById(R.id.slide);
        cityNameTextView = findViewById(R.id.cityNameTextView);
        localtime = findViewById(R.id.localtime);
        condition = findViewById(R.id.condition);
        temp_c = findViewById(R.id.temp_c);
        max_min_temp = findViewById(R.id.max_min_temp);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        wind = findViewById(R.id.wind);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        cloud = findViewById(R.id.cloud);
        noon = findViewById(R.id.noon);
        night = findViewById(R.id.night);
        city = findViewById(R.id.city);
        search = findViewById(R.id.search);
        swipeText = findViewById(R.id.swipeText);

//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd HH:mm")
//                .create();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.weatherapi.com")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        WeatherHolderApi weatherHolderApi = retrofit.create(WeatherHolderApi.class);

        WeatherHolderApi weatherHolderApi = RetrofitBuilder.getInstance();

        Call<Weather> holderApiWeather = weatherHolderApi.getWeather("Bishkek");
        holderApiWeather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                cityNameTextView.setText(weather.getLocation().getCityName());
                localtime.setText(weather.getLocation().getLocaltime().toString());
                condition.setText(weather.getCurrent().getCondition().getCondition());
                temp_c.setText(weather.getCurrent().getTemp_c() + "°C");
                max_min_temp.setText(weather.getForecast().getForecastdays().get(0).getDay().getMaxtemp() + "°C↑ \n" + response.body().getForecast().getForecastdays().get(0).getDay().getMintemp() + "°C↓ ");
                humidity.setText(weather.getCurrent().getHumidity() + "%");
                pressure.setText(weather.getCurrent().getPressure() + "\nmBar");
                wind.setText(weather.getCurrent().getWind() + " km/h");
                sunrise.setText(weather.getForecast().getForecastdays().get(0).getAstro().getSunrise());
                sunset.setText(weather.getForecast().getForecastdays().get(0).getAstro().getSunset());
                cloud.setText(weather.getCurrent().getCloud() + "%");
                if(weather.getCurrent().isDay() == 0) {
                    noon.setVisibility(View.GONE);
                    night.setVisibility(View.VISIBLE);
                } else {
                    noon.setVisibility(View.VISIBLE);
                    night.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                System.out.println(t.getMessage());
            }

        });


//        slideUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (slide.getVisibility() == View.GONE){
//                    slide.setVisibility(View.VISIBLE);
//                } else{
//                    slide.setVisibility(View.GONE);
//                }
//            }
//        });

        new SwipeListener(slideUp);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!city.getText().toString().isEmpty()) {
                    Call<Weather> call = weatherHolderApi.getWeather(city.getText().toString());
                    call.enqueue(new Callback<Weather>() {
                        @Override
                        public void onResponse(Call<Weather> call, Response<Weather> response) {
                            Weather weather = response.body();
                            cityNameTextView.setText(weather.getLocation().getCityName());
                            localtime.setText(weather.getLocation().getLocaltime().toString());
                            condition.setText(weather.getCurrent().getCondition().getCondition());
                            temp_c.setText(weather.getCurrent().getTemp_c() + "°C");
                            max_min_temp.setText(weather.getForecast().getForecastdays().get(0).getDay().getMaxtemp() + "°C↑ \n" + response.body().getForecast().getForecastdays().get(0).getDay().getMintemp() + "°C↓ ");
                            humidity.setText(weather.getCurrent().getHumidity() + "%");
                            pressure.setText(weather.getCurrent().getPressure() + "\nmBar");
                            wind.setText(weather.getCurrent().getWind() + " km/h");
                            sunrise.setText(weather.getForecast().getForecastdays().get(0).getAstro().getSunrise());
                            sunset.setText(weather.getForecast().getForecastdays().get(0).getAstro().getSunset());
                            cloud.setText(weather.getCurrent().getCloud() + "%");
                            if (weather.getCurrent().isDay() == 0) {
                                noon.setVisibility(View.GONE);
                                night.setVisibility(View.VISIBLE);
                            } else {
                                noon.setVisibility(View.VISIBLE);
                                night.setVisibility(View.GONE);
                                swipeText.setText("Swipe Down");
                            }
                        }

                        @Override
                        public void onFailure(Call<Weather> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }

                    });
                    slide.setVisibility(View.GONE);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Заполните поле!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private class SwipeListener implements View.OnTouchListener{
        GestureDetector gestureDetector;

        SwipeListener(View view){
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onDown(MotionEvent e) {
                            return true;
                        }

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                            float xDiff = e2.getX() - e1.getX();
                            float yDiff = e2.getY() - e1.getY();

                            try {
                                float absXDiff = Math.abs(xDiff);
                                float absYDiff = Math.abs(yDiff);

                                if (absXDiff > absYDiff){
                                    if (absXDiff > threshold && Math.abs(velocityX) > velocity_threshold){
                                        if (xDiff > 0){
                                            Log.i("right", "right swipe");
                                        }
                                        else Log.i("left", "left swipe");
                                    }
                                }
                                else {
                                    if (absYDiff > 0 && Math.abs(velocityY) > velocity_threshold){
                                        if (yDiff > 0){
                                            Log.i("down", "down swipe");
                                            if (slide.getVisibility() == View.GONE){
                                                slide.setVisibility(View.VISIBLE);
                                                swipeText.setText("Swipe Up");
                                            } else{
                                                slide.setVisibility(View.GONE);
                                                swipeText.setText("Swipe Up");
                                            }
                                        }
                                        else {
                                            Log.i("up", "up swipe");
                                            if (slide.getVisibility() == View.GONE){
                                                slide.setVisibility(View.VISIBLE);
                                                swipeText.setText("Swipe Down");
                                            } else{
                                                slide.setVisibility(View.GONE);
                                                swipeText.setText("Swipe Down");
                                            }
                                        }
                                    }
                                }
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            return false;
                        }
                    };

            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}