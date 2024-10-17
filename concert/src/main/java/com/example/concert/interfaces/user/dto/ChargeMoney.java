package com.example.concert.interfaces.user.dto;

public class ChargeMoney {

    public record Request(String userId, int money){

    }

    public record Response(int money){

    }

}
