package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;
import kr.hs.dgsw.bubblechat.apiServer.security.BubbleChatUserDetails;
import kr.hs.dgsw.bubblechat.apiServer.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/friend")
@Slf4j
public class FriendApiController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/search")
    public ResponseEntity<Users> searchFriends(HttpServletRequest request,
                                               @RequestParam String name) {

        Users foundFriends = friendService.findFriend(name);

        return ResponseEntity.ok(foundFriends);
    }

    @PostMapping("/relate")
    public ResponseEntity<Object> addFriend(HttpServletRequest request, @RequestBody Friend friend,
                                             Authentication authentication) {
        log.info("authentication {}", authentication);

        String userEmail = ((BubbleChatUserDetails) authentication.getPrincipal()).getUser().getEmail();
        Friend related = friendService.relateTo(userEmail, friend);
        return ResponseEntity.ok(related);

    }

}
