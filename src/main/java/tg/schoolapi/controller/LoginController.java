package tg.schoolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tg.schoolapi.model.dto.ContentDTO;
import tg.schoolapi.model.dto.LoginDTO;
import tg.schoolapi.service.LoginService;

@RestController
@RequestMapping({"/login"})
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping
    public boolean login(@RequestBody LoginDTO loginDTO) {
        return this.loginService.login(loginDTO);
    }
}
