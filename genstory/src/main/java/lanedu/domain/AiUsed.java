package lanedu.domain;

import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class AiUsed extends AbstractEvent {

    private Long id;
    private String title;
    private String contents;
    private Long memberId;
}
