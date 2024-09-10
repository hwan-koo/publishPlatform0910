package lanedu.domain;

import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class PurchaseRefunded extends AbstractEvent {

    private Long id;
    private Integer price;
    private String status;
    private Long bookId;
    private String memberId;
}
