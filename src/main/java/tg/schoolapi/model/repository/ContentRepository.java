package tg.schoolapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tg.schoolapi.model.entity.ContentEntity;

public interface ContentRepository extends JpaRepository<ContentEntity, Long> {
}
