package lanedu.domain;

import lanedu.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "bookPublishes",
    path = "bookPublishes"
)
public interface BookPublishRepository
    extends PagingAndSortingRepository<BookPublish, Long> {}
