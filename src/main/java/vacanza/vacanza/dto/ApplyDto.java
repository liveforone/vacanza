package vacanza.vacanza.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import vacanza.vacanza.domain.Apply;

@Data
@NoArgsConstructor
public class ApplyDto {

    private Long id;
    private String user;
    private int startMon;
    private int startDay;
    private int endMon;
    private int endDay;
    private String depart;
    private String contact;
    private String kind;

    public Apply toEntity() {
        return Apply.builder()
                .id(id)
                .user(user)
                .startMon(startMon)
                .startDay(startDay)
                .endMon(endMon)
                .endDay(endDay)
                .depart(depart)
                .contact(contact)
                .kind(kind)
                .build();
    }
}
