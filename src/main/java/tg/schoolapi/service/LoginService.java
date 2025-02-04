package tg.schoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.schoolapi.model.dto.LoginDTO;
import tg.schoolapi.model.entity.UserEntity;
import tg.schoolapi.model.repository.UserRepository;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    public LoginService() {
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<UserEntity> user = this.userRepository.findByCpf(loginDTO.getCpf());
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            System.out.println(userEntity);
            return true;
        }
        return false;
    }
}
