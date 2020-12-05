package com.hotelmanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class HotelReservationSystem
{
    public static HashMap<String, Integer> hotels = new HashMap<String, Integer>();

    public static void main(String[] args)
    {
        System.out.println("Welcime to Hotel Reservation System");
        addHotelToSystem("Lakewood", 110);
        addHotelToSystem("Bridgewood",160);
        addHotelToSystem("Ridgewood", 220);
        System.out.println(hotels);
    }

    public static void addHotelToSystem(String hotelName, Integer regularWeekdayRate)
    {
        Hotel lakewoodHotel = new Hotel(hotelName, regularWeekdayRate);
        hotels.put(hotelName, regularWeekdayRate);
    }
}
