package kr.hs.dgsw.bubblechat.apiServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lab")
@RequiredArgsConstructor
@Slf4j
public class UserTestController {

    @GetMapping("/{page}")
    public String index(HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @PathVariable("page") String page) {
        return "/lab/" + page;
    }

}
