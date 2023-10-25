package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.AuthUser;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;
import kr.hs.dgsw.bubblechat.apiServer.security.BubbleChatUserDetails;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<AuthUser> getUserData(HttpServletRequest request,
                                                @RequestBody User user) {

        // Provider에게 token을 이용해서 이메일을 얻는다.

        // 데이터베이스에 이메일이 등록되어 있다면 사용자 정보와 JWT을 만들어서 보내준다.
        // 없으면 error 메시지를 보낸다.

        log.info("User {}", user);

        AuthUser authUser = userService.signin(user);

        return ResponseEntity.ok(authUser);
    }

    @RequestMapping("/search")
    public ResponseEntity<Users> searchFriends(HttpServletRequest request,
                                               @RequestParam String name) {

        log.info("/search {}", name);
        Users foundFriends = userService.searchUser(name);
        log.info("/search result {}", foundFriends);

        return ResponseEntity.ok(foundFriends);
    }

    @GetMapping("/info")
    public ResponseEntity<User> userInfo(Authentication authentication) {

        String email = ((BubbleChatUserDetails) authentication.getPrincipal()).getUser().getEmail();

        User found = userService.getByEmail(email);

        return ResponseEntity.ok(found);

    }

    @PatchMapping("/info")
    public ResponseEntity<User> modifyInfo(Authentication authentication,
                                           @RequestBody User user) {

        String email = ((BubbleChatUserDetails) authentication.getPrincipal()).getUser().getEmail();

        User modified = userService.changeProfile(email, user);

        return ResponseEntity.ok(modified);

    }

}
