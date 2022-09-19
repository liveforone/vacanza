package vacanza.vacanza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vacanza.vacanza.domain.Apply;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    Apply findOneById(Long id);
}
