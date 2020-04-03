package com.wpate.four.server.service;

import com.wpate.four.api.contract.SeatAvalibilityService;
import com.wpate.four.api.contract.SeatService;
import com.wpate.four.api.contract.SessionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import static com.wpate.four.server.exception.TheaterExceptionFactory.insufficientFundsException;
import static com.wpate.four.server.exception.TheaterExceptionFactory.seatAlreadyBookedException;

@Stateful
@Remote(SessionService.class)
public class SessionServiceImpl implements SessionService {

    private int balance;

    @EJB
    private SeatService seatService;

    @EJB
    private SeatAvalibilityService seatAvalibilityService;

    @Lock
    @Override
    public boolean buyTicketForSeat(long id) {
        int price = seatService.getSeatPrice(id);

        validateTransaction(seatAvalibilityService.isAvailable(id), price);

        if(seatService.buyTicket(id)){
            balance -=  price;
            return true;
        }
        throw new RuntimeException("Something went wrong during transaction");
    }

    @Lock
    @Override
    public int getBalance() {
        return balance;
    }

    private void validateTransaction(boolean isAvailable, int price){
        if(!isAvailable) throw seatAlreadyBookedException();
        if (price > getBalance()) throw insufficientFundsException();
    }

    @PostConstruct
    void initBalance(){
        balance = 200;
    }
}
