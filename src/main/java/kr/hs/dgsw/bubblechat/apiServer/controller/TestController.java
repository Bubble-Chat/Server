package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Users> getUsers(HttpServletRequest request,
                                          HttpServletResponse response) {

        Users users = userService.getUsers();

        return ResponseEntity.ok(users);

    }

}
