package vacanza.vacanza.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vacanza.vacanza.domain.Apply;
import vacanza.vacanza.dto.ApplyDto;
import vacanza.vacanza.service.ApplyService;

import java.net.URI;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApplyController {

    private final ApplyService applyService;

    @GetMapping("/apply/home")
    public ResponseEntity<Page<Apply>> home(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Apply> applyList = applyService.getApplyList(pageable);

        return new ResponseEntity<>(applyList, HttpStatus.OK);
    }

    @GetMapping("/apply/post")
    public ResponseEntity<?> postPage() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/apply/post")
    public ResponseEntity<?> post(
            @RequestPart ApplyDto applyDto,
            @RequestPart("kind") String kind,
            Principal principal
    ) {
        String email = principal.getName();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/apply/home"));
        // 리다이렉트 되진 않고, 헤더는 넘어간다. 프론트에서 헤더 받아서 리다이렉트 처리.

        applyService.saveApply(applyDto, kind, email);
        log.info("Apply Save Success!!");

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/apply/{id}")
    public ResponseEntity<Apply> detail(@PathVariable("id") Long id) {
        Apply apply = applyService.getOne(id);

        return new ResponseEntity<>(apply, HttpStatus.OK);
    }
}
