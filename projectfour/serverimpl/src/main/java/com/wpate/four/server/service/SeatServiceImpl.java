package com.wpate.four.server.service;

import com.wpate.four.api.contract.SeatService;
import com.wpate.four.api.model.Seat;
import com.wpate.four.server.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.wpate.four.server.exception.TheaterExceptionFactory.noSeatWithIdExceptionSupplier;
import static java.util.stream.Collectors.toList;

@Slf4j
@Singleton
@Remote(SeatService.class)
public class SeatServiceImpl implements SeatService {

    @EJB
    private SeatRepository seatRepository;

    public List<Seat> getSeats() {
        return seatRepository.findAll().stream().sorted(Comparator.comparingInt(Seat::getPrice)).collect(toList());
    }

    public int getSeatPrice(long seatId) {
        return seatRepository.findById(seatId).map(Seat::getPrice).orElseThrow(noSeatWithIdExceptionSupplier(seatId));
    }

    public boolean buyTicket(long seatId) {
        Seat seatToBuyTicketFor = seatRepository.findById(seatId).orElseThrow(noSeatWithIdExceptionSupplier(seatId));
        seatToBuyTicketFor.setAvailable(false);
        return seatRepository.update(seatToBuyTicketFor);
    }

    @PostConstruct
    void initializeSeats(){
        log.info("Initializing seats");
        IntStream.rangeClosed(1, 10).forEach(
                i -> IntStream.rangeClosed(1, 10).forEach(
                        j -> seatRepository.update(Seat.of(i*10 + j, i, j, Math.round(100.f/i), true))
                )
        );
    }
}
