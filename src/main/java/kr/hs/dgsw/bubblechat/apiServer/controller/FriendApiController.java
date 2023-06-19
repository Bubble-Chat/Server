package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.Relation;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/friend")
public class FriendApiController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/list")
    public ResponseEntity<ArrayList<User>> friends(HttpServletRequest request) {

        ArrayList<User> users = new ArrayList<User>();

        users.add(new User("hinu0622", "HINU0622@gmail.com", "이재진", "블라", "블라"));
        users.add(new User("ksgyg2g7wy", "ksgyg2g7wy@privaterelay.appleid.com", "조근호", "이러쿵", "저러쿵"));
        users.add(new User("wyk172899", "wyk172899@dgsw.hs.kr", "우영기", "어쩌고", "저쩌고"));

        return ResponseEntity.ok(users);
    }

    @PostMapping("/list")
    public ResponseEntity<ArrayList<User>> searchFriends(HttpServletRequest request,
                                                         @ModelAttribute Relation relation) {

        ArrayList<User> friends = friendService.searchFriend(relation);

        return ResponseEntity.ok(friends);
    }

    @PostMapping("/list/relate")
    public ResponseEntity<Relation> addFriend(HttpServletRequest request, @ModelAttribute Relation relation) {
        Relation rel = friendService.relateTo(relation);
        return ResponseEntity.ok(rel);
    }

    @PostMapping("/list/renounce")
    public ResponseEntity<User> deleteFriend(HttpServletRequest request, @RequestParam String idx) {
        return ResponseEntity.ok(new User());
    }

}
