package lanedu.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import lanedu.config.kafka.KafkaProcessor;
import lanedu.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PaymenthistoryRepository paymenthistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PurchaseRefunded'"
    )
    public void wheneverPurchaseRefunded_PayCancelled(
        @Payload PurchaseRefunded purchaseRefunded
    ) {
        PurchaseRefunded event = purchaseRefunded;
        System.out.println(
            "\n\n##### listener PayCancelled : " + purchaseRefunded + "\n\n"
        );

        // Sample Logic //
        Paymenthistory.payCancelled(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
