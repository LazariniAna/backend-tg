package tg.schoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tg.schoolapi.model.dto.LoginDTO;
import tg.schoolapi.model.entity.UserEntity;
import tg.schoolapi.model.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import tg.schoolapi.config.JwtUtil;
import java.util.Map;
import java.util.HashMap;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginService() {
    }

    public Map<String, Object> login(LoginDTO loginDTO) {
        Optional<UserEntity> user = userRepository.findByCpf(loginDTO.getCpf());

        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            if (passwordEncoder.matches(loginDTO.getSenha(), userEntity.getSenha())) {
                String token = jwtUtil.generateToken(userEntity.getCpf());

                userEntity.setSenha(null);
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("user", userEntity);
                return response;
            }
        }

        return null;
    }
}
