package com.example.weatherapp;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//HERE I CALL IN THE OTHER CLASSES THAT CONTAIN THE DIFFERENT PARTS OF THE DATA THAT WAS SENT
public class MainObj {
    String name;
    Coord coord;
    //VERY IMPORTANT HERE.  THE WEATHER CONTAINS AN LIST INSTEAD OF AN OBJECT LIKE THE OTHER.  SO I HAD TO PUT IT INTO A LIST AS SHOWN.
    List<WeatherArray> weather = null;
    Main main;
    Long dt;
    Sys sys;
    Integer timezone;

    private String TAG = "MyMainObj";

    public List<WeatherArray> getWeather(){
        return weather;
    }

    public void setWeather(List<WeatherArray> weather){
        this.weather = weather;
    }


    public String displayTemp(){
        String t = String.format(Locale.ENGLISH,"%.0f", main.getTemp());
        return t;
    }

    public String displayTempRange(){
        String low = String.format(Locale.ENGLISH,"%.0f", main.getTempMin());
        String high = String.format(Locale.ENGLISH,"%.0f", main.getTempMax());
        Log.i(TAG,"the setting is "+ main.getTempMax());

        return "Low Temp " + low + "\nHigh Temp " + high;

    }

    public String weatherDescription(){
        Long sunrise = sys.getSunrise();
        Long sunset = sys.getSunset();
        Integer offset = getTimezone();

        sunrise = (sunrise * 1000);
        sunset = (sunset * 1000);

        Log.i(TAG,sunrise.toString());
        Log.i(TAG,sunset.toString());

        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        String sr = format.format(new Date(sunrise));
        String ss = format.format(new Date(sunset));
        String fl = String.format(Locale.ENGLISH,"%.0f", main.getFeelsLike());

        String description = "OTHER INFORMATION:\n";
        description += "Description: " + weather.get(0).getDescription() + "\n";
        description += "Humidity: " + main.getHumidity() + "%\n";
        description += "Feels like: " + fl + "\n";
        description += "Sunrise: " + sr + "\n";
        description += "Sunset: " + ss + "\n";

        return description;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getDt(){
        return dt;
    }

    public void setDt(Long dt){
        this.dt = dt;
    }

    public Integer getTimezone(){
        return timezone;
    }

    public void setTimeZone(Integer timezone){
        this.timezone = timezone;
    }

    public String getDate(){

        Long tz = (getDt() * 1000);

        SimpleDateFormat format = new SimpleDateFormat("E MM-dd-yyyy");
        String dateString = format.format(new Date(tz));
        return "Weather Forcast for " + name + "\nOn " + dateString;
     }
}

/*
***********THIS IS A SAMPLE RETURNED FROM THE WEATHER API**************************
{"coord":{"lon":-83.92,"lat":42.62},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13d"}],"base":"stations","main":{"temp":25.5,"feels_like":15.49,"temp_min":23,"temp_max":28.4,"pressure":1011,"humidity":85},"visibility":1207,"wind":{"speed":9.17,"deg":360,"gust":24.16},"snow":{"1h":0.25},"clouds":{"all":90},"dt":1582753539,"sys":{"type":1,"id":5267,"country":"US","sunrise":1582719425,"sunset":1582759233},"timezone":-18000,"id":0,"name":"Howell","cod":200}
 */
