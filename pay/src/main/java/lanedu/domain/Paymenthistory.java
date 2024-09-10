package lanedu.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lanedu.PayApplication;
import lanedu.domain.PayApproved;
import lombok.Data;

@Entity
@Table(name = "Paymenthistory_table")
@Data
//<<< DDD / Aggregate Root
public class Paymenthistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long purchaseId;

    @PostPersist
    public void onPostPersist() {
        PayApproved payApproved = new PayApproved(this);
        payApproved.publishAfterCommit();
    }

    public static PaymenthistoryRepository repository() {
        PaymenthistoryRepository paymenthistoryRepository = PayApplication.applicationContext.getBean(
            PaymenthistoryRepository.class
        );
        return paymenthistoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void payCancelled(PurchaseRefunded purchaseRefunded) {
        //implement business logic here:

        /** Example 1:  new item 
        Paymenthistory paymenthistory = new Paymenthistory();
        repository().save(paymenthistory);

        */

        /** Example 2:  finding and process
        
        repository().findById(purchaseRefunded.get???()).ifPresent(paymenthistory->{
            
            paymenthistory // do something
            repository().save(paymenthistory);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
