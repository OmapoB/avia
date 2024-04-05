package ru.omarov.aviatest;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightTimeByCarrierCalculator {
    private final List<Ticket> tickets;

    public FlightTimeByCarrierCalculator(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Map<String, Duration> calculate() {
        Map<String, Duration> minTimeByCarrier = new HashMap<>();
        tickets.forEach(ticket -> {
            if (!ticket.getOrigin().equals("VVO") || !ticket.getDestination().equals("TLV")) return;
            Duration curDuration = Duration.between(ticket.getArrivalDateTime(), ticket.getDepartureDateTime());
            Duration minDuration = minTimeByCarrier.getOrDefault(ticket.getCarrier(), curDuration);
            minTimeByCarrier.put(ticket.getCarrier(),
                                 minDuration.compareTo(curDuration) < 0 ? curDuration : minDuration);
        });
        return minTimeByCarrier;
    }
}
