package lanedu.domain;

import lanedu.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "bookReviews",
    path = "bookReviews"
)
public interface BookReviewRepository
    extends PagingAndSortingRepository<BookReview, Long> {}
