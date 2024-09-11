package lanedu.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lanedu.GenstoryApplication;
import lanedu.domain.StoryGenerated;
import lombok.Data;

@Entity
@Table(name = "GenStory_table")
@Data
//<<< DDD / Aggregate Root
public class GenStory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String story;
    
    private Long bookId;
    
    @PostPersist
    public void onPostPersist() {
        StoryGenerated storyGenerated = new StoryGenerated(this);
        storyGenerated.publishAfterCommit();
    }
    
    public static GenStoryRepository repository() {
        GenStoryRepository genStoryRepository = GenstoryApplication.applicationContext.getBean(
            GenStoryRepository.class
            );
        return genStoryRepository;
    }
    
    private static final String GPT_API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "your-openai-api-key"; // Replace with your actual API key
    
    public static void generateStory(AiUsed aiUsed) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            // GPT API 요청 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_KEY);
            headers.set("Content-Type", "application/json");

            // 요청 본문 구성
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode requestBody = objectMapper.createObjectNode()
                    .put("model", "gpt-4") // Replace with the model you're using
                    .put("prompt", "너는 아이들을 위한 동화작가로서, 다음의 이야기를 통해 7세 어린이가 이해하기 쉬운 재미있는 한국어 동화 이야기를 만들어줘." + aiUsed.getContents())
                    .put("max_tokens", 150) // Limit the length of the story
                    .put("temperature", 0.7);

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

            // GPT API 호출
            ResponseEntity<String> response = restTemplate.exchange(GPT_API_URL, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // 응답 처리
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());
                String story = jsonResponse.get("choices").get(0).get("text").asText();

                // 생성된 이야기 저장
                GenStory genStory = new GenStory();
                genStory.setStory(story);
                repository().save(genStory);
            } else {
                // 오류 처리
                throw new RuntimeException("Failed to generate story: " + response.getStatusCode());
            }
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            throw new RuntimeException("An error occurred while generating the story.", e);
        }
    }
        
    }
    //>>> DDD / Aggregate Root
    