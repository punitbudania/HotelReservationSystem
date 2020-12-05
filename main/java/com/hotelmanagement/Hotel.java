package com.hotelmanagement;

public class Hotel
{
    public  String hotelName;
    public int regularWeekdayRate;

    public Hotel(String hotelName, Integer regularWeekdayRate)
    {
        this.hotelName = hotelName;
        this.regularWeekdayRate = regularWeekdayRate;
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName(String hotelName)
    {
        this.hotelName = hotelName;
    }

    public Integer getRegularWeekdayRate()
    {
        return getRegularWeekdayRate();
    }

    public void setRegularWeekdayRate(Integer regularWeekdayRate)
    {
        this.regularWeekdayRate = regularWeekdayRate;
    }
}
