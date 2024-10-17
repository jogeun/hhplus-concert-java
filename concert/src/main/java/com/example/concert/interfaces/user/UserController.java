package src.main.java.com.example.concert.interfaces.user;

import com.example.concert.interfaces.user.dto.ChargeMoney;
import com.example.concert.interfaces.user.dto.GetToken;
import com.example.concert.interfaces.user.dto.GetUserMoney;
import com.example.concert.interfaces.user.dto.GetWaitOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    /**
     * 유저 토큰 생성 API
     * @param request - 유저 아이디
     * @return - 토큰
     */
    @PostMapping("token/{userId}")
    public ResponseEntity<GetToken.Response> createUserToken(@RequestBody GetToken.Request request) {

        return ResponseEntity.ok(new GetToken.Response(UUID.randomUUID().toString()));
    }

    /**
     * 대기 순서 조회
     * @param userId - 유저 아이디
     * @return - 대기 순서
     */
    @GetMapping("order/{userId}")
    public ResponseEntity<GetWaitOrder.Response> getWaitOrder(
            @RequestHeader("token") String token,
            @PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(new GetWaitOrder.Response(12));
    }

    /**
     * 유저 잔액 조회
     * @param userId - 유저 아이디
     * @return UserDto
     */
    @GetMapping("charge/{userId}")
    public ResponseEntity<GetUserMoney.Response> getUserMoney(
            @RequestHeader("token") String token,
            @PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(new GetUserMoney.Response(500000));
    }


    /**
     * 유저 잔액 충전
     * @param request - 유저 아이디, 충전금액
     * @return - 잔액
     */
    @PostMapping("/charge")
    public ResponseEntity<ChargeMoney.Response> chargeUserMoney(
            @RequestHeader("token") String token,
            @RequestBody ChargeMoney.Request request) {
        return ResponseEntity.ok(new ChargeMoney.Response(500000));
    }

}