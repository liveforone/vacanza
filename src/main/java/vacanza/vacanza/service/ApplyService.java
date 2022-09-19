package vacanza.vacanza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vacanza.vacanza.domain.Apply;
import vacanza.vacanza.dto.ApplyDto;
import vacanza.vacanza.repository.ApplyRepository;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;

    @Transactional(readOnly = true)
    public Page<Apply> getApplyList(Pageable pageable) {
        return applyRepository.findAll(pageable);
    }

    @Transactional
    public void saveApply(ApplyDto applyDto, String kind, String email) {
        applyDto.setEmail(email);
        applyDto.setKind(kind);
        applyRepository.save(applyDto.toEntity());
    }

    @Transactional(readOnly = true)
    public Apply getOne(Long id) {
        return applyRepository.findOneById(id);
    }
}
