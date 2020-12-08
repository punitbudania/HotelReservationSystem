package com.hotelmanagement;

import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HotelReservationSystem
{
    public static HashMap<String, Hotel> availabeHotels = new HashMap<String, Hotel>();
    public static HashMap<String, Integer> weekDayHotels = new HashMap<String, Integer>();
    public static HashMap<String, Integer> weekEndHotels = new HashMap<String, Integer>();
    public static HashMap<String, Integer> rateofHotels = new HashMap<String, Integer>();

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcime to Hotel Reservation System");
        addHotelToSystem("Lakewood", 110, 90, 3);
        addHotelToSystem("Bridgewood",160, 50,4);
        addHotelToSystem("Ridgewood", 220, 150, 5);
        System.out.println("Enter Start Date (in ddMMMyyyy format):");
        String startDate = sc.nextLine();
        System.out.println("Enter End Date (in ddMMMyyyy format):");
        String endDate = sc.nextLine();
        long days = totalDays(startDate, endDate);
        Integer price = lowestPrice();
        cheapestHotel(price);
    }


    public static void addHotelToSystem(String hotelName, Integer regularWeekdayRate, Integer regularWeekendRate, Integer hotelRating)
    {
        Hotel hotel = new Hotel(hotelName, regularWeekdayRate, regularWeekendRate, hotelRating);
        availabeHotels.put(hotelName, hotel);
        weekDayHotels.put(hotelName, regularWeekdayRate);
        weekEndHotels.put(hotelName, regularWeekendRate);
        rateofHotels.put(hotelName, hotelRating);
    }


    public static long totalDays(String startDate, String endDate)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate entryDate = LocalDate.parse(startDate, format);
        LocalDate exitDate = LocalDate.parse(endDate, format);
        long diff = ChronoUnit.DAYS.between(entryDate, exitDate);
        return (diff+1);
    }


    public static Integer lowestPrice()
    {
        Optional<Integer> min = weekDayHotels.entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getValue);
        Integer Price = min.get();
        return Price;
    }


    public static String cheapestHotel(Integer price)
    {
        List<String> hotelname;
        hotelname = weekDayHotels.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(price))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return hotelname.get(0);
    }

}
