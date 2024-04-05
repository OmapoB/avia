package ru.omarov.aviatest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(Main.class.getResourceAsStream("/tickets.json"),
                                                                 StandardCharsets.UTF_8));
        Map<String, List<Ticket>> fromJson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
                .fromJson(reader, new TypeToken<Map<String, List<Ticket>>>() {
                }.getType());
        reader.close();
        List<Ticket> tickets = fromJson.get("tickets");

        FlightTimeByCarrierCalculator calculator = new FlightTimeByCarrierCalculator(tickets);
        calculator.calculate().forEach((key, value) -> System.out.println(key + ": " + value));

        List<BigDecimal> prices = tickets.stream()
                .mapToDouble(Ticket::getPrice)
                .mapToObj(BigDecimal::valueOf)
                .toList();
        System.out.println(new PriceDifferenceCalculator(prices).calculate());
    }
}