package tg.schoolapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tg.schoolapi.model.entity.SchedulingEntity;
import java.util.List;
public interface SchedulingRepository extends JpaRepository<SchedulingEntity, Long> {

    List<SchedulingEntity> findByUsuarioId(Long usuarioId);
}
