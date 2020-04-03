package com.wpate.four.server.exception;

import com.wpate.four.api.exception.TheaterException;

import java.util.function.Supplier;

public class TheaterExceptionFactory {

    public static Supplier<TheaterException> noSeatWithIdExceptionSupplier(long id){
        return () -> new TheaterException("Could not find seat with id: " + id);
    }

    public static TheaterException seatAlreadyBookedException(){
        return new TheaterException("Seat is already booked");
    }

    public static TheaterException insufficientFundsException(){
        return new TheaterException("Insufficient founds to make transaction");
    }

}
