package ru.omarov.aviatest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String carrier;
    private int stops;
    private double price;
    private final transient DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.MM.yyH:mm");

    public Ticket() {
    }

    public String getOrigin() {
        return origin;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public LocalDateTime getDepartureDateTime() {
        return LocalDateTime.parse(departureDate + departureTime, dateTimeFormatter);
    }

    public LocalDateTime getArrivalDateTime() {
        return LocalDateTime.parse(arrivalDate + arrivalTime, dateTimeFormatter);
    }

    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public double getPrice() {
        return price;
    }
}
