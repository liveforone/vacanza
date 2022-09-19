package vacanza.vacanza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vacanza.vacanza.domain.Notice;
import vacanza.vacanza.dto.NoticeDto;
import vacanza.vacanza.repository.NoticeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public List<Notice> getNoticeList() {
        return noticeRepository.findAll();
    }

    @Transactional
    public void saveNotice(NoticeDto noticeDto, String user) {
        noticeDto.setUser(user);
        noticeRepository.save(noticeDto.toEntity());
    }

    @Transactional(readOnly = true)
    public Notice getOne(Long id) {
        return noticeRepository.findOneById(id);
    }

    @Transactional
    public void deleteOne(Long id) {
        noticeRepository.deleteById(id);
    }
}
