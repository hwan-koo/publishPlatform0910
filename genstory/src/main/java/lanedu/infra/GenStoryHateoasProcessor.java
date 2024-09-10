package lanedu.infra;

import lanedu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class GenStoryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<GenStory>> {

    @Override
    public EntityModel<GenStory> process(EntityModel<GenStory> model) {
        return model;
    }
}
