package com.hotelmanagement;

public class Hotel
{
    public String hotelName;
    public Integer regularWeekdayRate;
    public Integer regularWeekendRate;
    public Integer hotelRating;

    public Hotel(String hotelName, Integer regularWeekdayRate, Integer regularWeekendRate, Integer hotelRating)
    {
        this.hotelName = hotelName;
        this.regularWeekdayRate = regularWeekdayRate;
        this.regularWeekendRate = regularWeekendRate;
        this.hotelRating = hotelRating;
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName(String hotelName)
    {
        this.hotelName = hotelName;
    }

    public void setRegularWeekdayRate(Integer regularWeekdayRate)
    {
        this.regularWeekdayRate = regularWeekdayRate;
    }

    public Integer getRegularWeekdayRate()
    {
        return regularWeekdayRate;
    }

    public Integer getRegularWeekendRate()
    {
        return regularWeekendRate;
    }

    public void setRegularWeekendRate(Integer regularWeekendRate)
    {
        this.regularWeekendRate = regularWeekendRate;
    }

    public Integer gethotelRating()
    {
        return hotelRating;
    }

    public void setHotelRating(Integer hotelRating)
    {
        this.hotelRating = hotelRating;
    }
}
