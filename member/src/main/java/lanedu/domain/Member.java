package lanedu.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lanedu.MemberApplication;
import lanedu.domain.Signuped;
import lombok.Data;

@Entity
@Table(name = "Member_table")
@Data
//<<< DDD / Aggregate Root
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String introduction;

    private String password;

    @PrePersist
    public void onPrePersist() {
        Signuped signuped = new Signuped(this);
        signuped.publishAfterCommit();
    }

    public static MemberRepository repository() {
        MemberRepository memberRepository = MemberApplication.applicationContext.getBean(
            MemberRepository.class
        );
        return memberRepository;
    }
}
//>>> DDD / Aggregate Root
