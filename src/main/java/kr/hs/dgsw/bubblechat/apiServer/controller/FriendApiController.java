package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.response.ResponseError;
import kr.hs.dgsw.bubblechat.apiServer.security.BubbleChatUserDetails;
import kr.hs.dgsw.bubblechat.apiServer.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/friend")
@Slf4j
public class FriendApiController {

    @Autowired
    private FriendService friendService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public ResponseEntity<ArrayList<User>> friends(HttpServletRequest request) {

        ArrayList<User> users = new ArrayList<User>();

//        users.add(new User("hinu0622", "HINU0622@gmail.com", "이재진", "블라", "블라"));
//        users.add(new User("ksgyg2g7wy", "ksgyg2g7wy@privaterelay.appleid.com", "조근호", "이러쿵", "저러쿵"));
//        users.add(new User("wyk172899", "wyk172899@dgsw.hs.kr", "우영기", "어쩌고", "저쩌고"));

        return ResponseEntity.ok(users);
    }

    @PostMapping("/list")
    public ResponseEntity<List<User>> searchFriends(HttpServletRequest request,
                                                         @RequestParam String name) {

        List<User> friends = friendService.searchFriendByName(name);

        return ResponseEntity.ok(friends);
    }

    @GetMapping("/list/relate")
    public ResponseEntity<Object> addFriend(HttpServletRequest request, @ModelAttribute Friend friend,
                                             Authentication authentication) {
        log.info("authentication {}", authentication);

        String userEmail = ((BubbleChatUserDetails) authentication.getPrincipal()).getUser().getEmail();
        Friend related = friendService.relateTo(userEmail, friend);
        return ResponseEntity.ok(related);

    }

    @PostMapping("/list/renounce")
    public ResponseEntity<User> deleteFriend(HttpServletRequest request, @RequestParam String idx) {
        return ResponseEntity.ok(null);
    }

}
