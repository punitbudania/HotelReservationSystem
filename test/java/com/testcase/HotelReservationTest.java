package com.testcase;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.hotelmanagement.HotelReservationSystem.*;


public class HotelReservationTest
{
    @Test
    public void testHotelDetails()
    {
        addHotelToSystem("Lakewood", 110, 90, 3, 80, 80);
        addHotelToSystem("Bridgewood",160, 50,4, 110, 50);
        addHotelToSystem("Ridgewood", 220, 150, 5, 100, 40);
        Integer weekdayResult = weekDayHotels.get("Bridgewood");
        Integer weekendResult = weekEndHotels.get("Ridgewood");
        Assertions.assertEquals(160, weekdayResult);
        Assertions.assertEquals(150, weekendResult);
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnCheapestHotel()
    {
        addHotelToSystem("Lakewood", 110, 90, 3, 80, 80);
        addHotelToSystem("Bridgewood",160, 50,4, 110, 50);
        addHotelToSystem("Ridgewood", 220, 150, 5, 100, 40);
        String res = cheapestHotel(lowestPrice(weekDayHotels), weekDayHotels);
        long result = totalDays("12Mar2020", "16Mar2020")*lowestPrice(weekDayHotels);
        Assertions.assertEquals("Lakewood", res);
        Assertions.assertEquals(550, result);
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnCheapestHotelforWeekendAndWeekday()
    {
        addHotelToSystem("Lakewood", 110, 90, 3, 80, 80);
        addHotelToSystem("Bridgewood",160, 50,4, 110, 50);
        addHotelToSystem("Ridgewood", 220, 150, 5, 100, 40);
        long weekdayPrice = calcWeekDays("11Sep2020", "12Sep2020")*lowestPrice(weekDayHotels);
        long weekendPrice = (totalDays("11Sep2020", "12Sep2020") - calcWeekDays("11Sep2020", "12Sep2020"))*lowestPrice(weekEndHotels);
        System.out.println("Hotel for weekdays: " + cheapestHotel(lowestPrice(weekDayHotels), weekDayHotels)
                + " & Hotel for weekends: " + cheapestHotel(lowestPrice(weekEndHotels), weekEndHotels)
                + "with total price = " + (weekdayPrice+weekendPrice));
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnCheapestBestRatedHotel()
    {
        addHotelToSystem("Lakewood", 110, 90, 3, 80, 80);
        addHotelToSystem("Bridgewood",160, 50,4, 110, 50);
        addHotelToSystem("Ridgewood", 220, 150, 5, 100, 40);
        String hName = cheapestBestRatedHotel("11Sep2020", "12Sep2020");
        Assertions.assertEquals("Lakewood", hName);
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnHotelName()
    {
        addHotelToSystem("Lakewood", 110, 90, 3, 80, 80);
        addHotelToSystem("Bridgewood",160, 50,4, 110, 50);
        addHotelToSystem("Ridgewood", 220, 150, 5, 100, 40);
        List<String> a;
        a = rateofHotels.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        String hName = a.get(2);
        long totaldays = totalDays("11Sep2020", "12Sep2020");
        long weekdays = calcWeekDays("11Sep2020", "12Sep2020");
        long weekends = totaldays - weekdays;
        Long tPrice = (availabeHotels.get(hName).getRegularWeekdayRate()*weekdays)+(availabeHotels.get(hName).getRegularWeekendRate()*weekends);
        System.out.println(hName + " & Total Rate = " + tPrice);
    }

    @Test
    public void givenDateRange_SearchHotel_ReturnCheapestBestRatedHotelforRewardCust()
    {
        addHotelToSystem("Lakewood", 110, 90, 3, 80, 80);
        addHotelToSystem("Bridgewood",160, 50,4, 110, 50);
        addHotelToSystem("Ridgewood", 220, 150, 5, 100, 40);
        String hName = cheapestBestRatedHotel("11Sep2020", "12Sep2020");
        long totaldays = totalDays("11Sep2020", "12Sep2020");
        long weekdays = calcWeekDays("11Sep2020", "12Sep2020");
        long weekends = totaldays - weekdays;
        Integer rating = availabeHotels.get(hName).gethotelRating();
        Long price = (availabeHotels.get(hName).getRewardWeekdayRate()*weekdays)+(availabeHotels.get(hName).getRewardWeekendRate()*weekends);
        System.out.println(hName + ", Rating: " + rating + " & Total Rate: " + price );
        Assertions.assertEquals("Ridgewood", hName);
    }
}
