package com.testcase;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static com.hotelmanagement.HotelReservationSystem.*;


public class HotelReservationTest
{
    @Test
    public void testHotelDetails()
    {
        addHotelToSystem("Lakewood", 110, 90,3);
        addHotelToSystem("Bridgewood",160, 50, 4);
        addHotelToSystem("Ridgewood", 220, 150, 5);
        Integer weekdayResult = weekDayHotels.get("Bridgewood");
        Integer weekendResult = weekEndHotels.get("Ridgewood");
        Assertions.assertEquals(160, weekdayResult);
        Assertions.assertEquals(150, weekendResult);
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnCheapestHotel()
    {
        addHotelToSystem("Lakewood", 110, 90,3);
        addHotelToSystem("Bridgewood",160, 50, 4);
        addHotelToSystem("Ridgewood", 220, 150, 5);
        String res = cheapestHotel(lowestPrice(weekDayHotels), weekDayHotels);
        long result = totalDays("12Mar2020", "16Mar2020")*lowestPrice(weekDayHotels);
        Assertions.assertEquals("Lakewood", res);
        Assertions.assertEquals(550, result);
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnCheapestHotelforWeekendAndWeekday()
    {
        addHotelToSystem("Lakewood", 110, 90,3);
        addHotelToSystem("Bridgewood",160, 50, 4);
        addHotelToSystem("Ridgewood", 220, 150, 5);
        long weekdayPrice = calcWeekDays("11Sep2020", "12Sep2020")*lowestPrice(weekDayHotels);
        long weekendPrice = (totalDays("11Sep2020", "12Sep2020") - calcWeekDays("11Sep2020", "12Sep2020"))*lowestPrice(weekEndHotels);
        System.out.println("Hotel for weekdays: " + cheapestHotel(lowestPrice(weekDayHotels), weekDayHotels)
                + " & Hotel for weekends: " + cheapestHotel(lowestPrice(weekEndHotels), weekEndHotels)
                + "with total price = " + (weekdayPrice+weekendPrice));
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnCheapestBestRatedHotel()

    {
        addHotelToSystem("Lakewood", 110, 90,3);
        addHotelToSystem("Bridgewood",160, 50, 4);
        addHotelToSystem("Ridgewood", 220, 150, 5);
        String hName = cheapestBestRatedHotel("11Sep2020", "12Sep2020");
        Assertions.assertEquals("Lakewood", hName);
    }
}
