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
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/delete")
                .withRel("delete")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//edit")
                .withRel("/edit")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//useai")
                .withRel("/useai")
        );

        return model;
    }
}
