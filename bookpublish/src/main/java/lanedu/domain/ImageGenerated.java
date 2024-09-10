package lanedu.domain;

import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class ImageGenerated extends AbstractEvent {

    private Long id;
    private String imageUrl;
}
