package lanedu.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import lanedu.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/bookPublishes")
@Transactional
public class BookPublishController {

    @Autowired
    BookPublishRepository bookPublishRepository;

    @RequestMapping(
        value = "/bookPublishes/{id}/delete",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public BookPublish delete(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /bookPublish/delete  called #####");
        Optional<BookPublish> optionalBookPublish = bookPublishRepository.findById(
            id
        );

        optionalBookPublish.orElseThrow(() -> new Exception("No Entity Found"));
        BookPublish bookPublish = optionalBookPublish.get();
        bookPublish.delete();

        bookPublishRepository.delete(bookPublish);
        return bookPublish;
    }

    @RequestMapping(
        value = "/bookPublishes/{id}//edit",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public BookPublish edit(
        @PathVariable(value = "id") Long id,
        @RequestBody EditCommand editCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /bookPublish/edit  called #####");
        Optional<BookPublish> optionalBookPublish = bookPublishRepository.findById(
            id
        );

        optionalBookPublish.orElseThrow(() -> new Exception("No Entity Found"));
        BookPublish bookPublish = optionalBookPublish.get();
        bookPublish.edit(editCommand);

        bookPublishRepository.save(bookPublish);
        return bookPublish;
    }

    @RequestMapping(
        value = "/bookPublishes/{id}//useai",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public BookPublish useAi(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /bookPublish/useAi  called #####");
        Optional<BookPublish> optionalBookPublish = bookPublishRepository.findById(
            id
        );

        optionalBookPublish.orElseThrow(() -> new Exception("No Entity Found"));
        BookPublish bookPublish = optionalBookPublish.get();
        bookPublish.useAi();

        bookPublishRepository.save(bookPublish);
        return bookPublish;
    }
}
//>>> Clean Arch / Inbound Adaptor
