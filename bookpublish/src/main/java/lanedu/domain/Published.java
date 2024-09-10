package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Published extends AbstractEvent {

    private Long id;
    private String title;
    private String contents;
    private String imageUrl;
    private Integer price;
    private Long memberId;

    public Published(BookPublish aggregate) {
        super(aggregate);
    }

    public Published() {
        super();
    }
}
//>>> DDD / Domain Event
