package lanedu.domain;

import lanedu.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "paymenthistories",
    path = "paymenthistories"
)
public interface PaymenthistoryRepository
    extends PagingAndSortingRepository<Paymenthistory, Long> {}
