package lanedu.infra;

import lanedu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class PaymenthistoryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Paymenthistory>> {

    @Override
    public EntityModel<Paymenthistory> process(
        EntityModel<Paymenthistory> model
    ) {
        return model;
    }
}
