package tg.schoolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import tg.schoolapi.model.dto.LoginDTO;
import tg.schoolapi.service.LoginService;
import java.util.Map;

@RestController
@RequestMapping({"/login"})
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> result = loginService.login(loginDTO);

        if (result != null) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "CPF ou senha inválidos"));
    }
}
