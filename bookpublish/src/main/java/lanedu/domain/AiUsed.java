package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AiUsed extends AbstractEvent {

    private Long id;
    private String title;
    private String contents;
    private Long memberId;

    public AiUsed(BookPublish aggregate) {
        super(aggregate);
    }

    public AiUsed() {
        super();
    }
}
//>>> DDD / Domain Event
