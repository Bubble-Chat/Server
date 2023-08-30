package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw.bubblechat.apiServer.domain.AuthUser;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/signin")
    public ResponseEntity<AuthUser> getUserData(HttpServletRequest request,
                                            @RequestParam(value = "provider") String provider,
                                            @RequestParam(value = "token") String token) {

        // Provider에게 token을 이용해서 이메일을 얻는다.

        // 데이터베이스에 이메일이 등록되어 있다면 사용자 정보와 JWT을 만들어서 보내준다.
        // 없으면 error 메시지를 보낸다.

        AuthUser authUser = userService.getUserByToken(provider, token);

        return ResponseEntity.ok(authUser);

    }

}
