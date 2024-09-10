package lanedu.infra;

import lanedu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class BookPurchaseHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<BookPurchase>> {

    @Override
    public EntityModel<BookPurchase> process(EntityModel<BookPurchase> model) {
        return model;
    }
}
