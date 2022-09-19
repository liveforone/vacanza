package vacanza.vacanza.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    private String user;

    private int startMon;

    private int startDay;

    private int endMon;

    private int endDay;

    private String depart;  //부서

    private String contact;  //연락처

    private String kind; //휴가 및 재택 종류

    private String email;

    @Builder
    public Apply(Long id, String user, int startMon, int startDay, int endMon, int endDay, String depart, String contact, String kind, String email) {
        this.id = id;
        this.user = user;
        this.startMon = startMon;
        this.startDay = startDay;
        this.endMon = endMon;
        this.endDay = endDay;
        this.depart = depart;
        this.contact = contact;
        this.kind = kind;
        this.email = email;
    }
}
