package vacanza.vacanza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vacanza.vacanza.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Notice findOneById(Long id);
}
