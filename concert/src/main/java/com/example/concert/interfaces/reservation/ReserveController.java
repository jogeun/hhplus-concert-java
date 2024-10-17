package com.example.concert.interfaces.reservation;

import com.example.concert.application.reservation.ReservationService;
import com.example.concert.application.token.WaitTokenService;
import com.example.concert.domain.reservation.entity.Reservation;
import com.example.concert.domain.token.entity.WaitToken;
import com.example.concert.interfaces.reservation.dto.ReserveDTO;
import com.example.concert.interfaces.token.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reserve")
@Tag(name = "예약", description = "예약 관련 API")
public class ReserveController {
    private final ReservationService reservationService;

    //예약
    @PostMapping("/access/{userId}")
    @Operation(summary = "예약 신청", description = "유저별로 ACCESS 상태인 토큰이 존재해야 서비스 이용 가능",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "예약 생성 정보",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ReserveDTO.CreateReserve.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "생성된 예약 내역",
                            content = @Content(
                                    schema = @Schema(implementation = ReserveDTO.ReserveResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<ReserveDTO.ReserveResponse> makeReservation(
            @RequestBody @Valid ReserveDTO.CreateReserve reserve
    ){
        //원래는 파사드 호출
        Reservation result = reservationService.createReservation(reserve.concertId());
        return ResponseEntity.ok(new ReserveDTO.ReserveResponse("아무개","아이유","20241018 - 08:00"));
    }
}
