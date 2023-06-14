package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(HttpServletRequest request,
                                 @ModelAttribute User user) {
        LoggerFactory.getLogger(getClass()).info("USER : {}", user);

        // TODO

        return ResponseEntity.ok(user);
    }

}
