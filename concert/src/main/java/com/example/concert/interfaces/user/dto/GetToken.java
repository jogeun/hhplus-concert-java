package com.example.concert.interfaces.user.dto;

public class GetToken {
    public record Request(String userId){

    }

    public record Response(String totken){

    }
}
