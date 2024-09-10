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
// @RequestMapping(value="/bookPurchases")
@Transactional
public class BookPurchaseController {

    @Autowired
    BookPurchaseRepository bookPurchaseRepository;
}
//>>> Clean Arch / Inbound Adaptor
