package lanedu.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pay", url = "${api.url.pay}")
public interface PaymenthistoryService {
    @RequestMapping(method = RequestMethod.POST, path = "/paymenthistories")
    public void pay(@RequestBody Paymenthistory paymenthistory);
}
