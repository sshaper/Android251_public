package com.example.weatherapp;

public class Main {
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Integer pressure;
    private Integer humidity;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feels_like;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feels_like = feelsLike;
    }

    public Double getTempMin() {
        return temp_min;
    }

    public void setTempMin(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTempMax() {
        return temp_max;
    }

    public void setTempMax(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
