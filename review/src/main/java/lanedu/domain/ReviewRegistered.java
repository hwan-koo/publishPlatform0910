package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReviewRegistered extends AbstractEvent {

    private Long id;
    private String content;
    private Long memberId;
    private Double rating;
    private Long bookId;

    public ReviewRegistered(BookReview aggregate) {
        super(aggregate);
    }

    public ReviewRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
