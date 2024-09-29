package tg.schoolapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tg.schoolapi.model.entity.SchedulingEntity;

public interface SchedulingRepository extends JpaRepository<SchedulingEntity, Long> {
}
