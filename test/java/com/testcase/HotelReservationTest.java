package com.testcase;



import static com.hotelmanagement.HotelReservationSystem.addHotelToSystem;
import static com.hotelmanagement.HotelReservationSystem.hotels;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class HotelReservationTest
{

    @Test
    public void testHotelDetails()
    {
        addHotelToSystem("Lakewood", 110);
        addHotelToSystem("Bridgewood",160);
        addHotelToSystem("Ridgewood", 220);
        Integer ressult = hotels.get("Bridgewood");
        Assertions.assertEquals(160, ressult);
    }


}
