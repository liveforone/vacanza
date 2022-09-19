package vacanza.vacanza.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vacanza.vacanza.domain.Notice;
import vacanza.vacanza.dto.NoticeDto;
import vacanza.vacanza.service.NoticeService;

import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public ResponseEntity<List<Notice>> noticeHome() {
        List<Notice> noticeList = noticeService.getNoticeList();

        return new ResponseEntity<>(noticeList, HttpStatus.OK);
    }


    @GetMapping("/notice/post")
    public ResponseEntity<?> postPage() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/notice/post")
    public ResponseEntity<?> post(
            @RequestBody NoticeDto noticeDto,
            Principal principal
    ) {
        String user = principal.getName();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/notice"));

        noticeService.saveNotice(noticeDto, user);
        log.info("Notice Posting Success!!");

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/notice/{id}")
    public ResponseEntity<Map<String, Object>> detail(
            @PathVariable("id") Long id,
            Principal principal
    ) {
        Map<String, Object> map = new HashMap<>();
        String user = principal.getName();
        Notice notice = noticeService.getOne(id);

        map.put("user", user);
        map.put("notice", notice);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/notice/delete/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/notice"));

        noticeService.deleteOne(id);
        log.info("Delete Notice Success!!");

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}
