package tg.schoolapi.model.repository;
import tg.schoolapi.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNome(String nome);
    Optional<UserEntity> findByCpf(String cpf);

}