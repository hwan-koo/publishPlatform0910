package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PurchaseRequested extends AbstractEvent {

    private Long id;
    private Integer price;
    private String status;
    private Long bookId;
    private String memberId;

    public PurchaseRequested(BookPurchase aggregate) {
        super(aggregate);
    }

    public PurchaseRequested() {
        super();
    }
}
//>>> DDD / Domain Event
