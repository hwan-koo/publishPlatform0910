package lanedu.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lanedu.ReviewApplication;
import lanedu.domain.ReviewRegistered;
import lombok.Data;

@Entity
@Table(name = "BookReview_table")
@Data
//<<< DDD / Aggregate Root
public class BookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private Long memberId;

    private Double rating;

    private Long bookId;

    @PostPersist
    public void onPostPersist() {
        ReviewRegistered reviewRegistered = new ReviewRegistered(this);
        reviewRegistered.publishAfterCommit();
    }

    public static BookReviewRepository repository() {
        BookReviewRepository bookReviewRepository = ReviewApplication.applicationContext.getBean(
            BookReviewRepository.class
        );
        return bookReviewRepository;
    }
}
//>>> DDD / Aggregate Root
