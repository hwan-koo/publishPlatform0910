package lanedu.domain;

import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class StoryGenerated extends AbstractEvent {

    private Long id;
    private String story;
}
