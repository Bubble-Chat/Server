package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.Room;
import kr.hs.dgsw.bubblechat.apiServer.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/create")
    public Room createRoom(HttpServletRequest request,
                           @ModelAttribute Room room) {
        return roomService.createRoom(room);
    }

}
