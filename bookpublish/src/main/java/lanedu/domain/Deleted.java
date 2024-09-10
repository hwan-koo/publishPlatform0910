package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Deleted extends AbstractEvent {

    private Long id;

    public Deleted(BookPublish aggregate) {
        super(aggregate);
    }

    public Deleted() {
        super();
    }
}
//>>> DDD / Domain Event
