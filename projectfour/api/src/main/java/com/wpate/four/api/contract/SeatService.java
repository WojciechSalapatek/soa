package com.wpate.four.api.contract;

import com.wpate.four.api.model.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> getSeats();

    int getSeatPrice(long seatId);

    boolean buyTicket(long seatId);
}
