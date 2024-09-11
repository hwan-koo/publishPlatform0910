package lanedu.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lanedu.BookpublishApplication;
import lanedu.domain.Published;
import lombok.Data;

@Entity
@Table(name = "BookPublish_table")
@Data
//<<< DDD / Aggregate Root
public class BookPublish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String contents;

    private String imageUrl;

    private Integer price;

    private Long memberId;

    @PostPersist
    public void onPostPersist() {
        Published published = new Published(this);
        published.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static BookPublishRepository repository() {
        BookPublishRepository bookPublishRepository = BookpublishApplication.applicationContext.getBean(
            BookPublishRepository.class
        );
        return bookPublishRepository;
    }

    //<<< Clean Arch / Port Method
    public void deleteItem() {
        //implement business logic here:

        Deleted deleted = new Deleted(this);
        deleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void editItem(EditItemCommand editItemCommand) {
        //implement business logic here:

        Edited edited = new Edited(this);
        edited.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void useAi(String contents) {
        //implement business logic here:

        AiUsed aiUsed = new AiUsed(this);
        aiUsed.setContents(contents);
        aiUsed.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void contentsComplete(ImageGenerated imageGenerated) {
        //implement business logic here:

        /** Example 1:  new item 
        BookPublish bookPublish = new BookPublish();
        repository().save(bookPublish);

        */

        /** Example 2:  finding and process
        
        repository().findById(imageGenerated.get???()).ifPresent(bookPublish->{
            
            bookPublish // do something
            repository().save(bookPublish);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void storyComplete(StoryGenerated storyGenerated) {
        //implement business logic here:

        /** Example 1:  new item 
        BookPublish bookPublish = new BookPublish();
        repository().save(bookPublish);

        */

        /** Example 2:  finding and process
        
        repository().findById(storyGenerated.get???()).ifPresent(bookPublish->{
            
            bookPublish // do something
            repository().save(bookPublish);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
