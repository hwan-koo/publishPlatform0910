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
    GenStoryRepository genStoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AiUsed'"
    )
    public void wheneverAiUsed_GenerateStory(@Payload AiUsed aiUsed) {
        AiUsed event = aiUsed;
        System.out.println(
            "\n\n##### listener GenerateStory : " + aiUsed + "\n\n"
        );

        // Sample Logic //
        GenStory.generateStory(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
