package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ImageGenerated extends AbstractEvent {

    private Long id;
    private String imageUrl;

    public ImageGenerated(GenImage aggregate) {
        super(aggregate);
    }

    public ImageGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
