package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class StoryGenerated extends AbstractEvent {

    private Long id;
    private String story;

    public StoryGenerated(GenStory aggregate) {
        super(aggregate);
    }

    public StoryGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
