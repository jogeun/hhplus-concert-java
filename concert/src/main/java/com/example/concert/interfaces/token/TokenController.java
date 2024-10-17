package com.example.concert.interfaces.token;

import com.example.concert.application.token.WaitTokenService;
import com.example.concert.domain.token.entity.WaitToken;
import com.example.concert.interfaces.token.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
@Tag(name = "토큰", description = "토큰 관련 API")
public class TokenController {

    private final WaitTokenService waitTokenService;

    //토큰 발급 [접근 권한]
    @PostMapping("/access/{userId}")
    @Operation(summary = "토큰 발급[접근 권한]", description = "유저별로 ACCESS 상태인 토큰이 존재해야 서비스 이용 가능",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "토큰 생성 정보",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = TokenDTO.CreateTokenAccess.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "생성된 접근 권한 토큰",
                            content = @Content(
                                    schema = @Schema(implementation = TokenDTO.TokenAccessResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<TokenDTO.TokenAccessResponse> makeTokenAceess(
            @RequestBody @Valid TokenDTO.CreateTokenAccess token
    ){
        WaitToken waitToken = waitTokenService.createTokenByStatusAccess(token.userId());
        return ResponseEntity.ok(new TokenDTO.TokenAccessResponse(waitToken.getUserId(),waitToken.getWaitStatus(),waitToken.getToken()));
    }

    //토큰 발급 [예약 대기 권한]
    @PostMapping("/waiting")
    @Operation(summary = "토큰 발급[예약 대기 권한]", description = "유저, 콘서트별로 WAITING 상태인 토큰 발급하여 예약 대기",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "토큰 생성 정보",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = TokenDTO.CreateTokenWaiting.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "생성된 예약 대기 권한 토큰",
                            content = @Content(
                                    schema = @Schema(implementation = TokenDTO.TokenWaitingResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<TokenDTO.TokenWaitingResponse> makeTokenWaiting(
            @RequestBody @Valid TokenDTO.CreateTokenWaiting token
    ){
        WaitToken waitToken = waitTokenService.createTokenByStatusWait(token.userId(),token.concertId());
        return ResponseEntity.ok(new TokenDTO.TokenWaitingResponse(waitToken.getUserId(),waitToken.getWaitStatus(),waitToken.getToken(),waitToken.getExpireTime()));

    }

    //대기순번 조회
    @GetMapping("/my/order/{userId}/{concertId}")
    @Operation(summary = "대기순번 조회", description = "유저, 콘서트별로 현재 나의 대기순번 조회 (PROCCESS,EXIPRED,ACCESS 제외 카운트)",
            parameters={
                @Parameter(name = "userId", description = "사용자 id 키값", required = true, in = ParameterIn.PATH),
                @Parameter(name = "concertId", description = "콘서트 id 키값", required = true, in = ParameterIn.PATH)
            },
            responses = @ApiResponse(responseCode = "200", description = "순번 -> 추후 response 값 수정 예정"
            )
    )
    public ResponseEntity<Integer> searchMyOrder(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "concertId") Long concertId

    ){
        return ResponseEntity.ok(waitTokenService.findTokenOrder(userId,concertId));
    }
}
