package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.Room;
import kr.hs.dgsw.bubblechat.apiServer.domain.UserInRoom;
import kr.hs.dgsw.bubblechat.apiServer.security.BubbleChatUserDetails;
import kr.hs.dgsw.bubblechat.apiServer.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(HttpServletRequest request,
                                     Authentication authentication,
                                     @RequestBody Room room) {
        room.setAdmin(((BubbleChatUserDetails) authentication.getPrincipal()).getUser().getEmail());
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @PostMapping("/join")
    public ResponseEntity<UserInRoom> joinRoom(HttpServletRequest request,
                         Authentication authentication,
                         @ModelAttribute UserInRoom userInRoom) {
        String email = ((BubbleChatUserDetails) authentication.getPrincipal()).getUser().getEmail();
        userInRoom.setEmail(email);
        return ResponseEntity.ok(roomService.joinRoom(userInRoom));
    }

}
