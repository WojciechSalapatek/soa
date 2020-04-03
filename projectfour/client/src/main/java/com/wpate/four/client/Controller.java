package com.wpate.four.client;

import com.wpate.four.api.contract.SeatAvalibilityService;
import com.wpate.four.api.contract.SeatService;
import com.wpate.four.api.contract.SessionService;
import com.wpate.four.api.model.Seat;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Slf4j
@SessionScoped
@Named("Controller")
public class Controller implements Serializable {

    @EJB(lookup = "java:global/serverimpl/SeatServiceImpl")
    private SeatService seatService;

    @EJB(lookup = "java:global/serverimpl/SessionServiceImpl")
    private SessionService sessionService;

    @EJB(lookup = "java:global/serverimpl/SeatAvalibilityServiceImpl")
    private SeatAvalibilityService seatAvalibilityService;


    @Getter
    private String msg;
    @Getter
    private boolean hasError;

    public List<Seat> getSeats() {
        return seatService.getSeats();
    }

    public int getBalance(){
        return sessionService.getBalance();
    }

    public boolean canBuy(long id){
        return seatService.getSeatPrice(id) <= getBalance() && seatAvalibilityService.isAvailable(id);
    }

    public void buySeat(long id){
        try {
            sessionService.buyTicketForSeat(id);
            msg = "Successfully booked a seat";
        } catch (Exception e) {
            log.warn("{}", e);
            msg = e.getMessage();
            hasError = true;
        }
    }

    public void clear(){
        msg = null;
        hasError = false;

    }

}
