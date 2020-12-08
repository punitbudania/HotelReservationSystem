package com.hotelmanagement;

import java.text.ParseException;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


public class HotelReservationSystem
{
    public static HashMap<String, Hotel> availabeHotels = new HashMap<String, Hotel>();
    public static HashMap<String, Integer> weekDayHotels = new HashMap<String, Integer>();
    public static HashMap<String, Integer> weekEndHotels = new HashMap<String, Integer>();
    public static HashMap<Integer, String> rateofHotels = new HashMap<Integer, String>();

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcime to Hotel Reservation System");
        addHotelToSystem("Lakewood", 110, 90, 3, 80, 80);
        addHotelToSystem("Bridgewood",160, 50,4, 110, 50);
        addHotelToSystem("Ridgewood", 220, 150, 5, 100, 40);
        System.out.println("Enter Start Date (in ddMMMyyyy format):");
        String startDate = sc.nextLine();
        System.out.println("Enter End Date (in ddMMMyyyy format):");
        String endDate = sc.nextLine();
        try
        {
            long totaldays = totalDays(startDate, endDate);
        }
        catch (ParseException e)
        {
            System.out.println("Invalid date format! Correct format: ddMMMyyyy");
        }
        //Integer weekdayPrice = lowestPrice(weekDayHotels);
        //String weekdayHotel = cheapestHotel(weekdayPrice, weekDayHotels);
        //Integer weekendPrice = lowestPrice(weekEndHotels);
        //String weekendHotel = cheapestHotel(weekendPrice, weekEndHotels);
        //long weekdays = calcWeekDays(startDate, endDate);
        //long weekends = totaldays - weekdays;
    }


    public static void addHotelToSystem(String hotelName, Integer regularWeekdayRate, Integer regularWeekendRate, Integer hotelRating, Integer rewardWeekdayRate, Integer rewardWeekendRate)
    {
        Hotel hotel = new Hotel(hotelName, regularWeekdayRate, regularWeekendRate, hotelRating, rewardWeekdayRate, rewardWeekendRate);
        availabeHotels.put(hotelName, hotel);
        weekDayHotels.put(hotelName, regularWeekdayRate);
        weekEndHotels.put(hotelName, regularWeekendRate);
        rateofHotels.put(hotelRating, hotelName);
    }


    public static long totalDays(String startDate, String endDate)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate entryDate = LocalDate.parse(startDate, format);
        LocalDate exitDate = LocalDate.parse(endDate, format);
        long diff = ChronoUnit.DAYS.between(entryDate, exitDate);
        return (diff+1);
    }


    public static Integer lowestPrice(HashMap<String, Integer> hotelType)
    {
        Optional<Integer> min = hotelType.entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getValue);
        Integer Price = min.get();
        return Price;
    }


    public static String cheapestHotel(Integer price, HashMap<String, Integer> hotelType)
    {
        List<String> hotelname;
        hotelname = hotelType.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(price))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return hotelname.get(0);
    }

    public static long calcWeekDays(String startDate, String endDate)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate entryDate = LocalDate.parse(startDate, format);
        LocalDate exitDate = LocalDate.parse(endDate, format);
        final int startW = entryDate.getDayOfWeek().getValue();
        final int endW = exitDate.getDayOfWeek().getValue();

        final long diff = ChronoUnit.DAYS.between(entryDate, exitDate);
        long days = diff + 1;
        long result = days - 2*(days/7);

        if (days % 7 != 0)
        {
            if (startW == 7 || endW == 6)
            {
                result -= 1;
            }
            else if (endW == 7 || startW == 6)
            {
                result -= 2;
            }
            else if (endW < startW)
            {
                result -= 2;
            }
        }
        return result;
    }

    public static String cheapestBestRatedHotel(String startDate, String endDate)
    {
        long totaldays = totalDays(startDate, endDate);
        long weekdays = calcWeekDays(startDate, endDate);
        long weekends = totaldays - weekdays;
        HashMap<Integer, Long> a = new HashMap<Integer, Long>();
        a.put(3, (availabeHotels.get("Lakewood").getRewardWeekdayRate()*weekdays)+(availabeHotels.get("Lakewood").getRewardWeekendRate()*weekends));
        a.put(4, (availabeHotels.get("Bridgewood").getRewardWeekdayRate()*weekdays)+(availabeHotels.get("Bridgewood").getRewardWeekendRate()*weekends));
        a.put(5, (availabeHotels.get("Ridgewood").getRewardWeekdayRate()*weekdays)+(availabeHotels.get("Ridgewood").getRewardWeekendRate()*weekends));
        List<Integer> b;
        b = a.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return (rateofHotels.get(b.get(0)));
    }

}
