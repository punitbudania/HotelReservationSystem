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
        String res = cheapestHotel(lowestPrice());
        long result = totalDays("12Mar2020", "16Mar2020")*lowestPrice();
        Assertions.assertEquals("Lakewood", res);
        Assertions.assertEquals(550, result);
    }
}
