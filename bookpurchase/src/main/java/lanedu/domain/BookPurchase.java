package lanedu.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lanedu.BookpurchaseApplication;
import lanedu.domain.PurchaseRefunded;
import lanedu.domain.PurchaseRequested;
import lombok.Data;

@Entity
@Table(name = "BookPurchase_table")
@Data
//<<< DDD / Aggregate Root
public class BookPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer price;

    private String status;

    private Long bookId;

    private String memberId;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        lanedu.external.Paymenthistory paymenthistory = new lanedu.external.Paymenthistory();
        // mappings goes here
        BookpurchaseApplication.applicationContext
            .getBean(lanedu.external.PaymenthistoryService.class)
            .pay(paymenthistory);

        PurchaseRequested purchaseRequested = new PurchaseRequested(this);
        purchaseRequested.publishAfterCommit();

        PurchaseRefunded purchaseRefunded = new PurchaseRefunded(this);
        purchaseRefunded.publishAfterCommit();
    }

    public static BookPurchaseRepository repository() {
        BookPurchaseRepository bookPurchaseRepository = BookpurchaseApplication.applicationContext.getBean(
            BookPurchaseRepository.class
        );
        return bookPurchaseRepository;
    }
}
//>>> DDD / Aggregate Root
