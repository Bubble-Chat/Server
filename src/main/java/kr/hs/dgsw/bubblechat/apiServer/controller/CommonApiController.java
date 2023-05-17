package kr.hs.dgsw.bubblechat.apiServer.controller;

import kr.hs.dgsw.bubblechat.apiServer.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonApiController {

    @GetMapping("api/common/is-member")
    public Result isRegistered(@RequestParam(value = "email") String email){
        System.out.println(email);
        Result result = new Result();
        if(email.endsWith("gmail.com")){
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return result;
    }
}
