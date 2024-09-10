package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lanedu.domain.*;
import lanedu.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PayApproved extends AbstractEvent {

    private Long id;
    private Long purchaseId;

    public PayApproved(Paymenthistory aggregate) {
        super(aggregate);
    }

    public PayApproved() {
        super();
    }
}
//>>> DDD / Domain Event
