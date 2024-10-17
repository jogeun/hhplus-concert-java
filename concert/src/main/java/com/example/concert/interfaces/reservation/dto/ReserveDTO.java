package com.example.concert.interfaces.reservation.dto;

import com.example.concert.domain.token.entity.WaitStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class ReserveDTO {


    public record CreateReserve(
            @NotEmpty @Valid
            @Schema(name = "userId", description ="유저 아이디", example = "1")
            Long userId,
            @NotEmpty @Valid
            @Schema(name = "concertId", description ="콘서트 아이디", example = "1")
            Long concertId
    ){
    }


    public record ReserveResponse(
            @Schema(name = "userId", description = "유저 이름", example = "아무개")
            String userName,
            @Schema(name = "concertName", description = "콘서트 이름", example = "아이유")
            String concertName,

            @Schema(name = "concertTime", description = "콘서트 시간", example = "20241018 - 08:00")
            String concertTime



    ) {
    }

}
