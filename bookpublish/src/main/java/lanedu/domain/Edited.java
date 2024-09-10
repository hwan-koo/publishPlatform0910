package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Edited extends AbstractEvent {

    private Long id;
    private String title;
    private String contents;
    private String imageUrl;
    private Integer price;
    private Long memberId;

    public Edited(BookPublish aggregate) {
        super(aggregate);
    }

    public Edited() {
        super();
    }
}
//>>> DDD / Domain Event
