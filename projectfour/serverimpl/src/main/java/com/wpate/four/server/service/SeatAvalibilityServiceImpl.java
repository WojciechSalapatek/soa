package com.wpate.four.server.service;

import com.wpate.four.api.contract.SeatAvalibilityService;
import com.wpate.four.api.model.Seat;
import com.wpate.four.server.repository.SeatRepository;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import static com.wpate.four.server.exception.TheaterExceptionFactory.noSeatWithIdExceptionSupplier;

@Stateless
@Remote(SeatAvalibilityService.class)
public class SeatAvalibilityServiceImpl implements SeatAvalibilityService {

    @EJB
    private SeatRepository seatRepository;

    @Lock
    @Override
    public boolean isAvailable(long seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(noSeatWithIdExceptionSupplier(seatId));
        return seat.isAvailable();
    }
}
