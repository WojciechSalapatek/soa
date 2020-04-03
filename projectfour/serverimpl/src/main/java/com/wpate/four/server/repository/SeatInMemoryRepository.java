package com.wpate.four.server.repository;

import com.wpate.four.api.model.Seat;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Singleton
public class SeatInMemoryRepository implements SeatRepository{

    private static final List<Seat> SEATS = new ArrayList<>();


    @Override
    public Optional<Seat> findById(long seatId) {
        return SEATS.stream().filter(s -> s.getId() == seatId).findFirst();
    }

    @Override
    public boolean update(Seat seat) {
        Seat seatToUpdate = SEATS.stream().filter(s -> seat.getId() == s.getId()).findFirst().orElse(null);
        if(isNull(seatToUpdate)){
            SEATS.add(seat);
            return true;
        }

        int index = SEATS.indexOf(seatToUpdate);
        SEATS.set(index, seat);
        return true;
    }

    @Override
    public List<Seat> findAll(){
        return SEATS;
    }
}
