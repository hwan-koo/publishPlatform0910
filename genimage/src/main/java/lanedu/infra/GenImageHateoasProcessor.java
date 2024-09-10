package lanedu.infra;

import lanedu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class GenImageHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<GenImage>> {

    @Override
    public EntityModel<GenImage> process(EntityModel<GenImage> model) {
        return model;
    }
}
