package lanedu.infra;

import lanedu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class BookReviewHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<BookReview>> {

    @Override
    public EntityModel<BookReview> process(EntityModel<BookReview> model) {
        return model;
    }
}
