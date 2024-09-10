package lanedu.infra;

import lanedu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class BookPublishHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<BookPublish>> {

    @Override
    public EntityModel<BookPublish> process(EntityModel<BookPublish> model) {
        return model;
    }
}
