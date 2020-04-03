package com.wpate.four.server.repository;

import com.wpate.four.api.model.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {

    Optional<Seat> findById(long seatId);

    List<Seat> findAll();

    boolean update(Seat seat);

}
