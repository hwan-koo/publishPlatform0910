package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Signuped extends AbstractEvent {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String introduction;
    private String password;

    public Signuped(Member aggregate) {
        super(aggregate);
    }

    public Signuped() {
        super();
    }
}
//>>> DDD / Domain Event
