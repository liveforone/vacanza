package vacanza.vacanza.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import vacanza.vacanza.domain.Notice;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeDto {

    private Long id;
    private String title;
    private String content;
    private String user;
    private LocalDateTime createdDate;

    public Notice toEntity() {
        return Notice.builder()
                .id(id)
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
